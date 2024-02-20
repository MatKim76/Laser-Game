package code.jeu.objet;

public class Map 
{
	private int hauteur;
	private int longueur;
	//TODO ajout d'objet sur la map ?
	
	public Map(int hauteur, int longueur)
	{
		this.hauteur = hauteur;
		this.longueur = longueur;
	}

	public int getHauteur() {return this.hauteur;}
	public int getLongueur() {return this.longueur;}
}
