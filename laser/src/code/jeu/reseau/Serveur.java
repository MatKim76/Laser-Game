package code.jeu.reseau;

import java.net.*;
import java.io.*;

import java.util.ArrayList;

import code.Controleur;

public class Serveur implements Runnable
{
	private static Serveur serv;
	private Controleur ctrl;
	
	private static int portNumber = 6000;
	private static ArrayList<PrintWriter> array;

	private Serveur(Controleur ctrl)
	{
		this.ctrl = ctrl;

		System.out.println(this.ctrl == null);
		
		array = new ArrayList<PrintWriter>();
		
		Thread t = new Thread(this);
		t.start();
	}

	public static Serveur recupServeur(Controleur ctrl)
	{
		if(Serveur.serv != null)
			return Serveur.serv;

		Serveur.serv = new Serveur(ctrl);
		return Serveur.serv;
	}

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
				
				//PrintWriter out = new PrintWriter(toClient.getOutputStream(), true);
				//BufferedReader in = new BufferedReader(new InputStreamReader(toClient.getInputStream()));
				
				GerantDeClient client = new GerantDeClient(toClient, this.ctrl);
				array.add( client.getPrint() );
				
				Thread t = new Thread(client);
				t.start();
			}
		
		}catch( IOException e ){ System.out.println("erreur de connection"); }

	}
	
}