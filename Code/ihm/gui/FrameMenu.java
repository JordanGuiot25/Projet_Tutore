package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.ihm.gui.PanelDessin;

import PilierDeLaTerre.Controleur;

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
        this.setSize     ( 400, 400 );

       
        this.panelMenu = new PanelMenu(ctrl);
        this.add ( this.panelMenu );

        this.setVisible ( true );
    }



    public static void main(String[] args)
    {
        new FrameMenu(new Controleur());
    }
}



