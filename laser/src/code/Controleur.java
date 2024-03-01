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

	private Joueur joueur;
	
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

	public void setMap(Map map)
	{
		this.map = map;
	}

	public void lancerJeu()
	{
		//Serveur s = Serveur.recupServeur(this);
		//this.map = s.getMap();
		while (this.map == null) 
		{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		String nomJoueur = JOptionPane.showInputDialog(null, "Veuillez choisir un pseudo :");
		while( nomJoueur.length() > 10 )
			nomJoueur = JOptionPane.showInputDialog(null, "Pseudo trop long (10 cara max) :");

		this.joueur = new Joueur(nomJoueur, Controleur.COULEURS[(int)(Math.random()*Controleur.COULEURS.length)], map);
		this.map.addJoueur(this.joueur);

		this.frame = new FrameJeu(this, this.joueur);

		System.out.println("lancement 2");
	}

	public Joueur getJoueur()
	{
		return this.joueur;
	}
}
