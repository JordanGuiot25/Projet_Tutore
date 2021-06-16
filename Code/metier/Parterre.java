package Equipe_22.metier;

import Equipe_22.metier.Dalle;
import Equipe_22.metier.Joueur;

import java.util.ArrayList;
import iut.algo.Clavier;

/** Classe Parterre
 * @author  Raphaël Lizot, Jordan Guiot, Enguerrand Beltran, Gaspard Gordien
 */
public class Parterre
{
	/** GrilleDalle représentant le terrain de jeux
	 * @see {@link Dalle}
	 */
	private ArrayList<Dalle> grilleDalles;

	/** Variable représentant le premier joueur
	 * @see {@link Joueur}
	 */
	private Joueur joueur1;

	/** Variable représentant le deuxième joueur
	 * @see {@link Joueur}
	 */
	private Joueur joueur2;

	/** Variable représentant la Victoire du J1
	 * @see {@link Boolean}
	 */
	private boolean victoireJ1;

	/** Variable représentant la Victoire du J2
	 * @see {@link Boolean}
	 */
	private boolean victoireJ2;

	/** Variable représentant le nombre de tour 
	 * @see {@link Int}
	 */
	private int     tour;

	/** Variable servant pour la méthode enfermemant
	 * @see {@link Pilier}
	 */
	private ArrayList<Pilier> listeGroupePilier;

	/** Constructeur Parterre
	 * @param grille {@link Dalle}
	 * @param joueur1 {@link Joueur}
	 * @param joueur2 {@link Joueur}
	 * @param tour 	  {@link tour}
	*/
	public Parterre(ArrayList<Dalle> grille, Joueur joueur1, Joueur joueur2, int tour)
	{
		this.grilleDalles = grille;
		this.joueur1      = joueur1;
		this.joueur2      = joueur2;
		this.tour         = tour;
	}
	/** Constructeur Parterre*/
	public Parterre()
	{
		Scenario.getScenario(0);
	}	

	/**
	 * @param nomDalle {@link Char}
	 * @param numSommet {@link Int}
	 * @param coulJoueur {@link Char}
	 */
	public void finTourJoueur(char nomDalle, int numSommet, char coulJoueur)
	{
		this.verifEnfermement(nomDalle, numSommet, coulJoueur);
		this.verifControle();

		this.victoire();
	}

	/**Méthode de vérification du controle */
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
	/**
	 * 
	 * @param numJoueur {@link Int}
	 */
	public void setNumJoueur(int numJoueur )
	{
		for(Dalle dalle : this.grilleDalles )
		{
			dalle.setNumJoueur(numJoueur);
		}
	}

