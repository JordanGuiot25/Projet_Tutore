import iut.algo.Decomposeur;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class ParterrePersonalise
{
	private Dalle[][] parterrePersonalise;
	private final int NORD       = 0;
	private final int NORD_EST   = 1;
	private final int SUD_EST    = 2;
	private final int SUD        = 3;
	private final int SUD_OUEST  = 4;
	private final int NORD_OUEST = 5;
	char lastDalle ='B';


	
	private final int TAILLE = 31;
	private final int CENTER = ((TAILLE-1)/2);
	
	
	boolean bNordOk       = true;
	boolean bSudOk        = true;
	boolean bnordOuestOk  = true;
	boolean bNordEstOk    = true;
	boolean bsudEstOk     = true;
	boolean bsudOuestOk   = true;
	
	int nordX,sudX,nordOuestX,sudOuestX,nordEstX,sudEstX;
	int nordY,sudY,nordOuestY,sudOuestY,nordEstY,sudEstY;
	
	
	
	
	public ParterrePersonalise()
	{

		//System.out.println("centre " +CENTER);
		this.parterrePersonalise = new Dalle[TAILLE][TAILLE];
		Dalle dalleStart = new Dalle();
		this.parterrePersonalise[CENTER][CENTER] = dalleStart;
		new GuiJeu(this);
	}
	
	public boolean ajouterDalle(Dalle dalle, int x, int y)
	{
		if(!(this.lastDalle < 'Q')){return false;}
		if(this.parterrePersonalise[x][y]!=null) {return false;}
		if(!aUneDalleAdjacente(x,y)){return false;}
		
		this.parterrePersonalise[x][y] = dalle;
		ajouterAdjacents(dalle, x,y);
		lastDalle++;
		
		
		return true;
	}
	
	public boolean emplacementVide(int x,int y)
	{
		if(this.parterrePersonalise[x][y]!=null) {return false;}
		return true;
	}

	public boolean aUneDalleAdjacente(int x, int y)
	{
		outOfBoundCheck(x,y);
		/*System.out.println("-----------------");
		System.out.println("N  :" + this.nordEstX + " :" +this.nordY);
		System.out.println("NE :" + this.nordEstX + " :" +this.nordEstY);
		System.out.println("SE  :" + this.sudEstX + " :" +this.sudEstY);
		System.out.println("S  :" + this.sudX + " :" +this.sudY);
		System.out.println("SO  :" + this.sudOuestX + " :" +this.sudOuestY);
		System.out.println("NO  :" + this.nordOuestX + " :" +this.nordOuestY);*/



		if(this.bNordOk)      {if(!(this.parterrePersonalise[this.nordX]     [this.nordY]      == null)) {return true;}}
		
		if(this.bNordEstOk)   {if(!(this.parterrePersonalise[this.nordEstX]  [this.nordEstY]   == null)) {return true;}}
		
		if(this.bsudEstOk)    {if(!(this.parterrePersonalise[this.sudEstX]   [this.sudEstY]    == null)) {return true;}}
		
		if(this.bSudOk)       {if(!(this.parterrePersonalise[this.sudX]      [this.sudY]       == null)) {return true;}}
		if(this.bsudOuestOk)  {if(!(this.parterrePersonalise[this.sudOuestX] [this.sudOuestY]  == null)) {return true;}}
		if(this.bnordOuestOk) {if(!(this.parterrePersonalise[this.nordOuestX][this.nordOuestY] == null)) {return true;}}
		
		return false;
	}
	
	private void ajouterAdjacents(Dalle dalle, int x, int y)
	{
		outOfBoundCheck(x,y);
				
		if(this.bNordOk)      
		{
			if(!(this.parterrePersonalise[this.nordX]     [this.nordY]      == null)) 
			{
				dalle.ajouterAdjacent(this.parterrePersonalise[this.nordX]     [this.nordY]     , NORD);
				this.parterrePersonalise[this.nordX][this.nordY].ajouterAdjacent(dalle, SUD);
				//System.out.println("nord");
			}
		}
		if(this.bNordEstOk)   
		{
			if(!(this.parterrePersonalise[this.nordEstX]  [this.nordEstY]   == null)) 
			{
				dalle.ajouterAdjacent(this.parterrePersonalise[this.nordEstX]  [this.nordEstY]  , NORD_EST);
				this.parterrePersonalise[this.nordEstX][this.nordEstY].ajouterAdjacent(dalle, SUD_OUEST);
				//System.out.println("nordest");
			}
		}
		if(this.bsudEstOk)    
		{
			if(!(this.parterrePersonalise[this.sudEstX]   [this.sudEstY]    == null)) 
			{
				dalle.ajouterAdjacent(this.parterrePersonalise[this.sudEstX]   [this.sudEstY]   , SUD_EST);
				this.parterrePersonalise[this.sudEstX][this.sudEstY].ajouterAdjacent(dalle, NORD_OUEST);
				//System.out.println("sudest");
			}
		}
		if(this.bSudOk)       
		{
			if(!(this.parterrePersonalise[this.sudX]      [this.sudY]       == null)) 
			{
				dalle.ajouterAdjacent(this.parterrePersonalise[this.sudX]      [this.sudY]      , SUD);
				this.parterrePersonalise[this.sudX][this.sudY].ajouterAdjacent(dalle, NORD);
				//System.out.println("sud");
			}
		}
		if(this.bsudOuestOk)  
		{
			if(!(this.parterrePersonalise[this.sudOuestX] [this.sudOuestY]  == null)) 
			{
				dalle.ajouterAdjacent(this.parterrePersonalise[this.sudOuestX] [this.sudOuestY] , SUD_OUEST);
				this.parterrePersonalise[this.sudOuestX][this.sudOuestY].ajouterAdjacent(dalle, NORD_EST);
				//System.out.println("sudouest");
			}
		}
		if(this.bnordOuestOk) 
		{	
			if(!(this.parterrePersonalise[this.nordOuestX][this.nordOuestY] == null)) 
			{
				dalle.ajouterAdjacent(this.parterrePersonalise[this.nordOuestX][this.nordOuestY], this.NORD_OUEST);
				this.parterrePersonalise[this.nordOuestX][this.nordOuestY].ajouterAdjacent(dalle, SUD_EST);
				//System.out.println("nordouest");
			}
		}

	}



	
	
	public void outOfBoundCheck(int x, int y)
	{
		boolean bOffset;
		if(y%2==1){bOffset = true;}
		else{bOffset = false;}
		
		//System.out.println(y%2);
		/*this.bNordOk       = true;
		this.bSudOk        = true;
		this.bnordOuestOk  = true;
		this.bNordEstOk    = true;
		this.bsudEstOk     = true;
		this.bsudOuestOk   = true;*/
		
		if(bOffset)
		{
			//System.out.println(x +" "+ y);
			//System.out.println("-------------");
			this.nordX = x - 1 ;
			this.nordY = y     ;
			/*System.out.print("\n"+this.nordX);
			System.out.println(" "+this.nordY + "n");*/

			
			this.nordEstX = x     ;
			this.nordEstY = y + 1 ;
			/*System.out.print("\n"+this.nordEstX);
			System.out.println(" " +this.nordEstY + "ne");*/
			
			this.sudEstX = x +1  ;
			this.sudEstY = y + 1 ;
			/*System.out.print("\n"+this.sudEstX);
			System.out.println(" "+this.sudEstY + "se");*/
			
			this.sudX = x + 1 ;
			this.sudY = y     ;
			/*System.out.print("\n"+this.sudX);
			System.out.println(" "+this.sudY + "s");*/
			
			this.sudOuestX = x     ;
			this.sudOuestY = y - 1 ;
		/*	System.out.print("\n"+this.sudOuestX);
			System.out.println(" "+this.sudOuestY + "so");*/
			
			this.nordOuestX = x + 1   ;
			this.nordOuestY = y - 1 ;
			/*System.out.print("\n"+this.nordOuestX);
			System.out.println(" "+this.nordOuestY + "no");*/
		}
		else
		{		
			//System.out.println(x +" "+ y);
			//System.out.println("-------------");
			this.nordX = x - 1 ;
			this.nordY = y     ;
			/*System.out.print("\n"+this.nordX);
			System.out.println(" "+this.nordY + "n");*/

			
			this.nordEstX = x - 1 ;
			this.nordEstY = y + 1 ;
			/*System.out.print("\n"+this.nordEstX);
			System.out.println(" " +this.nordEstY + "ne");*/
			
			this.sudEstX = x     ;
			this.sudEstY = y + 1 ;
			/*System.out.print("\n"+this.sudEstX);
			System.out.println(" "+this.sudEstY + "se");*/
			
			this.sudX = x + 1 ;
			this.sudY = y     ;
			/*System.out.print("\n"+this.sudX);
			System.out.println(" "+this.sudY + "s");*/
			
			this.sudOuestX = x     ;
			this.sudOuestY = y - 1 ;
			/*System.out.print("\n"+this.sudOuestX);
			System.out.println(" "+this.sudOuestY + "so");*/
			
			this.nordOuestX = x - 1 ;
			this.nordOuestY = y - 1 ;
			/*System.out.print("\n"+this.nordOuestX);
			System.out.println(" "+this.nordOuestY + "no");*/
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
		if(this.parterrePersonalise[i][y]!=null)
		{
			return this.parterrePersonalise[i][y];
		}
		return null;
	}

	public char getLastDalle()
	{
		return this.lastDalle;
	}
	
	public static void main(String[]agrs)
	{
		new ParterrePersonalise();
	}

}
