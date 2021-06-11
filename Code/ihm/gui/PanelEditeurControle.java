package PilierDeLaTerre.ihm.gui;
import PilierDeLaTerre.metier.EditeurParterre;

import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.GridLayout;

public class PanelEditeurControle extends JPanel implements ActionListener
{
	private JLabel lblMessage;
	private JButton btnLancer;
	private JButton btnRetour;
	private EditeurParterre editeurParterre;

	public PanelEditeurControle(EditeurParterre editeurParterre)
	{
		this.editeurParterre = editeurParterre;
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


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(this.btnRetour))
		{
			this.editeurParterre.retour();		
		}
		else if(e.getSource().equals(this.btnLancer)&&this.editeurParterre.getLastDalle()>='P')
		{
			System.out.println("ojoij");
			this.editeurParterre.lancerPartie();
		}
		
	}
}
