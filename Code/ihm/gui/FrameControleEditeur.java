package PilierDeLaTerre.ihm.gui;
import PilierDeLaTerre.metier.EditeurParterre;

import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Point;

public class FrameControleEditeur extends JFrame implements ComponentListener
{
	private PanelEditeurControle panelEditeurControle;
	private EditeurParterre editeurParterre;

	public FrameControleEditeur(EditeurParterre editeurParterre)
	{
		this.editeurParterre = editeurParterre;
		this.setTitle	("EditeurParterre");
		this.setSize	(800,100);
		this.panelEditeurControle = new PanelEditeurControle(this.editeurParterre);
		this.add(this.panelEditeurControle);
		this.addComponentListener(this);
		this.setResizable(false);
		this.setVisible(true);
	}


	public void componentMoved(ComponentEvent e) 
	{
		Point p = this.getLocation(); 
		editeurParterre.DeplacerFrames(p.getX() , p.getY()-600.00 , 'c');
	}

	public void componentHidden(ComponentEvent e)
	{
		
	}

    public void componentShown(ComponentEvent e)
    {

    }

    public void componentResized(ComponentEvent e)
    {

    }
}
