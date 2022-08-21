package academy.mindswap.server;


import java.io.*;
import java.net.Socket;

public class ClientPlayer {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private final String HOST = "localhost";
    private final int PORT = 1234;

    public static void main(String[] args) {
        ClientPlayer client = new ClientPlayer();
        client.startConsoleReader();
        client.handleServer();
    }

    private void startConsoleReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    private void handleServer() {
        connectToServer();
        listenToServer();
        communicateWithServer();
        close();
    }
    private void connectToServer() {
        try {
            this.socket = new Socket(HOST, PORT);
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("The Casino is closed now");
            connectToServer();
        }
    }
    private void listenToServer() {
        try {
            new Thread(new ServerHandler(socket.getInputStream())).start();
        } catch (IOException e) {
            handleServer();
        }
    }
    private void communicateWithServer() {
        try {
            sendMessageToServer();
            communicateWithServer();
        } catch (IOException e) {
            System.out.println("The Casino is closed now");
            handleServer();
        }
    }
    private void sendMessageToServer() throws IOException {
        String message = readMessageFromServer();
        writer.println(message);
    }
    private String readMessageFromServer() {
        String message = null;
        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return message;
    }
    private void close() {
        try {
            socket.close();
            System.out.println("The Casino is closed now");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    private class ServerHandler implements Runnable {

        BufferedReader serverReader;

        public ServerHandler(InputStream inputStream) {
            this.serverReader = new BufferedReader(new InputStreamReader(inputStream));
        }

        private void readMessage() throws IOException {
            String readMessageFromServer = serverReader.readLine();
            System.out.println(readMessageFromServer);
            readMessage();
        }

        @Override
        public void run() {
            try {
                readMessage();
            } catch (IOException e) {
            }
        }
    }
}