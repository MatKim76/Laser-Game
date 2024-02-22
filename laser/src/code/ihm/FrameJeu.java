package code.ihm;

import code.Controleur;
import code.jeu.objet.Joueur;
import code.jeu.reseau.Serveur;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class FrameJeu extends JFrame 
{
	private PanelEcran pnlEcran;

	private Controleur ctrl;

	public FrameJeu(Controleur ctrl, Joueur j)
	{
		this.ctrl = ctrl;
		
		this.pnlEcran = new PanelEcran(ctrl, this, j);

		this.setTitle("Jeu");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

		this.add(this.pnlEcran);
	}

}
