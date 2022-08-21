package academy.mindswap.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientPLayer {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private final String HOST = "localhost";
    private final int PORT = 1234;

    public static void main(String[] args) {
        ClientPLayer client = new ClientPLayer();
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
}