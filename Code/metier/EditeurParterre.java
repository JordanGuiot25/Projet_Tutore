package Equipe_22.metier;

import Equipe_22.metier.Dalle;
import Equipe_22.ihm.gui.FrameControleEditeur;
import Equipe_22.ihm.gui.FrameEditeurParterre;
import Equipe_22.ihm.gui.PanelEditeurControle;
import Equipe_22.ihm.gui.PanelEdtieurAffichage;
import Equipe_22.Controleur;

import iut.algo.Decomposeur;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Point;

/**
 * Le metier de l'éditeur de parterre
 * @author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
 * 
 */

public class EditeurParterre 
{
	/**Tableau de dalle représentant le parterre
	 * @see {@link Dalle}
	 */
	private Dalle[][] parterre;
	/**Constante pour le bord nord de l'haxagone */
	private final int NORD       = 0;
	/**Constante pour le bord Nord-Est de l'haxagone */
	private final int NORD_EST   = 1;
	/**Constante pour le bord Sud-Est de l'haxagone */
	private final int SUD_EST    = 2;
	/**Constante pour le bord Sud de l'haxagone */
	private final int SUD        = 3;
	/**Constante pour le bord Sud-Ouest de l'haxagone */
	private final int SUD_OUEST  = 4;
	/**Constante pour le bord Nord-Ouest de l'haxagone */
	private final int NORD_OUEST = 5;
	/**Caractère représentant la derniere dalle posé */
	private char lastDalle ='B';


	/**Taille du tableau de Dalle*/
	private final int TAILLE = 31;
	/**Indice du centre du tableau de Dalle */
	private final int CENTER = ((TAILLE-1)/2);
	
	/**booleen indiquant si au nord de la dalle choisie il y a un out of bound */
	private boolean bNordOk       = true;
	/**booleen indiquant si au sud de la dalle choisie il y a un out of bound */
	private boolean bSudOk        = true;
	/**booleen indiquant si au Nord-Ouest de la dalle choisie il y a un out of bound */
	private boolean bnordOuestOk  = true;
	/**booleen indiquant si au Nord-Est de la dalle choisie il y a un out of bound */
	private boolean bNordEstOk    = true;
	/**booleen indiquant si au Sud-Est de la dalle choisie il y a un out of bound */
	private boolean bsudEstOk     = true;
	/**booleen indiquant si au Sud-Ouest de la dalle choisie il y a un out of bound */
	private boolean bsudOuestOk   = true;
	/**booleen indiquant si on a cliquer sur bouton retour */
	private boolean bRetour       = true;

	/**les coordoné en x des dalle autour des hexagones */
	private int nordX,sudX,nordOuestX,sudOuestX,nordEstX,sudEstX;
	/**les coordoné en y des dalle autour des hexagones */
	private int nordY,sudY,nordOuestY,sudOuestY,nordEstY,sudEstY;
	/**le Controleur 
	 * @see {@link Controleur}
	 */
	private Controleur ctrl;
	/**Liste de coordonées */
	private ArrayList<Point> lstCoord = new ArrayList<Point>();
	/**liste des dalles utiliser pour lancer une partie */
	private ArrayList<Dalle> lstDalle = new ArrayList<Dalle>();
	/**index des dalle dans le parterre */
	private Point p;
	
	
	
	/**Construeur du parterre
	 * @param ctrl {@link Controleur}
	 */
	public EditeurParterre(Controleur ctrl)
	{
		this.ctrl = ctrl;
		//Création du tableau
		this.parterre = new Dalle[TAILLE][TAILLE];
		//Création et ajout de la première dalle
		Dalle dalleStart = new Dalle();
		lstDalle.add(dalleStart);
		this.parterre[CENTER][CENTER] = dalleStart;
		this.p = new Point(CENTER,CENTER);
		lstCoord.add(this.p);
	}
	
	/** Ajoute une Dalle au parterre
	 * @param dalle {@link Dalle}
	 * @param x indice ligne du tableau de Dalle
	 * @param y indice colonne du tableau de Dalle
	*/
	public boolean ajouterDalle(Dalle dalle, int x, int y)
	{
		
		if(!(this.lastDalle < 'Q')){return false;}//Teste si on peut encore ajouter une dalle
		if(this.parterre[x][y]!=null) {return false;}//teste si l'emplacement est vide
		if(!aUneDalleAdjacente(x,y)){return false;}//Teste si l'emplacement est adjacent a une dalle
		
		//Ajout de la dalle
		this.parterre[x][y] = dalle;
		ajouterAdjacents(dalle, x,y);
		this.lastDalle++;
		lstDalle.add(dalle);	
		return true;
	}

