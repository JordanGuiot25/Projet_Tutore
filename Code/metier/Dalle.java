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
					this.listeDallesAdjacent[5].destructionDuPilier(sommetAdjacent);
				}
			}
			else
			{
				
				if(this.listeDallesAdjacent[numSommet-1] != null)
				{
					this.listeDallesAdjacent[numSommet-1].destructionDuPilier(sommetAdjacent);
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
				this.listeDallesAdjacent[numSommet].destructionDuPilier(sommetAdjacent);
			}

			return true;
		}

		return false;
	}

	private void posePilier(Pilier pilier, int numSommet)
	{
		this.listeSommet[numSommet] =  pilier;
	}

	public void destructionDuPilier(int numSommet)
	{
		this.listeSommet[numSommet] =  null;
	}

	public char getNom() { return this.nomDalle; }
	public int  getX()   { return this.x-33;   }
	public int  getY()   { return this.y-33;   }

	public int getMilieuX() { return this.x; }
	public int getMilieuY() { return this.y; }

	public Pilier getPrecedent(Pilier pilier)
	{
		for(int cpt = 0; cpt < this.listeSommet.length; cpt ++)
		{
			if( this.listeSommet[cpt] == pilier )
			{
				if( cpt == 0 )
					return this.listeSommet[5];
				else
					return this.listeSommet[cpt-1];
			}
		}

		return null;
	}
	public Pilier getSuivant(Pilier pilier)
	{
		for(int cpt = 0; cpt < this.listeSommet.length; cpt ++)
		{
			if( this.listeSommet[cpt] == pilier )
			{
				if( cpt == 5 )
					return this.listeSommet[0];
				else
					return this.listeSommet[cpt+1];
			}
		}

		return null;
	}

	public Pilier   getPilier(int numPilier)   { return this.listeSommet[numPilier];   }
	public int      getIndicePilier(Pilier pilier)
	{
		for(int cpt = 0; cpt < this.listeSommet.length; cpt++)
		{
			if ( this.listeSommet[cpt] == pilier)
				return cpt;
		}

		return 0;
	}
	public Dalle    getDalleAdjacent(int cote) { return this.listeDallesAdjacent[cote];}
	public Pilier[] getSommets()               { return this.listeSommet;              }
	public Dalle[]  getListeDallesAdjacent()   { return this.listeDallesAdjacent;      }

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
			case 0 -> voisin.setCoordonner(this.x,    this.y-68);
			case 1 -> voisin.setCoordonner(this.x+49, this.y-33);
			case 2 -> voisin.setCoordonner(this.x+49, this.y+33);
			case 3 -> voisin.setCoordonner(this.x,    this.y+68);
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

	public Joueur verifierProprietaireDalle(Joueur joueur1, Joueur joueur2)
	{
		int pilierJ1 = 0;
		int pilierJ2 = 0;

		for (Pilier pilierTmp : listeSommet) 
		{
			if( pilierTmp != null )
			{
				if(pilierTmp.getCoul() == joueur1.getCouleur())  {pilierJ1++;}	

				if(pilierTmp.getCoul() == joueur2.getCouleur())  {pilierJ2++;}
			}
		}

		if(pilierJ1 >= 4)
		{
			if ( this.getProprietaire() != joueur1 )
			{
				joueur1.incrementationNbPilierDetruis( this.destructionPilierJoueur(joueur1.getCouleur()) );
				this.setProprietaire(joueur1);

				for(Dalle dalleTmp: this.getListeDallesAdjacent())
				{
					if ( dalleTmp != null)
						dalleTmp.verifierProprietaireDalle(joueur1, joueur2);
				}

				return joueur1;
			}
			else
				return null;
			
		}

		if(pilierJ2 >= 4)
		{
			if ( this.getProprietaire() != joueur2 )
			{
				joueur2.incrementationNbPilierDetruis( this.destructionPilierJoueur(joueur2.getCouleur()) );
				this.setProprietaire(joueur2);

				for(Dalle dalleTmp: this.getListeDallesAdjacent())
				{
					if ( dalleTmp != null)
						dalleTmp.verifierProprietaireDalle(joueur1, joueur2);
				}

				return joueur2;
			}
			else
				return null;
			
		}

		if( this.joueurProprietaire != null )
			this.joueurProprietaire.retirerDalle(this);
		
		this.setProprietaire(null);

		return null;
	}

	private int destructionPilierJoueur(char couleurMajoriter )
	{
		int nbPilierDetruis=0;
		for(int cpt = 0; cpt < this.listeSommet.length; cpt++ )
		{
			if( this.listeSommet[cpt] != null && this.listeSommet[cpt].getCoul() != couleurMajoriter )
			{
				this.detruirePillier(cpt);
				nbPilierDetruis++;
			}	
		}

		return nbPilierDetruis;
	}



	public void setProprietaire(Joueur joueur)
	{
		this.joueurProprietaire = joueur;
	}

	
	public Joueur getProprietaire() { return this.joueurProprietaire;}

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
