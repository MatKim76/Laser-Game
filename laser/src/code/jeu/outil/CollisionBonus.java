package code.jeu.outil;

import java.util.ArrayList;

import code.jeu.objet.Bonus;
import code.jeu.objet.Joueur;
import code.jeu.objet.Map;

public class CollisionBonus implements Runnable
{
    private Map map;

    public CollisionBonus(Map map)
    {
        this.map = map;
    }
    
    @Override
    public void run() 
    {
        ArrayList<Bonus> lstBonus = map.getBonus();
        ArrayList<Joueur> lstJoueur = map.getJoueurs();

        while (true)
        {
            //Test colision entre joueurs et bonus
            for (int i = 0; i < lstJoueur.size(); i++) 
            {
                for (int j = 0; j < lstBonus.size(); j++) 
                {
                    Joueur joueur1 = lstJoueur.get(i);
                    Bonus bonus = lstBonus.get(j);

                    if (collision(joueur1, bonus) ) 
                    {
                        System.out.println("collision bonus");

                        joueur1.addScore(100);
                        lstBonus.remove(bonus);

                        break;
                    }
                }
            }
            try 
            {
                Thread.sleep(10);
            } catch (Exception e) { }
        }
    }

    //TODO petit probleme de test de collision je pense
    public boolean collision(Joueur joueur, Bonus bonus)
    {
        return (joueur.getX() <= bonus.getX() && joueur.getX() + joueur.getTaille() >= bonus.getX() &&
                joueur.getY() <= bonus.getY() && joueur.getY() + joueur.getTaille() >= bonus.getY() ||
                bonus.getX() <= joueur.getX() && bonus.getX() + bonus.getTaille() >= joueur.getX() &&
                bonus.getY() <= joueur.getY() && bonus.getY() + bonus.getTaille() >= joueur.getY() );
    }
    
}
