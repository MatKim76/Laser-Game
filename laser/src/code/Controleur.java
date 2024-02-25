package code;
import code.ihm.FrameJeu;
import code.ihm.FrameMenu;
import code.jeu.objet.Bonus;
import code.jeu.objet.Joueur;
import code.jeu.objet.Map;
import code.jeu.outil.CollisionBonus;
import code.jeu.outil.CollisionJoueur;
import code.jeu.outil.GenerateurBonus;
import code.jeu.reseau.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Controleur 
{
	private static Color[] COULEURS = {Color.RED, Color.CYAN, Color.YELLOW, Color.PINK, Color.GREEN, Color.GRAY};
	
	private FrameJeu frame;
	private Map map;

	private static int compteur = 0;
	
	public Controleur()
	{
		new FrameMenu(this);
	}

	public static void main(String[] args) 
	{
		new Controleur();
	}

	public ArrayList<Joueur> getJoueurs()
	{
		return this.map.getJoueurs();
	}

	public ArrayList<Bonus> getBonus()
	{
		return this.map.getBonus();
	}

	public Map getMap()
	{
		return this.map;
	}

	public void lancerJeu()
	{
		Serveur s = Serveur.recupServeur(this);
		this.map = s.getMap();

		String nomJoueur = JOptionPane.showInputDialog(null, "Veuillez choisir un pseudo :");
		while( nomJoueur.length() > 10 )
			nomJoueur = JOptionPane.showInputDialog(null, "Pseudo trop long (10 cara max) :");

		Joueur j = new Joueur(nomJoueur, Controleur.COULEURS[(int)(Math.random()*Controleur.COULEURS.length)], map);
		this.map.addJoueur(j);

		//Observe les collisions
		Thread collisionJoueur = new Thread( new CollisionJoueur(this.map)) ;
        collisionJoueur.start();

		Thread collisionBonus = new Thread( new CollisionBonus(this.map) );
        collisionBonus.start();

		//génere les bonus
		Thread generateurBonus = new Thread( new GenerateurBonus( this.map ) );
		generateurBonus.start();

		this.frame = new FrameJeu(this, j);

		System.out.println("lancement 2");
	}
}
