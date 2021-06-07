import javax.swing.*;

public class FrameDessin extends JFrame
{
	private PanelDessin panel;
	private Controleur  ctrl;

	public FrameDessin(Controleur  ctrl)
	{
		this.ctrl = ctrl;


		this.setTitle    ("Hexagonale");
		this.setLocation (0, 0    );
		this.setSize     (500, 500    );

		this.panel   = new PanelDessin(this.ctrl);

		this.add( this.panel );

		this.setVisible(true);
	}

	public void ajoutPilier(int numSommet)
	{
		this.panel.ajoutPilier(numSommet);
	}

	public void detruirePillier(int numSommet)
	{
		this.panel.detruirePillier(numSommet);
	}
}