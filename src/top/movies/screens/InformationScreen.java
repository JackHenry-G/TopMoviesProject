package top.movies.screens;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import top.movies.backend.Movie;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ESCAPE;


public class InformationScreen extends JFrame{
	
	private static final long serialVersionUID = -8316591193551620931L;
	
	// define JPanel and JFrame
	JFrame mainInformationFrame;
	JPanel mainInformationPanel;
	
	// constructor class automatically called
	InformationScreen(Movie movieOnButton) {
		
		// < -------------------------------------------------- >
		// by setting text in html tags it automatically formats the text to be the correct size of the window
		// set the message to be displayed to the user with the correct movie variables
		String movieDetails = "<html><div style='text-align: center; font-size: 11px;'>" + "Your number " + movieOnButton.getMovieRanking() + " movie is " + movieOnButton.getMovieName() + "!" + "<br/><br/>" +
		"A " + movieOnButton.getGenre() + " genre of movie, released in " + movieOnButton.getYearReleased() + "<br/><br/>" +
		"Directed by " + movieOnButton.getDirector() + " and starring: " + "<br/><br/>" +
		movieOnButton.getActor1() + " as '" + movieOnButton.getRole1() + "'" + "<br/><br/>" +
		movieOnButton.getActor2() + " as '" + movieOnButton.getRole2() + "'" + "<br/><br/>" +
		movieOnButton.getActor3() + " as '" + movieOnButton.getRole3() + "'" + "<br/><br/>" +
		"</div></html>";
		JLabel movieDetailsLabel = new JLabel(movieDetails, SwingConstants.CENTER); // makes text centred to the label
		movieDetailsLabel.setBounds(110, 34, 468, 244); // sets where to display label on screen
		
		// create the main frame of this part of the GUI
		mainInformationFrame = new JFrame("Top 50 movies");
		mainInformationFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == VK_ESCAPE) { // if the button pressed is the 'escape' button
					mainInformationFrame.dispose(); // dispose of GUI frame
			    	System.exit(0);  // terminate java application
		        }
			}
		});
		
		mainInformationPanel = new JPanel(); // initialise JPanel
		mainInformationPanel.setLayout(null); // set layout to null so we can place components anywhere we define
		
		// add text
		mainInformationPanel.add(movieDetailsLabel);

		// set background image		
		JLabel backgroundImageLabel = new JLabel("");
        backgroundImageLabel.setIcon(new ImageIcon(InformationScreen.class.getResource("/posterImages/movie_theatre.jpg")));
        backgroundImageLabel.setBounds(-60, 0, 777, 545);
        mainInformationPanel.add(backgroundImageLabel);
		
		// set title of the main frame and the size of it
        mainInformationFrame.setTitle("Movie details!");
        mainInformationFrame.setSize(693, 507);
		// this places the frame into the centre of the screen
        mainInformationFrame.setLocationRelativeTo(null);
		// user cannot resize it
        mainInformationFrame.setResizable(false);
        // add the split frame to it
        // make it visible
        mainInformationFrame.getContentPane().add(mainInformationPanel);
        // make the frame visible
        mainInformationFrame.setVisible(true);
	}
}