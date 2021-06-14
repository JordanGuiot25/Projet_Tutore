package Equipe_22.ihm.gui;

import Equipe_22.Controleur;
import Equipe_22.ihm.gui.PanelScenario;

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

/**La frame scénario
 * @author Jordan Guiot, Enguerrand Beltran, Raphael Lizot, Gaspard Gordien
 */
public class FrameScenario extends JFrame
{
    private Controleur ctrl;

    private PanelScenario panelScenario;

    /**Constructeur
     * @param ctrl {@link Constructeur}
     */
    public FrameScenario(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle    ("Scenario");
        this.setSize     (560, 350    );
        this.setLocation (400, 0    );

        /**Creation des composants */
        this.panelScenario = new PanelScenario(ctrl);

        /**Positionnement des composants */
        this.add(this.panelScenario);

        /**On setVisible(false) car quand on l'initialise, on ne veut pas l'afficher directement,
         * on l'affichera lorsque le joueur clique sur "lancer Scenario" 
         * et setResizable(false) pour que l'utilisateur ne puisse pas modifier la taille de la fenêtre*/ 
        this.setVisible(false);
        this.setResizable(false);
        /**Permettre la fermeture du programme quand la fenêtre se ferme */
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }
}
