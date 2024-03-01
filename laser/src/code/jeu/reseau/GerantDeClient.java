package code.jeu.reseau;

import java.net.*;

import code.jeu.objet.Joueur;
import code.jeu.objet.Map;

import java.io.*;

public class GerantDeClient implements Runnable 
{
	private Socket s;

	private BufferedReader in;
	private PrintWriter out;

	private Map map;
	
	public GerantDeClient(Socket s, Map map)
	{
		this.s = s;
		this.map = map;
		
		try
		{
			//this.in = new BufferedReader( new InputStreamReader(this.s.getInputStream()));
			this.out = new PrintWriter(this.s.getOutputStream(), true);
			//this.objectIn = new ObjectInputStream(this.s.getInputStream());
		}
		catch( IOException e ){ System.out.println("erreur de connection"); }

		this.out.println("START_GAME");
		this.out.flush();
	}
	
	public void run()
	{
		try 
		{
			ObjectOutputStream objectOut = new ObjectOutputStream(this.s.getOutputStream());
			ObjectInputStream objectIn = new ObjectInputStream(this.s.getInputStream());

			Thread.sleep(50);

            while (true)
			{
                System.out.println("envoie map");
				objectOut.writeObject(this.map);
				objectOut.flush();

				Thread.sleep(100);
				
				System.out.println("attente joueur");
				Joueur joueur = (Joueur) objectIn.readObject();
                this.map.modifJoueur(joueur);

				Thread.sleep(100);
            }
			
        } catch (Exception e) {
            System.out.println("Erreur lors de la réception des données du client : " + e);
        }

	}
	
	public PrintWriter getPrint()
	{
		return this.out;
	}

	public Socket getSocket()
	{
		return this.s;
	}
}