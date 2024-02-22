package test;

import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Serveur en attente de connexion...");
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Nouveau client connecté : " + clientSocket);
            
            Thread clientThread = new Thread(new ClientHandler(clientSocket));
            clientThread.start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            // Envoi d'un message de bienvenue au client
            out.println("Bienvenue sur le serveur !");
            
            // Lancement d'un nouveau frame pour le client
            FrameServeur frame = new FrameServeur();
            frame.setVisible(true);
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Message reçu du client : " + inputLine);
                out.println("Message reçu : " + inputLine);
            }
            
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


