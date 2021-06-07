public class Parterre
{
	private Dalle[] grilleDalles;

	public Parterre()
	{
		this.grilleDalles = new Dalle[16];

		this.initierPlateau();
	}


	public void initierPlateau()
	{
		int coordX = 200;
		int coordY =  50;

		Dalle dalleTmp = new Dalle(coordX, coordY);

		this.grilleDalles[0] = dalleTmp;

		dalleTmp.rajoutDalleAdjacent(4, dalleTmp.getMilieuX()-49, dalleTmp.getMilieuY()+33);

		
	}
}