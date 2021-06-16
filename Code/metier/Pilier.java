package Equipe_22.metier;

import java.awt.Image;
/** Cette classe gère les piliers
	*@author Jordan Guiot, Enguerrand Beltran, Raphael Lizot, Gaspard Gordien
 	*/
public class Pilier
{
	/**Couleur du pilier */
	private char  couleur;
	/**L'image des piliers */
	private Image imgPilier;
	/**les coordonées en x des piliers */
	private int   x;
	/**les coordonées en x des piliers */
	private int   y;

	// Deux possibilité soit G pour un pilier gris
	//                  soit M pour un pilier marron

	/**
	 * le constructeur de pilier
	 * @param couleur {@link char}
	 * @param x {@link int}
	 * @param y {@link int}
	 */
	public Pilier(char couleur,int x, int y)
	{
		if ( couleur == 'G' )
		{
			this.imgPilier = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/pilier_gris.png");
			
		}
		if ( couleur == 'M')
		{
			this.imgPilier = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/pilier_maron.png");
		}
		this.couleur   = couleur;
		this.x = x;
		this.y = y;
	}

	/**
	 * retourne l'imge du pilier
	 * @return imgPilier {@link Image}
	 */
	public Image getImage() { return this.imgPilier; }
	/**
	 * retourne la coordonée en x du pilier
	 * @return x {@link int}
	 */
	public int   getX()     { return this.x; }
	/**
	 * retourne la coordonée en y du pilier
	 * @return y {@link int}
	 */
	public int   getY()     { return this.y; }
	/**
	 * retourne la couleur du pilier
	 * @return couleur {@link char}
	 */
	public char  getCoul()  { return this.couleur;}
}