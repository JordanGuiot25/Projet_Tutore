package Equipe_22.ihm.gui;
import Equipe_22.Controleur;
import Equipe_22.metier.EditeurParterre;

import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Point;

/**La Frame de l'editeur de parterre
  * @author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
  */
public class FrameEditeurParterre extends JFrame implements AdjustmentListener , ComponentListener
{
	/**Panel de l'éditeur de parterre */
	private PanelEdtieurAffichage panelEdtieurAffichage;
	/**le Controleur de l'application {@link Controleur} */
	private Controleur ctrl;
	/**le ScrollePane pour reduire la taille de la fenêtre */
	private JScrollPane scrollPane;

	/**Constructeur de la Frame
	 * @param ctrl {@link Controleur}
	 */
	public FrameEditeurParterre(Controleur ctrl)
	{
		//Création
		Point p = new Point(400,800);
		this.panelEdtieurAffichage = new PanelEdtieurAffichage(ctrl);
		this.ctrl = ctrl;
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
		this.scrollPane.getViewport().setViewPosition(p);
		this.add(this.scrollPane);
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	/**On utilise les scrollbars
	 * @param e {@link AdjustementEvent}
	 */
	public void adjustmentValueChanged(AdjustmentEvent e) 
	{
		if(e.getSource().equals(this.scrollPane.getHorizontalScrollBar())|| e.getSource().equals(this.scrollPane.getVerticalScrollBar()))
		{
			panelEdtieurAffichage.revalidate();
			panelEdtieurAffichage.repaint();
		}
	}

	/**On bouge la fenêtre
	 * @param e {@link ComponentEvent}
	 */
	public void componentMoved(ComponentEvent e) 
	{
		Point p = this.getLocation(); 
		

		ctrl.DeplacerFrames(p.getX(), p.getY()+600.00, 'e');

	}


	//On ne s'en sert pas
	public void componentHidden(ComponentEvent e){}

    public void componentShown(ComponentEvent e){}

    public void componentResized(ComponentEvent e){}

	
}
	

	
	

