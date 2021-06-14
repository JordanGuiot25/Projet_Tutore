package PilierDeLaTerre.metier;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.ihm.gui.FrameControleEditeur;
import PilierDeLaTerre.ihm.gui.FrameEditeurParterre;
import PilierDeLaTerre.ihm.gui.PanelEditeurControle;
import PilierDeLaTerre.ihm.gui.PanelEdtieurAffichage;
import PilierDeLaTerre.Controleur;

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

public class EditeurParterre 
{
	private Dalle[][] editeurParterre;
	private final int NORD       = 0;
	private final int NORD_EST   = 1;
	private final int SUD_EST    = 2;
	private final int SUD        = 3;
	private final int SUD_OUEST  = 4;
	private final int NORD_OUEST = 5;
	private char lastDalle ='B';


	
	private final int TAILLE = 31;
	private final int CENTER = ((TAILLE-1)/2);
	
	
	private boolean bNordOk       = true;
	private boolean bSudOk        = true;
	private boolean bnordOuestOk  = true;
	private boolean bNordEstOk    = true;
	private boolean bsudEstOk     = true;
	private boolean bsudOuestOk   = true;
	private boolean bRetour       = true;
	
	private int nordX,sudX,nordOuestX,sudOuestX,nordEstX,sudEstX;
	private int nordY,sudY,nordOuestY,sudOuestY,nordEstY,sudEstY;
	private Controleur ctrl;
	private ArrayList<Point> lstCoord = new ArrayList<Point>();
	private ArrayList<Dalle> lstDalle = new ArrayList<Dalle>();
	private Point p;
	
	
	
	
	public EditeurParterre(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.editeurParterre = new Dalle[TAILLE][TAILLE];
		Dalle dalleStart = new Dalle();
		lstDalle.add(dalleStart);
		this.editeurParterre[CENTER][CENTER] = dalleStart;
		p = new Point(CENTER,CENTER);
		lstCoord.add(p);
		

	}
	
	public boolean ajouterDalle(Dalle dalle, int x, int y)
	{
		if(!(this.lastDalle < 'Q')){return false;}
		if(this.editeurParterre[x][y]!=null) {return false;}
		if(!aUneDalleAdjacente(x,y)){return false;}
		
		this.editeurParterre[x][y] = dalle;
		ajouterAdjacents(dalle, x,y);
		this.lastDalle++;
		lstDalle.add(dalle);
		
		
		return true;
	}

	public void setCoord(Point p, int coordx, int coordy)
	{
		this.editeurParterre[p.x][p.y].setCoordonner(coordx-400, coordy-700);

	}
	
	public boolean emplacementVide(int x,int y)
	{
		if(this.editeurParterre[x][y]!=null) {return false;}
		return true;
	}

	public boolean aUneDalleAdjacente(int x, int y)
	{
		outOfBoundCheck(x,y);



		if(this.bNordOk)      {if(!(this.editeurParterre[this.nordX]     [this.nordY]      == null)) {return true;}}
		
		if(this.bNordEstOk)   {if(!(this.editeurParterre[this.nordEstX]  [this.nordEstY]   == null)) {return true;}}
		
		if(this.bsudEstOk)    {if(!(this.editeurParterre[this.sudEstX]   [this.sudEstY]    == null)) {return true;}}
		
		if(this.bSudOk)       {if(!(this.editeurParterre[this.sudX]      [this.sudY]       == null)) {return true;}}
		if(this.bsudOuestOk)  {if(!(this.editeurParterre[this.sudOuestX] [this.sudOuestY]  == null)) {return true;}}
		if(this.bnordOuestOk) {if(!(this.editeurParterre[this.nordOuestX][this.nordOuestY] == null)) {return true;}}
		
		return false;
	}
	
	private void ajouterAdjacents(Dalle dalle, int x, int y)
	{
		outOfBoundCheck(x,y);
				
		if(this.bNordOk)      
		{
			if(!(this.editeurParterre[this.nordX]     [this.nordY]      == null)) 
			{
				dalle.rajoutDalleAdjacent(NORD, this.editeurParterre[this.nordX][this.nordY]);
				this.editeurParterre[this.nordX][this.nordY].rajoutDalleAdjacent(SUD, dalle);
			}
		}
		if(this.bNordEstOk)   
		{
			if(!(this.editeurParterre[this.nordEstX]  [this.nordEstY]   == null)) 
			{
				dalle.rajoutDalleAdjacent(NORD_EST, this.editeurParterre[this.nordEstX][this.nordEstY]);
				this.editeurParterre[this.nordEstX][this.nordEstY].rajoutDalleAdjacent(SUD_OUEST, dalle);
			}
		}
		if(this.bsudEstOk)    
		{
			if(!(this.editeurParterre[this.sudEstX]   [this.sudEstY]    == null)) 
			{
				dalle.rajoutDalleAdjacent(SUD_EST, this.editeurParterre[this.sudEstX][this.sudEstY]   );
				this.editeurParterre[this.sudEstX][this.sudEstY].rajoutDalleAdjacent(NORD_OUEST, dalle);
			}
		}
		if(this.bSudOk)       
		{
			if(!(this.editeurParterre[this.sudX]      [this.sudY]       == null)) 
			{
				dalle.rajoutDalleAdjacent(SUD, this.editeurParterre[this.sudX][this.sudY] );
				this.editeurParterre[this.sudX][this.sudY].rajoutDalleAdjacent(NORD, dalle);
			}
		}
		if(this.bsudOuestOk)  
		{
			if(!(this.editeurParterre[this.sudOuestX] [this.sudOuestY]  == null)) 
			{
				dalle.rajoutDalleAdjacent(SUD_OUEST, this.editeurParterre[this.sudOuestX][this.sudOuestY] );
				this.editeurParterre[this.sudOuestX][this.sudOuestY].rajoutDalleAdjacent(NORD_EST,dalle);
			}
		}
		if(this.bnordOuestOk) 
		{	
			if(!(this.editeurParterre[this.nordOuestX][this.nordOuestY] == null)) 
			{
				dalle.rajoutDalleAdjacent(NORD_OUEST,this.editeurParterre[this.nordOuestX][this.nordOuestY]);
				this.editeurParterre[this.nordOuestX][this.nordOuestY].rajoutDalleAdjacent(SUD_EST,dalle);
			}
		}

	}



	
	
