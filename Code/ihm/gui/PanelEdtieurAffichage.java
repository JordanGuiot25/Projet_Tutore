package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.Controleur;
import PilierDeLaTerre.metier.Dalle;

import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.FontMetrics;

/**
 * Le Panel Affichant l'editeur de parterre
 * @author Enguerrand Beltran, Raphael Lizot, Gaspard Gordien, Jordan Guiot
 * 
 */

public class PanelEdtieurAffichage extends JPanel
{

	/**Le Controleur de l'appilcation du Panel
	 * @see {@link Controleur} */
	private Controleur ctrl;
	/**L'image de la Dale
	 * @see {@link Image} */
	private Image imgDalle;
	/**L'image de la Dalle 50% transparante
	 * @see {@link Image} */
	private Image imgDalle50;
	/**Coordonées du "fantôme" de la Dalle que l'on veut placer
	 * @see {@link Point}*/
	private Point coordFantome ;
	/**Tableau des coordonées des Dalles visibles (il a une taille de 31 pour prendre en compte toute les possiblité de parterre differents)
	 * @see {@link Point}*/
	private Point[][] tabCoordDalleVisbile = new Point [31][31];
	/** Taille en largeur du panel calculée en fonction de la largeur des images moins les partie qui se superposent multiplié par leur nombre*/
	private final int RES_X = (67-16)*tabCoordDalleVisbile.length;
	/** Taille en hauteur du panel calculée en fonction de la hauteur des images multiplié par leur nombre*/
	private final int RES_Y = (67*tabCoordDalleVisbile.length)+33;
	/**Tableau de Bouton servant uniquement a la detection de la souris dans les zones ou on peut placer une Dalle
	 * @see {@link JButton} */
	private JButton[][] btnDetecteurs = new JButton[31][31];
	/**Booléen qui indique si oui ou non la souris est dans une zone adjacente a une Dalle*/
	private boolean bEstAdjacent;
	/**boolean gerant quel joueur place une dalle faux = joueur 1, vrai = joueur 2*/
	private boolean joueur;

	



	
	/**Constructeur du panel qui affiche le parterre personalisable, initialise le plateau, les coordées, les bouttons de detection et charge les images
	 * @param ctrl {@link Controleur}
	 * */
	public PanelEdtieurAffichage(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.bEstAdjacent = false;
		this.coordFantome = new Point(0,33);
		this.joueur = false ; 

		initPlateau();
		initCoord();

		this.setPreferredSize((new Dimension(this.RES_X+34,this.RES_Y)));
		this.setLayout(null);
		this.setBackground(new Color(0,0,0,0));

		String dalle = "../Ressources/Dalle.png";
		String dalle50 = "../Ressources/Dalle50.png";

		imgDalle = Toolkit.getDefaultToolkit().getImage( dalle );
		imgDalle50 = Toolkit.getDefaultToolkit().getImage( dalle50 );

		MediaTracker track = new MediaTracker(this);
		track.addImage(imgDalle, 0);
		track.addImage(imgDalle50, 1);

		try	{ track.waitForID(0);	}
		catch (InterruptedException e){}
		try	{ track.waitForID(1);	}
		catch (InterruptedException e){}
	}

	
	
	
	
	
	
	
	/**Peint le panel avec les images des dalles*/
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.clearRect(0,0,RES_X,RES_Y);//On efface le panel avant de le peindre (parce que le panel est dans un JScrollPane super.paintComponent n'efface rien)
		
		
		//on parcours chaque indice du tableau de coordonées des dalles qui doivent être afficher
		for(int i = 0 ; i < 31 ; i ++)
		{
			for(int y = 0 ; y < 31 ; y ++)
			{

				if(!(tabCoordDalleVisbile[i][y].x==-1) &&!(tabCoordDalleVisbile[i][y].y==-1))//si les coordonées sont égales a -1 on n'affiche rien
				{
					FontMetrics fontMetrics = g.getFontMetrics();//On utilise un FontMetrics pour centrer le plus possible les lettre en fonction de leur largeur

					String s =""+this.ctrl.getDalle(i,y).getNom();//on récupère le nom de la Dalle
					g.drawImage( imgDalle, tabCoordDalleVisbile[i][y].x ,tabCoordDalleVisbile[i][y].y , null);//on dessine les visibles
					g.drawString(s, (tabCoordDalleVisbile[i][y].x+37)-fontMetrics.stringWidth(s), tabCoordDalleVisbile[i][y].y+37);//on dessine leur noms
				}

				if(this.ctrl.getDalle(i,y)!=null&&this.ctrl.getLastDalle()!='Q')//si la dernière dalle n'a pas été posée
				{
					if(bEstAdjacent){g.drawImage( imgDalle50, this.coordFantome.x ,this.coordFantome.y, null);}//Affichage de la dalle semi opaque au coordonées du bouton que la souris survole
				}
				
			}
		}
	}


	/**Initialise le tableau de coordonées de Dalle visible a -1 sauf pour la cellule du millieu pour la Dalle 'A'*/
	public void initCoord()
	{
		for(int i = 0 ; i < this.tabCoordDalleVisbile.length ; i ++)
		{
			for(int y = 0 ; y < this.tabCoordDalleVisbile.length ; y ++)
			{
				this.tabCoordDalleVisbile[i][y] = new Point (-1,-1);//on met toute les coordnées a -1
			}
		}
		this.tabCoordDalleVisbile[15][15] = this.btnDetecteurs[15][15].getLocation();//on recupere les coordonées du bouton représentant la dalle A pour les mettre dans la tableau de coordonées visible
		Point pA = new Point(15,15);
		this.ctrl.setCoord(pA, this.tabCoordDalleVisbile[15][15].x, this.tabCoordDalleVisbile[15][15].y);//on envoie les coordonée pour le meiter du jeu en lui même
	}

	/**Initialise les boutons de detection des dalles */
	public void initPlateau()
	{
		int x = 0 ;
		int y = 0 ;
		int tmpy = 0;
		boolean bOffset = true;//boolean gérant le décalage en hauteur des bouton car la ligne d'hexagone n'est pas rectiligne

		for(int i = 0; i < this.btnDetecteurs.length; i ++)
		{

			for(int u = 0; u < this.btnDetecteurs.length; u ++)
			{
				JButton btn = new JButton();
				this.btnDetecteurs[i][u] = btn;
				this.add(this.btnDetecteurs[i][u]);
				this.btnDetecteurs[i][u].setLocation(x,y);
				this.btnDetecteurs[i][u].setSize(51,67);
				this.btnDetecteurs[i][u].setFont(new Font("Arial", Font.PLAIN, 7));
				this.btnDetecteurs[i][u].addMouseListener( new GestionSouris());//on y ajoute des mouseListener pour que la souris sait sur quoi elle clique
				this.btnDetecteurs[i][u].addMouseMotionListener( new GestionSouris());//on y ajoute des mouseListener pour que la souris sait quand est-ce quelle les survolle
				//apartir d'ici on rend les boutons invisbles
				this.btnDetecteurs[i][u].setOpaque(false);
				this.btnDetecteurs[i][u].setContentAreaFilled(false);
				this.btnDetecteurs[i][u].setBorderPainted(false);
				
				x = x+ 67 -16;
				if(bOffset){ y = tmpy + 33; }//si le bouton doit être décalé on le descent de 33 pixel
				else{ y = tmpy; }
				bOffset = !bOffset;//a chaque bouton ajouté on alterne le decalage
				
				
			}
			x = 0;
			tmpy += 67 ;
			y = tmpy;
			bOffset = true;//chaque nouvelle ligne le décalage est remis a zéro 
		}
	}



	/** Gère la souris*/
	private class GestionSouris extends MouseAdapter
	{
		/**Detecte si la souris entre sur un bouton */
		public void mouseEntered (MouseEvent e)
		{
			JButton btnTmp = (JButton) e.getComponent();
				for(int i = 0 ; i < tabCoordDalleVisbile.length ; i ++)
				{
					for(int y = 0 ; y < tabCoordDalleVisbile.length ; y ++)
					{
						if(btnTmp.equals(PanelEdtieurAffichage.this.btnDetecteurs[i][y]))
						{
							if(ctrl.aUneDalleAdjacente(i,y))//si il y a une dalle adjacante a ce bouton le fantôme est affiché
							{
								PanelEdtieurAffichage.this.coordFantome = e.getComponent().getLocation();
								PanelEdtieurAffichage.this.bEstAdjacent = true;
							}
							else{PanelEdtieurAffichage.this.bEstAdjacent = false;}
						}
					}
				}
				

				repaint();
		}
		
		/**detect si la souris sort d'un bouton*/
		public void mouseExited (MouseEvent e)
		{

			repaint();// on efface le fanôme
		}

		/**detecte un clique de souris*/
		public void mousePressed (MouseEvent e)
		{
			if(e.getButton()==MouseEvent.BUTTON1)//si c'est le bouton gauche
			{
				repaint();
				int i = 0 ;
				int y = 0 ;
				JButton btnTmp = (JButton) e.getComponent();
				for(JButton[] btnTab : PanelEdtieurAffichage.this.btnDetecteurs)
				{
					for(JButton btn : btnTab)
					{
						if(btnTmp.equals(btn))//on fait correspondre le bouton sur lequelle on a cliquer avec sa position dans le tableau
						{
							
							if(ctrl.getLastDalle()!='Q')//on verifie si on a le droit d'ajouter encore une Dalle
								if(ctrl.emplacementVide(i,y))//on verifie qu'il n'y a pas de Dalle a cet emplacement
								{
									Dalle d = new Dalle();
									if(ctrl.ajouterDalle(d,i,y))//Si la dalle est ajouté au parterre
									{
										Point p = new Point(i,y);
										ctrl.addCoord(p);
										tabCoordDalleVisbile[i][y] = btn.getLocation();
										ctrl.setCoord(p, tabCoordDalleVisbile[i][y].x, tabCoordDalleVisbile[i][y].y);
										joueur = !joueur;
										ctrl.setJoueur(joueur);
									}
									else{d.supprimerDalle();}
								}
						}
						y++;
					}
					y = 0 ;
					i++;
				}
				repaint();
			}
			else if(e.getButton()==MouseEvent.BUTTON3)//si on clique sur le bouton droit
			{
				if(ctrl.getCoordSize()>1)//on verifie que l'on a le droit d'enlever une dalle
				{
					
					Point p = new Point(ctrl.getLastCoord());
					tabCoordDalleVisbile[p.x][p.y].x = -1 ;//
					tabCoordDalleVisbile[p.x][p.y].y = -1 ;//on déaffiche les dalles
					ctrl.supprimerDalle(p.x, p.y);//on supprime la dalle
					joueur = !joueur;//on revient au tour précédent
					ctrl.setJoueur(joueur);
				}	
			}
		}

		public void mouseReleased(MouseEvent e){ repaint(); }//on actualise l'affichage dès que l'on relache le clique
		
				

	}

	




	
}
