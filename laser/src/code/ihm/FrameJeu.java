package code.ihm;

import code.Controleur;
import code.jeu.objet.Joueur;

import javax.swing.JFrame;

public class FrameJeu extends JFrame 
{
	private PanelEcran pnlEcran;

	private Controleur ctrl;

	public FrameJeu(Controleur ctrl, Joueur j)
	{
		this.ctrl = ctrl;
		
		this.pnlEcran = new PanelEcran(ctrl, j);

		this.setTitle("Jeu");
		this.setSize(300, 200);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		this.add(this.pnlEcran);
	}

}
