package academy.mindswap.server;

import academy.mindswap.game.Person;
import academy.mindswap.game.Player;

import java.io.*;
import java.net.Socket;

/**
 * Class that represents a connection client-server in a thread
 */
public class ClientHandler implements Runnable {

	private final Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private Player player;
	private boolean readyToStart;

	private final int MINIMUM_VALUE_TO_PLAY = 5;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		startIOCommunication();
	}

	@Override
	public void run() {
		init();
	}

	/**
	 * Method welcome players, to ask name, budget and bet
	 */
	public void init() {
		try {
			welcomeClient();
			readUsername();
			readBudget();
			askForBet();
			readyToStart = true;
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public boolean isReadyToStart() {
		return readyToStart;
	}

	/**
	 * MEthod to send welcome message to client when connected
	 * @throws IOException Writer exception
	 */
	public void welcomeClient() throws IOException {
		System.out.println(Messages.PLAYER_CONNECTED);
		sendMessageToUser(Messages.WELCOME_PLAYER);
		player = new Player();
	}

	/**
	 * Method to ask,  read  and set username, then welcome client
	 */
	public void readUsername() {
		String name = sendMessageAndReadAnswer(Messages.CHOOSE_USERNAME);
		while (name == null || name.isBlank()) {
			name = sendMessageAndReadAnswer(Messages.CHOOSE_USERNAME);
		}
		sendMessageToUser(String.format(Messages.WELCOME_USERNAME, name));
		player.setName(name);
	}

	/**
	 * Method to ask for budget available to play. Accepts only numbers bigger than 5.
	 * Return message to client and sets budget property.
	 */
	public void readBudget() {
		String value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
		while (!value.matches("^\\d+$") || Integer.parseInt(value) < MINIMUM_VALUE_TO_PLAY) {
			value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
		}
		int budget = Integer.parseInt(value);
		sendMessageToUser(String.format(Messages.AMOUNT_STARTED, budget));
		player.setBudget(budget);
	}

	/**
	 * Method to send messages to player
	 * @param message
	 */
	public void sendMessageToUser(String message) {
		try {
			writer.println(message);
		} catch (Exception e) {
			System.out.println(Messages.SOCKET_CLOSE);
		}
	}

	/**
	 * MEthod to send a message to player and return his answer
	 * @param message to be sent
	 * @return the response from the player
	 */
	public String sendMessageAndReadAnswer(String message) {
		sendMessageToUser(message);
		return readMessageFromUser();
	}

	public void quit() {
		try {
			sendMessageToUser(Messages.QUIT);
			socket.close();
		} catch (IOException e) {
			System.out.println(Messages.SOCKET_CANT_CLOSE);
		}
	}

	public Player getPlayer() {
		return player;
	}

	/**
	 * Method to ask client how much wants to bet on that round , and sets bet
	 */
	public void askForBet() {
		sendMessageToUser(Messages.CHIPS);
		int bet = Integer.parseInt(sendMessageAndReadAnswer(Messages.BET_AMOUNT));
		while (player.getBudget() < bet) {
			sendMessageToUser(String.format(Messages.NOT_ENOUGH_BUDGET, player.getBudget()));
			bet = Integer.parseInt(sendMessageAndReadAnswer(Messages.BET_AMOUNT));
		}
		player.setBet(bet);
	}

	/**
	 * Methos to send message to player with their cards drawn
	 * @param person player to receive message
	 */
	public void showCards(Person person) {
		sendMessageToUser(String.format(Messages.SHOW_CARDS, person.getName(), person.getHand().toString()));
		sendMessageToUser(String.format(Messages.SHOW_SCORE, person.getName(), person.getScore()));
	}

	/**
	 * Method to ask client what wants to do after receiving cards.
	 * Uses a switch to proceed conforming to the case chosen
	 */
	public void askForNextMove() {
		sendMessageToUser(Messages.PLAY_OPTIONS);
		String response = readMessageFromUser().toLowerCase();
		switch (response) {
			case "hit" -> player.setWantMoreCards(true);
			case "stand" -> player.setWantMoreCards(false);
			case "new game" -> init();
			case "quit" -> quit();
			default -> {
				sendMessageToUser(Messages.INVALID_OPTION);
				askForNextMove();
			}
		}
	}

	/**
	 * starts buffers
	 */

	private void startIOCommunication() {
		try {
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to receive message from player, closes the socket if null received
	 * @return the message read
	 */

	private String readMessageFromUser() {
		String message = null;
		try {
			message = reader.readLine();
		} catch (IOException | NullPointerException e) {
			quit();
		} finally {
			if (message == null) {
				quit();
			}
		}
		return message;
	}
}
