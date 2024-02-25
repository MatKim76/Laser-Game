package code.jeu.objet;

import java.awt.Color;

public class Bonus 
{
	private int x;
	private int y;

	private int taille;

	private Map map;

	private Color couleur;

	public Bonus(Map map)
	{
		this.map = map;
		
		this.x = (int)(Math.random()*this.map.getLongueur());
		this.y = (int)(Math.random()*this.map.getHauteur());

		this.taille = 10;

		this.couleur = new Color( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)  );
	}

	public int getX() {return x;}
	public int getY() {return y;}
	public Map getMap() {return map;}
	public int getTaille() {return this.taille;}
	public Color getCouleur() {return this.couleur;}
}
