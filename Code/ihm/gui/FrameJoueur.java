package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Pilier;
import PilierDeLaTerre.metier.Joueur;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.*;

import java.awt.Point;

public class FrameJoueur extends JFrame implements ActionListener
{
    public static final double POS_X = -400;
	public static final double POS_Y = 0;

    private Controleur ctrl;
    private Joueur     joueur;
    
    private JLabel     lbImg;
    private JPanel     panelJoueur;
    private JLabel     lblJoueur;
    private JTextField txtNumDalle;
    private JTextField txtSommet;
    private JButton    btnValider;
    private JPanel     panelNbPilier;
    private JLabel     labelNbPilier;
    private JLabel     labelNbPilierDetruit;

    public FrameJoueur(Controleur ctrl,Joueur j)
    {
        this.ctrl = ctrl;
        joueur = j;

		this.setTitle    ("Joueur1");
        this.setLayout( new BorderLayout());
        this.setSize     (400, 350    );
        this.setLocation (1350, 350    );

        /*creation des composants*/

        this.lbImg = new JLabel(new ImageIcon("../Ressources/Hexagone.png"));
        this.panelJoueur = new JPanel(new GridLayout(6,1));
        this.lblJoueur =new JLabel("Joueur1");
        this.txtNumDalle = new JTextField();
        this.txtSommet = new JTextField();
        this.btnValider = new JButton("Valider");
        this.panelScore = new JPanel(new GridLayout(1,4));
        this.labelNbPilier = new JLabel("24");
        this.labelNbPilierDetruit = new JLabel("0");

        /* positionement des composants */
        this.add(this.lbImg,BorderLayout.NORTH);
        this.add(this.panelJoueur,BorderLayout.CENTER);
        this.panelJoueur.add(this.lblJoueur);
        this.panelJoueur.add(new JLabel("Dalle"));
        this.panelJoueur.add(this.txtNumDalle);
        this.panelJoueur.add(new JLabel("Sommet"));
        this.panelJoueur.add(this.txtSommet);
        this.panelJoueur.add(this.btnValider);
        this.add(this.panelScore,BorderLayout.SOUTH);
        this.panelJoueur.add(new JLabel("nd pilier"));
        this.panelScore.add(this.labelNbPilier);
        this.panelJoueur.add(new JLabel("nb pilier detruit"));
        this.panelScore.add(this.labelNbPilierDetruit);

        /* actionement des composants */
        this.btnValider.addActionListener(this);
        

        this.addComponentListener( new GereDeplacerFrame());

        this.setVisible(false);
        this.setResizable(false);
    }

    public void ChangerJoueur(Joueur j)
    {
        this.joueur = j;
        this.setTitle("joueur"+j.getNom());
        this.lblJoueur.setText("joueur"+j.getNom());
        this.txtNumDalle.setText("");
        this.txtSommet.setText("");
        this.labelNbPilier.setText(""+j.getNbPilier());
        this.labelNbPilierDetruit.setText(""+j.getNbPilierDetruis());
    }

    public void actionPerformed(ActionEvent e)
    {
        char nomDalle = Character.toUpperCase(this.labelNbPilier.getText().charAt(0));
        int  numPilier= Integer.parseInt(""+  this.labelNbPilier.getText().charAt(0));
        if(e.getSource() == this.btnValider)
        {
            if( nomDalle >= 'A' && nomDalle <= 'P')
            {
                if(numPilier >= 1 && numPilier <= 5 )
                {
                    this.ctrl.poserPilier(this.joueur.getNom(),nomDalle,numPilier);
                }
            }

        }
    }

    private class GereDeplacerFrame extends ComponentAdapter
	{
		public void componentMoved (ComponentEvent  e)
		{
			FrameAide.this.ctrl.majLocation( 'P' );
		}
	}



    public static void main(String[] args)
    {
        new FrameJoueur();
    }

}
