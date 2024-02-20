package code.ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import code.Controleur;
import code.jeu.reseau.Client;
import code.jeu.reseau.Serveur;

public class PanelMenu extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton btnServ;
	private JButton btnCli;

	private FrameMenu parent;


	public PanelMenu(Controleur ctrl, FrameMenu parent)
	{
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(1, 2));

		//this.btnServ = new JButton("seveur");
		this.btnCli = new JButton("jouer");

		this.add(this.btnCli);
		//this.add(this.btnServ);

		this.btnCli.addActionListener(this);
		//this.btnServ.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnCli)
		{
			System.out.println("client");
			Serveur.recupServeur(ctrl);

			chercherServ();
		}

		/*if(e.getSource() == this.btnServ)
		{
			System.out.println("serv");
			Serveur.recupServeur(ctrl);
		}*/
	}

	public void chercherServ()
	{
		String s = "di-";
		String s2 = "c-";
		int num;
		

		for(num = 715; num < 730; num++)
		{
			for(int pc = 0; pc < 30; pc++)
			{
				Client c = new Client(s + num + "-" + String.format("%02d", pc));
				c.start();
				Client c2 = new Client(s2 + s + num + "-" + String.format("%02d", pc));
				c2.start();

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
