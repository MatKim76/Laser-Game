package code.jeu.reseau;

import java.net.*;

import code.Controleur;

import java.io.*;

public class Client extends Thread
{
	private static int portNumber = 8686;
	private String serveur = "di-715-08";

	private Controleur ctrl;

	public Client(String serveur, Controleur ctrl)
	{
		this.serveur = serveur;
		this.ctrl = ctrl;
	}
	
	public void run()
	{
		try
		{
			// Initialisation de la connexion avec le serveur
			Socket toServer = new Socket(this.serveur, portNumber);
			System.out.println("Connecté à " + this.serveur);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream()));
			PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
			
			// Attente du message pour démarrer le jeu
			String messageFromServer = in.readLine();
			if (messageFromServer.equals("START_GAME"))
			{
				this.ctrl.lancerJeu();
			}
			
			in.close();
			out.close();
			toServer.close();
			
		}catch( IOException e ){ System.out.println("erreur de connection Client " + e) ;}
	}
	
}
