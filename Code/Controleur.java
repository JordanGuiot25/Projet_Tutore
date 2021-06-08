package PilierDeLaTerre;

import PilierDeLaTerre.ihm.gui.FrameDessin;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;

import iut.algo.Clavier;

import java.util.ArrayList;

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
		}
		
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