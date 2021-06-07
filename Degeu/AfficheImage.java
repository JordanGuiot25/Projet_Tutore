import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.*;

public class AfficheImage extends JFrame implements ActionListener
{
    private Image imgDalle;
	private Image imgDalle50;
	private int coordx ;
	private int coordy ;
	private int resX = (67-16)*10;
	private int resY = (67*11);
	private JButton[][] lblDets = new JButton[10][10];
	private JPanel panelDetection = new JPanel();

    public AfficheImage()
    {
       this.coordx = 0;
       this.coordy = 33;
       this.setTitle   ("AfficheImage");
       this.setLocation(500,500);
       this.setSize    (resX,resY);

      
       this.panelDetection.setLayout(null);

		//initCoord();
		initLabel();
		this.panelDetection.setBackground(new Color(0,0,0,0));
       
       


       /*lblTest.addMouseListener( new GestionSouris());
       lblTest.addMouseMotionListener( new GestionSouris());*/
       add(this.panelDetection);
       String dalle = "../img/Dalle.png";
	   String dalle50 = "../img/Dalle50.png";

       imgDalle = Toolkit.getDefaultToolkit().getImage( dalle );
	   imgDalle50 = Toolkit.getDefaultToolkit().getImage( dalle50 );

       MediaTracker track = new MediaTracker(this);

       // 0 est l'identifiant de l'image a charger
       track.addImage(imgDalle, 0);
	   track.addImage(imgDalle50, 1);

		try   { track.waitForID(0);    }
		catch (InterruptedException e){}
		try   { track.waitForID(1);    }
		catch (InterruptedException e){}

       this.setVisible(true);
       

       this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

   public void paint (Graphics g)
    {
		g.clearRect(0,0,resX,resY);
		g.drawImage( imgDalle50, this.coordx ,this.coordy + 33 , null);
		/*for(int i = 0 ; i < tabX.length ; i ++)
		{
			for(int y = 0 ; y < tabX.length ; y ++)
			{
				if(!(tabX[i][y]==null) &&!(tabY[i][y]==null))
				{
					//g.drawImage( imgDalle, tabX[i][y] ,tabY[i][y] , null);
				}
			}
		}*/
		//g.drawImage( imgDalle, 0 ,33 , null);
		//g.drawImage( imgDalle50, 0 ,100 , null);
		//g.drawImage( imgDalle50, 49 ,67 , null);
		
		//g.drawImage( imgDalle50, 100 ,33 , null);
		//g.drawImage( imgDalle50, 50,67 , null);
		//g.drawImage( imgDalle50, 149 , 67, null);
    }
    

    public static void main(String [] a)
    {
        new AfficheImage();
    }
    

	public void actionPerformed(ActionEvent e)
	{
		repaint();
		
		
	}
	
	private class GestionSouris extends MouseAdapter
	{
		public void mouseEntered (MouseEvent e)
		{
			AfficheImage.this.coordx =(int) e.getComponent().getLocation().x;
			AfficheImage.this.coordy =(int) e.getComponent().getLocation().y;
			repaint();
			//System.out.println("ok");

		}
		
		public void mouseExited (MouseEvent e)
		{
			repaint();
		}
		
				

	}
	
	
	public void initLabel()
	{
		int x = 0 ;
		int y = 0 ;
		int tmpy = 0;
		boolean bOffset = true;
		
		for(JButton[] l : this.lblDets)
		{
			for(JButton  lb : l)
			{
				lb = new JButton();
				this.panelDetection.add(lb);
				lb.setLocation(x,y);

				lb.setSize(51,67);
				lb.addMouseListener( new GestionSouris());
				lb.addMouseMotionListener( new GestionSouris());
				lb.addActionListener(this);	
				lb.setOpaque(false);
				lb.setContentAreaFilled(false);
				lb.setBorderPainted(false);
				
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
