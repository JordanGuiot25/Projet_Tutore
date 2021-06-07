public class Pilier
{
	//private Joueur joueur
	private Dalle[] dalles;
	
	public Pilier()
	{
		
	}
	
	
	public boolean placerPilier(Dalle dalle, int pos)
	{
		if(!dalle.emplacementVide(pos)) {return false;}
		dalle.poserPilier(this, pos);
		
		return true;
	}
}