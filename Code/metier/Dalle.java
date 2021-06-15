package Equipe_22.metier;

import iut.algo.Clavier;

/** Classe Dalle 
 * @author Raphaël Lizot, Jordan Guiot, Enguerrand Beltran, Gaspard Gordien
 */
public class Dalle
{
	/** Compteur auto incrementer permettant de donner un nom de façon automatique à une dalle créer
	 */
	private static char         nbDalle = 'A';
	
	/** Nom de la dalle
	 * @see {@link char}
	 */
	private char      nomDalle;

	/** Tableau de 6 pilier representant les sommets de l'hexagone
	 * @see {@link Pillier}
	 */
	private Pilier[]  listeSommet;

	/** Coordonnée x de la dalle
	 * @see {@link int}
	 */
	private int       x;

	/** Coordonnée y de la dalle
	 * @see {@link int}
	 */
	private int       y;

	/** Indicateur si la dalle est contrôler par un joueur
	 * @see {@link boolean}
	 */
	private boolean   estControler;

	/** Le joueur qui controle la dalle
	 * @see {@link Joueur}
	 */
	private Joueur	  joueurProprietaire;

	/** Tableau des dalles voisines à la dalle
	 * @see {@link Dalle}
	 */
	private Dalle[]   listeDallesAdjacent;

	/** Numero du joueur qui joue
	 * @see {@link int}
	 */
	private int       numJoueur;

	/** tableau indicateur si le joueur 1 à détruit un pilier sur les sommets de la dalle
	 * @see {@link boolean}
	 */
	private boolean[] tabPilierDetruitJ1;

	/** tableau indicateur si le joueur 2 à détruit un pilier sur les sommets de la dalle
	 * @see {@link boolean}
	 */
	private boolean[] tabPilierDetruitJ2;


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

	/** Initie Dalle par default
	 */
	public Dalle()
	{
		this.nomDalle             = nbDalle++;
		this.listeSommet          = new Pilier[6];
		this.listeDallesAdjacent  = new Dalle[6];
		this.joueurProprietaire   = null;

		this.numJoueur            = 1;
		this.tabPilierDetruitJ1   = new boolean[6];
		this.tabPilierDetruitJ2   = new boolean[6];
	}

	/** Initie Dalle au coordonnée
	 * @param x {@link int}, y {@link Int}
	 */
	public Dalle(int x, int y) 
	{
		this.nomDalle             = nbDalle++;
		this.listeSommet          = new Pilier[6];
		
		this.x               = x;
		this.y               = y;

		this.listeDallesAdjacent  = new Dalle[6];

		this.joueurProprietaire   = null;

	}

	/** Renvoie le nom de la dalle
	 * @return nomDalle {@link Char}
	 */
	public char getNom() { return this.nomDalle; }

	/** Renvoie le X de la dalle
	 * @return x {@link Int}
	 */
	public int  getX()   { return this.x-33;   }

	/** Renvoie le Y de la dalle
	 * @return y {@link Int}
	 */
	public int  getY()   { return this.y-33;   }

	/** Renvoie le milieu X de la dalle
	 * @return x {@link Int}
	 */
	public int getMilieuX() { return this.x; }

	/** Renvoie le milieu Y de la dalle
	 * @return y {@link Int}
	 */
	public int getMilieuY() { return this.y; }
	
	/** Renvoie la dalle adjacent selon le numero du côté 
	 * @param  cote {@link Int}
	 * @return y {@link Int}
	 */
	public Dalle    getDalleAdjacent(int cote) { return this.listeDallesAdjacent[cote];}

	/** Renvoie le tableau des piliers de la dalle
	 * @return listeSommet {@link Pilier}
	 */
	public Pilier[] getSommets()               { return this.listeSommet;              }

	/** Renvoie le tableau des dalles adjacent 
	 * @return listeDallesAdjacent {@link Dalle}
	 */
	public Dalle[]  getListeDallesAdjacent()   { return this.listeDallesAdjacent;      }


