package code.jeu.reseau;

import java.net.*;
import java.io.*;

import java.util.ArrayList;

import code.Controleur;
import code.jeu.objet.Joueur;
import code.jeu.objet.Map;

public class Serveur implements Runnable
{
	private static Serveur serv;
	
	private static int portNumber = 8686;
	private static ArrayList<PrintWriter> array;

	private Map map;

	public Serveur(String nomServ, Controleur ctrl)
	{
		this.map = new Map(nomServ, 600, 800);//faire que sa sadapte a la frmae
		
		array = new ArrayList<PrintWriter>();
		
		Thread t = new Thread(this);
		t.start();
	}

	//TODO à modifier pour créer plusieur serv
	/*public static Serveur recupServeur(Controleur ctrl)
	{
		if(Serveur.serv == null)
		{
			System.out.println("Il n'y a pas de serveur... création du serveur");
			Serveur.serv = new Serveur(ctrl);
			return Serveur.serv;
		}

		System.out.println("Il y a deja un serveur... recup du serveur");
		return Serveur.serv;
	}*/

	@Override
	public void run() 
	{
		try{
		
			ServerSocket ss = new ServerSocket(portNumber);
			
			while(true)
			{
				System.out.println("attente d'un client...");
				
				Socket toClient = ss.accept();
				System.out.println("client arrivé");


				// Envoyer la référence de l'instance de la carte au client
                ObjectOutputStream objectOut = new ObjectOutputStream(toClient.getOutputStream());
                objectOut.writeObject(map);
                objectOut.flush();

				/*objectOut.writeObject(map.getJoueurs());
				objectOut.writeObject(map.getBonus());
				objectOut.flush();*/
				System.out.println(map);
				

				GerantDeClient client = new GerantDeClient(toClient);
				array.add( client.getPrint() );
				
				Thread t = new Thread(client);
				t.start();
			}
		
		}catch( IOException e ){ System.out.println("erreur de connection Serveur " + e); }

	}

	public Map getMap()
	{
		return this.map;
	}
	
	
}
