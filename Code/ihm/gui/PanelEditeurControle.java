package Equipe_22.ihm.gui;
import Equipe_22.Controleur;


import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.GridLayout;
/**Le panel avec les boutons de controles de l'éditeur
 * @author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
 */
public class PanelEditeurControle extends JPanel implements ActionListener
{
	/**Label affichant les messages */
	private JLabel lblMessage;
	/**Bouton lançant la partie */
	private JButton btnLancer;
	/**le bouton de retour au menu principal */
	private JButton btnRetour;
	/**le Controleur 
	 * @see {@link Controleur}
	 */
	private Controleur ctrl;


	/**Le constructeur du panel 
	 * @param ctrl {@link Controleur}
	 */
	public PanelEditeurControle(Controleur ctrl)
	{
		//Création
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(2,1));
		JPanel panelSave = new JPanel(new GridLayout(1,2));
		this.lblMessage = new JLabel("Joueur 1, placez une dalle",SwingConstants.CENTER);
		this.btnLancer = new JButton ("Lancer la partie");
		this.btnRetour = new JButton("Revenir au menu principal");

		//Placement
		this.add(this.lblMessage);
		panelSave.add(this.btnRetour);
		panelSave.add(this.btnLancer);
		this.add(panelSave);

		//Activation
		this.btnRetour.addActionListener(this);
		this.btnLancer.addActionListener(this);
		this.btnLancer.setEnabled(false);
		
		

	}
	
	/**Ecrit le message selon le joueur
	 * @param joueur {@link Boolean}
	 */
	public void setJoueur(boolean joueur)
	{
		if(!joueur) { this.lblMessage.setText("Joueur 1, placez une dalle"); }
		else{ this.lblMessage.setText("Joueur 2, placez une dalle");}
		if(this.ctrl.getLastDalle()>='Q'){this.btnLancer.setEnabled(true);}
		else{this.btnLancer.setEnabled(false);}
	}


	/**Effectue des action quand on clique sur un bouton
	 * @param e {@link ActionEvent}
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(this.btnRetour))
		{
			this.ctrl.retour('e');		
		}
		else if(e.getSource().equals(this.btnLancer)&&this.ctrl.getLastDalle()>='P')
		{
			this.ctrl.lancerPartieCustom();
		}
		
	}
}
