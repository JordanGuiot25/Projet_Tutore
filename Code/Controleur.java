package PilierDeLaTerre;

import PilierDeLaTerre.ihm.gui.FrameAide;
import PilierDeLaTerre.ihm.gui.FrameDessin;
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
	private FrameAide   ihmAide;
	private Parterre    metier ;

	public Controleur()
	{
		this.metier = Scenario.getScenario(0);

		this.ihm    = new FrameDessin(this);
		this.ihmAide= new FrameAide  (this);

		while( this.metier.getGagnant() == null )
		{
			System.out.println("-------------------------------");
			System.out.println("Tour " + this.metier.getTour() );
			char nomDalle;
			int  numSommet;

			
			//Choix de jeux du Joueur 1

			System.out.println("Tour du joueur 1 (" + this.metier.getJoueur(1).getCouleur() + ") :");
			System.out.print("\tChoissisez une dalle : ");
			nomDalle = Character.toUpperCase(Clavier.lire_char());
			while(nomDalle < 'A' || nomDalle > 'Z')
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
			this.metier.poserPilier(1, nomDalle, numSommet);

			//On met à jour la Grille
	
			this.ihm.miseAJourGrille();



			//Choix de jeux du Joueur 2

			System.out.println("Tour du joueur 2 (" + this.metier.getJoueur(2).getCouleur() + ") :");
			System.out.print("\tChoissisez une dalle : ");
			nomDalle = Clavier.lire_char();
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
			this.metier.poserPilier(2, nomDalle, numSommet);		

			this.ihm.miseAJourGrille();
			this.metier.finDeTour();
			this.ihm.miseAJourGrille();
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


	public void moveFrame ( char orig )
	{
		Point p;

		if ( orig == 'P' && this.ihm != null && this.ihmAide != null )
		{
			p = this.ihm.getLocation();
			this.ihmAide.setLocation ( p.x,p.y + 400 );
		}

		if ( orig == 'J' && this.ihm != null && this.ihmAide != null )
		{
			p = this.ihmAide.getLocation();
			this.ihm.setLocation ( p.x, p.y - 400 );
		}
	}


	public static void main (String[] a)
	{
		new Controleur();
	}


	public ArrayList<Dalle> getGrilleDalles() 
	{
		return this.metier.getGrilleDalles();
	}
}