package PilierDeLaTerre.metier;

import iut.algo.Clavier;

public class Dalle
{
	private static final String IMAGE   = "../Ressources/Dalle.png";
	private static char         nbDalle = 'A';
	
	private char      nomDalle;
	private Pilier[]  listeSommet;
	private int       x;
	private int       y;
	private boolean   estControler;
	private Joueur	  joueurProprietaire;

	private Dalle[]   listeDallesAdjacent;

	/*Numero des sommets--|
	|          0--1       |
	|         /    \      |
	|        5      2     |
	|         \    /      |
	|          4--3       |
	|--------------------*/

	/*--Numero des côtés---
	           0
	         ____
	      5 /    \ 1
	       /      \
	       \      / 2
	      4 \____/
	         
	          3
	---------------------*/

	public Dalle()
	{
		this.nomDalle             = nbDalle++;
		this.listeSommet          = new Pilier[6];
		this.listeDallesAdjacent  = new Dalle[6];
		this.joueurProprietaire   = null;
	}

	public Dalle(int x, int y)
	{
		this.nomDalle             = nbDalle++;
		this.listeSommet          = new Pilier[6];
		
		this.x               = x;
		this.y               = y;

		this.listeDallesAdjacent  = new Dalle[6];

		this.joueurProprietaire   = null;

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

	public boolean rajoutPillier(char couleur, int numSommet)
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

			Pilier pilierTmp = new Pilier(couleur,x,y);

			this.listeSommet[numSommet] = pilierTmp;


			/*  Rajout du pilier dans les autres dalles   */

			int sommetAdjacent = numSommet;

			/* Incrementation pour trouver le sommet de la dalle adjacent */
			for (int cpt = 0; cpt < 2; cpt ++ )
			{
				sommetAdjacent++;
				if( sommetAdjacent > 5 )
				{
					sommetAdjacent = 0;
				}
			}

			if ( numSommet == 0 )
			{
				if(this.listeDallesAdjacent[5] != null)
				{
					this.listeDallesAdjacent[5].posePilier(pilierTmp, sommetAdjacent);
				}
			}
			else
			{
				
				if(this.listeDallesAdjacent[numSommet-1] != null)
				{
					this.listeDallesAdjacent[numSommet-1].posePilier(pilierTmp, sommetAdjacent);
				}
			}
				
			for (int cpt = 0; cpt < 2; cpt ++ )
			{
				sommetAdjacent++;
				if( sommetAdjacent > 5 )
				{
					sommetAdjacent = 0;
				}
			}
			
			if(this.listeDallesAdjacent[numSommet] != null)
			{
				this.listeDallesAdjacent[numSommet].posePilier(pilierTmp, sommetAdjacent);
			}

			return true;
		}

		return false;
	}

	public boolean detruirePillier(int numSommet)
	{
		if ( this.listeSommet[numSommet] != null )
		{
			this.listeSommet[numSommet] = null;

			/*  Supression du pilier dans les autres dalles   */

			int sommetAdjacent = numSommet;

			/* Incrementation pour trouver le sommet de la dalle adjacent */
			for (int cpt = 0; cpt < 2; cpt ++ )
			{
				sommetAdjacent++;
				if( sommetAdjacent > 5 )
				{
					sommetAdjacent = 0;
				}
			}

			if ( numSommet == 0 )
			{
				if(this.listeDallesAdjacent[5] != null)
				{
					System.out.println(this.listeDallesAdjacent[5].getNom() + " " + sommetAdjacent);
					this.listeDallesAdjacent[5].detruirePilier(sommetAdjacent);
				}
			}
			else
			{
				
				if(this.listeDallesAdjacent[numSommet-1] != null)
				{
					System.out.println(this.listeDallesAdjacent[numSommet-1].getNom() + " " + sommetAdjacent);
					this.listeDallesAdjacent[numSommet-1].detruirePilier(sommetAdjacent);
				}
			}
				
			for (int cpt = 0; cpt < 2; cpt ++ )
			{
				sommetAdjacent++;
				if( sommetAdjacent > 5 )
				{
					sommetAdjacent = 0;
				}
			}
			
			if(this.listeDallesAdjacent[numSommet] != null)
			{
				System.out.println(this.listeDallesAdjacent[numSommet].getNom() + " " + sommetAdjacent);
				this.listeDallesAdjacent[numSommet].detruirePilier(sommetAdjacent);
			}

			return true;
		}

		return false;
	}

	private void posePilier(Pilier pilier, int numSommet)
	{
		this.listeSommet[numSommet] =  pilier;
	}

	private void detruirePilier(int numSommet)
	{
		this.listeSommet[numSommet] =  null;
	}

	public char getNom() { return this.nomDalle; }
	public int  getX()   { return this.x-33;   }
	public int  getY()   { return this.y-33;   }

	public int getMilieuX() { return this.x; }
	public int getMilieuY() { return this.y; }

	public Dalle    getDalleAdjacent(int cote) { return this.listeDallesAdjacent[cote];}
	public Pilier[] getSommets()            { return this.listeSommet;              }
	public Dalle[]  getListeDallesAdjacent(){ return this.listeDallesAdjacent;      }

	public void     setCoordonner(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void     setAdjacent  (int cote, Dalle voisin)
	{
		this.listeDallesAdjacent[cote] = voisin;

		switch(cote)
		{
			case 0 -> voisin.setCoordonner(this.x,    this.y-67);
			case 1 -> voisin.setCoordonner(this.x+49, this.y-33);
			case 2 -> voisin.setCoordonner(this.x+49, this.y+33);
			case 3 -> voisin.setCoordonner(this.x,    this.y+67);
			case 4 -> voisin.setCoordonner(this.x-49, this.y+33);
			case 5 -> voisin.setCoordonner(this.x-49, this.y-33);
		}

		for(int cpt = 0; cpt < 3; cpt++)
		{
			cote ++;
			if( cote > 5)
				cote = 0;
		}
		voisin.rajoutDalleAdjacent(cote, this);
	}

	public char verifierPropietaireDalle()
	{
		int pilierJ1 = 0;
		int pilierJ2 = 0;

		for (Pilier pilierTmp : listeSommet) 
		{
			if(pilierTmp.getCoul() == 'G')  {pilierJ1++;}	

			if(pilierTmp.getCoul() == 'M')  {pilierJ2++;}

			if(pilierJ1 >= 4){return 'G';}
			if(pilierJ2 >= 4){return 'M';}

		}

		this.setProprietaire(null);
		return 'N';

		
	}
	public void setProprietaire(Joueur joueur)
	{
		this.joueurProprietaire = joueur;
	}

	
	public Joueur getProprietaire() { return this.joueurProprietaire;}

	//methode /!\	
	public boolean estControler()
	{
		if(this.joueurProprietaire !=null) {return true;}
		return false;
	}
	
	

	public String toString()
	{
		String sMessage = this.getNom() + " : ";

		for ( Dalle voisin : this.getListeDallesAdjacent() )
		{
			if ( voisin != null )
				sMessage += voisin.getNom() + ", " ;
		}

		sMessage += "\n \tSommet : ";
		
		Pilier[] tabPilier = this.getSommets();

		for ( int cpt = 0; cpt < tabPilier.length; cpt++ )
		{
			if ( tabPilier[cpt] != null )
				sMessage += cpt + ", " ;
		}
		
		return sMessage;
	}
}