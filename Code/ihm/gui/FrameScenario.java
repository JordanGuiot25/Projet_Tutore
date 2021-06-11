package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;

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


public class FrameScenario extends JFrame implements ActionListener
{
    private Controleur ctrl;
    private JComboBox <String> cboxScenario;
    private String[] tabString;
    private static int NB_SCENARIO = 6;
    private JButton btnJouer;
    private JPanel panelBox;
    private Image  imgFond;

    
    public FrameScenario(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.imgFond              = getToolkit().getImage ( "../Ressources/imgScenario.jpg" );
        this.repaint();
        this.tabString = new String [this.NB_SCENARIO];

        for(int i = 0 ; i < 6 ; i++)
        {
            this.tabString[i] = "Scénario " + i;
        }

		this.setTitle    ("Scenario");
        this.setLayout( new BorderLayout());
        this.setSize     (400, 350    );
        this.setLocation (400, 0    );
        this.cboxScenario = new JComboBox<String>(  this.tabString );
        this.btnJouer = new JButton("Jouer");
        this.panelBox = new JPanel();
     
       
       
        this.panelBox.add(new JLabel("Scénario à lancer :"), BorderLayout.NORTH);
        this.panelBox.add(this.cboxScenario);
        this.add(this.panelBox,BorderLayout.CENTER);
        this.add(this.btnJouer,BorderLayout.SOUTH);

        this.cboxScenario.addActionListener(this);
        this.btnJouer.addActionListener(this);

        
        this.setVisible(false);
        this.setResizable(false);

        
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.btnJouer)
        {
            String sText = ""+this.cboxScenario.getSelectedItem();
            int numTmp = Integer.parseInt(sText.substring(9));
            this.ctrl.setScenario(numTmp);

            this.ctrl.lancerScenario();
        }
    }
    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage ( this.imgFond, 0, 0, this );

        System.out.println("coucou");
    }
   

}
