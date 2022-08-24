package academy.mindswap.server;

import academy.mindswap.game.Table;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class Server which is the connection with the clients
 */

public class Server {

    public static final int NUMBER_OF_PLAYERS_PER_GAME = 2;
    private static final int PORT = 1012;

    private ServerSocket serverSocket;
    private List<Table> games;
    private ExecutorService executorService;

    public static void main(String[] args) {

        Server server = new Server();
        server.startServer();
        server.acceptClient();
    }

    /**
     * Starts server with new socket, new pool and new list of games
     */
    private void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            executorService = Executors.newCachedThreadPool();
            games = new ArrayList<>();


        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(Messages.OPEN_SERVER);
    }

    /**
     * Method to find a game available to be played and accepts a connection from a client to start a game in a threadpool
     * Its is called recursively
     */
    private void acceptClient() {
        try {
            Table table = games.stream()
                    .filter(t -> !t.canStart())
                    .findFirst().orElse(new Table());

            if (!games.contains(table)) {
                games.add(table);
            }
            Socket socket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(socket);
            table.addClient(clientHandler);

            if (table.canStart()) {
                executorService.submit(table);
            }

            acceptClient();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
