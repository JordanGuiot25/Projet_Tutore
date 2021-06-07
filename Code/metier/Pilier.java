package PilierDeLaTerre.metier;

import java.awt.Image;

public class Pilier
{
	private Image imgPilier;
	private int x;
	private int y;

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
			this.imgPilier = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/pilier_marron.png");
		}
		
		this.x = x;
		this.y = y;
	}

	public Image getImage() { return this.imgPilier; }

	public int   getX()     { return this.x; }
	public int   getY()     { return this.y; }
}