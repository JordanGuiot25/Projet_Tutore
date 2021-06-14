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



/**La Frame des controles de l'editeur de parterre
  * @author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
  */
public class FrameControleEditeur extends JFrame implements ComponentListener
{
	/**Le panel qui contients les controles */
	private PanelEditeurControle panelEditeurControle;
	/**Le controleur de l'application {@link Controle} */
	private Controleur ctrl;

	/**le Constructeur du panel
	 * @param ctrl {@link Controleur}
	 */
	public FrameControleEditeur(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle	("EditeurParterre");
		this.setSize	(800,100);
		this.panelEditeurControle = new PanelEditeurControle(this.ctrl);
		this.add(this.panelEditeurControle);
		this.addComponentListener(this);
		this.setResizable(false);
		this.setVisible(true);

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	/**change change joueur d'etat pour alterner le numero du joueur
	 * @param joueur {@link Boolean}
	 */
	public void setJoueur(boolean joueur){ this.panelEditeurControle.setJoueur(joueur); }
	/**detecte mouvement de fenêtre
	 * @param e {@link ComponentEvent}
	 */
	public void componentMoved(ComponentEvent e) 
	{
		Point p = this.getLocation(); 
		this.ctrl.DeplacerFrames(p.getX() , p.getY()-600.00 , 'c');
	}
	//On untilise pas
	public void componentHidden(ComponentEvent e){}
    public void componentShown(ComponentEvent e){}
    public void componentResized(ComponentEvent e){}
}
