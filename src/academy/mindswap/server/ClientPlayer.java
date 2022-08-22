package academy.mindswap.server;


import java.io.*;
import java.net.Socket;

public class ClientPlayer {

    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private BufferedReader serverReader;
    private final String HOST = "localhost";
    private final int PORT = 1010;


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
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.serverReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println();
            connectToServer();
        }
    }
    private void listenToServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                listenServer();
            }
        });
    }
    private void listenServer(){
        String message = null;
        try {
            message = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(message);
        listenToServer();
    }
    private void communicateWithServer() {
        try {
            sendMessageToServer();
            communicateWithServer();
        } catch (IOException e) {
            System.out.println(Messages.ERROR_MESSAGE);
            handleServer();
        }
    }
    private void sendMessageToServer() throws IOException {
        String message = readMessageFromServer();
        writer.write(message);
        writer.newLine();
        writer.flush();
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
            System.out.println(Messages.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    private class ServerHandler implements Runnable {

        private BufferedReader serverReader;

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