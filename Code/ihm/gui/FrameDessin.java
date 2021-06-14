package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.ihm.gui.PanelDessin;

import java.awt.Dimension;
import java.awt.Point;



import PilierDeLaTerre.Controleur;

import javax.swing.*;

import java.awt.event.*;

public class FrameDessin extends JFrame implements AdjustmentListener
{
	private PanelDessin panel	 ;
	private Controleur  ctrl	 ;
	public static final double POS_X = 400;
	public static final double POS_Y = 0;
	private JScrollPane scrollPane;
	private JScrollBar horizontal;
	private JScrollBar vertical;

	public FrameDessin(Controleur  ctrl)
	{
		Point p = new Point(400,800);
		this.ctrl = ctrl;


		this.setTitle    ("Pilier de la terre");
		this.setLocation (0, 0    );
		this.setSize     (1000, 650    );

		this.panel   = new PanelDessin(this.ctrl);
		
		this.add( this.panel );
		this.addComponentListener( new GereDeplacerFrame());
		this.scrollPane = new JScrollPane(this.panel, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				this.scrollPane.setPreferredSize(new Dimension(800,500));




				
		this.scrollPane.getVerticalScrollBar().addAdjustmentListener(this);
		this.scrollPane.getHorizontalScrollBar().addAdjustmentListener(this);
		this.scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		this.scrollPane.getViewport().setViewPosition(p);
		this.add(this.scrollPane);


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
	
	
	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		if(e.getSource().equals(this.scrollPane.getHorizontalScrollBar())|| e.getSource().equals(this.scrollPane.getVerticalScrollBar()))
		{
			//System.out.println(this.scrollPane.getHorizontalScrollBar());
			panel.revalidate();
			panel.repaint();

		}
	}
}