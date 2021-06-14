package PilierDeLaTerre.ihm.gui;
import PilierDeLaTerre.Controleur;


import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.GridLayout;

public class PanelEditeurControle extends JPanel implements ActionListener
{
	private JLabel lblMessage;
	private JButton btnLancer;
	private JButton btnRetour;
	private Controleur ctrl;

	public PanelEditeurControle(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(2,1));
		JPanel panelSave = new JPanel(new GridLayout(1,2));
		this.lblMessage = new JLabel("Joueur 1, placez une dalle",SwingConstants.CENTER);
		this.btnLancer = new JButton ("Lancer la partie");
		this.btnRetour = new JButton("Revenir au menu principal");

		this.add(this.lblMessage);
		panelSave.add(this.btnRetour);
		panelSave.add(this.btnLancer);
		this.add(panelSave);


		this.btnRetour.addActionListener(this);
		this.btnLancer.addActionListener(this);

	}
	
	public void setJoueur(boolean joueur)
	{
		if(!joueur) { this.lblMessage.setText("Joueur 1, placez une dalle"); }
		else{ this.lblMessage.setText("Joueur 2, placez une dalle");}
	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(this.btnRetour))
		{
			this.ctrl.retour();		
		}
		else if(e.getSource().equals(this.btnLancer)&&this.ctrl.getLastDalle()>='P')
		{
			this.ctrl.lancerPartieCustom();
		}
		
	}
}
