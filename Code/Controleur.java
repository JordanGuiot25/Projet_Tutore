package PilierDeLaTerre;

import PilierDeLaTerre.ihm.gui.FrameDessin;
import PilierDeLaTerre.ihm.cui.IhmCui;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;
import PilierDeLaTerre.metier.Scenario;

import iut.algo.Clavier;

import java.util.ArrayList;

import java.io.PrintWriter;
import java.io.FileOutputStream;

import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Joueur;

public class Controleur
{
	private FrameDessin ihm;
	private Parterre    metier;

	public Controleur()
	{
		this.metier = Scenario.getScenario(0);
		this.ihm    = new FrameDessin(this);

		while( this.metier.getGagnant() == null )
		{
			System.out.println("-------------------------------");
			System.out.println("Tour " + this.metier.getTour() );
			char nomDalle;
			int  numSommet;

			System.out.println("Tour du joueur 1 (" + this.metier.getJoueur(1).getCouleur() + ") :");
			System.out.print("\tChoissisez une dalle : ");
			nomDalle = Clavier.lire_char();
			System.out.print("\tQuel côte (0 à 5): ");
			numSommet= Clavier.lire_int();
			this.metier.poserPilier(1, nomDalle, numSommet);

			this.ihm.miseAJourGrille();

			System.out.println("Tour du joueur 2 (" + this.metier.getJoueur(2).getCouleur() + ") :");
			System.out.print("\tChoissisez une dalle : ");
			nomDalle = Clavier.lire_char();
			System.out.print("\tQuel côte (0 à 5): ");
			numSommet= Clavier.lire_int();
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

	public static void main (String[] a)
	{
		new Controleur();
	}


	public ArrayList<Dalle> getGrilleDalles() 
	{
		return this.metier.getGrilleDalles();
	}
}