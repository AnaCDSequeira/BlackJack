package academy.mindswap.server;

import academy.mindswap.game.Person;
import academy.mindswap.game.Player;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private final Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private Player player;
	private boolean readyToStart;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		startIOCommunication();
	}

	@Override
	public void run() {
		init();
	}

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

	public void welcomeClient() throws IOException {
		System.out.println(Messages.PLAYER_CONNECTED);
		sendMessageToUser(Messages.WELCOME_PLAYER);
		player = new Player();
	}

	public void readUsername() {
		String name = sendMessageAndReadAnswer(Messages.CHOOSE_USERNAME);
		while (name == null || name.isBlank()) {
			name = sendMessageAndReadAnswer(Messages.CHOOSE_USERNAME);
		}
		sendMessageToUser(String.format(Messages.WELCOME_USERNAME, name));
		player.setName(name);
	}

	public void readBudget() {
		String value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
		while (!value.matches("^\\d+$") || Integer.parseInt(value) < 5) {
			value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
		}
		int budget = Integer.parseInt(value);
		sendMessageToUser(String.format(Messages.AMOUNT_STARTED, budget));
		player.setBudget(budget);
	}

	public void sendMessageToUser(String message) {
		try {
			writer.println(message);
		} catch (Exception e) {
			System.out.println("Socket is closed.");
		}
	}

	public String sendMessageAndReadAnswer(String message) {
		sendMessageToUser(message);
		return readMessageFromUser();
	}

	public void quit() {
		try {
			sendMessageToUser(Messages.QUIT);
			socket.close();
		} catch (IOException e) {
			System.out.println("Couldn't closer player socket");
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void askForBet() {
		sendMessageToUser(Messages.CHIPS);
		int bet = Integer.parseInt(sendMessageAndReadAnswer(Messages.BET_AMOUNT));
		while (player.getBudget() < bet) {
			sendMessageToUser(String.format(Messages.NOT_ENOUGH_BUDGET, player.getBudget()));
			bet = Integer.parseInt(sendMessageAndReadAnswer(Messages.BET_AMOUNT));
		}
		player.setBet(bet);
	}

	public void showCards(Person person) {
		sendMessageToUser(String.format(Messages.SHOW_CARDS, person.getName(), person.getHand().toString()));
		sendMessageToUser(String.format(Messages.SHOW_SCORE, person.getName(), person.getScore()));
	}

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

	private void startIOCommunication() {
		try {
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

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
