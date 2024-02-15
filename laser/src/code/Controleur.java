package code;
import code.ihm.FrameJeu;
import code.jeu.objet.Joueur;
import code.jeu.reseau.*;

import java.awt.Color;
import java.util.ArrayList;

public class Controleur 
{
	private static Color[] COULEURS = {Color.RED, Color.BLUE, Color.YELLOW, Color.PINK};
	
	private ArrayList<Joueur> lstJoueur;
	private ArrayList<FrameJeu> lstFrame;

	private static int compteur = 0;
	
	public Controleur()
	{
		//récupérer le serveur et le ctrl qui joint tout
		Serveur s = Serveur.recupServeur(this);

		this.lstFrame = new ArrayList<FrameJeu>();
		this.lstJoueur = new ArrayList<Joueur>();

		new Client();
	}

	public ArrayList<Joueur> getJoueurs()
	{
		return this.lstJoueur;
	}

	public void lancerJeu()
	{
		Joueur j = new Joueur('A', Controleur.COULEURS[++Controleur.compteur]);
		this.lstJoueur.add(j);
		this.lstFrame.add(new FrameJeu(this, j));
		System.out.println("lancement 2");
	}

	public static void main(String[] args) 
	{
		new Controleur();
	}
}
