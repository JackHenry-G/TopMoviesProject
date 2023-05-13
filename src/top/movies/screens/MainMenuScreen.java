package top.movies.screens;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import top.movies.animation.AnimationFrame;
import top.movies.backend.Constants;
import top.movies.backend.Loading;
import top.movies.backend.Movie;
import top.movies.backend.MovieMapManager;

public class MainMenuScreen extends JFrame {

	private static final long serialVersionUID = -8204352027239298821L;
	
	// predefine JPanel and JFrame and all border layouts to be used, this will create layout of the screen
	JFrame mainFrame;
	JPanel borderPanel;
	JPanel gridPanel;
	JPanel flowPanel;
	JPanel topBorderPanel;
	
	// static so the frame can be disposed after loading is finished
	static AnimationFrame loadingScreen;
		
	// defining a gold colour using RGB
	Color gold = new Color(255, 215, 0);

	// counter for changing movie when moving to next/previous screen
	int movieRank = 1;
	// pageCounter
	int pageCounter = 1;
	
	// predefine path of where to retrieve poster images
	String dir = System.getProperty("user.dir");
	String path = dir + "src/posterImages";
	
	// must be public so it can be accessed outside of package
	public MainMenuScreen(MovieMapManager movieList) {
		
	    JLabel pageCounterLabel = new JLabel("Page " + pageCounter + " of 11");
	    pageCounterLabel.setFont(new Font("Castellar", Font.PLAIN, 20));
	    pageCounterLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// < -------------------------------------------------- >
		// creating the frame (window) where the GUI will be held
		// making the dimensions set to full screen so when it opens it takes the full screen
		// set the frame to close when the 'x' button is pressed
		
		mainFrame = new JFrame("Top 50 movies");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get screen size of the users device
	    mainFrame.setSize(screenSize.width, screenSize.height);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	    // < -------------------------------------------------- >
	    //	creating all buttons for the grid panel
	 	//	defining the grid panel that holds album covers
	    
		// defining a border that using class BorderFactory to give a golden and white border to each button
	    Border moviePosterBorder = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, gold, Color.white);

	    // ---------------------------------------------------------------------------
	    // creating all the buttons on the main screen grid, these will hold each movie the user enters at that rank and will serve as a link to the information behind each movie
	    // each button has an image mapped accross it, so that the image is essentially the button, and then it has a background of dark gray and foreground (font colour) of light gray
		JButton b1 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(1).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
		b1.setBackground(Color.DARK_GRAY);
		b1.setForeground(Color.LIGHT_GRAY);
		// border with type already defined, with a tile of 'Rank 1' at x and y axis of 0. With title of gold coloured font
		b1.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 1", 0, 0, null, gold));
	    JButton b2 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(2).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
	    b2.setBackground(Color.DARK_GRAY);
		b2.setForeground(Color.LIGHT_GRAY);
		b2.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 2", 0, 0, null, gold));
	    JButton b3 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(3).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
	    b3.setBackground(Color.DARK_GRAY);
		b3.setForeground(Color.LIGHT_GRAY);
		b3.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 3", 0, 0, null, gold));
	    JButton b4 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(4).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
	    b4.setBackground(Color.DARK_GRAY);
		b4.setForeground(Color.LIGHT_GRAY);
		b4.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 4", 0, 0, null, gold));
	    JButton b5 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(5).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
	    b5.setBackground(Color.DARK_GRAY);
		b5.setForeground(Color.LIGHT_GRAY);
		b5.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 5", 0, 0, null, gold));
	    JButton b6 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(6).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
	    b6.setBackground(Color.DARK_GRAY);
		b6.setForeground(Color.LIGHT_GRAY);
		b6.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 6", 0, 0, null, gold));
	    JButton b7 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(7).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
	    b7.setBackground(Color.DARK_GRAY);
		b7.setForeground(Color.LIGHT_GRAY);
		b7.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 7", 0, 0, null, gold));
	    JButton b8 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(8).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
	    b8.setBackground(Color.DARK_GRAY);
		b8.setForeground(Color.LIGHT_GRAY);
		b8.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 8", 0, 0, null, gold));
	    JButton b9 = new JButton(new ImageIcon(((new ImageIcon(movieList.getMovieByRank(9).getPosterPath())).getImage()).getScaledInstance(380, 200, java.awt.Image.SCALE_SMOOTH)));
	    b9.setBackground(Color.DARK_GRAY);
		b9.setForeground(Color.LIGHT_GRAY);
		b9.setBorder(BorderFactory.createTitledBorder(moviePosterBorder, "Rank 9", 0, 0, null, gold));

