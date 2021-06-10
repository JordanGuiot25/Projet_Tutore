package PilierDeLaTerre.metier;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Joueur;

import java.util.ArrayList;
import iut.algo.Clavier;

public class Parterre
{
	private ArrayList<Dalle> grilleDalles;

	private Joueur joueur1;
	private Joueur joueur2;

	private boolean victoireJ1;
	private boolean victoireJ2;

	private int     tour;

	private ArrayList<Pilier> listeGroupePilier;

	public Parterre(ArrayList<Dalle> grille, Joueur joueur1, Joueur joueur2, int tour)
	{
		this.grilleDalles = grille;
		this.joueur1      = joueur1;
		this.joueur2      = joueur2;
		this.tour         = tour;
	}

	public Parterre()
	{
		this.grilleDalles = new ArrayList<Dalle>();
		this.joueur1      = new Joueur(1, 'G');
		this.joueur2      = new Joueur(2, 'M');

		this.initierPlateau();
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

	public void verifControle()
	{
		for(Dalle dalle : this.grilleDalles)
		{
			if( dalle != null)
			{
				Joueur joueurProprietaire = dalle.verifierProprietaireDalle(this.joueur1, this.joueur2);
				
				if( joueurProprietaire != null )
					joueurProprietaire.ajouterDalles(dalle);
			}
		}
	}

	public void verifEnfermement(char coulPilier)
	{
		this.listeGroupePilier = new ArrayList<Pilier>();


		for(Dalle dalle : this.grilleDalles)
		{
			Pilier[] tabSommets = dalle.getSommets();
			for(int cpt = 0; cpt < tabSommets.length; cpt++ )
			{
				if( tabSommets[cpt] != null && tabSommets[cpt].getCoul() == coulPilier)
				{
					if( !this.listeGroupePilier.contains(tabSommets[cpt]) ) 
					{
						this.listeGroupePilier.add(tabSommets[cpt]);

						Pilier[] tabVoisin  = this.getVoisin(dalle, cpt);

						if ( this.verifictionDesVoisins( tabVoisin, coulPilier) )
						{
							for(Pilier pilier : this.listeGroupePilier )
							{
								this.detruireLePilier(pilier);
							}
						}
					}
						
				}
			}
		}
	}

	private boolean  verifictionDesVoisins(Pilier[] tabVoisin, char couleur)
	{
		for(Pilier voisin : tabVoisin )
		{
			if( voisin == null )
				return false;

			if( voisin.getCoul() == couleur )
			{
				if( !this.listeGroupePilier.contains(voisin) )
				{
					this.listeGroupePilier.add(voisin);

					if( !this.parcourVoisin(voisin, couleur) )
					{
						return false;
					}
				}
			}
		}

		return true;
	}

	private boolean parcourVoisin(Pilier voisin, char couleur)
	{
		Dalle dalleDuVoisin = null;
		int   numDuVoisin   = 0;

		// Trouve le pilier sur la grille
		for(Dalle dalle : this.grilleDalles )
		{
			Pilier[] tabSommets = dalle.getSommets();
			for(int cpt = 0; cpt < tabSommets.length; cpt++ )
			{
				if( tabSommets[cpt] == voisin )
				{
					dalleDuVoisin = dalle;
					numDuVoisin   = cpt;
				}
			}
		}

		Pilier[] tabVoisin = this.getVoisin(dalleDuVoisin, numDuVoisin);

		return this.verifictionDesVoisins(tabVoisin, couleur);
	}

	private Pilier[] getVoisin(Dalle dalle, int numSommet)
	{
		int nbVoisin = 0;
		Pilier[] tabSommets = dalle.getSommets();

		// Verification d'un troisième voisin
		int numCoteAdjacentPrc = numSommet -1;
		if ( numCoteAdjacentPrc < 0 )
			numCoteAdjacentPrc = 5;

		int numCoteAdjacent    = numSommet;

		if ( dalle.getDalleAdjacent(numCoteAdjacentPrc) == null && dalle.getDalleAdjacent(numCoteAdjacent) == null)
			nbVoisin = 2;
		else
			nbVoisin = 3;

		// Création de la table voisin
		Pilier[] voisin     = new Pilier[nbVoisin];
		
		voisin[0] = dalle.getPrecedent(tabSommets[numSommet]);
		voisin[1] = dalle.getSuivant  (tabSommets[numSommet]);

		// Si le tableau contient trois voisins
		if( voisin.length == 3 )
		{
			if ( dalle.getDalleAdjacent( numCoteAdjacentPrc ) != null )
			{
				int numPilierVoisin = numSommet+1;
				if( numPilierVoisin > 5 )
					numPilierVoisin = 0;

				voisin[2] = dalle.getDalleAdjacent(numCoteAdjacentPrc).getPilier(numPilierVoisin);
			}
			else if ( dalle.getDalleAdjacent( numCoteAdjacent ) != null )
			{
				int numPilierVoisin = numSommet-1;
				if( numPilierVoisin < 0 )
					numPilierVoisin = 5;
				voisin[2] = dalle.getDalleAdjacent(numCoteAdjacent).getPilier(numPilierVoisin);
			}
		}

		System.out.println("\t\t ListeVoisin de dalle " + dalle.getNom() + " " + numSommet +" :");
		for(Pilier p : voisin )
		{
			if( p != null )
				System.out.println("\t\t" + p.getCoul() );
			else
				System.out.println("\t\t null");
		}
		
		return voisin;
	}
	

	private void detruireLePilier(Pilier pilier)
	{
		for(Dalle dalleTmp : this.grilleDalles )
		{
			Pilier[] tabSommets = dalleTmp.getSommets();
			for(int cpt = 0; cpt < tabSommets.length; cpt++)
			{
				if ( tabSommets[cpt] == pilier )
				{
					dalleTmp.detruirePillier(cpt);
				}
			}
		}
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

	public void finDeTour()
	{
		this.verifControle();
		System.out.println("-----------verif de M ------------");
		this.verifEnfermement('M');
		System.out.println("-----------verif de G ------------");
		this.verifEnfermement('G');

		this.victoire();
		this.tour++;
	}

	public boolean poserPilier(int numJoueur, char nomDalle, int numSommet )
	{
		Joueur joueur = null;

		if( numJoueur == 1 )
			joueur = this.joueur1;
		else
			joueur = this.joueur2;

		for(Dalle dalle : this.grilleDalles)
		{
			if ( dalle.getNom() == nomDalle )
			{
				dalle.rajoutPillier(joueur.getCouleur(), numSommet);
				joueur.incrementationNbPilier(-1);
			}
		}


		return false;
	}

	

	private boolean getVictoireJ1() {return this.victoireJ1;}
	private boolean getVictoireJ2() {return this.victoireJ2;}

	public ArrayList<Dalle> getGrilleDalles() { return this.grilleDalles; }

	public String getSauvegarde()
    {
		// Sauvegarde le plateau //
        String sRep ="//Plateau\n"+this.grilleDalles.get(0).getX() +'\t'+ this.grilleDalles.get(0).getY() +'\n';
		ArrayList<Dalle> arrDalleDejaUtil = new ArrayList<Dalle>();
        for(Dalle dSource: this.grilleDalles)
        {
            if(dSource != null)
            {
                int cpt =0;
                for(Dalle dDestination : dSource.getListeDallesAdjacent())
                {
                    if(dDestination != null && arrDalleDejaUtil.indexOf(dDestination) ==-1)
                    {
						sRep += dSource.getNom() +""+ dDestination.getNom() + cpt+"\n";
                    }
                    cpt++;
                }
            }
			arrDalleDejaUtil.add(dSource);
        }

		//Sauvegarde Les Pilliers//
		sRep +="//Pilier\n";
		for(Dalle dTemp: this.grilleDalles)
        {
            if(dTemp != null)
            {
				int cpt = 0;
				for(Pilier p : dTemp.getSommets())
				{
					if(p != null)
					{
						sRep += dTemp.getNom()+ "" + p.getCoul() +cpt+'\n';
					}
					cpt++;
				}
            }
        }

		//Sauvegarde les scores
		//Jx\tnbPilier\tnbPilierDetruit
		sRep +="//Score\n";
		sRep += "J" + this.joueur1.getNumJoueur() + "\t" + this.joueur1.getNbPilier() + "\t" +this.joueur1.getNbPilierDetruis()+'\n';
		for(Dalle dallePosseder : this.joueur1.getListeDalles())
		{
			if( dallePosseder != null )
				sRep += "J1" + dallePosseder.getNom() + "\n";
		}

		sRep += "J" + this.joueur2.getNumJoueur() + "\t" + this.joueur2.getNbPilier() + "\t" +this.joueur2.getNbPilierDetruis()+'\n';
		for(Dalle dallePosseder : this.joueur1.getListeDalles())
		{
			if( dallePosseder != null )
				sRep += "J2" + dallePosseder.getNom() + "\n";
		}

		//Sauvegarde les tours
		sRep +="//Tour\n";
		sRep += ""+(24 - this.joueur1.getNbPilier());
		
		return sRep;
    }

	public  Joueur  getJoueur(int numJoueur)
	{
		if ( numJoueur == 1 )
			return this.joueur1;
		else
			return this.joueur2;
	}

	public  Joueur getGagnant()
	{
		if( this.victoireJ1 )
			return this.joueur1;
		
		if( this.victoireJ2 )
			return this.joueur2;

		return null;
	}

	public int getTour()
	{
		return this.tour;
	}

	
}