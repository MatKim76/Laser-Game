package code.jeu.reseau;

import java.net.*;
import code.Controleur;
import java.io.*;

public class GerantDeClient implements Runnable 
{
	private Controleur ctrl;

	private Socket s;
	private BufferedReader in;
	private PrintWriter out;
	
	public GerantDeClient(Socket s, Controleur ctrl)
	{
		this.s = s;
		this.ctrl = ctrl;
		
		try
		{
			this.in = new BufferedReader( new InputStreamReader(this.s.getInputStream()));
			this.out = new PrintWriter(this.s.getOutputStream(), true);

		}catch( IOException e ){ System.out.println("erreur de connection"); }
		
	}
	
	public void run()
	{ // "main" de la thread
		/*try{
			int cpt = 1;
			boolean ok = true;
			while( ok ) { // boucle sans fin
				//out.println(in.readLine());
				try{ Thread.sleep(1000); } // attend 1000ms
				catch(InterruptedException ie) {}
				ok = !out.checkError();
			}
			System.out.println("deconnection de quelqu un");
			in.close();
			out.close();
			this.s.close();
		}catch( IOException e ){ System.out.println("erreur de connection"); }*/

		this.ctrl.lancerJeu();
		System.out.println("fin du lancement");
	}
	
	public PrintWriter getPrint()
	{
		return this.out;
	}
}