	public void outOfBoundCheck(int x, int y)
	{
		boolean bOffset;
		if(y%2==1){bOffset = true;}
		else{bOffset = false;}

		
		if(bOffset)
		{
			
			this.nordX = x - 1 ;
			this.nordY = y     ;


			
			this.nordEstX = x     ;
			this.nordEstY = y + 1 ;

			
			this.sudEstX = x +1  ;
			this.sudEstY = y + 1 ;

			
			this.sudX = x + 1 ;
			this.sudY = y     ;

			
			this.sudOuestX = x     ;
			this.sudOuestY = y - 1 ;

			
			this.nordOuestX = x + 1   ;
			this.nordOuestY = y - 1 ;

		}
		else
		{		

			this.nordX = x - 1 ;
			this.nordY = y     ;


			
			this.nordEstX = x - 1 ;
			this.nordEstY = y + 1 ;

			
			this.sudEstX = x     ;
			this.sudEstY = y + 1 ;
			
			
			this.sudX = x + 1 ;
			this.sudY = y     ;

			
			this.sudOuestX = x     ;
			this.sudOuestY = y - 1 ;

			
			this.nordOuestX = x - 1 ;
			this.nordOuestY = y - 1 ;

		}

		
		
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

	public Dalle getDalle(int i, int y)
	{
		if(this.editeurParterre[i][y]!=null)
		{
			return this.editeurParterre[i][y];
		}
		return null;
	}

	public char getLastDalle()
	{
		return this.lastDalle;
	}
	
	

	public void supprimerDalle( int x, int y)
	{
		if(bRetour)
		{
			if(this.lastDalle>'B')
			{
				this.lastDalle--;
				suprimerAdjacents(this.editeurParterre[x][y],x,y);
				this.editeurParterre[x][y].supprimerDalle();
				this.editeurParterre[x][y] = null;
				this.lstDalle.remove(this.lstDalle.size()-1);
			}
		}
		else
		{
			this.lastDalle--;
			suprimerAdjacents(this.editeurParterre[x][y],x,y);
			this.editeurParterre[x][y].supprimerDalle();
			this.editeurParterre[x][y] = null;
			this.lstDalle.remove(this.lstDalle.size()-1);

		}
	}

	private void suprimerAdjacents(Dalle dalle, int x, int y)
	{
		outOfBoundCheck(x,y);
				
		if(this.bNordOk)      
		{
			if(!(this.editeurParterre[this.nordX]     [this.nordY]      == null)) 
			{
				this.editeurParterre[this.nordX][this.nordY].rajoutDalleAdjacent(SUD, null);
			}
		}
		if(this.bNordEstOk)   
		{
			if(!(this.editeurParterre[this.nordEstX]  [this.nordEstY]   == null)) 
			{
				this.editeurParterre[this.nordEstX][this.nordEstY].rajoutDalleAdjacent(SUD_OUEST, null);
			}
		}
		if(this.bsudEstOk)    
		{
			if(!(this.editeurParterre[this.sudEstX]   [this.sudEstY]    == null)) 
			{
				this.editeurParterre[this.sudEstX][this.sudEstY].rajoutDalleAdjacent(NORD_OUEST, null);
			}
		}
		if(this.bSudOk)       
		{
			if(!(this.editeurParterre[this.sudX]      [this.sudY]       == null)) 
			{
				this.editeurParterre[this.sudX][this.sudY].rajoutDalleAdjacent(NORD, null);
			}
		}
		if(this.bsudOuestOk)  
		{
			if(!(this.editeurParterre[this.sudOuestX] [this.sudOuestY]  == null)) 
			{
				this.editeurParterre[this.sudOuestX][this.sudOuestY].rajoutDalleAdjacent(NORD_EST, null);
			}
		}
		if(this.bnordOuestOk) 
		{	
			if(!(this.editeurParterre[this.nordOuestX][this.nordOuestY] == null)) 
			{
				this.editeurParterre[this.nordOuestX][this.nordOuestY].rajoutDalleAdjacent(SUD_EST, null);
			}
		}

	}
	


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

	public ArrayList<Dalle> getNiveau()	{ return this.lstDalle;	}
	
	
	


	public void addCoord(Point point)
	{
		lstCoord.add(point);
	}

	public Point getLastCoord()
	{
		return lstCoord.remove(lstCoord.size()-1);
	}

	public int getCoordSize()
	{
		return lstDalle.size();
	}
	
	

}
