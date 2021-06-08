package PilierDeLaTerre;

import PilierDeLaTerre.ihm.gui.FrameDessin;
import PilierDeLaTerre.ihm.cui.IhmCui;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;

import iut.algo.Clavier;

import java.util.ArrayList;

import java.io.PrintWriter;
import java.io.FileOutputStream;

import PilierDeLaTerre.metier.Dalle;

public class Controleur
{
	private FrameDessin ihm;
	private Parterre    metier;

	public Controleur()
	{
		this.metier = new Parterre();
		this.ihm    = new FrameDessin(this);

		while(true)
		{
			ArrayList<Dalle> plateau = this.metier.getDalles();

			int nbAlea  = (int) (Math.random() * plateau.size() );
			Dalle dalle = plateau.get(nbAlea);

			System.out.println( dalle.getNom() + " quel sommet ? ");
			int sommet = Clavier.lire_int();

			dalle.rajoutPillier('G', sommet);

			this.ihm.miseAJourGrille();

			Clavier.lireString();
			System.out.println("Destruction du pillier");
			dalle.detruirePillier(sommet);

			this.ihm.miseAJourGrille();
			this.Sauvegarde();

		}
		
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