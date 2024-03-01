package test.square;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        ClientHandler clientHandler = new ClientHandler(socket, new Server());
        new Thread(clientHandler).start();

        Set<ClientHandler> clientHandlers = new HashSet<>();
        clientHandlers.add(clientHandler);

        MovableSquare movableSquare = new MovableSquare(clientHandlers);
        //movableSquare.setClientHandler(clientHandler);
        movableSquare.setVisible(true);
    }
}