package code.jeu.objet;

public class Bonus 
{
	private int x;
	private int y;

	private Map map;

	public Bonus(Map map)
	{
		this.map = map;
		
		this.x = (int)(Math.random()*this.map.getLongueur());
		this.y = (int)(Math.random()*this.map.getHauteur());
	}

	public int getX() {return x;}
	public int getY() {return y;}
	public Map getMap() {return map;}
}
