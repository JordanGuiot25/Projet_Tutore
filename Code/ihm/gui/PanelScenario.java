package Equipe_22.ihm.gui;

import Equipe_22.ihm.gui.FrameScenario;
import Equipe_22.Controleur;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


import java.awt.BorderLayout;

import java.awt.event.*;
import java.util.ArrayList;

/**Le panel des scénarios
 * @author Jordan Guiot, Enguerrand Beltran, Raphael Lizot, Gaspard Gordien
 */
public class PanelScenario extends JPanel implements ActionListener
{
	/**Nombres de scénario max à changer si on veut mettre plus de scénario */
	private final static int NB_SCENARIO = 6;	
	private Controleur ctrl;

	private JComboBox<String> cboxScenario;
	private JButton           btnJouer;
	private JButton			  btnRetour;
	private Image  			  imgFond;


	/**Constructeur
	 * @param ctrl {@link Controleur}
	 **/	
	public PanelScenario(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout   ( new BorderLayout() );
		this.setOpaque(false);
		
		/**image de fond dans le PanelScenario */
		this.imgFond = java.awt.Toolkit.getDefaultToolkit().getImage( "../Ressources/fond.png" );	

		/**tableau de String qui va stocker les scénarios avec leur numéro pour le mettre dans un JComboBox	 */
		String[] tabString = new String [PanelScenario.NB_SCENARIO];	

		/**Initialisation du tableau avec les Scénarios */
		for(int i = 0 ; i < PanelScenario.NB_SCENARIO ; i++)																
			tabString[i] = "Scénario " + i;

		/**Creation des composants */
		this.cboxScenario = new JComboBox<String>(  tabString );

		/**Bouton qui va servir pour lancer le scénario qui a été sélectionné */
		this.btnJouer     = new JButton("Jouer");		

		/**Bouton servant à retourner au menu principale lorsqu'on était dans le lanceur de scénario */
		this.btnRetour	  = new JButton("Retour au menu ");										
		JPanel panelBox   = new JPanel();
		
		/**Pour permettre de voir l'image de fond */
		panelBox.setOpaque(true);																	
     
	 	/**Positionnement des composants */
		panelBox.add(this.btnRetour);
		panelBox.add(new JLabel("Scénario à lancer :"));
		panelBox.add(this.cboxScenario);
		

		this.add( panelBox     ,BorderLayout.NORTH );
		this.add( this.btnJouer,BorderLayout.SOUTH  );


		/**Activation des compossants */
		this.cboxScenario.addActionListener(this);
		this.btnJouer.addActionListener(this);
		this.btnRetour.addActionListener(this);

		repaint();
	}

	/**Detecte si les boutons sont appuyés */
	public void actionPerformed(ActionEvent e)	
	{
		/**Cette condition sert pour lancer le scénario après avoir appuyé sur le bouton jouer*/
		if(e.getSource() == this.btnJouer)							
		{
			/**Récupération du numéro de scénario */
			String sText = ""+this.cboxScenario.getSelectedItem();	
			int numTmp = Integer.parseInt(sText.substring(9));		
			this.ctrl.setScenario(numTmp);

			/**lancement du scénario */
			this.ctrl.lancerScenario();								
		}
		/**Cette condition sert à retourner au menu principal */
		if(e.getSource() == this.btnRetour)							
		{
			/**On envoie 's' à la méhode permettant de retourner au menu principal */
			this.ctrl.retour('s');									
		}
	}

	/**Méhode pour dessiner le fond */
	public void paintComponent(Graphics g)							
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage ( this.imgFond, 0, 20, this );

	}
}