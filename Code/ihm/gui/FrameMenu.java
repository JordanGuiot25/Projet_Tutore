/**
 * @author Gaspard Gordien
 * 
 * 
 */
package Equipe_22.ihm.gui;

import Equipe_22.ihm.gui.PanelDessin;

import Equipe_22.Controleur;

import javax.swing.*;

public class FrameMenu extends JFrame
{
    private PanelMenu  panelMenu;

    private Controleur ctrl;

    public FrameMenu(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle    ( "Les pilier de la terre" );
        this.setLocation (  10,  10 );
        this.setSize     ( 560, 885 );
        //this.setResizable(false);
       
        this.panelMenu = new PanelMenu(ctrl);
        this.add ( this.panelMenu );

        this.setVisible ( true );
    }
}