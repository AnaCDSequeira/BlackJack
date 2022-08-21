package academy.mindswap.server;

import java.net.ServerSocket;

public class Server {

    private ServerSocket serverSocket;
    private static final int PORT = 1234;
    private List<ClientHandler> clientHandlerList;
}