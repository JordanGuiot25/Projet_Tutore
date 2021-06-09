package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Pilier;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import java.awt.event.*;

import java.awt.Point;

public class FrameAide extends JFrame implements ComponentListener
{
    

    private Controleur ctrl;
    
    public FrameAide(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setTitle    ("Aide");
        this.setLayout( new BorderLayout());
        this.setSize     (400, 350    );
        this.setLocation (400, 0    );



        JLabel lb = new JLabel(new ImageIcon("../Ressources/Hexagone.png"));

        this.add(lb,BorderLayout.CENTER);

        

        this.setVisible(true);
    }


    public void componentMoved​(ComponentEvent e)
    {
        this.ctrl.moveFrame('J');
    }
  
    public void componentHidden​(ComponentEvent e)
	{
		
	}

    public void componentShown(ComponentEvent e)
    {

    }

    public void componentResized(ComponentEvent e)
    {

    }

}
