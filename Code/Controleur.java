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
		this.metier = Scenario.getScenario(1);
		this.ihm    = new FrameDessin(this);

		

		Joueur joueur = this.metier.getJoueur(1);
		System.out.println("Joueur 1 :");
		System.out.println("Nb Pilier: " + joueur.getNbPilier() );
		System.out.println("Nb Pilier détruit: " + joueur.getNbPilierDetruis() );
		System.out.println("Couleur: " + joueur.getCouleur() );
		System.out.println("Liste des dalles du Joueur 1 :");
		for( Dalle dalleDuJoueur : joueur.getListeDalles() )
			System.out.println("\t" + dalleDuJoueur.getNom() );

		joueur = this.metier.getJoueur(2);
		System.out.println("Joueur 2 :");
		System.out.println("Nb Pilier: " + joueur.getNbPilier() );
		System.out.println("Nb Pilier détruit: " + joueur.getNbPilierDetruis() );
		System.out.println("Couleur: " + joueur.getCouleur() );
		System.out.println("Liste des dalles du Joueur 2 :");
		for( Dalle dalleDuJoueur : joueur.getListeDalles() )
			System.out.println("\t" + dalleDuJoueur.getNom() );

		Clavier.lireString();

		this.metier.posePilier(this.metier.getJoueur(2), 'O', 2);
		this.metier.verifControle();
		this.ihm.miseAJourGrille();

		System.out.println("-------------------------------------------------");


		joueur = this.metier.getJoueur(1);
		System.out.println("Joueur 1 :");
		System.out.println("Nb Pilier: " + joueur.getNbPilier() );
		System.out.println("Nb Pilier détruit: " + joueur.getNbPilierDetruis() );
		System.out.println("Couleur: " + joueur.getCouleur() );
		System.out.println("Liste des dalles du Joueur 1 :");
		for( Dalle dalleDuJoueur : joueur.getListeDalles() )
			System.out.println("\t" + dalleDuJoueur.getNom() );

		joueur = this.metier.getJoueur(2);
		System.out.println("Joueur 2 :");
		System.out.println("Nb Pilier: " + joueur.getNbPilier() );
		System.out.println("Nb Pilier détruit: " + joueur.getNbPilierDetruis() );
		System.out.println("Couleur: " + joueur.getCouleur() );
		System.out.println("Liste des dalles du Joueur 2 :");
		for( Dalle dalleDuJoueur : joueur.getListeDalles() )
			System.out.println("\t" + dalleDuJoueur.getNom() );
		
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
		return this.metier.getDalles();
	}
}