package code.ihm;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import code.jeu.objet.Joueur;
import code.jeu.objet.Map;
import code.Controleur;

public class PanelEcran extends JPanel implements KeyListener, Runnable
{
	private Controleur ctrl;
	private FrameJeu frame;

	private Joueur joueur;

	private boolean[] keysPressed = new boolean[256];

	public PanelEcran(Controleur ctrl, FrameJeu frame, Joueur j)
	{
		this.ctrl = ctrl;
		this.frame = frame;
		this.joueur = j;

		this.setFocusable(true);
		this.addKeyListener(this);
		requestFocusInWindow();

		Thread movementThread = new Thread(this);
        movementThread.start();
	}

	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);

		//dessine les joueurs
		for(Joueur j : this.ctrl.getJoueurs())
		{
			g.setColor(j.getCouleur());
			g.fillRect(j.getX(), j.getY(), j.getTaille(), j.getTaille());
			g.drawString(j.getNom() + "", j.getX() + 6 - j.getNom().length() * 3, j.getY() + j.getTaille() + 10);

			if(j.getBouclier())
			{
				g.setColor(Color.BLUE);
				g.drawRect(j.getX() - 5, j.getY() - 5, j.getTaille() + 10, j.getTaille() + 10);
			}	
		}

		//dessin des bordure 
		Map m = this.ctrl.getMap();

		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, m.getHauteur());
		g.drawLine(0, 0, m.getLongueur(), 0);
		g.drawLine(m.getLongueur(), 0, m.getLongueur(), m.getHauteur());
		g.drawLine(0, m.getHauteur(), m.getLongueur(), m.getHauteur());

		g.drawString("num charge : " + this.joueur.getNbBouclier(), 0, 50);

		//regarde les coliisions
		this.ctrl.checkColision();

		//verif si pas mort
		if(!this.ctrl.getJoueurs().contains(this.joueur))
			this.frame.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{

	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
        keysPressed[keyCode] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
        keysPressed[keyCode] = false;
	}

	public void run()
	{
		while(true)
		{
			if(keysPressed[68] || keysPressed[39]) {joueur.deplacer('E');}
			if(keysPressed[81] || keysPressed[37]) {joueur.deplacer('O');}
			if(keysPressed[90] || keysPressed[38]) {joueur.deplacer('N');}
			if(keysPressed[83] || keysPressed[40]) {joueur.deplacer('S');}

			if(keysPressed[32])
			{
				joueur.décharge();
			}
			else
			{
				joueur.charge();
			}

			repaint();

			try 
			{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		
	}
}