	    // button to add the new frame
	    b1.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	try {
	    		// when the button is pressed go to the movie information at that rank through the class MovieDetailsGUI
	    		Movie movieOnButton = movieList.getMovieByRank(movieRank);
		    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
		    	// if null pointer error is thrown, means the Movie object came back as null
		    	// meaning the user has not entered a movie for this rank yet
	    	} catch (NullPointerException nullError) {
                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
	    	}
	    	
	      }
	    });
	    
	    // button to add the new frame
	    b2.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  try {
		    		Movie movieOnButton = movieList.getMovieByRank(movieRank+1);
			    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
		    	} catch (NullPointerException nullError) {
	                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
		    	}
	      }
	    });
	    
	    // button to add the new frame
	    b3.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	try {
	    		Movie movieOnButton = movieList.getMovieByRank(movieRank+2);
		    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
	    	} catch (NullPointerException nullError) {
                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
	    	}
	    	
	      }
	    });
	    
	    // button to add the new frame
	    b4.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  try {
		    		Movie movieOnButton = movieList.getMovieByRank(movieRank+3);
			    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
		    	} catch (NullPointerException nullError) {
	                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
		    	}
	      }
	    });
	    
	    // button to add the new frame
	    b5.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	try {
	    		Movie movieOnButton = movieList.getMovieByRank(movieRank+4);
		    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
	    	} catch (NullPointerException nullError) {
                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
	    	}
	    	
	      }
	    });
	    
	    // button to add the new frame
	    b6.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  try {
		    		Movie movieOnButton = movieList.getMovieByRank(movieRank+5);
			    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
		    	} catch (NullPointerException nullError) {
	                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
		    	}
	      }
	    });
	    
	    // button to add the new frame
	    b7.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	try {
	    		Movie movieOnButton = movieList.getMovieByRank(movieRank+6);
		    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
	    	} catch (NullPointerException nullError) {
                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
	    	}
	    	
	      }
	    });
	    
	    // button to add the new frame
	    b8.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  try {
		    		Movie movieOnButton = movieList.getMovieByRank(movieRank+7);
			    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
		    	} catch (NullPointerException nullError) {
	                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
		    	}
	      }
	    });
	    
	    // button to add the new frame
	    b9.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	try {
	    		Movie movieOnButton = movieList.getMovieByRank(movieRank+8);
		    	InformationScreen MovieDetailsForm = new InformationScreen(movieOnButton);
	    	} catch (NullPointerException nullError) {
                JOptionPane.showMessageDialog(mainFrame,"No details to show as there is not a movie in this rank! Time to grab the popcorn and get watching!");  
	    	}
	    	
	      }
	    });
	    
	    // < -------------------------------------------------- >
	    // add each button to the grid layout
		gridPanel = new JPanel();
		gridPanel.add(b1);
		gridPanel.add(b2);
		gridPanel.add(b3);
		gridPanel.add(b4);
		gridPanel.add(b5);
		gridPanel.add(b6);
		gridPanel.add(b7);
		gridPanel.add(b8);
		gridPanel.add(b9);
		gridPanel.setLayout(new GridLayout(3,3));
		
		// < -------------------------------------------------- >
	 	//	defining the flow layout for the next/previous buttons
	    
	    Border buttonsBorder = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, gold, Color.white);
		
	    JButton previousButton = new JButton("Previous page");
	    previousButton.setBackground(Color.DARK_GRAY);
	    previousButton.setForeground(gold);
	    previousButton.setFont(new Font("Impact", Font.PLAIN, 20));
	    previousButton.setBorder(buttonsBorder);
	    JButton addMovieButton = new JButton("Overwrite the list!");
	    addMovieButton.setBackground(Color.DARK_GRAY);
	    addMovieButton.setForeground(gold);
	    addMovieButton.setFont(new Font("Impact", Font.PLAIN, 20));
	    addMovieButton.setBorder(buttonsBorder);
	    JButton nextButton = new JButton("Next page");
	    nextButton.setBackground(Color.DARK_GRAY);
	    nextButton.setForeground(gold);
	    nextButton.setFont(new Font("Impact", Font.PLAIN, 20));
	    nextButton.setBorder(buttonsBorder);
	    JButton exitButton = new JButton("Exit");
	    exitButton.setBackground(Color.DARK_GRAY);
	    exitButton.setForeground(gold);
	    exitButton.setFont(new Font("Impact", Font.PLAIN, 20));
	    exitButton.setBorder(buttonsBorder);
	    JButton tutorialButton = new JButton("Tutorial");
	    tutorialButton.setBackground(Color.DARK_GRAY);
	    tutorialButton.setForeground(gold);
	    tutorialButton.setFont(new Font("Impact", Font.PLAIN, 20));
	    tutorialButton.setBorder(buttonsBorder);

	    // button to add the new frame
	    addMovieButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	EditListScreen AddMovieForm = new EditListScreen(movieList);
	      }
	    });
	    
	    nextButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	// movieRank is initially 1. 1 + (9 x 10) = 91. So we can have 10 more pages before we reach the last page that has the max movie rank 99.
	    	if (movieRank < 91) {
	    		// call a swing worker thread that, in a background thread, will:
		    	// 1. Disable all buttons so that the user can not click on them and start another thread that could have dangerous effects on existing threads
		    	// 2. Start a timer (same as delay in loading method)
		    	// 3. Once timer is done and thread has completed, re-enable all buttons
		    	// this will therefore prevent the user 'spamming' the buttons and activating many threads. User can only flick between pages once loading has done.
			    disable(previousButton, nextButton, addMovieButton, tutorialButton, exitButton, b1, b2, b3, b4, b5, b6, b7, b8, b9, Constants.LOADINGDELAY);
			    
		    	// --------------------------------------------------------------------------------------------
		    	// page counter incremented then label changed to signal to use what page they are on
		    	pageCounter += 1;
		    	pageCounterLabel.setText("Page " + pageCounter + " of 11");
		    	// increment movieRank by 9, this is to keep all movie consistent
		    	movieRank += 9;
		    	
		    	// --------------------------------------------------------------------------------------------
		    	// display loading animation frame
	    		
	    		//MyLoadingScreenPanel loadingScreen = new MyLoadingScreenPanel();
	    		loadingScreen = new AnimationFrame(); // main thread will call the loading animation whilst secondary runnable thread will actually load the information
	    		
	    	    // --------------------------------------------------------------------------------------------
	    	    // using the java 'runnable' interface method of implementing threads
	    	    // using this method over extending the 'thread' class is more lightweight
	    	    // create instance of the class (pass all the necessary information into the constructor
	    		Loading loadingRunnable = new Loading(movieList, movieRank, b1, b2, b3, b4, b5, b6, b7, b8, b9);
	    		// then create an instance of the 'thread' class and pass in the class that instance fo the class that implements the runnable
	    		Thread loadingThread = new Thread(loadingRunnable);
	    		// starting the thread
	    		loadingThread.start();
	    	    
	    	}
	    	
	      }
	    });
	    
	    previousButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	// call a swing worker thread that, in a background thread, will:
	    	// 1. Disable all buttons so that the user can not click on them and start another thread that could have dangerous effects on existing threads
	    	// 2. Start a timer (same as delay in loading method)
	    	// 3. Once timer is done and thread has completed, re-enable all buttons
	    	// this will therefore prevent the user 'spamming' the buttons and activating many threads. User can only flick between pages once loading has done.
	    	disable(previousButton, nextButton, addMovieButton, tutorialButton, exitButton, b1, b2, b3, b4, b5, b6, b7, b8, b9, Constants.LOADINGDELAY);
	    	// if statement that ensures the user cannot go back a page if they are already one page 1
	    	// the first movie is rank 1, and thats what the variable 'one' represents
	    	// so you should only be able to move to the previous page if the 'one' variable is currently more than one which the user is on page 2 or more
	    	if (movieRank > 1) {
	    		
		    	// page counter decremented then label changed to signal to use what page they are on
		    	pageCounter -= 1;
		    	pageCounterLabel.setText("Page " + pageCounter + " of 11");
	    		movieRank -= 9;
	    		
	    		// --------------------------------------------------------------------------------------------
		    	// display loading animation frame
	    		
	    		loadingScreen = new AnimationFrame();
	    		
	    	    // --------------------------------------------------------------------------------------------
	    	    // using the java 'runnable' interface method of implementing threads
	    	    // using this method over extending the 'thread' class is more lightweight
	    	    // create instance of the class (pass all the necessary information into the constructor
	    		Loading loadingRunnable = new Loading(movieList, movieRank, b1, b2, b3, b4, b5, b6, b7, b8, b9);
	    		// then create an instance of the 'thread' class and pass in the class that instance fo the class that implements the runnable
	    		Thread loadingThread = new Thread(loadingRunnable);
	    		// starting the thread
	    		loadingThread.start();
	    	}
	      }
	    });
	    
	    exitButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    
	    	// --------------------------------------------------------------------------------------------
	    	// we don't write our data to disk using serialization because this is done straight after each addition or modification to the movie list
	    	// so the last data is already been added to disk before the exit button has been added
	    	  
	    	mainFrame.dispose(); // dispose of GUI frame
	    	System.exit(0);  // terminate java application
	      }
	    });
	    
	    tutorialButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  TutorialScreen getTutorialScreen = new TutorialScreen();
	    
	      }
	    });
	    
	    // method to add a gradient colour background to the flowPanel, meaning the bottom of the screen will have a gradient movie from gold to dark gray
	    // modified from - https://stackoverflow.com/questions/14364291/jpanel-gradient-background
	    // method must calculate teh width and height of the panel, so that it knows where to begin merging colours
	    // here the gradient 'begins' halfway through, so the width is divided by 2 to get that merge point
	    flowPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
            	 super.paintComponent(g);
                 Graphics2D g2d = (Graphics2D) g;
                 g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                 int w = getWidth();
                 int h = getHeight();
                 GradientPaint gp = new GradientPaint(w/2, 0, gold, w, 0, Color.DARK_GRAY, true);
                 g2d.setPaint(gp);
                 g2d.fillRect(0, 0, w, h);
          }
       };
	    flowPanel.add(previousButton);
	    flowPanel.add(addMovieButton);
	    flowPanel.add(nextButton);
	    flowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    flowPanel.setBackground(Color.DARK_GRAY);
	    
	    
	    // method to add a gradient colour background to the flowPanel, meaning the top of the screen will have a gradient movie from gold to dark gray
	    // modified from - https://stackoverflow.com/questions/14364291/jpanel-gradient-background
	    topBorderPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
            	 super.paintComponent(g);
                 Graphics2D g2d = (Graphics2D) g;
                 g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                 int w = getWidth();
                 int h = getHeight();
                 GradientPaint gp = new GradientPaint(w/2, 0, gold, w, 0, Color.DARK_GRAY, true);
                 g2d.setPaint(gp);
                 g2d.fillRect(0, 0, w, h);
          }
       };
       	topBorderPanel.setLayout(new GridLayout(1,3));
       	topBorderPanel.add(tutorialButton);
       	topBorderPanel.add(pageCounterLabel);
       	topBorderPanel.add(exitButton);
	    topBorderPanel.setSize(200, 200);
		
	    // method to add a gradient colour background to the flowPanel, meaning the screen will have a gradient movie from gold to dark gray
	    // modified from - https://stackoverflow.com/questions/14364291/jpanel-gradient-background
		borderPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
            	 super.paintComponent(g);
                 Graphics2D g2d = (Graphics2D) g;
                 g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                 int w = getWidth();
                 int h = getHeight();
                 GradientPaint gp = new GradientPaint(w/2, 0, gold, w, 0, Color.DARK_GRAY, true);
                 g2d.setPaint(gp);
                 g2d.fillRect(0, 0, w, h);
             
          }
       };
       
        // < -------------------------------------------------- >
		// defining the background layout that holds the grid at centre
		// and holds the previous/next buttons at SOUTH
		// this adds the grid layout at the centre of the border layout, meaning it appears a the centre of the screen with the properties set out in a grid like manner
		// then adds the flow panel at the south of the border layout, meaning it appears at the bottom, stretching accross the full x-axis as the properties flow accross
		borderPanel.setLayout(new BorderLayout(50, 50));
		borderPanel.add(gridPanel, BorderLayout.CENTER);
		borderPanel.add(flowPanel, BorderLayout.SOUTH);
		borderPanel.add(topBorderPanel, BorderLayout.NORTH);
		
		// < -------------------------------------------------- >
		// adding the background panel, which holds the grid and flow panels, to the main frame
		// set it visible so user can see the GUI
		mainFrame.getContentPane().add(borderPanel);
		mainFrame.setVisible(true);
	}
	
	// ********* Credit to user 'sgbj', Scott Batary, on stackoverflow at https://stackoverflow.com/questions/18753951/how-to-create-a-delay-between-button-click-to-prevent-button-spamming/18755428
	// call a swing worker thread that, in a background thread, will:
	// 1. Disable all buttons so that the user can not click on them and start another thread that could have dangerous effects on existing threads
	// 2. Start a timer (same as delay in loading method)
	// 3. Once timer is done and thread has completed, re-enable all buttons
	// this will therefore prevent the user 'spamming' the buttons and activating many threads. User can only flick between pages once loading has done.
	static void disable(final AbstractButton previousButton, final AbstractButton nextButton, final AbstractButton addMovieButton, final AbstractButton tutorialButton,
			final AbstractButton exitButton, final AbstractButton movieB1, final AbstractButton movieB2, final AbstractButton movieB3, final AbstractButton movieB4,
			final AbstractButton movieB5, final AbstractButton movieB6, final AbstractButton movieB7, final AbstractButton movieB8, final AbstractButton movieB9, final long ms) {
	    previousButton.setEnabled(false);
	    nextButton.setEnabled(false);
	    addMovieButton.setEnabled(false);
	    tutorialButton.setEnabled(false);
	    exitButton.setEnabled(false);
	    movieB1.setEnabled(false);
	    movieB2.setEnabled(false);
	    movieB3.setEnabled(false);
	    movieB4.setEnabled(false);
	    movieB5.setEnabled(false);
	    movieB6.setEnabled(false);
	    movieB7.setEnabled(false);
	    movieB8.setEnabled(false);
	    movieB9.setEnabled(false);
	    new SwingWorker() {
	        @Override protected Object doInBackground() throws Exception {
	            Thread.sleep(ms);
	            return null;
	        }
	        @Override protected void done() {
	        	previousButton.setEnabled(true);
	    	    nextButton.setEnabled(true);
	    	    addMovieButton.setEnabled(true);
	    	    tutorialButton.setEnabled(true);
	    	    exitButton.setEnabled(true);
	    	    movieB1.setEnabled(true);
	    	    movieB2.setEnabled(true);
	    	    movieB3.setEnabled(true);
	    	    movieB4.setEnabled(true);
	    	    movieB5.setEnabled(true);
	    	    movieB6.setEnabled(true);
	    	    movieB7.setEnabled(true);
	    	    movieB8.setEnabled(true);
	    	    movieB9.setEnabled(true);
	        }
	    }.execute();
	}

	/*
	 * The main difference between making a field public vs. exposing it through getters/setters is holding control over the property. 
	 * If you make a field public, it means you provide direct access to the caller. Then, the caller can do anything with your field, 
	 * either knowingly or unknowingly. For example, one can set a field to a null value, and if you use that field in another method, 
	 * it may blow up that method with a null pointer exception. Found a dzone.com - https://dzone.com/articles/why-should-i-write-getters-and-setters
	 * 
	 * This way we stop the loadingScreen from being unknowingly altered by limiting its access through getters and setters
	 */
	public static AnimationFrame getLoadingScreen() {
		return loadingScreen;
	}

	public static void setLoadingScreen(AnimationFrame loadingScreen) {
		MainMenuScreen.loadingScreen = loadingScreen;
	}
	
}
	

