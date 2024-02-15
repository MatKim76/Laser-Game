package code.jeu;

import java.awt.Color;

public class Joueur 
{
	private static int DISTANCE = 1;
	
	private char nom;
	private Color couleur;

	private int x;
	private int y;

	public Joueur(char nom, Color couleur)
	{
		this.nom = nom;
		this.couleur = couleur;

		this.x = 0;
		this.y = 0;
	}

	public void droite() {this.x += Joueur.DISTANCE;}
	public void gauche() {this.x -= Joueur.DISTANCE;}
	public void haut() {this.y -= Joueur.DISTANCE;}
	public void bas() {this.y += Joueur.DISTANCE;}

	public char getNom() {return this.nom;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public Color getCouleur() {return this.couleur;}
}
