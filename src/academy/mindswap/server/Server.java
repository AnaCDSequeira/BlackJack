package academy.mindswap.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

        private Socket socket;
        private PrintWriter writer;
        private BufferedReader reader;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        private void startIOCommunication() throws IOException {
            writer = new PrintWriter(socket.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        private void sendMessageToUser(String message) {
            writer.println(message);
        }

        private void readMessageFromUser() {
            try {
                String line = reader.readLine();
                if (line == null) {
                    socket.close();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void welcomeClient() throws IOException {
            System.out.println("New player arrived, waiting for other players");
            sendMessageToUser("Welcome to BlackJack");
        }

        @Override
        public void run() {

        }
    }
}