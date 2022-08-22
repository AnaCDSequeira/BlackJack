package academy.mindswap.server;

import java.io.*;
import java.net.Socket;

public class Client {

	public static final String HOST = "localhost";
	public static final int PORT = 1010;

	private Socket socket;
	private BufferedReader consoleReader;
	private BufferedWriter serverWriter;
	private BufferedReader serverReader;

	public static void main(String[] args) {
		Client client = new Client();
		client.startConsoleReader();
		client.handleServer();
	}

	private void startConsoleReader() {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
	}

	private void handleServer() {
		connectToServer();
		createServerWriterReader();
		listenToServer();
		communicateWithServer();
		close();
	}

	private void connectToServer() {
		try {
			socket = new Socket(HOST, PORT);
		} catch (IOException exception) {
			System.out.println();
			connectToServer();
		}
	}

	private void createServerWriterReader() {
		try {
			serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			serverWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	private void listenToServer() {
		ServerListener serverListener = new ServerListener();
		new Thread(serverListener).start();
	}

	private void communicateWithServer() {
		try {
			sendMessageToServer();
			communicateWithServer();
		} catch (IOException exception) {
			System.out.println(Messages.ERROR_MESSAGE);
			handleServer();
		}
	}

	private void sendMessageToServer() throws IOException {
		String message = readMessageFromServer();
		serverWriter.write(message);
		serverWriter.newLine();
		serverWriter.flush();
	}

	private String readMessageFromServer() {
		String message = null;
		try {
			message = consoleReader.readLine();
		} catch (IOException exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		return message;
	}

	private void close() {
		try {
			socket.close();
			System.out.println(Messages.ERROR_MESSAGE);
		} catch (IOException exception) {
			exception.printStackTrace();
			System.exit(1);
		}
	}

	private class ServerListener implements Runnable {
		private void listenToServer() {
			try {
				String message = serverReader.readLine();
				System.out.println(message);
				if (message == null) {
					serverWriter.close();
					return;
				}
				listenToServer();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}

		@Override
		public void run() {
			listenToServer();
		}
	}
}
