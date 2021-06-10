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

public class FrameAide extends JFrame
{
    public static final double POS_X = -400;
	public static final double POS_Y = 0;

    private Controleur ctrl;
    
    public FrameAide(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setTitle    ("Aide");
        this.setLayout( new BorderLayout());
        this.setSize     (400, 350    );
        this.setLocation (1350, 350    );



        JLabel lb = new JLabel(new ImageIcon("../Ressources/Hexagone.png"));

        this.add(lb,BorderLayout.CENTER);

        this.addComponentListener( new GereDeplacerFrame());

        this.setVisible(false);
        this.setResizable(false);
    }


    private class GereDeplacerFrame extends ComponentAdapter
	{
		public void componentMoved (ComponentEvent  e)
		{
			FrameAide.this.ctrl.majLocation( 'P' );
		}
	}

}
