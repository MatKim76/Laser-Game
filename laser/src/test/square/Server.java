package test.square;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 1234;
    private Set<String> userNames = new HashSet<>();
    private Set<ClientHandler> clientHandlers = new HashSet<>();
    private Set<Square> squares = new HashSet<>();

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    public synchronized boolean addUserName(String userName) {
        if (userNames.contains(userName)) {
            return false;
        }
        userNames.add(userName);
        return true;
    }

    public synchronized void removeUserName(String userName) {
        userNames.remove(userName);
    }

    public synchronized void addClientHandler(ClientHandler clientHandler) {
        clientHandlers.add(clientHandler);
    }

    public synchronized void removeClientHandler(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler != sender) {
                clientHandler.sendMessage(message);
            }
        }
    }

    public synchronized void addSquare(Square square) {
        squares.add(square);
    }

    public synchronized void removeSquare(Square square) {
        squares.remove(square);
    }

    public synchronized Set<Square> getSquares() {
        return squares;
    }
}

class Square {
    private int x, y;
    private int size;
    private Color color;

    public Square(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}