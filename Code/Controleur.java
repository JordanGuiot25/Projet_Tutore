import iut.algo.Clavier;

public class Controleur
{
	private FrameDessin ihm;


	public Controleur()
	{
		this.ihm = new FrameDessin(this);

		for(int cpt = 0; cpt < 6; cpt ++)
		{
			Clavier.lireString();
			this.ihm.ajoutPilier(cpt);	
		}
		
		for(int cpt = 0; cpt < 6; cpt ++)
		{
			Clavier.lireString();
			this.ihm.detruirePillier(cpt);	
		}
	}

	public static void main (String[] a)
	{
		new Controleur();
		
	}
}