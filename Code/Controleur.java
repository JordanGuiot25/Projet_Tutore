package PilierDeLaTerre;

import PilierDeLaTerre.ihm.gui.FrameDessin;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Parterre;

import iut.algo.Clavier;

import java.util.ArrayList;

public class Controleur
{
	private FrameDessin ihm;
	private Parterre    metier;

	public Controleur()
	{
		this.ihm    = new FrameDessin(this);
		this.metier = new Parterre();
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