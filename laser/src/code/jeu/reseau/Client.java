package code.jeu.reseau;

import java.net.*;

import code.Controleur;
import code.jeu.objet.Joueur;
import code.jeu.objet.Map;

import java.io.*;

public class Client implements Runnable
{
	private static int portNumber = 8686;
	private String serveur = "di-715-08";

	private Controleur ctrl;
	private Map map;

	private Socket toServer;

	public Client(String serveur, Controleur ctrl)
	{
		this.serveur = serveur;
		this.ctrl = ctrl;

		initialisationClient();
	}

	private void initialisationClient()
	{
		try
		{
			// Initialisation de la connexion avec le serveur
			this.toServer = new Socket(this.serveur, portNumber);
			System.out.println("Connecté à " + this.serveur);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(this.toServer.getInputStream()));
			//PrintWriter out = new PrintWriter(this.toServer.getOutputStream(), true);
			
			// Attente du message pour démarrer le jeu
			String messageFromServer = in.readLine();
			if (messageFromServer.equals("START_GAME"))
			{
				Thread t = new Thread(this);
				t.start();

				this.ctrl.lancerJeu();
			}

			//in.close();
			//out.close();
			//toServer.close();
			
		}catch( IOException e ){ System.out.println("erreur de connection Client " + e) ;}
	}
	
	public void run()
	{
		try
		{
			ObjectOutputStream objectOut = new ObjectOutputStream(this.toServer.getOutputStream());
			ObjectInputStream objectIn = new ObjectInputStream(this.toServer.getInputStream());

			while (true) 
			{
				System.out.println("attente map");
				Map mapServ = (Map) objectIn.readObject();
                this.ctrl.setMap(mapServ);

				Thread.sleep(100);

				System.out.println("envoie joueur");
				Joueur j = this.ctrl.getJoueur();
				if(j != null)
				{
					objectOut.writeObject(j );
					objectOut.flush();
				}

				Thread.sleep(100);
			}
		} 
		catch (Exception e) { System.out.println("probleme envoie du joueur " + e); }
	}
}
