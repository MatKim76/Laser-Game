package code.jeu.reseau;

import java.net.*;
import java.io.*;

public class Client extends Thread
{
	
	private static int portNumber = 6000;
	private String serveur = "di-715-08";

	public Client(String serveur)
	{
		this.serveur = serveur;
	}
	
	public void run()
	{
		try
		{
			System.out.println("connexion au serveur...");
			
			Socket toServer = new Socket(this.serveur, portNumber);
			System.out.println("connecté...");
			
			BufferedReader in = new BufferedReader( new InputStreamReader(toServer.getInputStream()));
			
			System.out.println( in.readLine() );
			
			/*PrintWriter out;
			String message = "";
			do
			{
				//out = new PrintWriter(toServer.getOutputStream(), true);
				//message = Clavier.lireString();
				//out.println( message );
				
				//System.out.println( in.readLine() );
			}while( message != "fin" ); */
			
			
			in.close();
			toServer.close();
			
		}catch( IOException e ){ System.out.println("erreur de connection Client " + e); }
	}
	
}
