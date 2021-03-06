
package Equipe_22.ihm.gui;

import Equipe_22.Controleur;
import Equipe_22.metier.Dalle;
import Equipe_22.metier.Pilier;
import Equipe_22.metier.Joueur;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.*;

/**
 * Frame qui permet au joueur de jouer qui indique une aide sur l'emplacement des piliers et qui indique le nombre de piliers restants et le nombre de piliers détruit 
 * @author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
 */

public class FrameJoueur extends JFrame implements ActionListener
{
    /**Le Controleur de l'appilcation de la frame
	 * @see {@link Controleur} */
    private Controleur ctrl;

    /**Le joueur actuel
	 * @see {@link Joueur} */
    private Joueur     joueur;      //le joueur en cours
    
    /**Le label qui contient l'image d'aide
	 * @see {@link JLabel} */
    private JLabel     lbImg;

    /**Le panel qui contient les actions possibles du joueur
	 * @see {@link JPanel} */
    private JPanel     panelJoueur;

    /**Le label qui indique quel joueur est en train de jouer
	 * @see {@link JLabel} */
    private JLabel     lblJoueur;

    /**Le textField ou le joueur indique la dalle ou il veut placer son pilier
	 * @see {@link JTextField} */
    private JTextField txtNumDalle;

    /**Le textField ou le joueur indique l'emplacement ou il veut placer son pilier
	 * @see {@link JTextField} */
    private JTextField txtSommet;

    /**Le bouton ou le joueur valide son choix
	 * @see {@link JButton} */
    private JButton    btnValider;

    /**Le label qui contient le score du joueur
	 * @see {@link JLabel} */
    private JLabel     lblScore;

    /**Constructeur de la frame prend un controleur en parametre 
	 * @param ctrl {@link Controleur}
	 * */
    public FrameJoueur(Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.joueur = null;

		this.setTitle    ("Joueur1");
        this.setLayout( new BorderLayout());
        this.setSize     (450, 650    );
        this.setLocation (1350, 350    );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        /*creation des composants*/
        this.lbImg       = new JLabel    (new ImageIcon("../Ressources/Hexagone.png"));
        this.panelJoueur = new JPanel    (new GridLayout(6,1));
        this.lblJoueur   = new JLabel    ("Joueur ?");
        this.txtNumDalle = new JTextField();
        this.txtNumDalle.requestFocus();
        this.txtSommet   = new JTextField();
        this.btnValider  = new JButton   ("Valider");
        this.lblScore  = new JLabel    ();
        
        /* positionement des composants */
        this            .add(this.lbImg,BorderLayout.NORTH);
        this            .add(this.panelJoueur,BorderLayout.CENTER);
        this            .add(this.lblScore, BorderLayout.SOUTH);
        this.panelJoueur.add(this.lblJoueur);
        this.panelJoueur.add(new JLabel("Dalle"));
        this.panelJoueur.add(this.txtNumDalle);
        this.panelJoueur.add(new JLabel("Sommet"));
        this.panelJoueur.add(this.txtSommet);
        this.panelJoueur.add(this.btnValider);
        

        /* actionement des composants */
        this.txtNumDalle.addKeyListener   ( new GestionTouche() );
        this.txtSommet  .addKeyListener   ( new GestionTouche() );
        this.btnValider .addActionListener(this);

        this.addComponentListener( new GereDeplacerFrame());

        this.setVisible  (false);
        this.setResizable(false);
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    /** gere le changement de joueur 
     * @param j {@link Joueur}
     */
    public void changerJoueur(Joueur j)
    {
        this.joueur = j;
        this.setTitle("Joueur"+j.getNumJoueur());
        this.lblJoueur  .setText("Joueur "+j.getNumJoueur());
        this.txtNumDalle.setText("");
        this.txtSommet  .setText("");
        this.lblScore .setText("nombre de pilier restant: "+j.getNbPilier()+"   nombre de pilier detruit: "+j.getNbPilierDetruis());
    }

    /** s'active quand le joueur clique sur le bouton 
     * @param e { @link ActionEvent} 
     */
    public void actionPerformed(ActionEvent e)
    {
        
        if(e.getSource() == this.btnValider)
        {
            this.creationPilier();
        }
        
        
    }

    /** quand la frame se fait deplacer la frame jeux se déplace avec */
    private class GereDeplacerFrame extends ComponentAdapter
	{
        /**se déclenche quand la fenetre est bougé 
         * @param e {componentEvent}
        */
		public void componentMoved (ComponentEvent  e)
		{
			FrameJoueur.this.ctrl.majLocation( 'P' );
		}
	}

    private void creationPilier()
    {
        this.lblJoueur.setText("Joueur "+ this.joueur.getNumJoueur() );

        if( this.txtNumDalle != null && this.txtNumDalle.getText().length() == 1 &&
            this.txtSommet   != null && this.txtSommet  .getText().length() == 1     )
        {
            char charDalle = this.txtNumDalle .getText().charAt(0);
            char charSommet= this.txtSommet  .getText().charAt(0);

            if( charDalle >= 'A' && charDalle <= 'P' ||
                charDalle >= 'a' && charDalle <= 'p'     )
            {
                if(charSommet >= '0' && charSommet <= '5' )
                {
                    char nomDalle = Character.toUpperCase(this.txtNumDalle.getText().charAt(0));
                    int  numPilier= Integer.parseInt(""+  this.txtSommet.getText().charAt(0));
                    
                    if ( this.ctrl.poserPilier(this.joueur.getNumJoueur(),nomDalle, numPilier) )
                    {
                        this.ctrl.changementJoueur( this.joueur );
                        this.txtNumDalle.requestFocus();
                    }
                        
                    else
                    {
                        this.lblJoueur.setText( this.lblJoueur.getText() + "   Erreur: Impossible de poser pilier."  );
                        this.txtNumDalle.requestFocus();
                    }
                        
                }
                else
                {
                    this.lblJoueur.setText( this.lblJoueur.getText() + "   Erreur: Sommet invalide."  );
                    this.txtSommet.requestFocus();
                }
                    
            }
            else
            {
                this.lblJoueur.setText( this.lblJoueur.getText() + "   Erreur: Dalle invalide."  );
                this.txtNumDalle.requestFocus();
            }
                

        }
        else
        {
            this.lblJoueur.setText( this.lblJoueur.getText() + "   Erreur: Saisie invalide."  );
            this.txtNumDalle.requestFocus();
        }
    }

    /** classe qui gere les touches*/
    private class GestionTouche extends KeyAdapter
	{
		public void keyPressed (KeyEvent  e)
		{
            
			if ( e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                if ( e.getSource() == FrameJoueur.this.txtNumDalle)
                {
                    FrameJoueur.this.txtSommet.requestFocus();
                }
                    
                else
                    FrameJoueur.this.creationPilier();
            }
        }
	}
}