	/**Teste si l'emplacement au coordonées x et y
	 * @param x indice ligne du tableau de Dalle
	 * @param y indice colonne du tableau de Dalle
	 */
	public boolean emplacementVide(int x,int y)
	{
		if(this.parterre[x][y]!=null) {return false;}
		return true;
	}

	/**Teste si il y a une dalle adajcent a l'emplacement au coordonées x et y
	 * @param x indice ligne du tableau de Dalle
	 * @param y indice colonne du tableau de Dalle
	 */
	public boolean aUneDalleAdjacente(int x, int y)
	{
		outOfBoundCheck(x,y);//teste les out of Bound du tableau


		//si il y a une dalle autour de l'hexagone choisi retourne true
		if(this.bNordOk)      {if(!(this.parterre[this.nordX]     [this.nordY]      == null)) {return true;}}
		
		if(this.bNordEstOk)   {if(!(this.parterre[this.nordEstX]  [this.nordEstY]   == null)) {return true;}}
		
		if(this.bsudEstOk)    {if(!(this.parterre[this.sudEstX]   [this.sudEstY]    == null)) {return true;}}
		
		if(this.bSudOk)       {if(!(this.parterre[this.sudX]      [this.sudY]       == null)) {return true;}}
		if(this.bsudOuestOk)  {if(!(this.parterre[this.sudOuestX] [this.sudOuestY]  == null)) {return true;}}
		if(this.bnordOuestOk) {if(!(this.parterre[this.nordOuestX][this.nordOuestY] == null)) {return true;}}
		
		return false;
	}
	
	/**Ajoute a la dalle posée ses dalles adjacentes et ajoute la dalle posée a ses dalle adjacentes
	 * @param dalle {@link Dalle}
	 * @param x indice ligne du tableau de Dalle
	 * @param y indice colonne du tableau de Dalle
	*/
	private void ajouterAdjacents(Dalle dalle, int x, int y)
	{
		outOfBoundCheck(x,y);//teste les out of Bound du tableau
				
		
		if(this.bNordOk)      
		{
			if(!(this.parterre[this.nordX]     [this.nordY]      == null)) 
			{
				dalle.rajoutDalleAdjacent(NORD, this.parterre[this.nordX][this.nordY]);//Ajoute la dalle adjacente au nord de la dalle posée
				this.parterre[this.nordX][this.nordY].rajoutDalleAdjacent(SUD, dalle);//Ajoute la dalle posée au sud de la dalle adjacente
			}
		}
		if(this.bNordEstOk)   
		{
			if(!(this.parterre[this.nordEstX]  [this.nordEstY]   == null)) 
			{
				dalle.rajoutDalleAdjacent(NORD_EST, this.parterre[this.nordEstX][this.nordEstY]);//même chose au nord est
				this.parterre[this.nordEstX][this.nordEstY].rajoutDalleAdjacent(SUD_OUEST, dalle);//même chose au sud ouest
			}
		}
		if(this.bsudEstOk)    
		{
			if(!(this.parterre[this.sudEstX]   [this.sudEstY]    == null)) 
			{
				dalle.rajoutDalleAdjacent(SUD_EST, this.parterre[this.sudEstX][this.sudEstY]   );
				this.parterre[this.sudEstX][this.sudEstY].rajoutDalleAdjacent(NORD_OUEST, dalle);
			}
		}
		if(this.bSudOk)       
		{
			if(!(this.parterre[this.sudX]      [this.sudY]       == null)) 
			{
				dalle.rajoutDalleAdjacent(SUD, this.parterre[this.sudX][this.sudY] );
				this.parterre[this.sudX][this.sudY].rajoutDalleAdjacent(NORD, dalle);
			}
		}
		if(this.bsudOuestOk)  
		{
			if(!(this.parterre[this.sudOuestX] [this.sudOuestY]  == null)) 
			{
				dalle.rajoutDalleAdjacent(SUD_OUEST, this.parterre[this.sudOuestX][this.sudOuestY] );
				this.parterre[this.sudOuestX][this.sudOuestY].rajoutDalleAdjacent(NORD_EST,dalle);
			}
		}
		if(this.bnordOuestOk) 
		{	
			if(!(this.parterre[this.nordOuestX][this.nordOuestY] == null)) 
			{
				dalle.rajoutDalleAdjacent(NORD_OUEST,this.parterre[this.nordOuestX][this.nordOuestY]);
				this.parterre[this.nordOuestX][this.nordOuestY].rajoutDalleAdjacent(SUD_EST,dalle);
			}
		}

	}

