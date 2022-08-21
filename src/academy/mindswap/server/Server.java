package academy.mindswap.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;
    private static final int PORT = 1234;
    private List<ClientHandler> clientHandlerList;

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer(PORT);
        server.acceptClient();
    }

    private void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientHandlerList = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("BlackJack is open and accepting players");
    }

    private void acceptClient() {
        Socket socket;
        try {
            socket = serverSocket.accept();///
            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandlerList.add(clientHandler);
            new Thread(clientHandler).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            acceptClient();
        }
    }

    private class ClientHandler implements Runnable {
        @Override
        public void run() {

        }
    }
}