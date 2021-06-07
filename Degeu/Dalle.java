import java.util.ArrayList;

public class Dalle
{
	private Dalle[] dalleAdjacentes = new Dalle [6];
	/*
     	  	0
		  _____
       5 /     \  1
    	/       \
      4 \       / 2
    	 \_____/
    	    3
    	    	*/
	private final String[] POS    = {"Nord","Nord_est","Sud_est","Sud","Sud_ouest","Nord_ouest"};
	private Pilier[] piliers         = new Pilier[6];
	private static char nbDalle       = 'A';
	private char nom ;
	
	
	public Dalle()
	{
		this.nom = nbDalle ++ ;
	}
	
	
	public boolean ajouterAdjacent(Dalle dalle, int cote)
	{
		if(!(this.dalleAdjacentes[cote]==null)) {return false;}
		this.dalleAdjacentes[cote] = dalle;
		return true;
	}
	
	public char   getNom() {return this.nom;}
	public char   getNomAdjacent(int cote) {return this.dalleAdjacentes[cote].getNom();}
	public char[] getNomAllAdajcent() 
	{
		char[] allAdjacent = new char[6];
		for(int i = 0; i < this.dalleAdjacentes.length; i++)
		{
			if(!(this.dalleAdjacentes[i] == null)) {allAdjacent[i] = this.dalleAdjacentes[i].getNom();}
		}
		return allAdjacent;
	}
	
	public Dalle[] getAllDalleAdjacentes()	{return this.dalleAdjacentes;}
	public Dalle getDalleAdjacent(int cote) {return this.dalleAdjacentes[cote];}
	
	
	public String[] getNomPosAllAdajcent() 
	{
		String[] allAdjacent = new String[6];
		for(int i = 0; i < this.dalleAdjacentes.length; i++)
		{
			if(!(this.dalleAdjacentes[i] == null)) {allAdjacent[i] = ""+this.dalleAdjacentes[i].getNom() + " - " + this.POS[i];}
		}
		return allAdjacent;
	}
	
	public boolean emplacementVide(int pos)
	{
		if(!(this.piliers[pos] == null)) {return true;}
		return false;
	}
	
	public void poserPilier(Pilier p, int pos)
	{
		this.piliers[pos] = p;
	}
	
}