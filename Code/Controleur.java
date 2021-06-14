package Equipe_22;

import Equipe_22.ihm.gui.FrameDessin;
import Equipe_22.ihm.gui.FrameJoueur;
import Equipe_22.ihm.gui.FrameMenu;
import Equipe_22.ihm.gui.FrameScenario;
import Equipe_22.ihm.gui.FrameControleEditeur;
import Equipe_22.ihm.gui.FrameEditeurParterre;
import Equipe_22.ihm.cui.IhmCui;
import Equipe_22.metier.Dalle;
import Equipe_22.metier.Parterre;
import Equipe_22.metier.Scenario;
import Equipe_22.metier.EditeurParterre;

import iut.algo.Clavier;

import java.util.ArrayList;

import java.awt.Point;

import java.io.PrintWriter;
import java.io.FileOutputStream;

import Equipe_22.metier.Dalle;
import Equipe_22.metier.Joueur;

/**Controleur de l'application
 * @author Jordan Guiot, Enguerrand Beltran, Raphael Lizot, Gaspard Gordien
 */
public class Controleur
{
	private FrameDessin ihm    ;
	private FrameMenu   ihmMenu;
	private FrameJoueur ihmJoueur;
	private FrameScenario ihmScenario;
	private IhmCui      ihmCui;
	private Parterre    metier ;
	private boolean 	bDebutPartie;
	private int         numScenario;
	private EditeurParterre editeurParterre;
	private FrameEditeurParterre frameEditeurParterre;
	private FrameControleEditeur frameControleEditeur;

	/**Constructeur du controleur */
	public Controleur()
	{
		this.ihmMenu  = new FrameMenu   (this);
		this.ihm      = new FrameDessin (this);
		this.ihmJoueur= new FrameJoueur (this);
		this.ihmScenario = new FrameScenario(this);
		

		this.bDebutPartie = false;
		while( !this.bDebutPartie )
		{
			System.out.print("");
		}
		this.ihmJoueur.changerJoueur(this.metier.getJoueur(1));
		this.ihmCui   = new IhmCui (this.metier);

	}
	/**Méthode servant à vérifier la victoire d'un des 2 joueurs */
	public void verificationVictoire()
	{
		if( this.metier.getGagnant() != null )
		{	
			System.out.println("FIN DE LA PARTIE");
			System.out.println("Le gagnant est le joueur :" + this.metier.getGagnant().getNumJoueur() );
			this.ihmJoueur.setVisible(false);
		}
	}
	/**Méthode pour poser un pilier
	 * @param  numJoueur {@link Int }
	 * @param  nomDalle  {@link Char}
	 * @param  numSommet {@link Int }
	 * @return {@link Boolean}
	 */
	public boolean poserPilier(int numJoueur, char nomDalle, int numSommet )
	{
		if ( this.metier.poserPilier(numJoueur,nomDalle,numSommet) )
		{

			this.metier.setNumJoueur(numJoueur);

			this.ihm.miseAJourGrille();
			this.metier.finTourJoueur(nomDalle, numSommet, this.metier.getJoueur(numJoueur).getCouleur());
			this.ihm.miseAJourGrille();

			System.out.println(this.ihmCui);

			this.verificationVictoire();

			return true;
		}

		return false;
	}
	/**Methode permettant d'alterner le joueur qui pose la dalle en partie custom 
	 * @param joueurPrc {@link Joueur}
	*/
	public void changementJoueur(Joueur joueurPrc )
	{
		if( joueurPrc == this.metier.getJoueur(1) )
		{
			this.ihmJoueur.changerJoueur( this.metier.getJoueur(2) );
		}
		else
		{
			this.ihmJoueur.changerJoueur( this.metier.getJoueur(1) );
			this.metier.prochainTour();
		}
			
	}
	/**Méthode pour mettre à jour le grille */
	public void maj()
	{
		this.ihm.miseAJourGrille();
	}
	/* Méthode qu'on a essayé de faire mais pas eu le temps
	public void Sauvegarde()
	{
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("../Scenario/Sauvegarde.txt") );
			pw.println ( this.metier.getSauvegarde());
			pw.close();
		}
		catch(Exception e){ e.printStackTrace(); }
	}
	*/
	public void MAJ()
	{
		this.ihm.miseAJourGrille();
	}
	/**Méthode permettant de faire deplacer les 2 frames ensemble 
	 * @param Destination {@link Char}
	*/
	public void majLocation(char Destination)
	{
		if( Destination == 'P' && this.ihm != null & this.ihmJoueur != null)
		{
			Point p = this.ihmJoueur.getLocation();
			this.ihm.setLocation(p.x-1000, p.y);
		}

		if( Destination == 'A' && this.ihm != null & this.ihmJoueur != null)
		{
			Point p = this.ihm.getLocation();
			this.ihmJoueur.setLocation (p.x+1000, p.y);
		}
	}

