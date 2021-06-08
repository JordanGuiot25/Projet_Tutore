package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.metier.Dalle;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.util.ArrayList;

public class PanelDessin extends JPanel
{
	private static final Image RESSOURCE_DALLE  = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/Dalle.png");
 
	private Controleur  ctrl;

	public PanelDessin(Controleur  ctrl)
	{
		this.ctrl  = ctrl;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		ArrayList<Dalle> listeDalle = this.ctrl.getGrilleDalles();

		for(Dalle dalle : listeDalle )
		{
			g2.drawImage( RESSOURCE_DALLE, dalle.getX(), dalle.getY(), this);
			g2.drawString( "" + dalle.getNom(), dalle.getMilieuX()-3, dalle.getMilieuY()+5);

			for( Pilier pilier : dalle.getSommets() )
			{
				g2.drawImage( pilier.getImage(), pilier.getX(), pilier.getY(), this);
			}
		}
	}

	
}