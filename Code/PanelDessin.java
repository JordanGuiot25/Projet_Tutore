import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class PanelDessin extends JPanel
{
	private static final Image RESSOURCE_DALLE  = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/Dalle.png");

	private int xPilier;
	private int yPilier;

	private Dalle dalle;

	private Controleur  ctrl;

	public PanelDessin(Controleur  ctrl)
	{
		this.setSize(100,100);

		this.ctrl  = ctrl;
		this.dalle = new Dalle(200,50);

	}

	public void ajoutPilier(int numSommet)
	{
		this.dalle.rajoutPillier(numSommet);

		this.repaint();
	}

	public void detruirePillier(int numSommet)
	{
		this.dalle.detruirePillier(numSommet);

		this.repaint();
	}


	public void paint(Graphics g)
	{
		Image imgPilier = java.awt.Toolkit.getDefaultToolkit().getImage("../Ressources/pilier_gris.png");
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage ( PanelDessin.RESSOURCE_DALLE, this.dalle.getX(), this.dalle.getY(), this );

		Pilier[] tabSommet = this.dalle.getSommets();

		for( Pilier sommet : tabSommet )
		{
			if( sommet != null )
			{
				g2.drawImage( sommet.getImage(), sommet.getX(), sommet.getY(), this);
			}
		}

		/*if ( this.xPilier == 0 && this.yPilier == 0)
			g2.drawImage ( PanelDessin.RESSOURCE_DALLE, this.dalle.getX(), this.dalle.getY(), this );
		else
			g2.drawImage( imgPilier, this.xPilier, this.yPilier, this);*/
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		
	}

	
}