	/** Renvoie le proprietaire de la dalle
	 * @return joueurProprietaire {@link Joueur}
	 */
	public Joueur getProprietaire() { return this.joueurProprietaire;}

	/** Renvoie le pilier precedent en fonction du pilier donné
	 * @param  pilier {@link Pilier}
	 * @return precedent {@link Pilier}
	 */
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

	/** Renvoie le pilier suivant en fonction du pilier donné
	 * @param  pilier {@link Pilier}
	 * @return suivant {@link Pilier}
	 */
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

	/** Defini le proprietaire de la dalle
	 * @param  joueur {@link Joueur}
	 */
	public void setProprietaire(Joueur joueur)
	{
		this.joueurProprietaire = joueur;
	}

	/** Renvoie le pilier selon le numero donné
	 * @param  numPilier {@link Int}
	 * @return pilier    {@link Pilier}
	 */
	public Pilier   getPilier(int numPilier)   { return this.listeSommet[numPilier];   }

	/** Renvoie l'indice du pilier
	 * @param  pilier {@link Pilier}
	 * @return indicePilier    {@link int}
	 */
	public int      getIndicePilier(Pilier pilier)
	{
		for(int cpt = 0; cpt < this.listeSommet.length; cpt++)
		{
			if ( this.listeSommet[cpt] == pilier)
				return cpt;
		}

		return 0;
	}

