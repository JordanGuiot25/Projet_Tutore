package Equipe_22.ihm.gui;

import Equipe_22.Controleur;
import Equipe_22.metier.Dalle;
import Equipe_22.metier.Pilier;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;

import java.util.ArrayList;

/**
 * panel contenu dans la frame menu
 *@author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
 */
public class PanelMenu extends JPanel implements ActionListener
{
    /**Le Controleur de l'appilcation de la frame
	 * @see {@link Controleur} */
	private Controleur  ctrl;

    /**Le bouton qui lance une partie dans un parterre prédéfini
	 * @see {@link JButton} */
    private JButton btnPartieRapide     ;

    /**Le bouton qui lance une frame qui montre un choix de scénario
	 * @see {@link JButton} */
    private JButton btnLancerScenar     ;    

    /**Le bouton qui lance l'editeur de parterre
	 * @see {@link JButton} */
    private JButton btnPartieCustom     ;
    
    /**Le bouton qui quitte l'application
	 * @see {@link JButton} */
    private JButton btnQuitter          ;
    
    /**L'image de fond
	 * @see {@link Image} */
    private Image   imgFond             ;
    
    /**L'image du boutton partie rapide
	 * @see {@link Image} */
    private Image   imgPartieRapide     ;
    
    /**L'image du boutton lancer scenario
	 * @see {@link Image} */
    private Image   imgLancerScenario   ;
    
    /**L'image du boutton editeur
	 * @see {@link Image} */
    private Image   imgPartieCustom     ;
    
    /**L'image du boutton quitter
	 * @see {@link Image} */
    private Image   imgQuitter          ;

    /**
     * constructeur qui prend un controleur en parametre
     * @param ctrl {link Controleur}
     */
	public PanelMenu(Controleur  ctrl)
	{
        /*creation des composants */
		this.setLayout(new GridLayout(6,3,20,20));
        this.ctrl                 = ctrl;
        this.imgFond              = getToolkit().getImage ( "../Ressources/fond.png" );
        this.imgPartieRapide      = getToolkit().getImage ( "../Ressources/PartieRapide.png" );
        this.imgLancerScenario    = getToolkit().getImage ( "../Ressources/LancerScenario.png" );
        this.imgPartieCustom      = getToolkit().getImage ( "../Ressources/PartieCustom.png" );
        this.imgQuitter           = getToolkit().getImage ( "../Ressources/Quitter.png" );
        
        /*boutons*/
        this.btnPartieRapide      = new JButton(new ImageIcon(imgPartieRapide));
        this.btnLancerScenar      = new JButton(new ImageIcon(imgLancerScenario));
        this.btnPartieCustom      = new JButton(new ImageIcon(imgPartieCustom));
        this.btnQuitter           = new JButton(new ImageIcon(imgQuitter));

        //taille
        this.btnPartieRapide     .setPreferredSize(new Dimension(50,50));
        this.btnLancerScenar     .setPreferredSize(new Dimension(50,50));
        this.btnChargerSauvegarde.setPreferredSize(new Dimension(50,50));
        this.btnPartieCustom     .setPreferredSize(new Dimension(50,50));
        this.btnQuitter          .setPreferredSize(new Dimension(50,50));

        //transparence
        this.btnPartieRapide.setOpaque(false);
        this.btnPartieRapide.setContentAreaFilled(false);
        this.btnPartieRapide.setBorderPainted(false);

        this.btnLancerScenar.setOpaque(false);
        this.btnLancerScenar.setContentAreaFilled(false);
        this.btnLancerScenar.setBorderPainted(false);

        this.btnPartieCustom.setOpaque(false);
        this.btnPartieCustom.setContentAreaFilled(false);
        this.btnPartieCustom.setBorderPainted(false);

        this.btnQuitter.setOpaque(false);
        this.btnQuitter.setContentAreaFilled(false);
        this.btnQuitter.setBorderPainted(false);


        /* placement des composants */
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(this.btnPartieRapide);

        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(this.btnPartieCustom);

        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(this.btnLancerScenar);

        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(this.btnQuitter);

        this.add(new JLabel(""));
        this.add(new JLabel(""));


        /* activation des composants */
		
        this.btnPartieRapide     .addActionListener(this);
        this.btnLancerScenar     .addActionListener(this);
        this.btnPartieCustom     .addActionListener(this);
        this.btnQuitter          .addActionListener(this);
        repaint();

    }

    /** s'active quand on appuis sur un boutton  
     * @param e {@link ActionEvent}
    */
	public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.btnPartieRapide)
            this.ctrl.LancerPartieRapide();
        else if(e.getSource() ==this.btnLancerScenar) 
            this.ctrl.scenario();
        else if(e.getSource() ==this.btnPartieCustom)
            this.ctrl.partieCustom();
        else if(e.getSource() ==this.btnQuitter)
            this.ctrl.quitter();                
    }

    /** methode qui peint le fond
     * @param g {@link Graphics}
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage ( this.imgFond, 0, 0, this );
    }


}
