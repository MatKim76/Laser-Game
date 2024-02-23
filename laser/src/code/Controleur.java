package code;
import code.ihm.FrameJeu;
import code.ihm.FrameMenu;
import code.jeu.objet.Joueur;
import code.jeu.objet.Map;
import code.jeu.reseau.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Controleur 
{
	private static Color[] COULEURS = {Color.RED, Color.CYAN, Color.YELLOW, Color.PINK, Color.GREEN, Color.GRAY};
	
	private ArrayList<Joueur> lstJoueur;
	private FrameJeu frame;
	private Map map;

	private static int compteur = 0;
	
	public Controleur()
	{
		//récupérer le serveur et le ctrl qui joint tout
		//Serveur s = Serveur.recupServeur(this);

		//new Client();


		new FrameMenu(this);
	}

	//regarde les colisions entre joueurs
	//TODO faut changer
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

					if(!joueur1.getBouclier())
						this.lstJoueur.remove(joueur1);

					if(!joueur2.getBouclier())
						this.lstJoueur.remove(joueur2);
                }
            }
        }
	}

	public static void main(String[] args) 
	{
		new Controleur();
	}

	public ArrayList<Joueur> getJoueurs()
	{
		return this.lstJoueur;
	}

	public Map getMap()
	{
		return this.map;
	}

	public void lancerJeu()
	{
		Serveur s = Serveur.recupServeur(this);
		this.lstJoueur = s.getJoueurs();
		this.map = s.getMap();

		String nomJoueur = JOptionPane.showInputDialog(null, "Veuillez choisir un pseudo :");
		while( nomJoueur.length() > 10 )
			nomJoueur = JOptionPane.showInputDialog(null, "Pseudo trop long (10 cara max) :");

		Joueur j = new Joueur(nomJoueur, Controleur.COULEURS[Controleur.compteur++], map);
		this.lstJoueur.add(j);

		this.frame = new FrameJeu(this, j);

		System.out.println("lancement 2");
	}
}
