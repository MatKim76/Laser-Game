package code.ihm;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import code.jeu.objet.Joueur;
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

			if(j.getBouclier())
			{
				g.setColor(Color.CYAN);
				g.drawRect(j.getX() - 5, j.getY() - 5, j.getTaille() + 10, j.getTaille() + 10);
			}	
		}

		//dessin des bordure 
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 0, 400);
		g.drawLine(0, 0, 500, 0);
		g.drawLine(500, 0, 500, 400);
		g.drawLine(0, 400, 500, 400);

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
