package code.jeu.reseau;

import java.net.*;
import java.io.*;

public class Client
{
	
	private static int portNumber = 6000;
	private static String serveur = "di-724-19";
	
	public Client()
	{
		try{
		
		//System.out.println("Se connecter sur le serveur : ");
		//String serveur = Clavier.lireString();
		
		System.out.println("connexion au serveur...");
		
		Socket toServer = new Socket(serveur, portNumber);
		System.out.println("connecté...");
		
		BufferedReader in = new BufferedReader( new InputStreamReader(toServer.getInputStream()));
		
		System.out.println( in.readLine() );
		
		PrintWriter out;
		String message = "";
		do
		{
			//out = new PrintWriter(toServer.getOutputStream(), true);
			//message = Clavier.lireString();
			//out.println( message );
			
			//System.out.println( in.readLine() );
		}while( message != "fin" );
		
		
		in.close();
		toServer.close();
		
		}catch( IOException e ){ System.out.println("erreur de connection" + e); }
	}
	
}
