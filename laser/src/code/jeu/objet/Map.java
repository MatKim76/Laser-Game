package code.jeu.objet;

import java.util.ArrayList;

public class Map 
{
	private int hauteur;
	private int longueur;
	
	private ArrayList<Joueur> lstJoueurs;
	private ArrayList<Bonus> lstBonus;
	
	public Map(int hauteur, int longueur)
	{
		this.hauteur = hauteur;
		this.longueur = longueur;
	}

	public int getHauteur() {return this.hauteur;}
	public int getLongueur() {return this.longueur;}
}
