package top.movies.backend;

import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import top.movies.animation.AnimationPanel;
import top.movies.screens.MainMenuScreen;

// my LoadMovieInformation class implementing the 'Runnable' interface
// immediately executes the 'run()' method and then exits the thread
public class Loading implements Runnable {
	
	// < -------------------------------------------------- >
	// define all variables to be stored about an individual movie
	int movieRank;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	JButton b6;
	JButton b7;
	JButton b8;
	JButton b9;
	
	// < -------------------------------------------------- >
	// define the movieList, representing the current state of the movies saved to disk, to be loaded for the application
	MovieMapManager movieList;
	
	// < -------------------------------------------------- >
	// define the loadingScreenPanel which displays the animation that will need to be closed once loading is done
	AnimationPanel loadingScreenPanel;
	
	// < -------------------------------------------------- >
	// define the graphical user interface components to be used
	Color gold = new Color(255, 215, 0); // define custom colour
    Border moviePosterBorder = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, gold, Color.white); // define customer border
	
    // AtomicBoolean can be updated automatically and is used for flags in programs, e.g. here it is used to indicate when the thread has ended and to shut it down
    private final AtomicBoolean loadingInformation = new AtomicBoolean(true);
	
    // constructor is necessary to pass in the properties from the frame to change the information
    // use the arguments passed in to set this current version of the MovieMapManager movieList
	public Loading(MovieMapManager movieList, int movieRank, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6, JButton b7, JButton b8, JButton b9) {
		this.movieList = movieList;
		this.movieRank = movieRank;
		this.b1 = b1; // button 1
		this.b2 = b2;
		this.b3 = b3;
		this.b4 = b4;
		this.b5 = b5;
		this.b6 = b6;
		this.b7 = b7;
		this.b8 = b8;
		this.b9 = b9;
	}
			
	@Override
	public synchronized void run() {
		loadingScreenPanel = new AnimationPanel(); // this doesn't start the animation, this gets an instance fo the animationPanel class so that we can later dispose of the panel
		
		while (loadingInformation.get()) {
			try {
				// wait 5 seconds to mimic the impression of loading the values (because it doesn't take long enough to load)
				Thread.sleep(Constants.LOADINGDELAY);
				
				// once "loading" simulation is done, set all the buttons the correct movie
				try {
			    	b1.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH))); // sets the button to have a foreground of the poster image tied to t
			    	b1.setBackground(Color.DARK_GRAY); // set background colour to be gray (area behind the image on the button)
					b1.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + movieRank, 0, 0, null, gold)); // set the border to the pre-defined customer border
					b1.setText(null); // set text to be null because we have found a movie and therefore want the poster image to be displayed on the button and do not need text
		    	} catch (NullPointerException noMovieFound) {
		    		b1.setIcon(null); // no movie entered so no image to display, remove any other poster images already here
		    		b1.setBackground(Color.DARK_GRAY); // set background to be gray
					b1.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + movieRank, 0, 0, null, gold));  // set the border to the pre-defined customer border
					b1.setForeground(Color.LIGHT_GRAY); // set foreground (text colour) to be a lighter gray
			    	b1.setText("N/A - movie " + (movieRank)); // set text, as there is no image found) so that the user can see there has not been an movie entered at this rank yet
		    	}
		    	try {
			    	b2.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank+1).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
			    	b2.setBackground(Color.DARK_GRAY);
					b2.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+1), 0, 0, null, gold));
					b2.setText(null);
		    	} catch (NullPointerException noMovieFound) {
		    		b2.setIcon(null);
		    		b2.setBackground(Color.DARK_GRAY);
					b2.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+1), 0, 0, null, gold));
					b2.setForeground(Color.LIGHT_GRAY);
			    	b2.setText("N/A - movie " + (movieRank+1));
		    	}
		    	try {
			    	b3.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank+2).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
			    	b3.setBackground(Color.DARK_GRAY);
					b3.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+2), 0, 0, null, gold));
					b3.setText(null);
		    	} catch (NullPointerException noMovieFound) {
		    		b3.setIcon(null);
		    		b3.setBackground(Color.DARK_GRAY);
					b3.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+2), 0, 0, null, gold));
					b3.setForeground(Color.LIGHT_GRAY);
			    	b3.setText("N/A - movie " + (movieRank+2));
		    	}
		    	try {
			    	b4.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank+3).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
			    	b4.setBackground(Color.DARK_GRAY);
					b4.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+3), 0, 0, null, gold));
					b4.setText(null);
		    	} catch (NullPointerException noMovieFound) {
		    		b4.setIcon(null);
		    		b4.setBackground(Color.DARK_GRAY);
					b4.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+3), 0, 0, null, gold));
					b4.setForeground(Color.LIGHT_GRAY);
			    	b4.setText("N/A - movie " + (movieRank+3));
		    	}
		    	try {
			    	b5.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank+4).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
			    	b5.setBackground(Color.DARK_GRAY);
					b5.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+4), 0, 0, null, gold));
					b5.setText(null);
		    	} catch (NullPointerException noMovieFound) {
		    		b5.setIcon(null);
		    		b5.setBackground(Color.DARK_GRAY);
					b5.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+4), 0, 0, null, gold));
					b5.setForeground(Color.LIGHT_GRAY);
			    	b5.setText("N/A - movie " + (movieRank+4));
		    	}
		    	try {
			    	b6.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank+5).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
			    	b6.setBackground(Color.DARK_GRAY);
					b6.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+5), 0, 0, null, gold));
					b6.setText(null);
		    	} catch (NullPointerException noMovieFound) {
		    		b6.setIcon(null);
		    		b6.setBackground(Color.DARK_GRAY);
					b6.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+5), 0, 0, null, gold));
					b6.setForeground(Color.LIGHT_GRAY);
			    	b6.setText("N/A - movie " + (movieRank+5));
		    	}
		    	try {
			    	b7.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank+6).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
			    	b7.setBackground(Color.DARK_GRAY);
					b7.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+6), 0, 0, null, gold));
					b7.setText(null);
		    	} catch (NullPointerException noMovieFound) {
		    		b7.setIcon(null);
		    		b7.setBackground(Color.DARK_GRAY);
					b7.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+6), 0, 0, null, gold));
					b7.setForeground(Color.LIGHT_GRAY);
			    	b7.setText("N/A - movie " + (movieRank+6));
		    	}
		    	try {
			    	b8.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank+7).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
			    	b8.setBackground(Color.DARK_GRAY);
					b8.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+7), 0, 0, null, gold));
					b8.setText(null);
		    	} catch (NullPointerException noMovieFound) {
		    		b8.setIcon(null);
		    		b8.setBackground(Color.DARK_GRAY);
					b8.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+7), 0, 0, null, gold));
					b8.setForeground(Color.LIGHT_GRAY);
			    	b8.setText("N/A - movie " + (movieRank+7));
		    	}
		    	try {
			    	b9.setIcon(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(movieRank+8).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
			    	b9.setBackground(Color.DARK_GRAY);
					b9.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+8), 0, 0, null, gold));
					b9.setText(null);
		    	} catch (NullPointerException noMovieFound) {
		    		b9.setIcon(null);
		    		b9.setBackground(Color.DARK_GRAY);
					b9.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank " + (movieRank+8), 0, 0, null, gold));
					b9.setForeground(Color.LIGHT_GRAY);
			    	b9.setText("N/A - movie " + (movieRank+8));
		    	}
		    	shutdown(); // shutdown thread now that the loading has finished
			} catch (InterruptedException unkownError) {
				unkownError.printStackTrace();
			}	
		}
		// stop the timer now that everything is loaded so that the action listener is no longer called and the animation stops
		loadingScreenPanel.stopTimer();
		// get rid of the frame (that holds the loading animation) as everything has now been loaded
		MainMenuScreen.getLoadingScreen().dispose();
		
	}
	
	// method to stop the thread
	public synchronized void shutdown() {
		loadingInformation.set(false);
	}

}
