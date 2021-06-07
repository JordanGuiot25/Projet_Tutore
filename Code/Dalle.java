public class Dalle
{
	private static final String IMAGE = "../Ressources/Dalle.png";
	private static char         nbDalle = 'A';
	 
	
	private char      nomDalle;
	private Pilier[]  listeSommet;
	private int       xDalle;
	private int       yDalle;

	private Dalle[]   listeDallesAdjacent;

	/*Numero des sommets--|
	|          0--1       |
	|         /    \      |
	|        5      2     |
	|         \    /      |
	|          4--3       |
	|--------------------*/

	/*--------------------
	           0
	         ____
	      5 /    \ 1
	       /      \
	       \      / 2
	      4 \____/
	         
	          3
	---------------------*/

	public Dalle(int xDalle, int yDalle)
	{
		this.nomDalle    = nbDalle++;
		this.listeSommet = new Pilier[6];
		
		this.xDalle           = xDalle;
		this.yDalle           = yDalle;

		this.listeDallesAdjacent  = new Dalle[6];
	}

	public boolean rajoutDalleAdjacent (int cote, Dalle voisin)
	{
		if ( voisin == null || cote > 5 && cote < 0 || this.listeDallesAdjacent[cote] != null )
		{
			return false;
		}

		this.listeDallesAdjacent[cote] = voisin;

		return true;
	}

	public boolean rajoutPillier(int numSommet)
	{
		if ( this.listeSommet[numSommet] == null )
		{
			int x = 0; 
			int y = 0;
			switch( numSommet )
			{
				case 0 -> 
				{
					x = this.getMilieuX() -16-6;
					y = this.getMilieuY() -33-6;
				}
				case 1 ->
				{
					x = this.getMilieuX() +16-6;
					y = this.getMilieuY() -33-6;
				}
				case 2 ->
				{
					x = this.getMilieuX() +33-6;
					y = this.getMilieuY()-6;
				}

				case 3 ->
				{
					x = this.getMilieuX() +16-6;
					y = this.getMilieuY() +33-6;
				}
				
				case 4 ->
				{
					x = this.getMilieuX() -16-6;
					y = this.getMilieuY() +33-6;
				}
				case 5 ->
				{
					x = this.getMilieuX() -33-6;
					y = this.getMilieuY()-6;
				}
			}

			System.out.println( numSommet + " : " + x + " " + y);

			this.listeSommet[numSommet] = new Pilier('M',x,y);

			return true;
		}

		return false;
	}

	public boolean detruirePillier(int numSommet)
	{
		if ( this.listeSommet[numSommet] != null )
		{
			this.listeSommet[numSommet] = null;

			return true;
		}

		return false;
	}

	public char getNom() { return this.nomDalle; }
	public int  getX()   { return this.xDalle-33;   }
	public int  getY()   { return this.yDalle-33;   }

	public int getMilieuX() { return this.xDalle; }
	public int getMilieuY() { return this.yDalle; }

	public Dalle    getDalleAdjacent(int cote) { return this.listeDallesAdjacent[cote];}
	public Pilier[] getSommets()            { return this.listeSommet;              }
	public Dalle[]  getListeDallesAdjacent(){ return this.listeDallesAdjacent;      }


	public String toString()
	{
		String sMessage = this.getNom() + " : ";

		for ( Dalle voisin : this.getListeDallesAdjacent() )
		{
			if ( voisin != null )
				sMessage += voisin.getNom() + ", " ;
		}
		
		return sMessage;
	}
}