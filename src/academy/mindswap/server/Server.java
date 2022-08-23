package academy.mindswap.server;

import academy.mindswap.game.Table;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final int NUMBER_OF_PLAYERS_PER_GAME = 1;
	private static final int PORT = 1010;
	private ServerSocket serverSocket;
	private List<ClientHandler> clients;

	public static void main(String[] args) {
		Server server = new Server();
		server.startServer();
		server.acceptClient();
	}

	private void startServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			clients = new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println(Messages.OPEN_SERVER);
	}

	private void acceptClient() {
		try {
			Socket socket = serverSocket.accept();
			ClientHandler clientHandler = new ClientHandler(socket);
			clients.add(clientHandler);

			clientHandler.run();

			createGame();

			acceptClient();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createGame() {
		if (clients.size() < NUMBER_OF_PLAYERS_PER_GAME) {
			return;
		}

		Table table = new Table(clients);
		new Thread(table).start();
	}
}
