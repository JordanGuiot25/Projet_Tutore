package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.ihm.gui.FrameScenario;
import PilierDeLaTerre.Controleur;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


import java.awt.BorderLayout;

import java.awt.event.*;
import java.util.ArrayList;


public class PanelScenario extends JPanel implements ActionListener
{
	private static int NB_SCENARIO = 6;

	private Controleur ctrl;

	private JComboBox<String> cboxScenario;
	private JButton           btnJouer;
	private Image  imgFond;

	public PanelScenario(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout   ( new BorderLayout() );
		this.setOpaque(false);

		this.imgFond = java.awt.Toolkit.getDefaultToolkit().getImage( "../Ressources/fond.png" );

		String[] tabString = new String [this.NB_SCENARIO];

		for(int i = 0 ; i < 6 ; i++)
			tabString[i] = "Scénario " + i;

		//Creation des composants
		this.cboxScenario = new JComboBox<String>(  tabString );
		this.btnJouer     = new JButton("Jouer");
		JPanel panelBox   = new JPanel();
		panelBox.setOpaque(true);
     
	 	//Positionnement des composants
		panelBox.add(new JLabel("Scénario à lancer :"));
		panelBox.add(this.cboxScenario);
		

		this.add( panelBox     ,BorderLayout.NORTH );
		this.add( this.btnJouer,BorderLayout.SOUTH  );


		//Activation des compossants
		this.cboxScenario.addActionListener(this);
		this.btnJouer.addActionListener(this);

		repaint();
	}


	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.btnJouer)
		{
			String sText = ""+this.cboxScenario.getSelectedItem();
			int numTmp = Integer.parseInt(sText.substring(9));
			this.ctrl.setScenario(numTmp);

			this.ctrl.lancerScenario();
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage ( this.imgFond, 0, 20, this );

	}
}