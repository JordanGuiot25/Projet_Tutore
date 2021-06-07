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
}