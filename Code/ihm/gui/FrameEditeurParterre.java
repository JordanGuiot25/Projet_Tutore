import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Point;


public class GuiJeu extends JFrame implements AdjustmentListener
{
	//private Controleur ctrl;

	private PanelAffichage panelAffichage;
	private ParterrePersonalise patr;
	private JScrollPane scrollPane;
	private JScrollBar horizontal;
	private JScrollBar vertical;

	/*public static void main(String[]agrs)
	{
		new GuiJeu();
	}*/

	public GuiJeu(ParterrePersonalise patr)
	{
		
		Point p = new Point(400,800);
		//p.setLocation(400, 800);
		panelAffichage = new PanelAffichage(patr);
		this.patr = patr;
		//this.setContentPane(this.panelAffichage);
		this.setTitle	("GuiJeu");
		this.setLocation(500,500);
		this.setSize	(800,600);
		
		this.scrollPane = new JScrollPane(this.panelAffichage, 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scrollPane.setPreferredSize(new Dimension(800,500));




		
		this.scrollPane.getVerticalScrollBar().addAdjustmentListener(this);
		this.scrollPane.getHorizontalScrollBar().addAdjustmentListener(this);
		this.scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		//this.add(this.panelAffichage);
		this.scrollPane.getViewport().setViewPosition(p);
		this.add(this.scrollPane);
		
		/*this.layerPanel.add(this.panelAffichage);
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
			panelAffichage.revalidate();
			panelAffichage.repaint();

		}
	}

	
}
	

	
	

