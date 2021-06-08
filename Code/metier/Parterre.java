package PilierDeLaTerre.metier;

import PilierDeLaTerre.metier.Dalle;

import java.util.ArrayList;
import iut.algo.Clavier;

public class Parterre
{
	private ArrayList<Dalle> grilleDalles;

	private Joueur joueur1;
	private Joueur joueur2;

	private boolean victoireJ1;
	private boolean victoireJ2;

	public Parterre()
	{
		this.grilleDalles = new ArrayList<Dalle>();
		this.joueur1      = new Joueur(1, 'G');
		this.joueur2      = new Joueur(2, 'M');

		this.initierPlateau();

		while ( !(this.joueur1.getNbPilier() <= 0) && !(this.joueur2.getNbPilier() <= 0) )
		{
			for(int cpt = 0; cpt < 2; cpt++)
			{
				System.out.println("Tour joueur" + cpt);


			}
		}

	}


	public void initierPlateau()
	{
		int nbDalle = 1;

		int x = 200;
		int y =  50;

		for(int cpt1 = 0; cpt1 < 7; cpt1++ )
		{
			int xTmp = x;
			for ( int cpt2 = 0; cpt2 < nbDalle; cpt2++)
			{
				this.grilleDalles.add( new Dalle(xTmp,y) );
				xTmp = xTmp + 49 +49;
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

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(1) );
		this.grilleDalles.get(1).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(2) );
		this.grilleDalles.get(2).rajoutDalleAdjacent(5, dalle);

		dalle                   .rajoutDalleAdjacent(3, this.grilleDalles.get(4) );
		this.grilleDalles.get(4).rajoutDalleAdjacent(0, dalle);

		/*   Dalle  B      */
		dalle = this.grilleDalles.get(1);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(3) );
		this.grilleDalles.get(3).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(4) );
		this.grilleDalles.get(4).rajoutDalleAdjacent(5, dalle);

		dalle                   .rajoutDalleAdjacent(3, this.grilleDalles.get(7) );
		this.grilleDalles.get(7).rajoutDalleAdjacent(0, dalle);

		/*   Dalle  C      */
		dalle = this.grilleDalles.get(2);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(4) );
		this.grilleDalles.get(4).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(5) );
		this.grilleDalles.get(5).rajoutDalleAdjacent(5, dalle);

		dalle                   .rajoutDalleAdjacent(3, this.grilleDalles.get(8) );
		this.grilleDalles.get(8).rajoutDalleAdjacent(0, dalle);
		

		/*   Dalle  D      */
		dalle = this.grilleDalles.get(3);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(6) );
		this.grilleDalles.get(6).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(7) );
		this.grilleDalles.get(7).rajoutDalleAdjacent(5, dalle);

		dalle                    .rajoutDalleAdjacent(3, this.grilleDalles.get(10) );
		this.grilleDalles.get(10).rajoutDalleAdjacent(0, dalle);

		/*   Dalle  E      */
		dalle = this.grilleDalles.get(4);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(7) );
		this.grilleDalles.get(7).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(8) );
		this.grilleDalles.get(8).rajoutDalleAdjacent(5, dalle);

		dalle                    .rajoutDalleAdjacent(3, this.grilleDalles.get(11) );
		this.grilleDalles.get(11).rajoutDalleAdjacent(0, dalle);

		/*   Dalle  F      */
		dalle = this.grilleDalles.get(5);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(8) );
		this.grilleDalles.get(8).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(9) );
		this.grilleDalles.get(9).rajoutDalleAdjacent(5, dalle);

		dalle                    .rajoutDalleAdjacent(3, this.grilleDalles.get(12) );
		this.grilleDalles.get(12).rajoutDalleAdjacent(0, dalle);

		/*   Dalle  G      */
		dalle = this.grilleDalles.get(6);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(10) );
		this.grilleDalles.get(10).rajoutDalleAdjacent(5, dalle);

		/*   Dalle  H      */
		dalle = this.grilleDalles.get(7);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(10) );
		this.grilleDalles.get(10).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(11) );
		this.grilleDalles.get(11).rajoutDalleAdjacent(5, dalle);

		dalle                    .rajoutDalleAdjacent(3, this.grilleDalles.get(13) );
		this.grilleDalles.get(13).rajoutDalleAdjacent(0, dalle);

		/*   Dalle  I      */
		dalle = this.grilleDalles.get(8);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(11) );
		this.grilleDalles.get(11).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(12) );
		this.grilleDalles.get(12).rajoutDalleAdjacent(5, dalle);

		dalle                    .rajoutDalleAdjacent(3, this.grilleDalles.get(14) );
		this.grilleDalles.get(14).rajoutDalleAdjacent(0, dalle);

		/*   Dalle  J      */
		dalle = this.grilleDalles.get(9);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(12) );
		this.grilleDalles.get(12).rajoutDalleAdjacent(1, dalle);

		/*   Dalle  K      */
		dalle = this.grilleDalles.get(10);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(13) );
		this.grilleDalles.get(13).rajoutDalleAdjacent(5, dalle);

		/*   Dalle  L      */
		dalle = this.grilleDalles.get(11);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(13) );
		this.grilleDalles.get(13).rajoutDalleAdjacent(1, dalle);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(14) );
		this.grilleDalles.get(14).rajoutDalleAdjacent(5, dalle);

		dalle                    .rajoutDalleAdjacent(3, this.grilleDalles.get(15) );
		this.grilleDalles.get(15).rajoutDalleAdjacent(0, dalle);

		/*   Dalle  M      */
		dalle = this.grilleDalles.get(12);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(14) );
		this.grilleDalles.get(14).rajoutDalleAdjacent(1, dalle);


		/*   Dalle  N      */
		dalle = this.grilleDalles.get(13);

		dalle                   .rajoutDalleAdjacent(2, this.grilleDalles.get(15) );
		this.grilleDalles.get(15).rajoutDalleAdjacent(5, dalle);

		/*   Dalle  O      */
		dalle = this.grilleDalles.get(14);

		dalle                   .rajoutDalleAdjacent(4, this.grilleDalles.get(15) );
		this.grilleDalles.get(15).rajoutDalleAdjacent(1, dalle);

		/*   Dalle  P      */
	}

	public void victoire()
	{
		if(this.joueur1.getListeDalles().size() == 9) { this.victoireJ1 = true;}

		if(this.joueur2.getListeDalles().size() == 9) { this.victoireJ2 = true;}

		if(this.joueur1.getNbPilier() == 0 && this.joueur2.getNbPilier() == 0)
		{
			if(this.joueur1.getListeDalles().size() > this.joueur2.getListeDalles().size()&& this.victoireJ1 == false && this.victoireJ2 == false) { this.victoireJ1 = true;}
			if(this.joueur1.getListeDalles().size() < this.joueur2.getListeDalles().size()&& this.victoireJ1 == false && this.victoireJ2 == false) { this.victoireJ2 = true;}

			if(this.joueur1.getNbPilierDetruis()    > this.joueur2.getNbPilierDetruis()   && this.victoireJ1 == false && this.victoireJ2 == false) {this.victoireJ1 = true ;}
			if(this.joueur1.getNbPilierDetruis()    < this.joueur2.getNbPilierDetruis()   && this.victoireJ1 == false && this.victoireJ2 == false) {this.victoireJ2 = true ;}

			
		}

		 
	}

	public boolean getVictoireJ1() {return this.victoireJ1;}
	public boolean getVictoireJ2() {return this.victoireJ2;}





	public ArrayList<Dalle> getDalles() { return this.grilleDalles; }
}