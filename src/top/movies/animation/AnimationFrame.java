package top.movies.animation;

import javax.swing.JFrame;

public class AnimationFrame extends JFrame {
	
	private static final long serialVersionUID = 984277171261900595L;
	
	// pre-define variable for the animation panel
	AnimationPanel animationPanel;
	
	public AnimationFrame() {
		
		// instantiate new JPanel
		animationPanel = new AnimationPanel();
		
		// sets JFrame to not close when the exit button is closed - we do not want the user to close the animation during loading
		// as this could cause thread problems. It would also prevent masking of loading times
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// adds JPanel to this defined JFrame
		this.add(animationPanel);
		// fits the window to the preferred size of subcomponents
		this.pack();
		// set is so user can not resize image - user should not be able to see behind the scenes of image processing
		this.setResizable(false);
		// display JFrame in centre of the user's screen
		this.setLocationRelativeTo(null);
		// set it to be visible to the user
		this.setVisible(true);
		
	}

}