	/**Méthode permettant de lancer une partie rapide avec une map prédéfinie */
	public void LancerPartieRapide()
	{
		this.ihmMenu.setVisible(false);

		this.metier = Scenario.getScenario(0);

		this.ihm    .setVisible(true);
		this.ihmJoueur.setVisible(true);

		this.bDebutPartie = true;

	}
	/**Méthode permettant de lancer une partie custom */
	public void lancerPartieCustom()
	{
		if(this.editeurParterre.getLastDalle()>'P')
		{
			this.metier = new Parterre(this.editeurParterre.getNiveau(), new Joueur(1,'M'), new Joueur(2,'G'), 1);
			this.frameControleEditeur.setVisible(false);
			this.frameEditeurParterre.setVisible(false);
			this.ihm.setVisible(true);
			this.ihmJoueur.setVisible(true);
			this.bDebutPartie = true;
		}
	}
	/**Méthode permettant d'initialiser la map de la partie custom avant son lancement */
	public void partieCustom()
	{
		
		this.editeurParterre = new EditeurParterre(this);
		this.ihmMenu.setVisible(false);
		this.frameEditeurParterre = new FrameEditeurParterre(this);
		this.frameEditeurParterre.setLocation(500, 100);
		this.frameControleEditeur = new FrameControleEditeur(this);
		this.frameControleEditeur.setLocation(500,100+this.frameEditeurParterre.getSize().height);
		
	}
	/**Méthode permettant de revenir dans le menu principal
	 * @param choixIhm {@link Char}
	 */
	public void retour(char choixIhm)
	{
		if(choixIhm == 'e')
		{	
			this.editeurParterre.retour();
			this.frameControleEditeur.setVisible(false);
			this.frameEditeurParterre.setVisible(false);
		}
		else
		{
			this.ihmScenario.setVisible(false);
		}
		this.ihmMenu.setVisible(true);
	}
	/**Méthode permettant de quitter l'application */
	public void quitter()
	{
		System.exit(0);
	}
	/**Méthode permettant de set un numéro de scénario
	 * @param num {@link Int}
	 */
	public void setScenario(int num)
	{
		this.numScenario = num;
	}
	/**Méthode permettant de lancer une partie avec un scénario */
	public void lancerScenario()
	{
		this.ihmScenario.setVisible(false);
		this.metier = Scenario.getScenario(this.numScenario);
		this.ihm    .setVisible(true);
		this.ihmJoueur.setVisible(true);
		this.bDebutPartie = true;

	}
	/**Méthode permettant d'afficher la fenêtre pour selectionner son scénario avant le lancement de la partie */
	public void scenario()
	{
		this.ihmMenu.setVisible(false);
		this.ihmScenario.setVisible(true);
	}
	/**Méthode permettant de récuperer le tableau de la map
	 * @return Arraylist<Dalle> 
	 */
	public ArrayList<Dalle> getGrilleDalles() 
	{
		return this.metier.getGrilleDalles();
	}
	/**Méthode permettant de savoir si la partie est lancé
	 * @return boolean 
	 */
	private boolean getBDebutPartie() { return this.bDebutPartie;}

	/**Méthode permettant de déplacer les frames qui servent à créer la partie custom
	 * @param posX {@link Double}
 	 * @param posY {@link Double}
	 * @param frame{@link Char  }
	 */
	public void DeplacerFrames(double posX, double posY, char frame)
	{
		if(frame == 'c' && this.frameEditeurParterre != null && this.frameControleEditeur != null)
		{
			frameEditeurParterre.setLocation((int) posX, (int) posY);
		}
		if(frame == 'e' && this.frameEditeurParterre != null && this.frameControleEditeur != null)
		{
			frameControleEditeur.setLocation( (int) posX , (int) posY);
		}
	}
	/**retourne le nom de la dernière dalle posée
	 * @return char
	 */
	public char getLastDalle() {return this.editeurParterre.getLastDalle();}
	/**retourne la dalle aux coordonées x y
	 * @param i int
	 * @param y int
	 * @return {@link Dalle}
	 */
	public Dalle getDalle(int i, int y) {return this.editeurParterre.getDalle(i, y);}
	/**Attribue les coordonées a une Dalle
	 * @param p {@link Point}
	 * @param coordX int
	 * @param coordY int
	 */
	public void setCoord(Point p, int coordx, int coordy) {this.editeurParterre.setCoord(p, coordx, coordy);}
	/**verifie si il a une dalle adajcente autour des coordonées rentrées en parametre
	 * @param p {@link Point}
	 * @param x int
	 * @param y int
	 * @return boolean
	 */
	public boolean aUneDalleAdjacente(int x, int y) {return this.editeurParterre.aUneDalleAdjacente(x, y);}
		/**verifie si l'emplacement des coordonées rentrées en parametre est vide
	 * @param x int
	 * @param y int
	 * @return boolean
	 */
	public boolean emplacementVide(int x,int y) {return this.editeurParterre.emplacementVide(x, y);}
	/** Ajoute les coordonées a la dalle aux indices contenu dans le Point
	 * @param point {@link Point}
	*/
	public void addCoord(Point point) {this.editeurParterre.addCoord(point);}
	/**Ajoute une Dalle aux coordonées entrées en parametre et renvoi si c'est possible ou non
	 * @param dalle {@link Dalle}
	 * @param x int
	 * @param y int
	 * @return boolean
	 */
	public boolean ajouterDalle(Dalle dalle, int x, int y) {return this.editeurParterre.ajouterDalle(dalle, x, y);}
	/** retourne les dernière coordonées ajouté a la liste
	 * @return {@link Point}
	*/
	public Point getLastCoord() {return this.editeurParterre.getLastCoord();}
	/** retourne la Taille de la liste de coordonées
	 * @return {@link Int}
	*/
	public int getCoordSize() {return this.editeurParterre.getCoordSize();}
	/**supprime la Dalle aux indice x,y
	 * @param x int
	 * @param y int
	 */
	public void supprimerDalle( int x, int y) {this.editeurParterre.supprimerDalle(x, y);}
	/**Indique a l'editeur quel joueur doit posé une dalle
	 * @param joueur {@link boolean}
	 */
	public void setJoueur(boolean joueur) {this.frameControleEditeur.setJoueur(joueur);}
	
	
	
	public static void main (String[] a)
	{
		new Controleur();
	}
}