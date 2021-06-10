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
			boolean bOk = false;

			
			//Choix de jeux du Joueur 1

			System.out.println("Tour du joueur 1 (" + this.metier.getJoueur(1).getCouleur() + ") :");
			do
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
			while(!bOk);

			//On met à jour la Grille
	
			this.ihm.miseAJourGrille();



			//Choix de jeux du Joueur 2

			System.out.println("Tour du joueur 2 (" + this.metier.getJoueur(2).getCouleur() + ") :");
			do
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
				bOk = this.metier.poserPilier(2, nomDalle, numSommet);	
			}
			while(!bOk);	

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


	
	public void DeplacerFrames(double posX, double posY, char frame)
	{
		if(frame == 'd' && this.ihmAide != null && this.ihm != null)
		{
			ihmAide.setLocation((int) posX, (int) posY);
		}
		if(frame == 'a' && this.ihmAide != null && this.ihm != null)
		{
			ihm.setLocation( (int) posX , (int) posY);
		}
	}


	public static void main (String[] a)
	{
		new Controleur();
	}

	public void LancerPartieRapide()
	{
		System.out.println("partieRapide");
	}

	public void LancerPartieCustom()
	{
		System.out.println("partieCustom");
	}


	public ArrayList<Dalle> getGrilleDalles() 
	{
		return this.metier.getGrilleDalles();
	}
}