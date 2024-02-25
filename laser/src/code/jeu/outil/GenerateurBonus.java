package code.jeu.outil;

import java.util.ArrayList;

import code.jeu.objet.Bonus;
import code.jeu.objet.Map;

public class GenerateurBonus implements Runnable
{
    private Map map;

    public GenerateurBonus(Map map)
    {
        this.map = map;
    }
    
    @Override
    public void run() 
    {
        while (true) 
        {
            try 
            {
                int dodo = (int)(Math.random()*9000) + 1000;
                Thread.sleep(dodo);    
            } catch (Exception e) {}

            synchronized (map) //jsp si c'est utile
            {
                ArrayList<Bonus> lstBonus = map.getBonus();
                lstBonus.add(new Bonus(map));
            }
        }
    }
}