	/**supprime les relations d'adjacence d'une dalle
	 * @param dalle {@link Dalle}
	 * @param x indice ligne du tableau de Dalle
	 * @param y indice colonne du tableau de Dalle
	 */
	private void suprimerAdjacents(Dalle dalle, int x, int y)
	{
		outOfBoundCheck(x,y);//teste les out of Bound du tableau
				
		if(this.bNordOk)      
		{
			if(!(this.parterre[this.nordX]     [this.nordY]      == null)) 
			{
				this.parterre[this.nordX][this.nordY].rajoutDalleAdjacent(SUD, null);
			}
		}
		if(this.bNordEstOk)   
		{
			if(!(this.parterre[this.nordEstX]  [this.nordEstY]   == null)) 
			{
				this.parterre[this.nordEstX][this.nordEstY].rajoutDalleAdjacent(SUD_OUEST, null);
			}
		}
		if(this.bsudEstOk)    
		{
			if(!(this.parterre[this.sudEstX]   [this.sudEstY]    == null)) 
			{
				this.parterre[this.sudEstX][this.sudEstY].rajoutDalleAdjacent(NORD_OUEST, null);
			}
		}
		if(this.bSudOk)       
		{
			if(!(this.parterre[this.sudX]      [this.sudY]       == null)) 
			{
				this.parterre[this.sudX][this.sudY].rajoutDalleAdjacent(NORD, null);
			}
		}
		if(this.bsudOuestOk)  
		{
			if(!(this.parterre[this.sudOuestX] [this.sudOuestY]  == null)) 
			{
				this.parterre[this.sudOuestX][this.sudOuestY].rajoutDalleAdjacent(NORD_EST, null);
			}
		}
		if(this.bnordOuestOk) 
		{	
			if(!(this.parterre[this.nordOuestX][this.nordOuestY] == null)) 
			{
				this.parterre[this.nordOuestX][this.nordOuestY].rajoutDalleAdjacent(SUD_EST, null);
			}
		}
	}

