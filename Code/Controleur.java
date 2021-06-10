package PilierDeLaTerre;

import PilierDeLaTerre.ihm.gui.FrameAide;
import PilierDeLaTerre.ihm.gui.FrameDessin;
import PilierDeLaTerre.ihm.gui.FrameMenu;
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
	private FrameAide   ihmAide;
	private Parterre    metier ;
	private boolean 	bDebutPartie;

	public Controleur()
	{
		this.bDebutPartie = false;
		this.ihmMenu= new FrameMenu(this);
		this.ihm    = new FrameDessin(this);
		this.ihmAide= new FrameAide  (this);

		while( this.metier.getGagnant() == null )
		{
			System.out.println("-------------------------------");
			System.out.println("Tour " + this.metier.getTour() );
			char nomDalle = '.';
			int  numSommet=  0;
			boolean bOk = false;

			
			//Choix de jeux du Joueur 1

			System.out.println("Tour du joueur 1 (" + this.metier.getJoueur(1).getCouleur() + ") :");
			while(!bOk)
			{
				System.out.print("\tChoissisez une dalle : ");
				nomDalle = Character.toUpperCase(Clavier.lire_char());
				while(!(nomDalle >= 'A' && nomDalle <= 'P'))
				{
					System.out.println("Erreur saisie dans la dalle");
					System.out.print("\tChoissisez une dalle : ");
					nomDalle = Character.toUpperCase(Clavier.lire_char());
				}
				
				System.out.print("\tQuel côte (0 à 5): ");
				numSommet= Clavier.lire_int();
				while(numSommet < 0 || numSommet > 5)
				{
					System.out.println("Erreur dans la saisie du sommet");
					System.out.print("\tQuel côte (0 à 5): ");
					numSommet= Clavier.lire_int();
				}

				bOk = this.metier.poserPilier(1, nomDalle, numSommet);
			}
			System.out.println("-------------------");
			

			//On met à jour la Grille ET on verifie les piliers
	
			this.ihm.miseAJourGrille();
			this.metier.finTourJoueur(nomDalle, numSommet, this.metier.getJoueur(1).getCouleur());
			this.ihm.miseAJourGrille();



			//Choix de jeux du Joueur 2
			System.out.println("-------------------");
			System.out.println("| Tour du joueur 2 (" + this.metier.getJoueur(2).getCouleur() + ") :");
			bOk = false;
			while(!bOk)
			{	
				System.out.print("|\tChoissisez une dalle : ");
				nomDalle = Character.toUpperCase(Clavier.lire_char());
				while(!(nomDalle >= 'A' && nomDalle <= 'P'))
				{
					System.out.println("Erreur saisie dans la dalle");
					System.out.print("|\tChoissisez une dalle : ");
					nomDalle = Character.toUpperCase(Clavier.lire_char());
				}

				System.out.print("|\tQuel côte (0 à 5): ");
				numSommet= Clavier.lire_int();
				while(numSommet < 0 || numSommet > 5)
				{
					System.out.println("Erreur dans la saisie du sommet");
					System.out.print("|\tQuel côte (0 à 5): ");
					numSommet= Clavier.lire_int();
				}
				bOk = this.metier.poserPilier(2, nomDalle, numSommet);	
			}	
			System.out.println("-------------------");

			this.ihm.miseAJourGrille();
			this.metier.finTourJoueur(nomDalle, numSommet, this.metier.getJoueur(2).getCouleur());
			this.ihm.miseAJourGrille();

			this.metier.prochainTour();
		}
		System.out.println("FIN DE LA PARTIE");
		System.out.println("Le gagnant est le joueur :" + this.metier.getGagnant().getNumJoueur() );
		
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


	
	public void majLocation(char Destination)
	{
		if( Destination == 'P' && this.ihm != null & this.ihmAide != null)
		{
			Point p = this.ihmAide.getLocation();
			this.ihm.setLocation(p.x-1000, p.y);
		}

		if( Destination == 'A' && this.ihm != null & this.ihmAide != null)
		{
			Point p = this.ihm.getLocation();
			this.ihmAide.setLocation (p.x+1000, p.y);
		}
	}

	public void LancerPartieRapide()
	{
		this.ihmMenu.setVisible(false);
		this.metier = Scenario.getScenario(0);
		this.ihm.setVisible(true);
		this.ihmAide.setVisible(true);
		this.bDebutPartie = true;
	}

	public void LancerPartieCustom()
	{
	}

	public void quitter()
	{
		System.exit(0);
	}

	public void lancerScenario()
	{
		
		//this.ihmMenu.setVisible(false);
		//this.ihmScenario.setVisible(true);
		//int numScenario = this.ihmScenario.getScenario();
		//this.metier = Scenario.getScenario(nomScenario);
		//this.ihm.setVisible(true);
		//this.ihmAide.setVisible(true);
		//this.bDebutPartie = true;
	}
	
	public ArrayList<Dalle> getGrilleDalles() 
	{
		return this.metier.getGrilleDalles();
	}

	public static void main (String[] a)
	{
		new Controleur();
	}
}