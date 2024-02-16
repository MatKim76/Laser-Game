package code.jeu.reseau;

import java.net.*;
import java.io.*;

import java.util.ArrayList;
import java.awt.Color;

import code.Controleur;
import code.ihm.FrameJeu;
import code.jeu.objet.Joueur;

public class Serveur implements Runnable
{
	private static Color[] COULEURS = {Color.RED, Color.BLUE, Color.YELLOW, Color.PINK};
	private static int compteur = 0;
	
	private static Serveur serv;
	private Controleur ctrl;
	
	private static int portNumber = 6000;
	private static ArrayList<PrintWriter> array;

	private ArrayList<Joueur> lstJoueur;

	private Serveur(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.lstJoueur = new ArrayList<Joueur>();

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
				
				GerantDeClient client = new GerantDeClient(toClient, this);
				array.add( client.getPrint() );
				
				Thread t = new Thread(client);
				t.start();
			}
		
		}catch( IOException e ){ System.out.println("erreur de connection"); }

	}

	public void lancerJeu()
	{
		Joueur j = new Joueur('A', Serveur.COULEURS[++Serveur.compteur]);
		this.lstJoueur.add(j);
		//this.lstFrame.add(new FrameJeu(this, j));
		new FrameJeu(this, j);
		System.out.println("lancement 2");
	}
	
	public ArrayList<Joueur> getJoueurs()
	{
		return this.lstJoueur;
	}
}
