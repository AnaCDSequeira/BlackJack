package academy.mindswap.server;

import academy.mindswap.game.Player;

import java.io.*;
import java.net.Socket;

public class ClientHandler {

	private final Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;

	private Player player;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		startIOCommunication();
	}

	public void run() {
		try {
			welcomeClient();
			readUsername();
			readBudget();
			askForBet();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
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
		while (!value.matches("^\\d+$") || Integer.parseInt(value) <= 5) {
			value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
		}
		int budget = Integer.parseInt(value);
		sendMessageToUser(String.format(Messages.AMOUNT_STARTED, budget));
		player.setBudget(budget);
	}

	private void startIOCommunication() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void sendMessageToUser(String message) {
		try {
			writer.write(message);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String readMessageFromUser() {
		String line = "";
		try {
			line = reader.readLine();
			if (line == null) {
				readMessageFromUser();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	public String sendMessageAndReadAnswer(String message) {
		sendMessageToUser(message);
		return readMessageFromUser();
	}

	public Player getPlayer() {
		return player;
	}

	public void askForBet() {
		int bet = Integer.parseInt(sendMessageAndReadAnswer("How much do you want to bet?"));
		//TODO create enum for chips
		while (player.getBudget() < bet) {
			sendMessageToUser("There's not enough money to bet. You have: " + player.getBudget());
			askForBet();
		}
		player.setBet(bet);
	}

	public boolean wantMoreCards() {
		String response = sendMessageAndReadAnswer("Do you want more cards? Say \"hit\"");
		player.setWantCard(response.equals("hit"));
		return player.getWantCard();
	}

	public void showCards() {
		sendMessageToUser(player.getHand().toString());
	}
}
