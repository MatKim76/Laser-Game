package code.jeu.reseau;

import java.net.*;

import java.io.*;

public class GerantDeClient implements Runnable 
{

	private Socket s;
	private BufferedReader in;
	private PrintWriter out;
	
	public GerantDeClient(Socket s)
	{
		this.s = s;
		
		try
		{
			this.in = new BufferedReader( new InputStreamReader(this.s.getInputStream()));
			this.out = new PrintWriter(this.s.getOutputStream(), true);

		}catch( IOException e ){ System.out.println("erreur de connection"); }
		
	}
	
	public void run()
	{ 
		//envoie au client le message de lancement
		this.out.println("START_GAME");
	}
	
	public PrintWriter getPrint()
	{
		return this.out;
	}
}