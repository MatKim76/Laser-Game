package code.ihm;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import code.jeu.objet.Bonus;
import code.jeu.objet.Joueur;
import code.jeu.objet.Map;
import code.jeu.outil.CollisionBonus;
import code.jeu.outil.CollisionJoueur;
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

		this.setBackground(Color.WHITE);

		//Regarde les commandes clavier
		Thread movementThread = new Thread(this);
        movementThread.start();
	}

	/*public void paintComponent (Graphics g)
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

		//verif si pas mort
		if(!this.ctrl.getJoueurs().contains(this.joueur))
			this.frame.dispose();
	}*/

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
	
		// Récupérer la position du joueur
		int joueurX = this.joueur.getX();
		int joueurY = this.joueur.getY();
	
		// Calculer les coordonnées de dessin du joueur pour qu'il soit au centre du panel
		int joueurDessinX = getWidth() / 2 - joueurX - joueur.getTaille()/2;
		int joueurDessinY = getHeight() / 2 - joueurY - joueur.getTaille()/2;
	
		// Dessiner les autres éléments de la carte en ajustant leurs coordonnées de dessin
		for (Joueur j : this.ctrl.getJoueurs()) 
		{
			int dessinX = joueurDessinX + j.getX();
			int dessinY = joueurDessinY + j.getY();
	
			// Dessiner le joueur à sa nouvelle position calculée
			g.setColor(j.getCouleur());
			g.fillRect(dessinX, dessinY, j.getTaille(), j.getTaille());
			g.drawString(j.getNom() + "", dessinX + 6 - j.getNom().length() * 3, dessinY + j.getTaille() + 10);
	
			if (j.getBouclier()) {
				g.setColor(Color.BLUE);
				g.drawRect(dessinX - 5, dessinY - 5, j.getTaille() + 10, j.getTaille() + 10);
			}
		}

		// Dessin des bonus
		for (Bonus b : this.ctrl.getBonus()) 
		{
			int dessinX = joueurDessinX + b.getX();
			int dessinY = joueurDessinY + b.getY();
	
			// Dessiner le bonus à sa nouvelle position calculée
			g.setColor(b.getCouleur());
			g.fillRect(dessinX, dessinY, b.getTaille(), b.getTaille());
		}
	
		// Dessiner d'autres éléments de la carte (bordures, etc.) en ajustant leurs coordonnées de dessin
		Map m = this.ctrl.getMap();
		int dessinBordureX = joueurDessinX;
		int dessinBordureY = joueurDessinY;
	
		g.setColor(Color.BLACK);
		g.drawLine(dessinBordureX, dessinBordureY, dessinBordureX, dessinBordureY + m.getHauteur());
		g.drawLine(dessinBordureX, dessinBordureY, dessinBordureX + m.getLongueur(), dessinBordureY);
		g.drawLine(dessinBordureX + m.getLongueur(), dessinBordureY, dessinBordureX + m.getLongueur(), dessinBordureY + m.getHauteur());
		g.drawLine(dessinBordureX, dessinBordureY + m.getHauteur(), dessinBordureX + m.getLongueur(), dessinBordureY + m.getHauteur());
	
		// Dessiner d'autres éléments non liés à la carte (informations, etc.) normalement
		g.drawString("score : " + this.joueur.getScore(), 0, 20);
		g.drawString("kill : " + this.joueur.getKill(), 0, 35);

		//affichage du leaderboard
		this.ctrl.getJoueurs().sort(null);
		for (int i=0 ; i < this.ctrl.getJoueurs().size() ; i++)
		{
			if(i > 10 ) break;

			g.drawString( (i+1) + ". " + this.ctrl.getJoueurs().get(i).toString(), this.getWidth() - 100, 20 + i*15);
		}

		// affichage de charge bouclier
		g.setColor(Color.BLUE);
		g.fillRect( 0, this.getHeight() - 10, (int)(this.getWidth() * this.joueur.getNbBouclier()/this.joueur.getNbMaxBouclier()), 15);
		

		// Vérifier si le joueur est mort
		if (!this.ctrl.getJoueurs().contains(this.joueur))
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
                Thread.sleep(10);//changer la valeur pour voir sur les pc IUT
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		
	}
}
