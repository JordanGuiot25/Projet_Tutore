package Equipe_22;

import Equipe_22.ihm.gui.FrameDessin;
import Equipe_22.ihm.gui.FrameJoueur;
import Equipe_22.ihm.gui.FrameMenu;
import Equipe_22.ihm.gui.FrameScenario;
import Equipe_22.ihm.gui.FrameControleEditeur;
import Equipe_22.ihm.gui.FrameEditeurParterre;
import Equipe_22.ihm.cui.IhmCui;
import Equipe_22.metier.Dalle;
import Equipe_22.metier.Parterre;
import Equipe_22.metier.Scenario;
import Equipe_22.metier.EditeurParterre;

import iut.algo.Clavier;

import java.util.ArrayList;

import java.awt.Point;

import java.io.PrintWriter;
import java.io.FileOutputStream;

import Equipe_22.metier.Dalle;
import Equipe_22.metier.Joueur;

public class Controleur
{
	private FrameDessin ihm    ;
	private FrameMenu   ihmMenu;
	private FrameJoueur ihmJoueur;
	private FrameScenario ihmScenario;
	private IhmCui      ihmCui;
	private Parterre    metier ;
	private boolean 	bDebutPartie;
	private int         numScenario;
	private EditeurParterre editeurParterre;
	private FrameEditeurParterre frameEditeurParterre;
	private FrameControleEditeur frameControleEditeur;

	public Controleur()
	{
		this.ihmMenu  = new FrameMenu   (this);
		this.ihm      = new FrameDessin (this);
		this.ihmJoueur= new FrameJoueur (this);
		this.ihmScenario = new FrameScenario(this);
		

		this.bDebutPartie = false;
		while( !this.bDebutPartie )
		{
			System.out.print("");
		}
		this.ihmJoueur.changerJoueur(this.metier.getJoueur(1));
		this.ihmCui   = new IhmCui (this.metier);

	}

	public void verificationVictoire()
	{
		if( this.metier.getGagnant() != null )
		{	
			System.out.println("FIN DE LA PARTIE");
			System.out.println("Le gagnant est le joueur :" + this.metier.getGagnant().getNumJoueur() );
			this.ihmJoueur.setVisible(false);
		}
	}

	public boolean poserPilier(int numJoueur, char nomDalle, int numSommet )
	{
		if ( this.metier.poserPilier(numJoueur,nomDalle,numSommet) )
		{

			this.metier.setNumJoueur(numJoueur);

			this.ihm.miseAJourGrille();
			this.metier.finTourJoueur(nomDalle, numSommet, this.metier.getJoueur(numJoueur).getCouleur());
			this.ihm.miseAJourGrille();

			System.out.println(this.ihmCui);

			this.verificationVictoire();

			return true;
		}

		return false;
	}

	public void changementJoueur(Joueur joueurPrc )
	{
		if( joueurPrc == this.metier.getJoueur(1) )
		{
			this.ihmJoueur.changerJoueur( this.metier.getJoueur(2) );
		}
		else
		{
			this.ihmJoueur.changerJoueur( this.metier.getJoueur(1) );
			this.metier.prochainTour();
		}
			
	}

	public void maj()
	{
		this.ihm.miseAJourGrille();
	}

