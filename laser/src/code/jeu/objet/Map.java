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

		this.lstJoueurs = new ArrayList<Joueur>();
		this.lstBonus = new ArrayList<Bonus>();
	}

	public void addJoueur(Joueur j)
	{
		this.lstJoueurs.add(j);
	}

	public void addBonus(Bonus b)
	{
		this.lstBonus.add(b);
	}

	public int getHauteur() {return this.hauteur;}
	public int getLongueur() {return this.longueur;}
	public ArrayList<Joueur> getJoueurs() {return this.lstJoueurs;}
	public ArrayList<Bonus> getBonus() {return this.lstBonus;}
}
