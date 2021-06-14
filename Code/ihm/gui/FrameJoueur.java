/**
 * @author Gaspard Gordien
 * 
 * 
 */
package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.metier.Dalle;
import PilierDeLaTerre.metier.Pilier;
import PilierDeLaTerre.metier.Joueur;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.*;

public class FrameJoueur extends JFrame implements ActionListener
{
    private Controleur ctrl;        //relis le controleur a la frame
    private Joueur     joueur;      //le joueur en cours
    
    private JLabel     lbImg;       //le label qui contient l'image d'aide
    private JPanel     panelJoueur; //le panel qui contient ce que le joueur peut faire
    private JLabel     lblJoueur;   //label qui affiche le joueur en cours
    private JTextField txtNumDalle; //textField ou le joueur indique la dalle 
    private JTextField txtSommet;   //textField ou le joueur indique l'emplacement ou il veut placer un pilier
    private JButton    btnValider;  //bouton ou le joueur valide son choix
    private JPanel     panelScore;  //le panel qui contient le score du joueur
    private JLabel     labelScore;  //le label qui affiche le score

    public FrameJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.joueur = null;

		this.setTitle    ("Joueur1");
        this.setLayout( new BorderLayout());
        this.setSize     (450, 650    );
        this.setLocation (1350, 350    );

        /*creation des composants*/
        this.lbImg       = new JLabel    (new ImageIcon("../Ressources/Hexagone.png"));
        this.panelJoueur = new JPanel    (new GridLayout(6,1));
        this.lblJoueur   = new JLabel    ("Joueur 1");
        this.txtNumDalle = new JTextField();
        this.txtSommet   = new JTextField();
        this.btnValider  = new JButton   ("Valider");
        this.panelScore  = new JPanel    (new GridLayout(1,4));
        this.labelScore  = new JLabel    ("nombre de pilier restant: 24  nombre de pilier detruit: 0");
        
        /* positionement des composants */
        this            .add(this.lbImg,BorderLayout.NORTH);
        this            .add(this.panelJoueur,BorderLayout.CENTER);
        this.panelJoueur.add(this.lblJoueur);
        this.panelJoueur.add(new JLabel("Dalle"));
        this.panelJoueur.add(this.txtNumDalle);
        this.panelJoueur.add(new JLabel("Sommet"));
        this.panelJoueur.add(this.txtSommet);
        this.panelJoueur.add(this.btnValider);
        this            .add(this.panelScore,BorderLayout.SOUTH);
        this.panelScore .add(this.labelScore);

        /* actionement des composants */
        this.txtSommet .addKeyListener   ( new GestionTouche() );
        this.btnValider.addActionListener(this);
        

        this.addComponentListener( new GereDeplacerFrame());

        this.setVisible  (false);
        this.setResizable(false);
    }

    /* gere le changement de joueur */
    public void changerJoueur(Joueur j)
    {
        this.joueur = j;
        this.setTitle("Joueur"+j.getNumJoueur());
        this.lblJoueur.setText("Joueur "+j.getNumJoueur());
        this.txtNumDalle.setText("");
        this.txtSommet.setText("");
        this.labelScore.setText("nombre de pilier restant: "+j.getNbPilier()+"   nombre de pilier detruit: "+j.getNbPilierDetruis());
    }

    /* s'active quand le joueur clique sur le bouton */
    public void actionPerformed(ActionEvent e)
    {
        this.lblJoueur.setText("Joueur "+ this.joueur.getNumJoueur() );
        if( this.txtNumDalle != null && this.txtNumDalle.getText().length() == 1 &&
            this.txtSommet   != null && this.txtSommet  .getText().length() == 1     )
        {
            

            if(e.getSource() == this.btnValider)
            {
                char charDalle = this.txtNumDalle .getText().charAt(0);
                char charSommet= this.txtSommet  .getText().charAt(0);
                if( charDalle >= 'A' && charDalle <= 'P')
                {
                    if(charSommet >= '0' && charSommet <= '5' )
                    {
                        char nomDalle = Character.toUpperCase(this.txtNumDalle.getText().charAt(0));
                        int  numPilier= Integer.parseInt(""+  this.txtSommet.getText().charAt(0));
                        
                        if ( this.ctrl.poserPilier(this.joueur.getNumJoueur(),nomDalle, numPilier) )
                            this.ctrl.changementJoueur( this.joueur );
                        else
                            this.lblJoueur.setText( this.lblJoueur.getText() + "   Erreur: Impossible de poser pilier."  );
                    }
                    else
                        this.lblJoueur.setText( this.lblJoueur.getText() + "   Erreur: Sommet invalide."  );
                }
                else
                    this.lblJoueur.setText( this.lblJoueur.getText() + "   Erreur: Dalle invalide."  );

            }
        }
        else
                    this.lblJoueur.setText( this.lblJoueur.getText() + "   Erreur: Saisie invalide."  );
        
    }

    /* quand la frame se fait deplacer la frame jeux se déplace avec */
    private class GereDeplacerFrame extends ComponentAdapter
	{
		public void componentMoved (ComponentEvent  e)
		{
			FrameJoueur.this.ctrl.majLocation( 'P' );
		}
	}

    /* quand le joueur appuis sur entrée dans le txtField pilier ça valide*/
    private class GestionTouche extends KeyAdapter
	{
		public void keyPressed (KeyEvent  e)
		{
			if ( e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                FrameJoueur.this.lblJoueur.setText("Joueur "+ FrameJoueur.this.joueur.getNumJoueur() );

                if( FrameJoueur.this.txtNumDalle  != null && FrameJoueur.this.txtNumDalle.getText().length() == 1 &&
                    FrameJoueur.this.txtSommet != null && FrameJoueur.this.txtSommet  .getText().length() == 1     )
                {
                    char charDalle = FrameJoueur.this.txtNumDalle .getText().charAt(0);
                    char charSommet= FrameJoueur.this.txtSommet  .getText().charAt(0);
                    if( charDalle >= 'A' && charDalle <= 'P')
                    {
                        if( charSommet >= '0' && charSommet <= '5' )
                        {
                            char nomDalle = Character.toUpperCase(     charDalle  );
                            int  numPilier= Integer  .parseInt   (""+  charSommet );

                            if ( FrameJoueur.this.ctrl.poserPilier(FrameJoueur.this.joueur.getNumJoueur(),nomDalle, numPilier) )
                                FrameJoueur.this.ctrl.changementJoueur( FrameJoueur.this.joueur );
                            else
                                FrameJoueur.this.lblJoueur.setText( FrameJoueur.this.lblJoueur.getText() + "   Erreur: Impossible de poser pilier."  );
                        }
                        else
                            FrameJoueur.this.lblJoueur.setText( FrameJoueur.this.lblJoueur.getText() + "   Erreur: Sommet invalide."  );
                    }
                    else
                        FrameJoueur.this.lblJoueur.setText( FrameJoueur.this.lblJoueur.getText() + "   Erreur: Dalle invalide."  );

                }
                else
                            FrameJoueur.this.lblJoueur.setText( FrameJoueur.this.lblJoueur.getText() + "   Erreur: Saisie invalide."  );
            }
		}
	}
}
