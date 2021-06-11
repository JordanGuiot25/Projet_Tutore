package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.ihm.gui.PanelScenario;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;


import java.awt.BorderLayout;

import java.awt.event.*;
import java.util.ArrayList;


public class FrameScenario extends JFrame
{
    private Controleur ctrl;

    private PanelScenario panelScenario;

    public FrameScenario(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle    ("Scenario");
        this.setSize     (560, 350    );
        this.setLocation (400, 0    );

        //Creation des composants
        this.panelScenario = new PanelScenario(ctrl);

        //Positionnement des composants
        this.add(this.panelScenario);

        
        this.setVisible(false);
        this.setResizable(false);
    }
}
