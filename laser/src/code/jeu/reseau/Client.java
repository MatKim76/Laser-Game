package code.jeu.reseau;

import java.net.*;
import java.util.ArrayList;

import code.Controleur;
import code.jeu.objet.Bonus;
import code.jeu.objet.Joueur;
import code.jeu.objet.Map;

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

			//zone test
			ObjectInputStream objectIn = new ObjectInputStream(toServer.getInputStream());
            Map map = null;
			try 
			{
				map = (Map) objectIn.readObject();
				System.out.println("reussi");
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
            this.ctrl.setMap(map);

			/*ArrayList<Joueur> joueurs = null;
			ArrayList<Bonus> bonus = null;
			try 
			{
				joueurs = (ArrayList<Joueur>) objectIn.readObject();
				bonus = (ArrayList<Bonus>) objectIn.readObject();
				System.out.println("test j " +joueurs.size());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			map.setJoueurs(joueurs);
			map.setBonus(bonus);*/

			
			// Attente du message pour démarrer le jeu
			String messageFromServer = in.readLine();
			if (messageFromServer.equals("START_GAME"))
			{
				this.ctrl.lancerJeu();
			}
			
			objectIn.close();
			in.close();
			out.close();
			toServer.close();
			
		}catch( IOException e ){ System.out.println("erreur de connection Client " + e) ;}
	}
	
}
