package academy.mindswap.server;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientPLayer {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private final String HOST = "localhost";
    private final int PORT = 1234;

    private void startConsoleReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    private void handleServer() {
        connectToServer();
        listenToServer();
        communicateWithServer();
        close();
    }
}