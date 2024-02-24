package code.jeu.outil;

import java.net.Socket;

import javax.swing.JComboBox;

public class RechercheServeur implements Runnable
{
	private String serv;
	private JComboBox<String> serverList;

	public RechercheServeur(String serv, JComboBox<String> serverList)
	{
		this.serv = serv;
		this.serverList = serverList;
	}

	@Override
	public void run()
	{
		try
		{
			Socket socket = new Socket(this.serv, 8686);
			this.serverList.addItem(this.serv);
			socket.close();
		} catch (Exception e) {}
	}
}
