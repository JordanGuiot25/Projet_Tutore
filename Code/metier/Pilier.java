package Equipe_22.metier;

import java.awt.Image;

public class Pilier
{
	private char  couleur;
	private Image imgPilier;
	private int   x;
	private int   y;

	// Deux possibilité soit G pour un pilier gris
	//                  soit M pour un pilier marron

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

	public Image getImage() { return this.imgPilier; }

	public int   getX()     { return this.x; }
	public int   getY()     { return this.y; }
	public char  getCoul()  { return this.couleur;}
}