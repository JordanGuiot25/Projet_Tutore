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
		this.addComponentListener(this);


		this.setVisible(false);
		this.setResizable(false);

	}

	public void componentMoved(ComponentEvent e) 
	{
		Point p = this.getLocation(); 
		ctrl.DeplacerFrames(p.getX()+POS_X  ,p.getY() , 'd');
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