package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.ihm.gui.PanelDessin;
import java.awt.Point;



import PilierDeLaTerre.Controleur;

import javax.swing.*;

import java.awt.event.*;

public class FrameDessin extends JFrame implements ComponentListener
{
	private PanelDessin panel	 ;
	private Controleur  ctrl	 ;
	public static final double POS_X = 100;
	public static final double POS_Y = 100;

	public FrameDessin(Controleur  ctrl)
	{
		this.ctrl = ctrl;


		this.setTitle    ("Pilier de la terre");
		this.setLocation (0, 0    );
		this.setSize     (400, 350    );

		this.panel   = new PanelDessin(this.ctrl);
		

		this.add( this.panel );

		this.setVisible(true);
	}

	public void componentMoved(ComponentEvent e) 
	{
		Point p = this.getLocation(); 
		ctrl.DeplacerFrames(p.getX() + POS_X ,p.getY() + POS_Y, 'p');
	}

	public void componentHidden​(ComponentEvent e)
	{
		
	}

    public void componentShown(ComponentEvent e)
    {

    }

    public void componentResized(ComponentEvent e)
    {

    }


	public void miseAJourGrille()
	{
		this.panel.miseAJourGrille();
	}
}