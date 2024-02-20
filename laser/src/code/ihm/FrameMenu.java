package code.ihm;

import javax.swing.JFrame;

import code.Controleur;
import code.jeu.objet.Joueur;

public class FrameMenu extends JFrame
{
	private PanelMenu pnlMenu;

	private Controleur ctrl;

	public FrameMenu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Menu");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.pnlMenu = new PanelMenu(ctrl,this);
		this.add(this.pnlMenu);

		this.setVisible(true);
	}
}
