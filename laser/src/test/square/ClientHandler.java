package test.square;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;
    private PrintWriter out;
    private String userName;
    private int x;
    private int y;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                out.println("Enter your name:");
                userName = in.readLine();
                if (server.addUserName(userName)) {
                    out.println("Welcome, " + userName + "!");
                    server.broadcastMessage(userName + " has joined the server.", this);
                    break;
                } else {
                    out.println("Name already taken. Please choose another one.");
                }
            }

            String message;
            while ((message = in.readLine()) != null) {
                server.broadcastMessage(userName + ": " + message, this);
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            server.removeUserName(userName);
            server.removeClientHandler(this);
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}