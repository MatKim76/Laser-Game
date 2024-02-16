package code.ihm;

import code.Controleur;
import code.jeu.objet.Joueur;
import code.jeu.reseau.Serveur;

import javax.swing.JFrame;

public class FrameJeu extends JFrame 
{
	private PanelEcran pnlEcran;

	private Controleur ctrl;
	private Serveur serv;

	public FrameJeu(Serveur serv, Joueur j)
	{
		//this.ctrl = ctrl;
		this.serv = serv;
		
		this.pnlEcran = new PanelEcran(serv, j);

		this.setTitle("Jeu");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		this.add(this.pnlEcran);
	}

}
