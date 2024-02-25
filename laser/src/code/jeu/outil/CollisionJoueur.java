package code.jeu.outil;

import java.util.ArrayList;

import code.jeu.objet.Joueur;
import code.jeu.objet.Map;

public class CollisionJoueur implements Runnable
{
    private Map map;

    public CollisionJoueur(Map map)
    {
        this.map = map;
    }
    
    @Override
    public void run() 
    {
        ArrayList<Joueur> lstJoueur = map.getJoueurs();
        while (true)
        {
            //Test colision entre joueurs
            for (int i = 0; i < lstJoueur.size(); i++) 
            {
                for (int j = i + 1; j < lstJoueur.size(); j++) 
                {
                    Joueur joueur1 = lstJoueur.get(i);
                    Joueur joueur2 = lstJoueur.get(j);

                    if(joueur1.equals(joueur2)) break;

                    if (collision(joueur1, joueur2)) 
                    {
                        System.out.println("collision");

                        if(!joueur1.getBouclier())
                            lstJoueur.remove(joueur1);

                        if(!joueur2.getBouclier())
                            lstJoueur.remove(joueur2);
                        
                        // Controle des kills et du score
                        if(lstJoueur.contains(joueur1) && !lstJoueur.contains(joueur2))
                        {
                            joueur1.addKill();
                            joueur1.addScore((int)(joueur2.getScore() /2 ));
                        }

                        if(lstJoueur.contains(joueur2) && !lstJoueur.contains(joueur1))
                        {
                            joueur2.addKill();
                            joueur2.addScore((int)(joueur1.getScore() /2 ));
                        }
                    }
                }
            }
        }
    }

    public boolean collision(Joueur joueur1, Joueur joueur2)
    {
        return (joueur1.getX() <= joueur2.getX() && joueur1.getX() + joueur1.getTaille() >= joueur2.getX() &&
                joueur1.getY() <= joueur2.getY() && joueur1.getY() + joueur1.getTaille() >= joueur2.getY() ||
                joueur2.getX() <= joueur1.getX() && joueur2.getX() + joueur2.getTaille() >= joueur1.getX() &&
                joueur2.getY() <= joueur1.getY() && joueur2.getY() + joueur2.getTaille() >= joueur1.getY());
    }
    
}
