package Equipe_22.ihm.gui;
import Equipe_22.ihm.gui.PanelDessin;
import Equipe_22.Controleur;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.*;

/**cette frame affiche le jeu en cours */
public class FrameDessin extends JFrame implements AdjustmentListener
{
	/**
	 * le panel qui contient le jeu 
	 * @see {@link PanelDessin}
	 */
	private PanelDessin panel	 ;

	/**le Controleur 
	 * @see {@link Controleur}
	 */
	private Controleur  ctrl	 ;

	/**
	 * un panel qui fait que l'on peut avoir des parterres bien plus grand que la frame
	 * @see {@link JScrollPane}
	 */
	private JScrollPane scrollPane;

	/**
	 * le constructeur de la frame qui prend un Controleur en parametre
	 * @param ctrl {@link Controleur}
	 */
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
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

	/**
	 * classe qui permet de déplacer la frame Joueur avec celle ci
	 */
	private class GereDeplacerFrame extends ComponentAdapter
	{
		public void componentMoved (ComponentEvent  e)
		{
			FrameDessin.this.ctrl.majLocation( 'A' );
		}
	}

	/**
	 * méthode qui refresh l'affichage du parterre
	 */
	public void miseAJourGrille()
	{
		this.panel.miseAJourGrille();
	}
	
	
	/**
	 * methode qui permet l'utilisation du ScrollPane
	 * @param e {@link AdjustementEvent}
	 */
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