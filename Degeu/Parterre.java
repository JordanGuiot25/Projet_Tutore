/**
 * 
 * @author Michel forever
 */

public class Parterre
{
	private Dalle[][] parterre;
	private final int NORD       = 0;
	private final int NORD_EST   = 1;
	private final int SUD_EST    = 2;
	private final int SUD        = 3;
	private final int SUD_OUEST  = 4;
	private final int NORD_OUEST = 5;
	
	private final int TAILLE = 31;
	private final int CENTER = ((TAILLE-1)/2)-1;
	
	
	
	
	public Parterre()
	{

		this.parterre = new Dalle[TAILLE][TAILLE];
		Dalle dalleStart = new Dalle();
		this.parterre[CENTER][CENTER] = dalleStart;
	}
	
	public boolean ajouterDalle(Dalle dalle, int x, int y)
	{
		if(!(this.parterre[x][y] == null)) {return false;}
		if(!aUneDalleAdjacente(x,y)){return false;}
		
		this.parterre[x][y] = dalle;
		ajouterAdjacents(dalle, x,y);
		
		
		return true;
	}
	
	/** Fonction detectant si il y a une dalle adjacente a la position a laquelle nous voullons mettre une dalle
	 * @param x - <b> int </b> - ligne du tableau de dalle
	 * @param y - <b> int </b> - Colone du tableau de dalle */
	private boolean aUneDalleAdjacente(int x, int y)
	{
		int nordX,sudX,nordOuestX,sudOuestX,nordEstX,sudEstX;
		int nordY,sudY,nordOuestY,sudOuestY,nordEstY,sudEstY;
		
		boolean bNordOk       = true;
		boolean bSudOk        = true;
		boolean bnordOuestOk  = true;
		boolean bNordEstOk    = true;
		boolean bsudEstOk     = true;
		boolean bsudOuestOk   = true;
		
		nordX = x    ;
		nordY = y -1 ;
		
		nordEstX = x + 1 ;
		nordEstY = y     ;
		
		sudEstX = x + 1 ;
		sudEstY = y + 1 ;
		
		sudX = x     ;
		sudY = y + 1 ;
		
		sudOuestX = x - 1 ;
		sudOuestY = y + 1 ;
		
		nordOuestX = x - 1 ;
		nordOuestY = y     ;
		
		if(nordX      < 0 || nordX      > 30 || nordY      < 0 || nordY      > 30 ) {bNordOk      = false;}
		if(nordEstX   < 0 || nordEstX   > 30 || nordEstY   < 0 || nordEstY   > 30 ) {bNordEstOk   = false;}
		if(sudEstX    < 0 || sudEstX    > 30 || sudEstY    < 0 || sudEstY    > 30 ) {bsudEstOk    = false;}
		if(sudX       < 0 || sudX       > 30 || sudY       < 0 || sudY       > 30 ) {bSudOk       = false;}
		if(sudOuestX  < 0 || sudOuestX  > 30 || sudOuestY  < 0 || sudOuestY  > 30 ) {bsudOuestOk  = false;}
		if(nordOuestX < 0 || nordOuestX > 30 || nordOuestY < 0 || nordOuestY > 30 ) {bnordOuestOk = false;}
		
		if(bNordOk)      {if(!(this.parterre[nordX]     [nordY]      == null)) {return true;}}
		if(bNordEstOk)   {if(!(this.parterre[nordEstX]  [nordEstY]   == null)) {return true;}}
		if(bsudEstOk)    {if(!(this.parterre[sudEstX]   [sudEstY]    == null)) {return true;}}
		if(bSudOk)       {if(!(this.parterre[sudX]      [sudY]       == null)) {return true;}}
		if(bsudOuestOk)  {if(!(this.parterre[sudOuestX] [sudOuestY]  == null)) {return true;}}
		if(bnordOuestOk) {if(!(this.parterre[nordOuestX][nordOuestY] == null)) {return true;}}
		
		return false;
	}
	