	public void Sauvegarde()
	{
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("../Scenario/Sauvegarde.txt") );
			pw.println ( this.metier.getSauvegarde());
			pw.close();
		}
		catch(Exception e){ e.printStackTrace(); }
	}

	public void MAJ()
	{
		this.ihm.miseAJourGrille();
	}

	public void majLocation(char Destination)
	{
		if( Destination == 'P' && this.ihm != null & this.ihmJoueur != null)
		{
			Point p = this.ihmJoueur.getLocation();
			this.ihm.setLocation(p.x-1000, p.y);
		}

		if( Destination == 'A' && this.ihm != null & this.ihmJoueur != null)
		{
			Point p = this.ihm.getLocation();
			this.ihmJoueur.setLocation (p.x+1000, p.y);
		}
	}

	public void LancerPartieRapide()
	{
		this.ihmMenu.setVisible(false);

		this.metier = Scenario.getScenario(0);

		this.ihm    .setVisible(true);
		this.ihmJoueur.setVisible(true);

		this.bDebutPartie = true;

	}

	public void lancerPartieCustom()
	{
		this.metier = new Parterre(this.editeurParterre.getNiveau(), new Joueur(1,'M'), new Joueur(2,'G'), 1);
		this.frameControleEditeur.setVisible(false);
		this.frameEditeurParterre.setVisible(false);
		this.ihm.setVisible(true);
		this.ihmJoueur.setVisible(true);
		this.bDebutPartie = true;	
		
	}

	public void partieCustom()
	{
		
		this.editeurParterre = new EditeurParterre(this);
		this.ihmMenu.setVisible(false);
		this.frameEditeurParterre = new FrameEditeurParterre(this);
		this.frameEditeurParterre.setLocation(500, 100);
		this.frameControleEditeur = new FrameControleEditeur(this);
		this.frameControleEditeur.setLocation(500,100+this.frameEditeurParterre.getSize().height);
		
	}
	
	public void retour(char choixIhm)
	{
		if(choixIhm == 'e')
		{	
			this.editeurParterre.retour();
			this.frameControleEditeur.setVisible(false);
			this.frameEditeurParterre.setVisible(false);
		}
		else
		{
			this.ihmScenario.setVisible(false);
		}
		this.ihmMenu.setVisible(true);
	}

	public void quitter()
	{
		System.exit(0);
	}

	public void setScenario(int num)
	{
		this.numScenario = num;
	}
	public void lancerScenario()
	{
		this.ihmScenario.setVisible(false);
		this.metier = Scenario.getScenario(this.numScenario);
		this.ihm    .setVisible(true);
		this.ihmJoueur.setVisible(true);
		this.bDebutPartie = true;

	}

	public void scenario()
	{
		this.ihmMenu.setVisible(false);
		this.ihmScenario.setVisible(true);
	}
	
	public ArrayList<Dalle> getGrilleDalles() 
	{
		return this.metier.getGrilleDalles();
	}

	private boolean getBDebutPartie() { return this.bDebutPartie;}
	
	public void DeplacerFrames(double posX, double posY, char frame)
	{
		if(frame == 'c' && this.frameEditeurParterre != null && this.frameControleEditeur != null)
		{
			frameEditeurParterre.setLocation((int) posX, (int) posY);
		}
		if(frame == 'e' && this.frameEditeurParterre != null && this.frameControleEditeur != null)
		{
			frameControleEditeur.setLocation( (int) posX , (int) posY);
		}
	}
	
	public char getLastDalle() {return this.editeurParterre.getLastDalle();}
	public Dalle getDalle(int i, int y) {return this.editeurParterre.getDalle(i, y);}
	public void setCoord(Point p, int coordx, int coordy) {this.editeurParterre.setCoord(p, coordx, coordy);}
	public boolean aUneDalleAdjacente(int x, int y) {return this.editeurParterre.aUneDalleAdjacente(x, y);}
	public boolean emplacementVide(int x,int y) {return this.editeurParterre.emplacementVide(x, y);}
	public void addCoord(Point point) {this.editeurParterre.addCoord(point);}
	public boolean ajouterDalle(Dalle dalle, int x, int y) {return this.editeurParterre.ajouterDalle(dalle, x, y);}
	public Point getLastCoord() {return this.editeurParterre.getLastCoord();}
	public int getCoordSize() {return this.editeurParterre.getCoordSize();}
	public void supprimerDalle( int x, int y) {this.editeurParterre.supprimerDalle(x, y);}
	public void setJoueur(boolean joueur) {this.frameControleEditeur.setJoueur(joueur);}
	
	
	
	public static void main (String[] a)
	{
		new Controleur();
	}
}