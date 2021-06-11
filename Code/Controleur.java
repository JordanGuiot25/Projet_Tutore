package PilierDeLaTerre;

import PilierDeLaTerre.ihm.gui.FrameAide;
import PilierDeLaTerre.ihm.gui.FrameDessin;
import PilierDeLaTerre.ihm.gui.FrameJoueur;
import PilierDeLaTerre.ihm.gui.FrameMenu;
import PilierDeLaTerre.ihm.gui.FrameScenario;
import PilierDeLaTerre.ihm.cui.IhmCui;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;
import PilierDeLaTerre.metier.Scenario;

import iut.algo.Clavier;

import java.util.ArrayList;

import java.awt.Point;

import java.io.PrintWriter;
import java.io.FileOutputStream;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Joueur;

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

	public void LancerPartieCustom()
	{
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

	public static void main (String[] a)
	{
		new Controleur();
	}
}