	/** Définie les coordonnées de la dalle
	 * @param  pilier {@link Pilier}
	 */
	public void     setCoordonner(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/** Définie une dalle adjacent à la dalle
	 * @param  cote {@link Int}, voisin {@link Dalle}
	 */
	public void     setAdjacent  (int cote, Dalle voisin)
	{
		this.listeDallesAdjacent[cote] = voisin;

		switch(cote)
		{
			case 0 : voisin.setCoordonner(this.x,    this.y-66);break;
			case 1 : voisin.setCoordonner(this.x+49, this.y-33);break;
			case 2 : voisin.setCoordonner(this.x+49, this.y+33);break;
			case 3 : voisin.setCoordonner(this.x,    this.y+66);break;
			case 4 : voisin.setCoordonner(this.x-49, this.y+33);break;
			case 5 : voisin.setCoordonner(this.x-49, this.y-33);break;
		}

		for(int cpt = 0; cpt < 3; cpt++)
		{
			cote ++;
			if( cote > 5)
				cote = 0;
		}
		voisin.rajoutDalleAdjacent(cote, this);
	}

	/** Définie une dalle adjacent à la dalle
	 * @param  cote {@link Int}, voisin {@link Dalle}
	 * @return validation {@link Boolean}
	 */
	public boolean rajoutDalleAdjacent (int cote, Dalle voisin)
	{
		if ( voisin == null || cote > 5 && cote < 0 || this.listeDallesAdjacent[cote] != null )
		{
			return false;
		}

		this.listeDallesAdjacent[cote] = voisin;

		return true;
	}

	/** Définie un pilier d'une certaine couleur sur un sommet de la dalle
	 * @param  couleur {@link Char}, numSommet {@link Int}
	 * @return validation {@link Boolean}
	 */
	public boolean rajoutPillier(char couleur, int numSommet)
	{
		if ( this.listeSommet[numSommet] == null )
		{
			int x = 0; 
			int y = 0;

			switch( numSommet )
			{
				case 0 :
				{
					x = this.getMilieuX() -16-6;
					y = this.getMilieuY() -33-6;
					break;
				}
				case 1 :
				{
					x = this.getMilieuX() +16-6;
					y = this.getMilieuY() -33-6;
					break;
				}
				case 2 :
				{
					x = this.getMilieuX() +33-6;
					y = this.getMilieuY()-6;
					break;
				}

				case 3 :
				{
					x = this.getMilieuX() +16-6;
					y = this.getMilieuY() +33-6;
					break;
				}
				
				case 4 :
				{
					x = this.getMilieuX() -16-6;
					y = this.getMilieuY() +33-6;
					break;
				}
				case 5 :
				{
					x = this.getMilieuX() -33-6;
					y = this.getMilieuY()-6;
					break;
				}
			}

			Pilier pilierTmp = new Pilier(couleur,x,y);

			if( !this.posePilier(pilierTmp, numSommet) )
				return false;


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

	/** Définie un pilier sur un sommet de la dalle
	 * @param  pilier {@link Pilier}, numSommet {@link Int}
	 * @return validation {@link Boolean}
	 */
	private boolean posePilier(Pilier pilier, int numSommet) 
	{
		if( !this.getTabPilierDetruit()[numSommet] )
		{
			this.listeSommet[numSommet] =  pilier;

			return true;
		}

		return false;
			
	}

	/** Detruis un pilier selon le numero
	 * @param  numSommet {@link Int}
	 * @return validation {@link Boolean}
	 */
	public boolean detruirePillier(int numSommet)
	{
		if ( this.listeSommet[numSommet] != null )
		{
			this.listeSommet[numSommet] = null;
			this.setPilierDetruit(numSommet);
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
					this.listeDallesAdjacent[5].setPilierDetruit(sommetAdjacent);
				}
			}
			else
			{
				
				if(this.listeDallesAdjacent[numSommet-1] != null)
				{
					this.listeDallesAdjacent[numSommet-1].destructionDuPilier(sommetAdjacent);
					this.listeDallesAdjacent[numSommet-1].setPilierDetruit(sommetAdjacent);
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
				this.listeDallesAdjacent[numSommet].setPilierDetruit(sommetAdjacent);
			}

			return true;
		}

		return false;
	}

	/** Défini le numero du joueur
	 * @param  num {@link Int}
	 */
	public void setNumJoueur(int num )
	{
		this.numJoueur = num;

		if ( num == 2 )
			this.tabPilierDetruitJ2 = new boolean[6];
		if ( num == 1 )
			this.tabPilierDetruitJ1 = new boolean[6];
	}
	/** Renvoie le numero du joueur
	 * @return  numJoueur {@link Int}
	 */
	public int  getNumJoueur()         { return this.numJoueur; }

	/** Définie un pilierDetruit selon le numero du sommet
	 * @param   numSommet  {@link Int}
	 * @return  validation {@link Boolean}
	 */
	public boolean   setPilierDetruit(int numSommet)
	{
		if( this.numJoueur == 1 )
		{
			this.tabPilierDetruitJ1[numSommet] = true;

			return true;
		}
		if(this.numJoueur  == 2 )
		{
			this.tabPilierDetruitJ2[numSommet] = true;

			return true;
		}

		return false;
	}

	/** Renvoie le tableau des piliers detruit
	 * @return  tabPilierDetruit {@link Boolean}
	 */
	public boolean[] getTabPilierDetruit()
	{
		if( this.numJoueur == 1 )
			return this.tabPilierDetruitJ1;
		if(this.numJoueur  == 2 )
			return this.tabPilierDetruitJ2;

		return null;
	}

	/** Met à null un pilier selon le numero
	 * @param  numSommet {@link Int}
	 */
	public void destructionDuPilier(int numSommet)
	{
		this.listeSommet[numSommet] =  null;
	}

	/** Verifie le proprietaire de la Dalle
	 * @param   joueur1 {@link Joueur}, joueur2 {@link Joueur}
	 * @return  proprietaire {@link Joueur}
	 */
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

	/** Detruit les piliers qui n'ont pas la couleur mis en paramètre
	 * @param   couleurMajoriter {@link Char}
	 * @return  nbPilierDetruit {@link Int}
	 */
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

	/** Informe si la dalle est contrôler
	 * @return  estControler {@link Boolean}
	 */
	public boolean estControler()
	{
		if(this.joueurProprietaire !=null) {return true;}
		return false;
	}
	
	
	/** Renvoie les informations de la dalle
	 * @return  sMessage {@link String}
	 */
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

	/** Supprime une dalle
	 */
	public void supprimerDalle()
	{
		this.nbDalle--;
		for(Dalle d : this.listeDallesAdjacent){ d = null ; }
	}
}
