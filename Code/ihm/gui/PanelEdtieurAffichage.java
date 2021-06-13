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

public class PanelEdtieurAffichage extends JPanel
{

	private Controleur ctrl;
	private Image imgDalle;
	private Image imgDalle50;
	private int coordx ;
	private int coordy ;
	private int tabX[][] = new int [31][31];
	private int tabY[][] = new int [31][31];
	private int resX = (67-16)*tabX.length;
	private int resY = (67*tabX.length)+33;
	private JButton[][] lblDets = new JButton[31][31];
	private boolean bEstAdjacent;

	



	
	
	public PanelEdtieurAffichage(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.bEstAdjacent = false;
		this.coordx = 0;
		this.coordy = 33;
		
		initPlateau();
		initCoord();

		this.setPreferredSize((new Dimension(this.resX+34,this.resY)));
		this.setLayout(null);

		this.setBackground(new Color(0,0,0,0));



		String dalle = "../Ressources/Dalle.png";
		String dalle50 = "../Ressources/Dalle50.png";

		imgDalle = Toolkit.getDefaultToolkit().getImage( dalle );
		imgDalle50 = Toolkit.getDefaultToolkit().getImage( dalle50 );

		MediaTracker track = new MediaTracker(this);

		// 0 est l'identifiant de l'image a charger
		track.addImage(imgDalle, 0);
		track.addImage(imgDalle50, 1);

		try	{ track.waitForID(0);	}
		catch (InterruptedException e){}
		try	{ track.waitForID(1);	}
		catch (InterruptedException e){}
	}

	
	
	
	
	
	
	
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.clearRect(0,0,resX,resY);
		
		
		for(int i = 0 ; i < 31 ; i ++)
		{
			for(int y = 0 ; y < 31 ; y ++)
			{
				if(!(tabX[i][y]==-1) &&!(tabY[i][y]==-1))
				{
					FontMetrics fontMetrics = g.getFontMetrics();

					String s =""+this.ctrl.getDalle(i,y).getNom();
					g.drawImage( imgDalle, tabX[i][y] ,tabY[i][y] , null);
					g.drawString(s, (tabX[i][y]+37)-fontMetrics.stringWidth(s), tabY[i][y]+37);
				}

				if(this.ctrl.getDalle(i,y)!=null&&this.ctrl.getLastDalle()!='Q')
				{
					if(bEstAdjacent){g.drawImage( imgDalle50, this.coordx ,this.coordy, null);}
				}
				
			}
		}
	}



	public void initCoord()
	{
		for(int i = 0 ; i < this.tabX.length ; i ++)
		{
			for(int y = 0 ; y < this.tabX.length ; y ++)
			{
			  this.tabX[i][y] = -1;
			  this.tabY[i][y] = -1;
			}
		}
		this.tabX[15][15]=this.lblDets[15][15].getLocation().x;
		this.tabY[15][15]=this.lblDets[15][15].getLocation().y;
		Point pA = new Point(15,15);
		this.ctrl.setCoord(pA, this.tabX[15][15], this.tabY[15][15]);
	}

	public void initPlateau()
	{
		int x = 0 ;
		int y = 0 ;
		int tmpy = 0;
		//int nb = 0;
		boolean bOffset = true;

		for(int i = 0; i < this.lblDets.length; i ++)
		{

			for(int u = 0; u < this.lblDets.length; u ++)
			{
				JButton btn = new JButton(/*""+i+""+u*/);
				//System.out.print(i + ":" + u + " ");
				this.lblDets[i][u] = btn;
				this.add(this.lblDets[i][u]);
				this.lblDets[i][u].setLocation(x,y);
				this.lblDets[i][u].setSize(51,67);
				this.lblDets[i][u].setFont(new Font("Arial", Font.PLAIN, 7));
				this.lblDets[i][u].addMouseListener( new GestionSouris());
				this.lblDets[i][u].addMouseMotionListener( new GestionSouris());

				this.lblDets[i][u].setOpaque(false);
				this.lblDets[i][u].setContentAreaFilled(false);
				this.lblDets[i][u].setBorderPainted(false);
				
				x = x+ 67 -16;
				if(bOffset){ y = tmpy + 33; }
				else{ y = tmpy; }
				bOffset = !bOffset;
				
				
			}
			//System.out.println();
			x = 0;
			tmpy += 67 ;
			y = tmpy;
			bOffset = true;
		}
	}




	private class GestionSouris extends MouseAdapter
	{
		public void mouseEntered (MouseEvent e)
		{
			JButton btnTmp = (JButton) e.getComponent();
				for(int i = 0 ; i < tabX.length ; i ++)
				{
					for(int y = 0 ; y < tabX.length ; y ++)
					{
						if(btnTmp.equals(PanelEdtieurAffichage.this.lblDets[i][y]))
						{
							if(ctrl.aUneDalleAdjacente(i,y))
							{
								PanelEdtieurAffichage.this.coordx =(int) e.getComponent().getLocation().x;
								PanelEdtieurAffichage.this.coordy =(int) e.getComponent().getLocation().y;
								PanelEdtieurAffichage.this.bEstAdjacent = true;
								
								
							}
							else{PanelEdtieurAffichage.this.bEstAdjacent = false;}
						}
					}
				}
				

				repaint();
		}
		
		public void mouseExited (MouseEvent e)
		{

			repaint();
		}

		public void mousePressed (MouseEvent e)
		{
			if(e.getButton()==MouseEvent.BUTTON1)
			{
				repaint();
				int i = 0 ;
				int y = 0 ;		
				JButton btnTmp = (JButton) e.getComponent();
				for(JButton[] btnTab : PanelEdtieurAffichage.this.lblDets)
				{
					for(JButton btn : btnTab)
					{
						if(btnTmp.equals(btn))
						{
							
							if(ctrl.getLastDalle()!='Q')
								if(ctrl.emplacementVide(i,y))
								{
									Dalle d = new Dalle();
									if(ctrl.ajouterDalle(d,i,y))
									{
										Point p = new Point(i,y);
										ctrl.addCoord(p);
										tabX[i][y] = btn.getLocation().x;
										tabY[i][y] = btn.getLocation().y;
										ctrl.setCoord(p,tabX[i][y], tabY[i][y]);
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
			else if(e.getButton()==MouseEvent.BUTTON3)
			{
				if(ctrl.getCoordSize()>1)
				{
					
					Point p = new Point(ctrl.getLastCoord());
					tabX[p.x][p.y]=-1;
					tabY[p.x][p.y]=-1;
					ctrl.supprimerDalle(p.x, p.y);
				}
			}
		}

		public void mouseReleased(MouseEvent e){ repaint(); }
		
				

	}

	




	
}
