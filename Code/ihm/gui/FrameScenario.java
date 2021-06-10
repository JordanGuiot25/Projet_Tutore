package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;

import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import java.awt.event.*;


public class FrameScenario extends JFrame implements ActionListener
{
    private Controleur ctrl;
    
    public FrameScenario(Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setTitle    ("Scenario");
        this.setLayout( new BorderLayout());
        this.setSize     (400, 350    );
        this.setLocation (400, 0    );




        this.add(lb,BorderLayout.CENTER);

        this.addComponentListener(this);

        this.setVisible(false);
        this.setResizable(false);
    }


    public void componentMoved(ComponentEvent e) 
	{
		Point p = this.getLocation(); 
		

		ctrl.DeplacerFrames(p.getX()+POS_X , p.getY() , 'a');

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
