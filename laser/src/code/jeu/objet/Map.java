package code.jeu.objet;

import java.io.Serializable;
import java.util.ArrayList;

import code.jeu.outil.CollisionBonus;
import code.jeu.outil.CollisionJoueur;
import code.jeu.outil.GenerateurBonus;

public class Map implements Serializable
{
	private String nom;

	private int hauteur;
	private int longueur;
	
	private ArrayList<Joueur> lstJoueurs;
	private ArrayList<Bonus> lstBonus;
	
	public Map(String nom, int hauteur, int longueur)
	{
		this.nom = nom;

		this.hauteur = hauteur;
		this.longueur = longueur;

		this.lstJoueurs = new ArrayList<Joueur>();
		this.lstBonus = new ArrayList<Bonus>();

		creationOutils();
	}

	public void creationOutils()
	{
		//Observe les collisions
		Thread collisionJoueur = new Thread( new CollisionJoueur( this )) ;
        collisionJoueur.start();

		Thread collisionBonus = new Thread( new CollisionBonus( this ) );
        collisionBonus.start();

		//génere les bonus
		Thread generateurBonus = new Thread( new GenerateurBonus( this ) );
		generateurBonus.start();
	}

	public void addJoueur(Joueur j)
	{
		this.lstJoueurs.add(j);
	}

	public void addBonus(Bonus b)
	{
		this.lstBonus.add(b);
	}

	public void setJoueurs(ArrayList<Joueur> lst)
	{
		this.lstJoueurs = lst;
	}

	public void setBonus(ArrayList<Bonus> lst)
	{
		this.lstBonus = lst;
	}

	public int getHauteur() {return this.hauteur;}
	public int getLongueur() {return this.longueur;}
	public ArrayList<Joueur> getJoueurs() {return this.lstJoueurs;}
	public ArrayList<Bonus> getBonus() {return this.lstBonus;}

	public String toString()
	{
		String s = this.nom + "  " + this.lstJoueurs.size() + "\n";
		for(Joueur j : this.lstJoueurs)
		{
			s += j.toString()  + "\n";
		}
		return s;
	}

	public void modifJoueur(Joueur joueur)
	{
		if(joueur == null) return;
		
		if(!this.lstJoueurs.contains(joueur))
		{
			this.lstJoueurs.add(joueur);
		}
		else
		{
			for(Joueur j : this.lstJoueurs)
			{
				if(joueur == j)
				{
					this.lstJoueurs.remove(j);
					this.lstJoueurs.add(joueur);
					break;
				}
			}
		}
	}
}