	private void ajouterAdjacents(Dalle dalle, int x, int y)
	{
		int nordX,sudX,nordOuestX,sudOuestX,nordEstX,sudEstX;
		int nordY,sudY,nordOuestY,sudOuestY,nordEstY,sudEstY;
		
		boolean bNordOk       = true;
		boolean bSudOk        = true;
		boolean bnordOuestOk  = true;
		boolean bNordEstOk    = true;
		boolean bsudEstOk     = true;
		boolean bsudOuestOk   = true;
		
		nordX = x    ;
		nordY = y -1 ;
		
		nordEstX = x + 1 ;
		nordEstY = y     ;
		
		sudEstX = x + 1 ;
		sudEstY = y + 1 ;
		
		sudX = x     ;
		sudY = y + 1 ;
		
		sudOuestX = x - 1 ;
		sudOuestY = y + 1 ;
		
		nordOuestX = x - 1 ;
		nordOuestY = y     ;
		
		if(nordX      < 0 || nordX      > 30 || nordY      < 0 || nordY      > 30 ) {bNordOk      = false;}
		if(nordEstX   < 0 || nordEstX   > 30 || nordEstY   < 0 || nordEstY   > 30 ) {bNordEstOk   = false;}
		if(sudEstX    < 0 || sudEstX    > 30 || sudEstY    < 0 || sudEstY    > 30 ) {bsudEstOk    = false;}
		if(sudX       < 0 || sudX       > 30 || sudY       < 0 || sudY       > 30 ) {bSudOk       = false;}
		if(sudOuestX  < 0 || sudOuestX  > 30 || sudOuestY  < 0 || sudOuestY  > 30 ) {bsudOuestOk  = false;}
		if(nordOuestX < 0 || nordOuestX > 30 || nordOuestY < 0 || nordOuestY > 30 ) {bnordOuestOk = false;}
			
		
		

		
		if(bNordOk)      
		{
			if(!(this.parterre[nordX]     [nordY]      == null)) 
			{
				dalle.ajouterAdjacent(this.parterre[nordX]     [nordY]     , NORD);
				this.parterre[nordX][nordY].ajouterAdjacent(dalle, SUD);
				System.out.println("nord");
			}
		}
		if(bNordEstOk)   
		{
			if(!(this.parterre[nordEstX]  [nordEstY]   == null)) 
			{
				dalle.ajouterAdjacent(this.parterre[nordEstX]  [nordEstY]  , NORD_EST);
				this.parterre[nordEstX][nordEstY].ajouterAdjacent(dalle, SUD_OUEST);
				System.out.println("nordest");
			}
		}
		if(bsudEstOk)    
		{
			if(!(this.parterre[sudEstX]   [sudEstY]    == null)) 
			{
				dalle.ajouterAdjacent(this.parterre[sudEstX]   [sudEstY]   , SUD_EST);
				this.parterre[sudEstX][sudEstY].ajouterAdjacent(dalle, NORD_OUEST);
				System.out.println("sudest");
			}
		}
		if(bSudOk)       
		{
			if(!(this.parterre[sudX]      [sudY]       == null)) 
			{
				dalle.ajouterAdjacent(this.parterre[sudX]      [sudY]      , SUD);
				this.parterre[sudX][sudY].ajouterAdjacent(dalle, NORD);
				System.out.println("sud");
			}
		}
		if(bsudOuestOk)  
		{
			if(!(this.parterre[sudOuestX] [sudOuestY]  == null)) 
			{
				dalle.ajouterAdjacent(this.parterre[sudOuestX] [sudOuestY] , SUD_OUEST);
				this.parterre[sudOuestX][sudOuestY].ajouterAdjacent(dalle, NORD_EST);
				System.out.println("sudouest");
			}
		}
		if(bnordOuestOk) 
		{	
			if(!(this.parterre[nordOuestX][nordOuestY] == null)) 
			{
				dalle.ajouterAdjacent(this.parterre[nordOuestX][nordOuestY], this.NORD_OUEST);
				this.parterre[nordOuestX][nordOuestY].ajouterAdjacent(dalle, SUD_EST);
				System.out.println("nordouest");
			}
		}
		
		
		
		
		
		
		
		
		
	}
	

}