package test;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        String serverHostname = "localhost";
        int serverPort = 12345;
        
        try (
            Socket clientSocket = new Socket(serverHostname, serverPort);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Message du serveur : " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Hôte inconnu : " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erreur d'E/S pour la connexion à : " + serverHostname);
            System.exit(1);
        }
    }
}
