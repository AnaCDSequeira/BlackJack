package academy.mindswap.server;

import academy.mindswap.game.Hand;
import academy.mindswap.game.Person;
import academy.mindswap.game.Table;

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
    private ArrayList<ClientHandler> clientHandlerList;

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
        System.out.println(Messages.OPEN_SERVER);
    }

    private void acceptClient() {
        Socket socket;
        try {
            socket = serverSocket.accept();///

            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandlerList.add(clientHandler);
            new Thread(clientHandler).start();
            Table table = new Table(clientHandlerList);
            table.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            acceptClient();
        }
    }


    public class ClientHandler extends Person implements Runnable {

        private Socket socket;
        private PrintWriter writer;
        private BufferedReader reader;

        private String username;
        private int score;
        private int budget;

        private int bet;
        private Hand hand;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }


        private void startIOCommunication() throws IOException {
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        private void sendMessageToUser(String message) {
            writer.println(message);
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

        private void welcomeClient() throws IOException {
            System.out.println(Messages.PLAYER_CONNECTED);
            sendMessageToUser(Messages.WELCOME_PLAYER);
        }


        public void getUsername() {
            username = sendMessageAndReadAnswer(Messages.CHOOSE_USERNAME);
            while (username.isBlank()) {
                username = sendMessageAndReadAnswer(Messages.CHOOSE_USERNAME);
            }
            sendMessageToUser(String.format(Messages.WELCOME_USERNAME, username));
        }

        public void getBudget() {
            String value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
            while (!value.matches("^[\\d]+$") || Integer.parseInt(value) <= 5) {
                value = sendMessageAndReadAnswer(Messages.SPENT_AMOUNT);
            }
            budget = Integer.parseInt(value);
            sendMessageToUser(String.format(Messages.AMOUNT_STARTED, budget));
        }

        private String sendMessageAndReadAnswer(String message) {
            sendMessageToUser(message);
            return readMessageFromUser();
        }
        public boolean wantMoreCards() {
            return true; // TODO(ask player)
        }

        public boolean canPlay() {
            // TODO: Verify minimum bet amount (table should define it)
            return hand.canPlay();
        }

        public void askForBet() {
            // TODO: Ask for bet
            bet = 100;
            budget -= bet;
        }

        public boolean hasBlackJack() {
            return hand.hasBlackJack();
        }

        public int getScore() {
            return hand.getScore();
        }

        public void getPayment(double betMultiplier) {
            budget += bet * betMultiplier;
            resetBet();
        }

        public int getBet() {
            return bet;
        }

        private void resetBet() {
            bet = 0;
        }

        @Override
        public void run() {
            try {
                startIOCommunication();
                welcomeClient();
                getUsername();
                getBudget();
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}