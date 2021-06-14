package Equipe_22.ihm.gui;

import Equipe_22.ihm.gui.PanelDessin;
import Equipe_22.Controleur;

import javax.swing.*;

/**
 * frame qui s'affiche au lancement 
 * @author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
 */
public class FrameMenu extends JFrame
{

    /**Le panel qui compose la frame 
	 * @see {@link PanelMenu} */
    private PanelMenu  panelMenu;

    /**Le Controleur de l'appilcation de la frame
	 * @see {@link Controleur} */
    private Controleur ctrl;


    /**Le Constructeur de la frame qui prend un controleur en parametre
	 * @param ctrl {@link Controleur} */
    public FrameMenu(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle    ( "Les pilier de la terre" );
        this.setLocation (  10,  10 );
        this.setSize     ( 560, 885 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        //this.setResizable(false);
       
        this.panelMenu = new PanelMenu(ctrl);
        this.add ( this.panelMenu );

        this.setVisible ( true );
    }
}