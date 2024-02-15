package code;
import code.ihm.FrameJeu;
import code.jeu.objet.Joueur;
import code.jeu.reseau.*;

import java.awt.Color;
import java.util.ArrayList;

public class Controleur 
{
	private static Color[] COULEURS = {Color.RED, Color.BLUE, Color.YELLOW, Color.PINK};
	
	private ArrayList<Joueur> lstJoueur;
	private ArrayList<FrameJeu> lstFrame;

	private static int compteur = 0;
	
	public Controleur()
	{
		//récupérer le serveur et le ctrl qui joint tout
		Serveur s = Serveur.recupServeur(this);

		this.lstFrame = new ArrayList<FrameJeu>();
		this.lstJoueur = new ArrayList<Joueur>();

		new Client();
	}

	public ArrayList<Joueur> getJoueurs()
	{
		return this.lstJoueur;
	}

	public void lancerJeu()
	{
		Joueur j = new Joueur('A', Controleur.COULEURS[++Controleur.compteur]);
		this.lstJoueur.add(j);
		this.lstFrame.add(new FrameJeu(this, j));
		System.out.println("lancement 2");
	}

	//regarde les colisions entre joueurs
	public void checkColision()
	{
		for (int i = 0; i < lstJoueur.size(); i++) 
		{
            for (int j = i + 1; j < lstJoueur.size(); j++) 
			{
                Joueur joueur1 = lstJoueur.get(i);
                Joueur joueur2 = lstJoueur.get(j);

				if(joueur1.equals(joueur2)) return;

                if (joueur1.getX() <= joueur2.getX() && joueur1.getX() + joueur1.getTaille() >= joueur2.getX() &&
				    joueur1.getY() <= joueur2.getY() && joueur1.getY() + joueur1.getTaille() >= joueur2.getY() ||
					joueur2.getX() <= joueur1.getX() && joueur2.getX() + joueur2.getTaille() >= joueur1.getX() &&
				    joueur2.getY() <= joueur1.getY() && joueur2.getY() + joueur2.getTaille() >= joueur1.getY() ) 
				{
                    System.out.println("colision");
					this.lstJoueur.remove(joueur1);
					this.lstJoueur.remove(joueur2);
                }
            }
        }
	}

	public static void main(String[] args) 
	{
		new Controleur();
	}
}