	/**supprime une dalle  a l'emplacement au coordonées x et y
	 * @param x indice ligne du tableau de Dalle
	 * @param y indice colonne du tableau de Dalle
	*/
	public void supprimerDalle( int x, int y)
	{
		if(bRetour)//Teste si on a cliquer sur le bouton retour ou juste si l'on supprime une dalle
		{
			if(this.lastDalle>'B')//teste si on peut encore supprimer un dalle
			{
				this.lastDalle--;//décremente la dalle
				suprimerAdjacents(this.parterre[x][y],x,y);//supprime les dalles adjacentes
				this.parterre[x][y].supprimerDalle();//supprime la dalle
				this.parterre[x][y] = null;
				this.lstDalle.remove(this.lstDalle.size()-1);//enlève la dalle da la liste
			}
		}
		else
		{
			this.lastDalle--;//décremente la dalle
			suprimerAdjacents(this.parterre[x][y],x,y);//supprime les dalles adjacentes
			this.parterre[x][y].supprimerDalle();//supprime la dalle
			this.parterre[x][y] = null;
			this.lstDalle.remove(this.lstDalle.size()-1);//enlève la dalle da la lis

		}
	}

	
	/**empêche de faire des action hors du tableau de dalles
	 * @param x indice ligne du tableau de Dalle
	 * @param y indice colonne du tableau de Dalle
	 */
	public void outOfBoundCheck(int x, int y)
	{
		boolean bOffset;
		if(y%2==1){bOffset = true;}
		else{bOffset = false;}

		//Teste si il y a un décalage car les lignes hexagonales ne sont pas droites
		if(bOffset)
		{
			//coordonées au nord de la dalle
			this.nordX = x - 1 ;
			this.nordY = y     ;
			//coordonées au nord-est de la dalle
			this.nordEstX = x     ;
			this.nordEstY = y + 1 ;
			//coordonées au sud-est de la dalle
			this.sudEstX = x + 1 ;
			this.sudEstY = y + 1 ;
			//coordonées au sud de la dalle
			this.sudX = x + 1 ;
			this.sudY = y     ;
			//coordonées au sud-ouest de la dalle
			this.sudOuestX = x + 1 ;
			this.sudOuestY = y - 1 ;
			//coordonées au nord-ouest de la dalle
			this.nordOuestX = x     ;
			this.nordOuestY = y - 1 ;
		}
		else
		{		
			//coordonées au nord de la dalle
			this.nordX = x - 1 ;
			this.nordY = y     ;
			//coordonées au nord-est de la dalle
			this.nordEstX = x - 1 ;
			this.nordEstY = y + 1 ;
			//coordonées au sud-est de la dalle
			this.sudEstX = x     ;
			this.sudEstY = y + 1 ;
			//coordonées au sud de la dalle
			this.sudX = x + 1 ;
			this.sudY = y     ;
			//coordonées au sud-ouest de la dalle
			this.sudOuestX = x     ;
			this.sudOuestY = y - 1 ;
			//coordonées au nord-ouest de la dalle
			this.nordOuestX = x - 1 ;
			this.nordOuestY = y - 1 ;
		}

		
		//Attribue aux booleen autour de la dalle si elle sont out of bound ou pas
		if(this.nordX      < 0 || this.nordX      > this.TAILLE-1 || this.nordY      < 0 || this.nordY      > this.TAILLE-1 ) {this.bNordOk      = false;}
		else																												  {this.bNordOk      = true ;}
		if(this.nordEstX   < 0 || this.nordEstX   > this.TAILLE-1 || this.nordEstY   < 0 || this.nordEstY   > this.TAILLE-1 ) {this.bNordEstOk   = false;}
		else																												  {this.bNordEstOk   = true ;}
		if(this.sudEstX    < 0 || this.sudEstX    > this.TAILLE-1 || this.sudEstY    < 0 || this.sudEstY    > this.TAILLE-1 ) {this.bsudEstOk    = false;}
		else																												  {this.bsudEstOk    = true ;}
		if(this.sudX       < 0 || this.sudX       > this.TAILLE-1 || this.sudY       < 0 || this.sudY       > this.TAILLE-1 ) {this.bSudOk       = false;}
		else																												  {this.bSudOk       = true ;}
		if(this.sudOuestX  < 0 || this.sudOuestX  > this.TAILLE-1 || this.sudOuestY  < 0 || this.sudOuestY  > this.TAILLE-1 ) {this.bsudOuestOk  = false;}
		else																												  {this.bsudOuestOk  = true ;}
		if(this.nordOuestX < 0 || this.nordOuestX > this.TAILLE-1 || this.nordOuestY < 0 || this.nordOuestY > this.TAILLE-1 ) {this.bnordOuestOk = false;}
		else																												  {this.bnordOuestOk = true ;}

	}	

	/**Effectue le retour au menu principal et remet les dalles a zéro */
	public void retour()
	{
		bRetour = false ;
		while(lstCoord.size()!=0)
		{	
			Point pCoord = new Point(lstCoord.remove(lstCoord.size()-1));
			supprimerDalle(pCoord.x,pCoord.y);
		}	
		bRetour = true;
	}
	/**retourne le parterre actuelle
	 * @return ArrayList<{@link Dalle}>
	 */
	public ArrayList<Dalle> getNiveau()	{ return this.lstDalle;	}
	/**ajoute les coordonées a la liste de coordonées
	 * @param point {@link Point}
	 */
	public void addCoord(Point point){ lstCoord.add(point); }
	/** retourne la dernière coordoné inserée dans la liste
	 * @return lstCoord ArrayList<{@link Point}>
	*/
	public Point getLastCoord(){ return lstCoord.remove(lstCoord.size()-1); }
	/**retourne la taille de la liste de dalle
	 * @return lstDalle ArrayList<{@link Dalle}>
	 */
	public int getCoordSize(){ return lstDalle.size(); }
	/**retourne la dernière dalle
	 * @return lastDalle {@link Char}
	 */
	public char getLastDalle(){ return this.lastDalle; }
	/**ajoute les coordoner dans une dalle
	 * @param p {@link Point}
	 * @param coordx int - coordonées en x
	 * @param coordy int - coordonées en y
	 */
	public void setCoord(Point p, int coordx, int coordy){ this.parterre[p.x][p.y].setCoordonner(coordx, coordy); }
	/** retourne la dalle aux coordonées x et y
	 * @param x int - coordonées en x
	 * @param y int - coordonées en y
	 * @return {@link Dalle}
	*/
	public Dalle getDalle(int x, int y)
	{
		if(this.parterre[x][y]!=null){ return this.parterre[x][y]; }
		return null;
	}
}
