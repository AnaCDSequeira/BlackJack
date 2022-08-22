package academy.mindswap.server;

import academy.mindswap.game.Card;
import academy.mindswap.game.Hand;
import academy.mindswap.game.Person;
import academy.mindswap.game.Table;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private static final int PORT = 1010;
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
            while (clientHandlerList.size() < 2) {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandlerList.add(clientHandler);
                new Thread(clientHandler).start();
            }

            Table table = new Table(clientHandlerList);
            table.startGame();

        } catch (IOException e) {
            e.printStackTrace();
        }
            acceptClient();
    }


    public class ClientHandler extends Person implements Runnable {

        private Socket socket;
        private BufferedWriter writer;
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
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
            String line = "" ;
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

        public void welcomeClient() throws IOException {
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
            bet = Integer.parseInt(sendMessageAndReadAnswer("How much do you want to bet ?"));
            //TODO create enum for chips
            if (budget < bet) {
                sendMessageToUser("There's not enough money to bet. You have: " + budget);
                askForBet();
            }
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
        public void addCard(Card card) {
            hand.addCard(card);
            sendMessageToUser(card.getValue());
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