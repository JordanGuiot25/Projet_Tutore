package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.ihm.gui.PanelDessin;
import java.awt.Point;



import PilierDeLaTerre.Controleur;

import javax.swing.*;

import java.awt.event.*;

public class FrameDessin extends JFrame
{
	private PanelDessin panel	 ;
	private Controleur  ctrl	 ;
	public static final double POS_X = 400;
	public static final double POS_Y = 0;

	public FrameDessin(Controleur  ctrl)
	{
		this.ctrl = ctrl;


		this.setTitle    ("Pilier de la terre");
		this.setLocation (350, 350    );
		this.setSize     (1000, 650    );

		this.panel   = new PanelDessin(this.ctrl);
		
		this.add( this.panel );
		this.addComponentListener( new GereDeplacerFrame());


		this.setVisible(false);
		this.setResizable(false);

	}

	private class GereDeplacerFrame extends ComponentAdapter
	{
		public void componentMoved (ComponentEvent  e)
		{
			FrameDessin.this.ctrl.majLocation( 'A' );
		}
	}


	public void miseAJourGrille()
	{
		this.panel.miseAJourGrille();
	}
}