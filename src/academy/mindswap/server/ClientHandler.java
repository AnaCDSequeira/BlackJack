package academy.mindswap.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private final Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;

	private String username;
	private int budget;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		startIOCommunication();
	}

	@Override
	public void run() {
		try {
			welcomeClient();
			readUsername();
			readBudget();
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
	}

	public void readUsername() {
		username = sendMessageAndReadAnswer(Messages.CHOOSE_USERNAME);
		while (username == null || username.isBlank()) {
			username = sendMessageAndReadAnswer(Messages.CHOOSE_USERNAME);
		}
		sendMessageToUser(String.format(Messages.WELCOME_USERNAME, username));
	}

	public void readBudget() {
		String value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
		while (!value.matches("^\\d+$") || Integer.parseInt(value) <= 5) {
			value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
		}
		budget = Integer.parseInt(value);
		sendMessageToUser(String.format(Messages.AMOUNT_STARTED, budget));
	}

	public String getUsername() {
		return username;
	}

	public int getBudget() {
		return budget;
	}

	private void startIOCommunication() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void sendMessageToUser(String message) {
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

	private String sendMessageAndReadAnswer(String message) {
		sendMessageToUser(message);
		return readMessageFromUser();
	}
}
