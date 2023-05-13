package top.movies.screens;

import static java.awt.event.KeyEvent.VK_ESCAPE;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TutorialScreen  extends JFrame{

	private static final long serialVersionUID = -8901611422183417688L;
	
	JFrame mainFrame;
	JPanel detailsPanel;
	
	Color gold = new Color(255, 215, 0);
	
	TutorialScreen() {

		// create the main frame of this part of the GUI
		mainFrame = new JFrame("Tutorial");
		mainFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == VK_ESCAPE) { // if the button pressed is the 'escape' button
					mainFrame.dispose(); // dispose of GUI frame
			    	System.exit(0);  // terminate java application
		        }
			}
		});
		
		detailsPanel = new JPanel();
		detailsPanel.setLayout(null);
		
		// < -------------------------------------------------- >
		// by setting text in html tags it automatically formats the text to be the correct size of the window
		String tutorialText = "<html><div style='text-align: center;'>" + "Use this application to store all your favourite movies in a ranked list so you can remember you viewing history and which cinematic masterpieces you liked the best! Come back to the list whenever you like because the movies are saved for your future self!" + "<br /><br />" + 
				"Use the ‘next’ and ‘previous’ buttons to flick between pages of your favourite movies." + "<br /><br />" + 
				"Use the exit button to close the program whenever you want."+ "<br /><br />" + 
				"Overwrite the list feature:"+ "<br /><br />" + 
				"1.	Overwrite – this lets you add a new movie at the specified position. Or you can overwrite an movie already in the list meaning you can essentially remove a movie."+ "<br /><br />" + 
				"2.	Slot in movie – if you watch a new movie and it’s your new all-time favourite, go to rank one, enter your details, and ‘slot in’ the movie. This will enter your new movie at rank 1oneand move the existing movie all up one rank!" + "</div></html>";
		
		JLabel tutorialTextLabel = new JLabel(tutorialText);
		tutorialTextLabel.setBounds(70, 0, 535, 283);
        detailsPanel.add(tutorialTextLabel);
		
		// set background image		
		JLabel backgroundImageLabel = new JLabel("");
        backgroundImageLabel.setIcon(new ImageIcon(InformationScreen.class.getResource("/posterImages/movie_theatre.jpg")));
        backgroundImageLabel.setBounds(-60, 0, 777, 545);
        detailsPanel.add(backgroundImageLabel);
        
        // < -------------------------------------------------- > set layout of screen
		// set size of frame
        mainFrame.setSize(693, 507);
		// this places the frame into the centre of the screen
        mainFrame.setLocationRelativeTo(null);
		// user cannot resize it
        mainFrame.setResizable(false);
        // add the split frame to it
        // make it visible
        mainFrame.getContentPane().add(detailsPanel);
        // make the frame visible
        mainFrame.setVisible(true);
	}
}
