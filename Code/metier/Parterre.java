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
		Scenario.getScenario(0);
	}	


	public void finTourJoueur(char nomDalle, int numSommet, char coulJoueur)
	{
		this.verifControle();
		this.verifEnfermement(nomDalle, numSommet, coulJoueur);

		this.victoire();
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

	public void verifEnfermement(char nomDalle, int numSommet, char coulPilier)
	{
		this.listeGroupePilier = new ArrayList<Pilier>();

		for(Dalle dalle : this.grilleDalles)
		{
			Pilier[] tabSommets = dalle.getSommets();
			if( dalle.getNom() == nomDalle)
			{
				Pilier[] tabPilierAdjacent  = this.getVoisin(dalle, numSommet);

				for(int cpt = 0; cpt < tabPilierAdjacent.length; cpt++ )
				{
					if(tabPilierAdjacent[cpt] != null && tabPilierAdjacent[cpt].getCoul() == coulPilier )
					{
						if( !this.listeGroupePilier.contains(tabPilierAdjacent[cpt]) ) 
						{
							this.listeGroupePilier.add(tabPilierAdjacent[cpt]);
							Pilier[] tabVoisin  = this.getVoisin(dalle, cpt);

							if ( this.verifictionDesVoisins( tabVoisin, coulPilier) )
							{
								for(Pilier pilier : this.listeGroupePilier )
								{
									this.detruireLePilier(pilier);
								}
							}
							else
								this.listeGroupePilier.clear();
						}
					}
				}
			}
		}
	



		/*for(Dalle dalle : this.grilleDalles)
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
						else
							this.listeGroupePilier.clear();
					}
						
				}
			}
		}*/
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
				if ( dalle.rajoutPillier(joueur.getCouleur(), numSommet) ) 
				{
					joueur.incrementationNbPilier(-1);
					return true;
				}
				
			}
		}


		return false;
	}

	public void     prochainTour() { this.tour++;}

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