	/**
	 * 
	 * @param nomDalle {@link Char}
	 * @param numSommet {@link Int}
	 * @param coulPilier {@link Char}
	 */
	public void verifEnfermement(char nomDalle, int numSommet, char coulPilier)
	{
		this.listeGroupePilier = new ArrayList<Pilier>();

		Dalle dalle         = this.getDalleGrille(nomDalle);
		Pilier[] tabVoisin  = this.getVoisin(dalle, numSommet);

		for(int cpt = 0; cpt < tabVoisin.length; cpt++ )
		{
			this.listeGroupePilier.clear();
			if( tabVoisin[cpt] != null && tabVoisin[cpt].getCoul() != coulPilier)
			{

				
				this.listeGroupePilier.add(tabVoisin[cpt]);

				Dalle dalleVoisin = this.getDallePilier(tabVoisin[cpt]);
				

				Pilier[] tabPilierDuVoisin = this.getVoisin(dalleVoisin, dalleVoisin.getIndicePilier(tabVoisin[cpt]));

				if ( this.verificationDesVoisins( tabPilierDuVoisin, tabVoisin[cpt].getCoul()) )
				{
					for(Pilier pilier : this.listeGroupePilier )
					{
						this.detruireLePilier(pilier);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param tabVoisin {@link Pillier}
	 * @param couleur {@link Char}
	 * @return {@link Boolean}
	 */
	private boolean  verificationDesVoisins(Pilier[] tabVoisin, char couleur)
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

	/**
	 * 
	 * @param voisin {@link Pilier}
	 * @param couleur {@link Char}
	 * @return {@link Boolean}
	 */
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

		return this.verificationDesVoisins(tabVoisin, couleur);
	}

	/**
	 * 
	 * @param nomDalle {@link Char}
	 * @return {@link Dalle}
	 */
	private Dalle   getDalleGrille(char   nomDalle)
	{
		for(Dalle dalle : this.grilleDalles)
		{
			if ( dalle.getNom() == nomDalle )
				return dalle;
		}

		return null;
	}

	/**
	 * 
	 * @param pilier {@link Pillier}
	 * @return {@link Dalle}
	 */
	private Dalle   getDallePilier(Pilier pilier)
	{
		for (Dalle dalle : this.grilleDalles )
		{
			for(int cpt = 0; cpt < 6; cpt++ )
			{
				Pilier pilierTmp = dalle.getPilier(cpt);

				if( pilierTmp != null && pilierTmp == pilier )
					return dalle;
			}
		}

		return null;
	}

	/**
	 * 
	 * @param dalle {@link Dalle}
	 * @param numSommet {@link Int}
	 * @return {@link Pilier}
	 */
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
		
		return voisin;
	}
	
	/**
	 * 
	 * @param pilier {@link Pilier}
	 */
	private void detruireLePilier(Pilier pilier)
	{
		for(Dalle dalleTmp : this.grilleDalles )
		{
			Pilier[] tabSommets = dalleTmp.getSommets();
			for(int cpt = 0; cpt < tabSommets.length; cpt++)
			{
				if ( tabSommets[cpt] == pilier )
				{
					dalleTmp.detruirePilier(cpt);
				}
			}
		}
	}

	/**Méthode permettant de vérifier si il y a victoire */
	public void victoire()
	{
		if(this.joueur1.getListeDalles().size() >= 9) { this.victoireJ1 = true;}

		if(this.joueur2.getListeDalles().size() >= 9) { this.victoireJ2 = true;}

		if(this.joueur1.getNbPilier() == 0 && this.joueur2.getNbPilier() == 0)
		{
			if(this.joueur1.getListeDalles().size() > this.joueur2.getListeDalles().size()&& this.victoireJ1 == false && this.victoireJ2 == false) { this.victoireJ1 = true;}
			if(this.joueur1.getListeDalles().size() < this.joueur2.getListeDalles().size()&& this.victoireJ1 == false && this.victoireJ2 == false) { this.victoireJ2 = true;}

			if(this.joueur1.getNbPilierDetruis()    > this.joueur2.getNbPilierDetruis()   && this.victoireJ1 == false && this.victoireJ2 == false) {this.victoireJ1 = true ;}
			if(this.joueur1.getNbPilierDetruis()    < this.joueur2.getNbPilierDetruis()   && this.victoireJ1 == false && this.victoireJ2 == false) {this.victoireJ2 = true ;}
		}
	}

	/**
	 * méthode permettant de poser un pilier
	 * @param numJoueur
	 * @param nomDalle
	 * @param numSommet
	 * @return {@link Boolean}
	 */
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
				if ( dalle.rajoutPilier(joueur.getCouleur(), numSommet) ) 
				{
					joueur.incrementationNbPilier(-1);
					return true;
				}
				
			}
		}


		return false;
	}

	/**Méthode permettant d'incrémenter la variable tour */
	public void     prochainTour() { this.tour++;}

	/**
	 * Méthode permettant de retourner le booléen de la victoire J1
	 * @return {@link Boolean}
	 */
	private boolean getVictoireJ1() {return this.victoireJ1;}

	/**
	 * Méthode permettant de retourner le booléen de la victoire J2
	 * @return {@link Boolean}
	 */
	private boolean getVictoireJ2() {return this.victoireJ2;}

	/**
	 * retourne la grilleDalles
	 * @return {@link Dalle}
	 */

	public ArrayList<Dalle> getGrilleDalles() { return this.grilleDalles; }


	//méhode pas utilisé
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

	/**
	 * retourne le joueur
	 * @param numJoueur
	 * @return {@link Joueur}
	 */
	public  Joueur  getJoueur(int numJoueur)
	{
		if ( numJoueur == 1 )
			return this.joueur1;
		else
			return this.joueur2;
	}

	/**
	 * retourne le gagnant
	 * @return	{@link Joueur}
	 */
	public  Joueur getGagnant()
	{
		if( this.victoireJ1 )
			return this.joueur1;
		
		if( this.victoireJ2 )
			return this.joueur2;

		return null;
	}

	/**
	 * retourne le nombre de Tour
	 * @return {@link Int}
	 */
	public int getTour()
	{
		return this.tour;
	}

	
}