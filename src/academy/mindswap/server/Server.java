package academy.mindswap.server;

import academy.mindswap.game.Player;
import academy.mindswap.game.Table;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

	private static final int PORT = 1010;
	public static final int NUMBER_OF_PLAYERS_PER_GAME = 1;

	private ServerSocket serverSocket;
	private List<Player> players;
	private List<ClientHandler> clients;

	public static void main(String[] args) {
		Server server = new Server();
		server.startServer();
		server.acceptClient();
	}

	private void startServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			players = new ArrayList<>();
			clients = new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println(Messages.OPEN_SERVER);
	}

	private void acceptClient() {
		try {
			while (players.size() < NUMBER_OF_PLAYERS_PER_GAME) {
				Socket socket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler(socket);
				clients.add(clientHandler);
				new Thread(clientHandler).start();
			}

			createGame();

			acceptClient();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createGame() {
		for (ClientHandler client: clients) {
			Player player = new Player(client.getUsername(), client.getBudget());
			players.add(player);
		}
		Table table = new Table(players);
		new Thread(table).start();
	}
}
