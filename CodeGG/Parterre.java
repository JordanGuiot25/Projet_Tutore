import java.util.ArrayList;

public class Parterre
{
	private ArrayList<Dalle> grilleDalles;

	public Parterre()
	{
		this.grilleDalles = new ArrayList<Dalle>();

		this.initierPlateau();
	}

	public int getNbDallePlace()
	{
		return this.getDalles().length;
	}

	public Dalle[] getDalles()
	{
		ArrayList<Dalle> arrDalles = new ArrayList<Dalle>();

		for(Dalle d :this.grilleDalles)
		{
			if(d != null)
			{
				arrDalles.add(d);
			}
		}

		return arrDalles.toArray(new Dalle[arrDalles.size()]);
	}


	public void initierPlateau()
	{
		int nbDalle = 1;

		int x = 200;
		int y =  50;

		for(int cpt1 = 0; cpt1 < 7; cpt1++ )
		{
			for ( int cpt2 = 0; cpt2 < nbDalle; cpt2++)
			{
				int xTmp = x;

				this.grilleDalles.add( new Dalle(xTmp,y) );

				xTmp += 49 +49;
			}

			if ( cpt1 < 3)
				x = x - 49;
			else
				x = x + 49;

			y = y + 33; 


			if ( cpt1 < 3)
				nbDalle++;
			else
				nbDalle--;
		}



		/*   Dalle  A      */
		Dalle dalle = this.grilleDalles.get(0);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(1) );
		this.grilleDalles.get(1).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(2) );
		this.grilleDalles.get(2).ajouterAdjacent(5, dalle);

		dalle                   .ajouterAdjacent(3, this.grilleDalles.get(4) );
		this.grilleDalles.get(4).ajouterAdjacent(0, dalle);

		/*   Dalle  B      */
		dalle = this.grilleDalles.get(1);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(3) );
		this.grilleDalles.get(3).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(4) );
		this.grilleDalles.get(4).ajouterAdjacent(5, dalle);

		dalle                   .ajouterAdjacent(3, this.grilleDalles.get(7) );
		this.grilleDalles.get(7).ajouterAdjacent(0, dalle);

		/*   Dalle  C      */
		dalle = this.grilleDalles.get(2);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(4) );
		this.grilleDalles.get(4).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(5) );
		this.grilleDalles.get(5).ajouterAdjacent(5, dalle);

		dalle                   .ajouterAdjacent(3, this.grilleDalles.get(8) );
		this.grilleDalles.get(8).ajouterAdjacent(0, dalle);
		

		/*   Dalle  D      */
		dalle = this.grilleDalles.get(3);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(6) );
		this.grilleDalles.get(6).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(7) );
		this.grilleDalles.get(7).ajouterAdjacent(5, dalle);

		dalle                    .ajouterAdjacent(3, this.grilleDalles.get(10) );
		this.grilleDalles.get(10).ajouterAdjacent(0, dalle);

		/*   Dalle  E      */
		dalle = this.grilleDalles.get(4);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(7) );
		this.grilleDalles.get(7).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(8) );
		this.grilleDalles.get(8).ajouterAdjacent(5, dalle);

		dalle                    .ajouterAdjacent(3, this.grilleDalles.get(11) );
		this.grilleDalles.get(11).ajouterAdjacent(0, dalle);

		/*   Dalle  F      */
		dalle = this.grilleDalles.get(5);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(8) );
		this.grilleDalles.get(8).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(9) );
		this.grilleDalles.get(9).ajouterAdjacent(5, dalle);

		dalle                    .ajouterAdjacent(3, this.grilleDalles.get(12) );
		this.grilleDalles.get(12).ajouterAdjacent(0, dalle);

		/*   Dalle  G      */
		dalle = this.grilleDalles.get(6);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(10) );
		this.grilleDalles.get(10).ajouterAdjacent(5, dalle);

		/*   Dalle  H      */
		dalle = this.grilleDalles.get(7);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(10) );
		this.grilleDalles.get(10).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(11) );
		this.grilleDalles.get(11).ajouterAdjacent(5, dalle);

		dalle                    .ajouterAdjacent(3, this.grilleDalles.get(13) );
		this.grilleDalles.get(13).ajouterAdjacent(0, dalle);

		/*   Dalle  I      */
		dalle = this.grilleDalles.get(8);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(11) );
		this.grilleDalles.get(11).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(12) );
		this.grilleDalles.get(12).ajouterAdjacent(5, dalle);

		dalle                    .ajouterAdjacent(3, this.grilleDalles.get(14) );
		this.grilleDalles.get(14).ajouterAdjacent(0, dalle);

		/*   Dalle  J      */
		dalle = this.grilleDalles.get(9);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(12) );
		this.grilleDalles.get(12).ajouterAdjacent(1, dalle);

		/*   Dalle  K      */
		dalle = this.grilleDalles.get(10);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(13) );
		this.grilleDalles.get(13).ajouterAdjacent(5, dalle);

		/*   Dalle  L      */
		dalle = this.grilleDalles.get(11);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(13) );
		this.grilleDalles.get(13).ajouterAdjacent(1, dalle);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(14) );
		this.grilleDalles.get(14).ajouterAdjacent(5, dalle);

		dalle                    .ajouterAdjacent(3, this.grilleDalles.get(15) );
		this.grilleDalles.get(15).ajouterAdjacent(0, dalle);

		/*   Dalle  M      */
		dalle = this.grilleDalles.get(12);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(14) );
		this.grilleDalles.get(14).ajouterAdjacent(1, dalle);


		/*   Dalle  N      */
		dalle = this.grilleDalles.get(13);

		dalle                   .ajouterAdjacent(2, this.grilleDalles.get(15) );
		this.grilleDalles.get(15).ajouterAdjacent(5, dalle);

		/*   Dalle  O      */
		dalle = this.grilleDalles.get(14);

		dalle                   .ajouterAdjacent(4, this.grilleDalles.get(15) );
		this.grilleDalles.get(15).ajouterAdjacent(1, dalle);

		/*   Dalle  P      */
	}
}