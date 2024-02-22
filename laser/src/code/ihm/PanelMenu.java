package code.ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import code.Controleur;
import code.jeu.reseau.Client;
import code.jeu.reseau.RechercheServeur;
import code.jeu.reseau.Serveur;

import javax.swing.JComboBox;

public class PanelMenu extends JPanel implements ActionListener {
    private Controleur ctrl;
    private FrameMenu parent;

    private JButton btnServeur;
	private JButton btnSearch;
    private JComboBox<String> serverList;
    private JButton btnConnect;

    public PanelMenu(Controleur ctrl, FrameMenu parent) {
        this.ctrl = ctrl;
        this.parent = parent;

        this.setLayout(new GridLayout(4, 1, 10, 10));
		this.setBorder ( new EmptyBorder  ( 10, 10, 10, 10 ) );

        this.btnServeur = new JButton("Créer Serveur");
		this.btnSearch = new JButton("Rechercher Serveurs");
        this.serverList = new JComboBox<>();
        this.btnConnect = new JButton("Se Connecter");

        this.add(this.btnServeur);
		this.add(this.btnSearch);
        this.add(this.serverList);
        this.add(this.btnConnect);

        this.btnServeur.addActionListener(this);
		this.btnSearch.addActionListener(this);
        this.btnConnect.addActionListener(this);

		this.serverList.addItem("localhost");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnSearch)
		{
            this.serverList.removeAllItems();
			chercherServ();
        }

		if (e.getSource() == this.btnConnect)
		{
            // Se connecter au serveur sélectionné dans la liste
            String selectedServer = (String) this.serverList.getSelectedItem();
            System.out.println("+"+selectedServer+"+");
			if (selectedServer != null)
			{
                Client c = new Client(selectedServer, this.ctrl);
				c.start();
            }
        }

		if(e.getSource() == this.btnServeur)
		{
			Serveur.recupServeur(ctrl);
		}
    }

	public void chercherServ()
	{
		//j'espère que ca pose pas trop de problème car lance au max 50 Thread
		ExecutorService executor = Executors.newFixedThreadPool(50);
		
		String s = "di-";
		String s2 = "c-";
		int num;

		for(num = 715; num < 730; num++)
		{
			for(int pc = 0; pc < 30; pc++)
			{
				String serv1 = s + num + "-" + String.format("%02d", pc);
				String serv2 = s2 + serv1;
				
				executor.execute(new RechercheServeur(serv1, this.serverList));
                executor.execute(new RechercheServeur(serv2, this.serverList));
			}
		}
		System.out.println("Fin recherche serveur");

	}
}

