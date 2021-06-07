import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.event.*;
import java.awt.Toolkit;

public class GuiJeu extends JFrame //implements ActionListener
{
	//private Controleur ctrl;
	private Image imgDalle;
	private Image imgDalle50;
	private int coordx ;
	private int coordy ;
	private int resX = (67-16)*10;
	private int resY = (67*11);
	private JButton[][] lblDets = new JButton[10][10];
	private JPanel panelDetection = new JPanel();
	private int tabX[][] = new int [10][10];
	private int tabY[][] = new int [10][10];


	/*public static void main(String[]agrs)
	{
		new GuiJeu();
	}*/

	public GuiJeu(/*Controleur ctrl*/)
	{
		//this.ctrl = ctrl;
		this.coordx = 0;
		this.coordy = 33;
		this.setTitle	("GuiJeu");
		this.setLocation(500,500);
		this.setSize	(resX,resY);

		
		
		for(int i = 0 ; i < tabX.length ; i ++)
		{
			for(int y = 0 ; y < tabX.length ; y ++)
			{
			  tabX[i][y] = -1;
			  tabY[i][y] = -1;
			}
		}

		this.panelDetection.setLayout(null);


		initPlateau();
		this.panelDetection.setBackground(new Color(0,0,0,0));
		
		
		add(this.panelDetection);
		String dalle = "../img/Dalle.png";
		String dalle50 = "../img/Dalle50.png";

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

		this.setVisible(true);
		

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	public void paint (Graphics g)
	{
		g.clearRect(0,0,resX,resY);
		g.drawImage( imgDalle50, this.coordx ,this.coordy + 33 , null);
		for(int i = 0 ; i < tabX.length ; i ++)
		{
			for(int y = 0 ; y < tabX.length ; y ++)
			{
				if(!(tabX[i][y]==-1) &&!(tabY[i][y]==-1))
				{
					g.drawImage( imgDalle, tabX[i][y] ,tabY[i][y]+33 , null);
				}
			}
		}
	}
	
	
	private class GestionSouris extends MouseAdapter
	{
		public void mouseEntered (MouseEvent e)
		{
			GuiJeu.this.coordx =(int) e.getComponent().getLocation().x;
			GuiJeu.this.coordy =(int) e.getComponent().getLocation().y;
			repaint();
		}
		
		public void mouseExited (MouseEvent e)
		{
			repaint();
		}

		public void mousePressed (MouseEvent e)
		{
			repaint();
			int i = 0 ;
			int y = 0 ;		
			JButton btnTmp = (JButton) e.getComponent();
			for(JButton[] btnTab : GuiJeu.this.lblDets)
			{
				for(JButton btn : btnTab)
				{
					if(btnTmp.equals(btn))
					{
						tabX[i][y] = btn.getLocation().x;
						tabY[i][y] = btn.getLocation().y;
					}
					y++;
				}
				y = 0 ;
				i++;
			}
			repaint();
		}

		public void mouseReleased(MouseEvent e)
		{
			repaint();
		}
		
				

	}
	

	
	public void initPlateau()
	{
		int x = 0 ;
		int y = 0 ;
		int tmpy = 0;
		boolean bOffset = true;

		for(int i = 0; i < this.lblDets.length; i ++)
		{

			for(int u = 0; u < this.lblDets.length; u ++)
			{
				JButton btn = new JButton();
				this.lblDets[i][u] = btn;
				this.panelDetection.add(this.lblDets[i][u]);
				this.lblDets[i][u].setLocation(x,y);
				this.lblDets[i][u].setSize(51,67);
				
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
			x = 0;
			tmpy += 67 ;
			y = tmpy;
		}
	}
}
