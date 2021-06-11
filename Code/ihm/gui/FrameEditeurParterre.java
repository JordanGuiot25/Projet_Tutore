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


public class FrameEditeurParterre extends JFrame implements AdjustmentListener , ComponentListener
{
	//private Controleur ctrl;

	private PanelEdtieurAffichage panelEdtieurAffichage;
	private EditeurParterre editeurParterre;
	private JScrollPane scrollPane;
	private JScrollBar horizontal;
	private JScrollBar vertical;

	/*public static void main(String[]agrs)
	{
		new EditeurParterre();
	}*/

	public FrameEditeurParterre(EditeurParterre editeurParterre)
	{
		
		Point p = new Point(400,800);
		//p.setLocation(400, 800);
		panelEdtieurAffichage = new PanelEdtieurAffichage(editeurParterre);
		this.editeurParterre = editeurParterre;
		//this.setContentPane(this.panelEdtieurAffichage);
		this.setTitle	("EditeurParterre");
		this.setSize	(800,600);
		this.setResizable(false);
		this.addComponentListener(this);
		
		this.scrollPane = new JScrollPane(this.panelEdtieurAffichage, 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setPreferredSize(new Dimension(800,500));




		
		this.scrollPane.getVerticalScrollBar().addAdjustmentListener(this);
		this.scrollPane.getHorizontalScrollBar().addAdjustmentListener(this);
		this.scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		//this.add(this.panelEdtieurAffichage);
		this.scrollPane.getViewport().setViewPosition(p);
		this.add(this.scrollPane);
		
		/*this.layerPanel.add(this.panelEdtieurAffichage);
		this.add(this.layerPanel);*/

				
		


		
		
		
		
		//this.scrollPane.getVerticalScrollBar().setValue(800);
		
		//this.scrollPane.getHorizontalScrollBar().setValue(400);
		//add(this.panelDetection);


		this.setVisible(true);
		

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		if(e.getSource().equals(this.scrollPane.getHorizontalScrollBar())|| e.getSource().equals(this.scrollPane.getVerticalScrollBar()))
		{
			//System.out.println(this.scrollPane.getHorizontalScrollBar());
			panelEdtieurAffichage.revalidate();
			panelEdtieurAffichage.repaint();

		}
	}


	public void componentMoved(ComponentEvent e) 
	{
		Point p = this.getLocation(); 
		

		editeurParterre.DeplacerFrames(p.getX(), p.getY()+600.00, 'e');

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
	

	
	

