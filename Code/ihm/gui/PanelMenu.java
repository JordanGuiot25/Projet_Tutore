package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Pilier;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.*;

import java.util.ArrayList;

public class PanelMenu extends JPanel implements ActionListener
{
	private static final Image RESSOURCE_DALLE        = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/Dalle.png"       );
	private static final Image RESSOURCE_ANNEAU_MARON = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/anneau_maron.png");
	private static final Image RESSOURCE_ANNEAU_GRIS  = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/anneau_gris.png" );
	private Controleur  ctrl;
    private JButton bouttonPartieRapide;
    private JButton bouttonPartieCustom;
    private Image      imgFond;

	public PanelMenu(Controleur  ctrl)
	{
        /*creation des composants */
		this.setLayout(new BorderLayout());
        this.ctrl  = ctrl;
        this.bouttonPartieCustom = new JButton("Partie custom");
        this.bouttonPartieRapide = new JButton("Partie rapide");
        JPanel panelBouton = new JPanel(new GridLayout(1,2));
        this.imgFond = getToolkit().getImage ( "../Ressources/fond.png" );
        /* placement des composants */
        this.add(panelBouton, BorderLayout.SOUTH);
        panelBouton.add(bouttonPartieRapide);
        panelBouton.add(bouttonPartieCustom);
        panelBouton.setOpaque ( true );

        /* activation des composants */
        this.bouttonPartieCustom.addActionListener(this);
        this.bouttonPartieRapide.addActionListener(this);
        repaint();

    }

	public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.bouttonPartieCustom)
        {
            ctrl.LancerPartieCustom();
        }
        if (e.getSource() == this.bouttonPartieRapide)
        {
            ctrl.LancerPartieRapide();
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage ( this.imgFond, 0, 0, this );
    }


}