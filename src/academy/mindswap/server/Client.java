package academy.mindswap.server;

import java.io.*;
import java.net.Socket;

/**
 * Class Client which is the connection with the server
 */

public class Client {

    public static final String HOST = "localhost";
    public static final int PORT = 1011;

    private Socket socket;
    private BufferedReader consoleReader;
    private PrintWriter serverWriter;
    private BufferedReader serverReader;

    public static void main(String[] args) {
        Client client = new Client();
        client.startConsoleReader();
        client.handleServer();
    }

    /**
     * Reader for console
     */
    private void startConsoleReader() {
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Method that connects to server with sockets, instantiates the buffers and keeps communication open with server
     */
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

    /**
     * Method to instantiate buffers
     */
    private void createServerWriterReader() {
        try {
            serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            serverWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Method to create a new thread to listen the server
     */

    private void listenToServer() {
        ServerListener serverListener = new ServerListener();
        new Thread(serverListener).start();
    }

    /**
     * Method to keep running communication with server
     */
    private void communicateWithServer() {
        try {
            sendMessageToServer();
            communicateWithServer();
        } catch (IOException exception) {
            System.out.println(Messages.ERROR_MESSAGE);
            handleServer();
        }
    }

    /**
     * Method called above
     *
     * @throws IOException shows message and connects again to server
     */
    private void sendMessageToServer() throws IOException {
        String message = readMessageFromServer();
        serverWriter.println(message);
    }

    /**
     * Method to reads messages from server with bufferedReader and returns the message
     *
     * @return String message
     */
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

    /**
     * Method to close socket and shows message with error
     */
    private void close() {
        try {
            socket.close();
            System.out.println(Messages.ERROR_MESSAGE);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Inner class that handles the methods to receive messages from the server on a new Thread
     */
    private class ServerListener implements Runnable {

        /**
         * Method to receive messages from server and runs recursively
         */
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
