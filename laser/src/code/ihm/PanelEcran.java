package code.ihm;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import code.jeu.objet.Joueur;
import code.Controleur;

public class PanelEcran extends JPanel implements KeyListener, Runnable
{
	private Controleur ctrl;

	private Joueur joueur;

	private boolean[] keysPressed = new boolean[256];

	public PanelEcran(Controleur ctrl, Joueur j)
	{
		this.ctrl = ctrl;
		this.joueur = j;

		this.setFocusable(true);
		this.addKeyListener(this);
		requestFocusInWindow();

		Thread movementThread = new Thread(this);
        movementThread.start();
	}

	public void paint (Graphics g)
	{
		super.paint(g);

		for(Joueur j : this.ctrl.getJoueurs())
		{
			g.setColor(j.getCouleur());
			g.fillRect(j.getX(), j.getY(), 10, 10);
		}
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
			if(keysPressed[68]) {joueur.droite();}
			if(keysPressed[81]) {joueur.gauche();}
			if(keysPressed[90]) {joueur.haut();}
			if(keysPressed[83]) {joueur.bas();}

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
