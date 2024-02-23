package code.jeu.objet;

import java.awt.Color;

public class Joueur 
{
	private static int VITESSE_BASE = 2;
	private static int TAILLE = 20;
	private static int MAX_BOUCLIER = 500;
	
	private String nom;
	private Color couleur;

	private int chargeBouclier;
	private boolean bouclier;

	private Map map;

	private int x;
	private int y;

	private int vitesse = Joueur.VITESSE_BASE;

	public Joueur(String nom, Color couleur, Map map)
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
			this.vitesse = Joueur.VITESSE_BASE*2;
			return;
		}
		this.bouclier = false;
	}

	public void charge()
	{
		this.bouclier = false;
		this.vitesse = Joueur.VITESSE_BASE;

		if(chargeBouclier < Joueur.MAX_BOUCLIER)
			this.chargeBouclier++;
	}

	public void deplacer(char dir)
	{
		switch(dir)
		{
			case 'N' -> this.y -= vitesse;
			case 'S' -> this.y += vitesse;
			case 'E' -> this.x += vitesse;
			case 'O' -> this.x -= vitesse;
		}

		if(this.x < 0) this.x = 0;
		if(this.x > map.getLongueur() - Joueur.TAILLE) this.x = map.getLongueur() - Joueur.TAILLE;
		if(this.y < 0) this.y = 0;
		if(this.y > map.getHauteur() - Joueur.TAILLE) this.y = map.getHauteur() - Joueur.TAILLE;
	}

	public String getNom() {return this.nom;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public Color getCouleur() {return this.couleur;}
	public int getTaille() {return Joueur.TAILLE;}
	public boolean getBouclier() {return this.bouclier;}
	public int getNbBouclier() {return this.chargeBouclier;}
}
