package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Pilier;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;

import java.util.ArrayList;

public class PanelMenu extends JPanel implements ActionListener
{
	private Controleur  ctrl;

    private JButton btnPartieRapide     ;
    private JButton btnLancerScenar     ;
    private JButton btnChargerSauvegarde;
    private JButton btnPartieCustom     ;
    private JButton btnQuitter          ;
    private Image   imgFond             ;
    private Image   imgPartieRapide     ;
    private Image   imgLancerScenario   ;
    private Image   imgChargerSave      ;
    private Image   imgPartieCustom     ;
    private Image   imgQuitter          ;

	public PanelMenu(Controleur  ctrl)
	{
        /*creation des composants */
		this.setLayout(new GridLayout(6,3,20,20));
        this.ctrl                 = ctrl;
        this.imgFond              = getToolkit().getImage ( "../Ressources/fond.png" );
        this.imgPartieRapide      = getToolkit().getImage ( "../Ressources/PartieRapide.png" );
        this.imgLancerScenario    = getToolkit().getImage ( "../Ressources/LancerScenario.png" );
        this.imgChargerSave       = getToolkit().getImage ( "../Ressources/ChargerSauvegarde.png" );
        this.imgPartieCustom      = getToolkit().getImage ( "../Ressources/PartieCustom.png" );
        this.imgQuitter           = getToolkit().getImage ( "../Ressources/Quitter.png" );
        
        /*boutons*/
        this.btnPartieRapide      = new JButton(new ImageIcon(imgPartieRapide));
        this.btnLancerScenar      = new JButton(new ImageIcon(imgLancerScenario));
        this.btnChargerSauvegarde = new JButton(new ImageIcon(imgChargerSave));
        this.btnPartieCustom      = new JButton(new ImageIcon(imgPartieCustom));
        this.btnQuitter           = new JButton(new ImageIcon(imgQuitter));

        //taille
        this.btnPartieRapide.setPreferredSize(new Dimension(50,50));
        this.btnLancerScenar.setPreferredSize(new Dimension(50,50));
        this.btnChargerSauvegarde.setPreferredSize(new Dimension(50,50));
        this.btnPartieCustom.setPreferredSize(new Dimension(50,50));
        this.btnQuitter.setPreferredSize(new Dimension(50,50));

        //transparence
        this.btnPartieRapide.setOpaque(false);
        this.btnPartieRapide.setContentAreaFilled(false);
        this.btnPartieRapide.setBorderPainted(false);
        this.btnLancerScenar.setOpaque(false);
        this.btnLancerScenar.setContentAreaFilled(false);
        this.btnLancerScenar.setBorderPainted(false);
        this.btnChargerSauvegarde.setOpaque(false);
        this.btnChargerSauvegarde.setContentAreaFilled(false);
        this.btnChargerSauvegarde.setBorderPainted(false);
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
        this.add(new JLabel(""));//this.add(this.btnChargerSauvegarde);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(this.btnQuitter);
        this.add(new JLabel(""));
        this.add(new JLabel(""));


        /* activation des composants */
        this.btnPartieRapide.addActionListener(this);
        this.btnLancerScenar.addActionListener(this);
        this.btnChargerSauvegarde.addActionListener(this);
        this.btnPartieCustom.addActionListener(this);
        this.btnQuitter.addActionListener(this);
        repaint();

    }

	public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.btnPartieRapide)
            ctrl.LancerPartieRapide();
        else if(e.getSource() ==this.btnLancerScenar) 
            ctrl.ouvrirScenar();
        else if(e.getSource() == this.btnChargerSauvegarde)
            ctrl.LancerSauv();
        else if(e.getSource() ==this.btnPartieCustom)
            ctrl.LancerEditeur();
        else if(e.getSource() ==this.btnQuitter)
            ctrl.quitter();                
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage ( this.imgFond, 0, 0, this );
    }


}