package code.jeu.objet;

import java.awt.Color;

public class Joueur 
{
	private static int DISTANCE = 1;
	private static int TAILLE = 10;
	private static int MAX_BOUCLIER = 500;
	
	private char nom;
	private Color couleur;

	private int chargeBouclier;
	private boolean bouclier;

	private Map map;

	private int x;
	private int y;

	public Joueur(char nom, Color couleur, Map map)
	{
		this.nom = nom;
		this.couleur = couleur;

		this.chargeBouclier = Joueur.MAX_BOUCLIER;
		this.bouclier = false;

		this.map = map;

		this.x = 0;
		this.y = 0;
	}

	public void décharge()
	{
		if(chargeBouclier >= 5)
		{
			chargeBouclier -= 5;
			this.bouclier = true;
			return;
		}
		this.bouclier = false;
	}

	public void charge()
	{
		this.bouclier = false;

		if(chargeBouclier < Joueur.MAX_BOUCLIER)
			this.chargeBouclier++;
	}

	public void deplacer(char dir)
	{
		switch(dir)
		{
			case 'N' -> this.y -= Joueur.DISTANCE;
			case 'S' -> this.y += Joueur.DISTANCE;
			case 'E' -> this.x += Joueur.DISTANCE;
			case 'O' -> this.x -= Joueur.DISTANCE;
		}

		if(this.x < 0) this.x = 0;
		if(this.x > map.getLongueur() - Joueur.TAILLE) this.x = map.getLongueur() - Joueur.TAILLE;
		if(this.y < 0) this.y = 0;
		if(this.y > map.getHauteur() - Joueur.TAILLE) this.y = map.getHauteur() - Joueur.TAILLE;
	}

	public char getNom() {return this.nom;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public Color getCouleur() {return this.couleur;}
	public int getTaille() {return Joueur.TAILLE;}
	public boolean getBouclier() {return this.bouclier;}
	public int getNbBouclier() {return this.chargeBouclier;}
}
