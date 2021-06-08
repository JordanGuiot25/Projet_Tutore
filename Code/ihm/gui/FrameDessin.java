package PilierDeLaTerre.ihm.gui;

import PilierDeLaTerre.ihm.gui.PanelDessin;

import PilierDeLaTerre.Controleur;

import javax.swing.*;

public class FrameDessin extends JFrame
{
	private PanelDessin panel;
	private Controleur  ctrl;

	public FrameDessin(Controleur  ctrl)
	{
		this.ctrl = ctrl;


		this.setTitle    ("Pilier de la terre");
		this.setLocation (0, 0    );
		this.setSize     (400, 350    );

		this.panel   = new PanelDessin(this.ctrl);

		this.add( this.panel );

		this.setVisible(true);
	}
}