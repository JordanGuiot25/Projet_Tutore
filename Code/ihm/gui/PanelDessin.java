package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Pilier;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.util.ArrayList;

public class PanelDessin extends JPanel
{
	private static final Image RESSOURCE_DALLE        = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/Dalle.png"       );
	private static final Image RESSOURCE_ANNEAU_MARON = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/anneau_maron.png");
	private static final Image RESSOURCE_ANNEAU_GRIS  = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/anneau_gris.png" );
	private Controleur  ctrl;
	/** Taille en largeur du panel calculée en fonction de la largeur des images moins les partie qui se superposent multiplié par leur nombre*/
	private final int RES_X = (67-16)*31;
	/** Taille en hauteur du panel calculée en fonction de la hauteur des images multiplié par leur nombre*/
	private final int RES_Y = (67*31)+33;

	public PanelDessin(Controleur  ctrl)
	{
		this.ctrl  = ctrl;
		this.setPreferredSize((new Dimension(this.RES_X+34,this.RES_Y)));
		this.miseAJourGrille();
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		ArrayList<Dalle> listeDalle = this.ctrl.getGrilleDalles();

		for(Dalle dalle : listeDalle )
		{
			g2.drawImage( RESSOURCE_DALLE, dalle.getX(), dalle.getY(), this);
			g2.drawString( "" + dalle.getNom(), dalle.getMilieuX()-3, dalle.getMilieuY()+5);
			if( dalle.estControler() )
			{
				if ( dalle.getProprietaire().getCouleur() == 'G' )
				{
					g2.drawImage( RESSOURCE_ANNEAU_GRIS, dalle.getX(), dalle.getY(), this);
				}
				else
				{
					g2.drawImage( RESSOURCE_ANNEAU_MARON, dalle.getX(), dalle.getY(), this);
				}
			}
				
			
		}

		for(Dalle dalle : listeDalle )
		{
			for( Pilier pilier : dalle.getSommets() )
			{
				if( pilier != null)
					g2.drawImage( pilier.getImage(), pilier.getX(), pilier.getY(), this);
			}
		}
	}

	public void miseAJourGrille()
	{
		this.repaint();
	}

	
}