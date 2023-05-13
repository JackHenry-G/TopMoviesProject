package top.movies.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import top.movies.backend.Constants;
import top.movies.backend.Movie;
import top.movies.backend.MovieMapManager;
import top.movies.backend.Serializer;

public class EditListScreen extends JFrame{

	private static final long serialVersionUID = -6916996466867116921L;
	
	// < -------------------------------------------------- >
	// define the JFrame and JPanel to be used
	JFrame mainEditFrame;
	JPanel mainEditPanel;

	// < -------------------------------------------------- >
	// define all variables that need to be entered by the user
	private int movieRank;
	private String movieName;
	private String movieGenre;
	private int yearReleased;
	private String director;
	private String posterImgFilePath;
	private String actorOne;
	private String actorTwo;
	private String actorThree;
	private String roleOne;
	private String roleTwo;
	private String roleThree;
	
	// < -------------------------------------------------- >
	// define custom graphical components that will be used
	Color gold = new Color(255, 215, 0); // defining gold colour with RGB
    Border moviePosterBorder = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, gold, Color.white); // defining borders for the buttons
    Border buttonsBorder = BorderFactory.createLineBorder(gold, 2); // defining borders for the buttons
    
    // < -------------------------------------------------- >
    // the path to the image for the movie on the above and below rank
    private String oneBelowMovieImgPath;
    private String oneAboveMovieImgPath;

	// constructor class that is automatically called when this class is instantiated
	EditListScreen(MovieMapManager movieList) {
		
		// < -------------------------------------------------- >
		mainEditFrame = new JFrame("Top 50 movies"); // initialise new JFrame and set title relevant to the JFrame usage
		mainEditPanel = new JPanel(); // initialise new JPanel
		mainEditPanel.setLayout(null); // set layout to be null so that we can place components anywhere we define
		mainEditPanel.setBackground(Color.DARK_GRAY); // set background colour to be dark gray
		
		// < -------------------------------------------------- > Top left inputs
	    // should be option from 1 - 50
	    JLabel movieRankLabel = new JLabel("Movie rank");
	    SpinnerModel possibleRanks = new SpinnerNumberModel(10, 1, Constants.MAXMOVIECOUNT, 1);
	    JSpinner movieRankSpinner = new JSpinner(possibleRanks);
	    
	    JLabel movieNameLabel = new JLabel("Movie name");
	    JTextField movieNameTextField = new JTextField();

	    String[] movieGenres = {"Action", "Comedy", "Crime", "Dance", "Drama", "Fantasy", "Horror", "Musical", "Mystery", "Romance", "Sci-Fi", "Sports", "Thriller", "War", "Western"};
	    JComboBox<String> movieGenreOptions = new JComboBox<String>(movieGenres);
	    movieGenreOptions.setSelectedIndex(-1);
	    JLabel movieGenreLabel = new JLabel("Movie genre");
	    
	    // Setting the positions of components
        movieRankLabel.setBounds(50, 50, 100, 30);
        movieRankLabel.setForeground(Color.white);
        movieRankSpinner.setBounds(150, 50, 150, 30);
        movieRankSpinner.setBackground(Color.LIGHT_GRAY);
        movieRankSpinner.setBorder(buttonsBorder);
        movieNameLabel.setBounds(50, 120, 100, 30);
        movieNameLabel.setForeground(Color.white);
        movieNameTextField.setBounds(150, 120, 150, 30);
        movieNameTextField.setBackground(Color.LIGHT_GRAY);
        movieNameTextField.setBorder(buttonsBorder);
        movieGenreLabel.setBounds(50, 190, 100, 30);
        movieGenreLabel.setForeground(Color.white);
        movieGenreOptions.setBounds(150, 190, 150, 30);
        movieGenreOptions.setBackground(Color.LIGHT_GRAY);
        movieGenreOptions.setBorder(buttonsBorder);
	    
        // < -------------------------------------------------- > Top right inputs
	    JLabel yearReleasedLabel = new JLabel("Year released");
	    SpinnerModel possibleYears = new SpinnerNumberModel(2000, 1888, 2050, 1);
	    JSpinner yearReleasedSpinner = new JSpinner(possibleYears);
	    yearReleasedSpinner.setBorder(buttonsBorder);
	    // new editor for displaying number without comma
	    // # represents that the format should be display as just one number. Another example for a differnt format = '###,###.###'
	    JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearReleasedSpinner, "#");
	    yearReleasedSpinner.setEditor(editor);
	    
	    JLabel directorNameLabel = new JLabel("Director name");
	    JTextField directorNameTextField = new JTextField();
	    directorNameTextField.setBackground(Color.LIGHT_GRAY);
	    directorNameTextField.setBorder(buttonsBorder);

	    JButton chooseImageButton = new JButton("Press me...");
	    JLabel fileLabel = new JLabel("Nothing selected...");
	    
	    // Setting the positions of components
	    yearReleasedLabel.setBounds(380, 120, 100, 30);
	    yearReleasedLabel.setForeground(Color.white);
        yearReleasedSpinner.setBounds(530, 120, 100, 30);
        directorNameLabel.setBounds(380, 50, 100, 30);
        directorNameLabel.setForeground(Color.white);
        directorNameTextField.setBounds(530, 50, 150, 30);
        directorNameTextField.setBackground(Color.LIGHT_GRAY);
        directorNameTextField.setBorder(buttonsBorder);
        chooseImageButton.setBounds(530, 190, 150, 30);
        chooseImageButton.setBackground(Color.LIGHT_GRAY);
        chooseImageButton.setBorder(buttonsBorder);
        fileLabel.setBounds(380, 190, 150, 30);
        fileLabel.setForeground(Color.white);
        
        // < -------------------------------------------------- > Bottom left inputs
        JLabel actorOneLabel = new JLabel("Actor name");
	    JTextField actorOneTextField = new JTextField();
	    
	    JLabel actorTwoLabel = new JLabel("Actor name");
	    JTextField actorTwoTextField = new JTextField();

	    JLabel actorThreeLabel = new JLabel("Actor name");
	    JTextField actorThreeTextField = new JTextField();
	    
	    // Setting the positions of components
	    actorOneLabel.setBounds(50, 260, 100, 30);
	    actorOneLabel.setForeground(Color.white);
	    actorOneTextField.setBounds(150, 260, 150, 30);
	    actorOneTextField.setBackground(Color.LIGHT_GRAY);
	    actorOneTextField.setBorder(buttonsBorder);
	    actorTwoLabel.setBounds(50, 330, 100, 30);
	    actorTwoLabel.setForeground(Color.white);
	    actorTwoTextField.setBounds(150, 330, 150, 30);
	    actorTwoTextField.setBackground(Color.LIGHT_GRAY);
	    actorTwoTextField.setBorder(buttonsBorder);
	    actorThreeLabel.setBounds(50, 400, 100, 30);
	    actorThreeLabel.setForeground(Color.white);
	    actorThreeTextField.setBounds(150, 400, 150, 30);
	    actorThreeTextField.setBackground(Color.LIGHT_GRAY);
	    actorThreeTextField.setBorder(buttonsBorder);
	    
	    // < -------------------------------------------------- > Bottom right inputs
	    // should be option from 1 - 50
		
        JLabel roleOneLabel = new JLabel("Role name");
	    JTextField roleOneTextField = new JTextField();
	    
	    JLabel roleTwoLabel = new JLabel("Role name");
	    JTextField roleTwoTextField = new JTextField();

	    JLabel roleThreeLabel = new JLabel("Role name");
	    JTextField roleThreeTextField = new JTextField();
	    
	    // Setting the positions of components
	    roleOneLabel.setBounds(380, 260, 100, 30);
	    roleOneLabel.setForeground(Color.white);
	    roleOneTextField.setBounds(530, 260, 150, 30);
	    roleOneTextField.setBackground(Color.LIGHT_GRAY);
	    roleOneTextField.setBorder(buttonsBorder);
	    roleTwoLabel.setBounds(380, 330, 100, 30);
	    roleTwoLabel.setForeground(Color.white);
	    roleTwoTextField.setBounds(530, 330, 150, 30);
	    roleTwoTextField.setBackground(Color.LIGHT_GRAY);
	    roleTwoTextField.setBorder(buttonsBorder);
	    roleThreeLabel.setBounds(380, 400, 150, 30);
	    roleThreeLabel.setForeground(Color.white);
	    roleThreeTextField.setBounds(530, 400, 150, 30);
	    roleThreeTextField.setBackground(Color.LIGHT_GRAY);
	    roleThreeTextField.setBorder(buttonsBorder);
        
		// < -------------------------------------------------- > Submit and reset and slot in movie buttons
	    JButton submitButton = new JButton("Overwrite");
	    JButton resetButton = new JButton("Reset");
	    JButton slotInMovieButton = new JButton("Slot in movie");
	   
	    // setToolTipText API allows us to display an information box when the user hovers over the button
	    // this means we can explain the function of the button to the user before they press the button and make any changes
        submitButton.setToolTipText("This button will replace the movie at the current rank selected");
        resetButton.setToolTipText("Clear any entered information so it is easier to enter a new movie (Will not overwrite data until submitted!)");
        slotInMovieButton.setToolTipText("This button will place in your movie at the current rank selected but instead of replacing, it will take the current movie already there and any adjacent movies and move each on up by one rank");

        submitButton.setBounds(185, 460, 100, 30);
        submitButton.setBackground(Color.DARK_GRAY);
        submitButton.setForeground(gold);
        submitButton.setFont(new Font("Impact", Font.PLAIN, 20));
	    submitButton.setBorder(buttonsBorder);
        resetButton.setBounds(325, 460, 100, 30);
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(gold);
        resetButton.setFont(new Font("Impact", Font.PLAIN, 20));
        resetButton.setBorder(buttonsBorder);
        slotInMovieButton.setBounds(465, 460, 120, 30);
        slotInMovieButton.setBackground(Color.DARK_GRAY);
        slotInMovieButton.setForeground(gold);
        slotInMovieButton.setFont(new Font("Impact", Font.PLAIN, 20));
        slotInMovieButton.setBorder(buttonsBorder);

		// < -------------------------------------------------- > Adding components to the mainEditPanel
        mainEditPanel.add(movieRankLabel);
        mainEditPanel.add(movieNameLabel);
        mainEditPanel.add(movieGenreLabel);
        mainEditPanel.add(movieGenreOptions);
        mainEditPanel.add(movieRankSpinner);
        mainEditPanel.add(movieNameTextField);
        mainEditPanel.add(yearReleasedLabel);
        mainEditPanel.add(yearReleasedSpinner);
        mainEditPanel.add(directorNameLabel);
        mainEditPanel.add(directorNameTextField);
        mainEditPanel.add(chooseImageButton);
        mainEditPanel.add(fileLabel);
        mainEditPanel.add(actorOneLabel);
        mainEditPanel.add(actorOneTextField);
        mainEditPanel.add(actorTwoLabel);
        mainEditPanel.add(actorTwoTextField);
        mainEditPanel.add(actorThreeLabel);
        mainEditPanel.add(actorThreeTextField);
        mainEditPanel.add(roleOneLabel);
        mainEditPanel.add(roleOneTextField);
        mainEditPanel.add(roleTwoLabel);
        mainEditPanel.add(roleTwoTextField);
        mainEditPanel.add(roleThreeLabel);
        mainEditPanel.add(roleThreeTextField);
        mainEditPanel.add(submitButton);
        mainEditPanel.add(resetButton);
        mainEditPanel.add(slotInMovieButton);
        
		// < -------------------------------------------------- > 
        // code to add all background images to the GUI
        // uses labels to hold the image and place it within the panel
        // images must be rescaled if too large, to do this we created a buffered image and then rescale this from there
        // Java BufferedImage class is a subclass of Image class. It is used to handle and manipulate the image data.
        
        JLabel imageLabelForTickets = new JLabel();
        BufferedImage ticketsImg = null;
        try {
        	ticketsImg = ImageIO.read(EditListScreen.class.getResource("/posterImages/tickets-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledTicketsImg = ticketsImg.getScaledInstance(80, 120,
                Image.SCALE_SMOOTH);
        ImageIcon ticketsImgIcon = new ImageIcon(scaledTicketsImg);
        imageLabelForTickets.setIcon(ticketsImgIcon);
        imageLabelForTickets.setBounds(35, 425, 80, 104);
        mainEditPanel.add(imageLabelForTickets);
        
        JLabel imageLabelForPopcorn = new JLabel();
        BufferedImage popcornImg = null;
        try {
        	popcornImg = ImageIO.read(EditListScreen.class.getResource("/posterImages/popcorn-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledPopcornImg = popcornImg.getScaledInstance(80, 120,
                Image.SCALE_SMOOTH);
        ImageIcon popcornImgIcon = new ImageIcon(scaledPopcornImg);
        imageLabelForPopcorn.setIcon(popcornImgIcon);
        imageLabelForPopcorn.setBounds(439, 214, 87, 116);
        mainEditPanel.add(imageLabelForPopcorn);
        
        JLabel infoLabel = new JLabel("Hover over these buttons to see their functions!");
        infoLabel.setForeground(Color.LIGHT_GRAY);
        infoLabel.setBounds(249, 515, 305, 14);
        mainEditPanel.add(infoLabel);

		// < -------------------------------------------------- > (SUBMIT) Adding action listeners to give function to buttons
        submitButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {            //Coding Part of LOGIN button
            if (e.getSource() == submitButton) {
                try {
                    movieRank = (int) movieRankSpinner.getValue();
                    movieName = movieNameTextField.getText();
                    movieGenre = (String) movieGenreOptions.getItemAt(movieGenreOptions.getSelectedIndex());
                    yearReleased = (int) yearReleasedSpinner.getValue();
                    director = directorNameTextField.getText();
                    posterImgFilePath = fileLabel.getText();
                    actorOne = actorOneTextField.getText();
                    actorTwo = actorTwoTextField.getText();
                    actorThree = actorThreeTextField.getText();
                    roleOne = roleOneTextField.getText();
                    roleTwo = roleTwoTextField.getText();
                    roleThree = roleThreeTextField.getText();
                    // if statement that checks if the user has entered all the necessary details!
                    // must check the movie in the rank one below and one above to make sure the poster path has been entered by the user. We must check various things
                    // firstly that the poster path string is not "Nothing selected..." which is the default state
                    // secondly that is is not "cancelled by the user!" meaning they clicked the button but then did not select an image
                    // then we must check that the poster path string is not the same as the movie below or above, because when scrolling through the movies (only) the image selector keeps the same value as the movie previously it was on
                    // this means that the value adopts the one of the movie before, to counter this we can just make sure it is not the same
                    // finally, we must check its not null or empty (just in case)
                    // these checks all ensure the user has selected an image for their movie, and will not let them submit until they have done
                    // this was much easier with the other components because the image selector component did not allow resets
                    try {
                    	oneBelowMovieImgPath = movieList.getMovieByRank(movieRank-1).getPosterPath();
                        oneAboveMovieImgPath = movieList.getMovieByRank(movieRank+1).getPosterPath();
                    } catch (NullPointerException movieNotEnteredYet) {
                    	oneBelowMovieImgPath = null;
                        oneAboveMovieImgPath = null;
                    }
                    
                    if ( (movieName.isEmpty() || movieName == null) || ( movieGenre == null || movieGenre.isEmpty())
                    		|| (director.isEmpty() || director == null) || (posterImgFilePath.isEmpty() || posterImgFilePath == null)
                    		|| (posterImgFilePath.equals(oneBelowMovieImgPath)  && !oneBelowMovieImgPath.isEmpty() && oneBelowMovieImgPath != null) || (posterImgFilePath.equals(oneAboveMovieImgPath) && !oneAboveMovieImgPath.isEmpty() && oneBelowMovieImgPath != null)
                    		|| (posterImgFilePath.contains("Cancelled by user!")) || (posterImgFilePath.contains("Nothing selected...")) 
                    		|| (actorOne.isEmpty() || actorOne == null) || (actorTwo.isEmpty() || actorTwo == null)
                    		|| (actorThree.isEmpty() || actorThree == null) || (roleOne.isEmpty() || roleOne == null)
                    		|| (roleTwo.isEmpty() || roleTwo == null) || (roleThree.isEmpty() || roleThree == null) ) {
                		JOptionPane.showMessageDialog(mainEditFrame,"Not all details entered!");  
                	// if it passed the initial check then the details have been entered
                	// therefore, this movie object can be created, added to the movie list and then serialized to save it
                    } else {
                    	// now we have captured all the details we can create a new object which essentially creates a new movie
                        Movie newMovie = new Movie(movieRank, movieName, movieGenre, yearReleased, director, actorOne, roleOne, actorTwo, roleTwo, actorThree, roleThree, posterImgFilePath);
                        // using this new movie object and the 'movieListManager' passed into this class 
                        // we can use the 'addMovie' method of the 'movieListManager' ojbect to add the new movie object 'newMovie' to the hashmap
                        // that holds the users top movies
                        movieList.addMovie(newMovie);
                        // then serialize the object that holds that movie list hashmap, saving it to disk
                        Serializer.serializeMovieList(movieList);
                        
                        // prompt user that the details were captured and the list has been updated
                		JOptionPane.showMessageDialog(mainEditFrame,"Details captured, your list has been updated!");  
                        
                        // reset inputs again for next time
                        movieGenreOptions.setSelectedIndex(-1); // -1 indicates no selection
                        movieRankSpinner.setValue(10);
                        movieNameTextField.setText("");
                        yearReleasedLabel.setText("Year released");
                        yearReleasedSpinner.setValue(2000);
                        directorNameTextField.setText("");
                        fileLabel.setText("Nothing selected...");
                        actorOneTextField.setText("");
                        actorTwoTextField.setText("");
                        actorThreeTextField.setText("");
                        roleOneTextField.setText("");
                        roleTwoTextField.setText("");
                        roleThreeTextField.setText("");                    }

                    
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(mainEditFrame,"Something went wrong! Contact system administrator!");  
                    error.printStackTrace();
                }
     
            }
            
            

	      }
	    });
        
		// < -------------------------------------------------- > (RESET) Adding action listeners to give function to buttons
        resetButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {  
            //Coding Part of RESET button
            if (e.getSource() == resetButton) {
                movieGenreOptions.setSelectedIndex(-1); // -1 indicates no selection
                //movieRankSpinner.setValue(10);
                movieNameTextField.setText("");
                yearReleasedSpinner.setValue(2000);
                directorNameTextField.setText("");
                fileLabel.setText("Nothing selected...");
                actorOneTextField.setText("");
                actorTwoTextField.setText("");
                actorThreeTextField.setText("");
                roleOneTextField.setText("");
                roleTwoTextField.setText("");
                roleThreeTextField.setText("");
            }

	      }
	    });
        
     // < -------------------------------------------------- > (SLOT) Adding action listeners to give function to buttons
        slotInMovieButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {            //Coding Part of LOGIN button
            if (e.getSource() == slotInMovieButton) {
                try {
                    movieRank = (int) movieRankSpinner.getValue();
                    movieName = movieNameTextField.getText();
                    movieGenre = (String) movieGenreOptions.getItemAt(movieGenreOptions.getSelectedIndex());
                    yearReleased = (int) yearReleasedSpinner.getValue();
                    director = directorNameTextField.getText();
                    posterImgFilePath = fileLabel.getText();
                    actorOne = actorOneTextField.getText();
                    actorTwo = actorTwoTextField.getText();
                    actorThree = actorThreeTextField.getText();
                    roleOne = roleOneTextField.getText();
                    roleTwo = roleTwoTextField.getText();
                    roleThree = roleThreeTextField.getText();
                                        
                    // if statement that checks if the user has entered all the necessary details!
                    // checks variables to see if the string was left empty or was null, meaning user did not enter anything
                    // yearReleased and movieRank not checked if empty as these are options panes that cannot be empty
                    if ( (movieName.isEmpty() || movieName == null) || ( movieGenre == null || movieGenre.isEmpty())
                    		|| (director.isEmpty() || director == null) || (posterImgFilePath.isEmpty() || posterImgFilePath == null)
                    		|| (posterImgFilePath.equals(oneBelowMovieImgPath)  && !oneBelowMovieImgPath.isEmpty() && oneBelowMovieImgPath != null) || (posterImgFilePath.equals(oneAboveMovieImgPath) && !oneAboveMovieImgPath.isEmpty() && oneBelowMovieImgPath != null)
                    		|| (posterImgFilePath.contains("Cancelled by user!")) || (posterImgFilePath.contains("Nothing selected...")) 
                    		|| (actorOne.isEmpty() || actorOne == null) || (actorTwo.isEmpty() || actorTwo == null)
                    		|| (actorThree.isEmpty() || actorThree == null) || (roleOne.isEmpty() || roleOne == null)
                    		|| (roleTwo.isEmpty() || roleTwo == null) || (roleThree.isEmpty() || roleThree == null) ) {
                		JOptionPane.showMessageDialog(mainEditFrame,"Not all details entered!");  
                	// if it passed the initial check then the details have been entered
                	// therefore, this movie object can be created, added to the movie list and then serialized to save it
                    } else {
                    	// now we have captured all the details we can create a new object which essentially creates a new movie
                        Movie newMovie = new Movie(movieRank, movieName, movieGenre, yearReleased, director, actorOne, roleOne, actorTwo, roleTwo, actorThree, roleThree, posterImgFilePath);
                       
                        // call method to movie all adjacent movies up one rank, so there is room for the movie the user wants to 'slot in'
            			movieList.moveAllMoviesUpOneRank(newMovie);
            			
            			// using this new movie object and the 'movieListManager' passed into this class 
                        // we can use the 'addMovie' method of the 'movieListManager' ojbect to add the new movie object 'newMovie' to the hashmap
                        // that holds the users top movies
                        movieList.addMovie(newMovie);
                        
                        // then serialize the object that holds that movie list hashmap, saving it to disk
                        Serializer.serializeMovieList(movieList);
                        
                        // prompt user that the details were captured and the list has been updated
                		JOptionPane.showMessageDialog(mainEditFrame,"Details captured, your list has been updated!");  
                        
                        // reset inputs again for next time
                        movieGenreOptions.setSelectedIndex(-1); // -1 indicates no selection
                        movieRankSpinner.setValue(10);
                        movieNameTextField.setText("");
                        yearReleasedLabel.setText("Year released");
                        yearReleasedSpinner.setValue(2000);
                        directorNameTextField.setText("");
                        fileLabel.setText("Nothing selected...");
                        actorOneTextField.setText("");
                        actorTwoTextField.setText("");
                        actorThreeTextField.setText("");
                        roleOneTextField.setText("");
                        roleTwoTextField.setText("");
                        roleThreeTextField.setText("");                    }

                    
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(mainEditFrame,"Something went wrong! Contact system administrator!");  
                    error.printStackTrace();
                }
     
            }
            
            //Coding Part of RESET button
            if (e.getSource() == resetButton) {
                movieRankSpinner.setValue(10);
                movieNameTextField.setText("");
            }

	      }
	    });
	
		// < -------------------------------------------------- > Action listener to recognise file choice
        // modified from https://www.geeksforgeeks.org/java-swing-jfilechooser/
        chooseImageButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {  
            //Coding Part of RESET button
            if (e.getSource() == chooseImageButton) {
            	// create an object of JFileChooser class 
            	
        		String path = "src/posterImages";
                JFileChooser selectedFile = new JFileChooser(path); 
      
                // invoke the showsSaveDialog function to show the save dialog 
                int wasFileValid = selectedFile.showSaveDialog(null); 
      
                // if the user selects a file 
                if (wasFileValid == JFileChooser.APPROVE_OPTION) 
      
                { 
                    // set the label to the path of the selected file 
                	fileLabel.setText(selectedFile.getSelectedFile().getAbsolutePath()); 
                } 
                // if the user cancelled the operation 
                else
                	fileLabel.setText("Cancelled by user!"); 
            } 
	      }
	    });
        
		// < -------------------------------------------------- > Action listener that recognises when a movie rank is chosen
        // when the rank is chosen, if a movie is present in the list already, it will get that information and set all the labels to that value
        // making it easy to modify an individual movie's details
        movieRankSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
        		//JOptionPane.showMessageDialog(mainEditFrame,"Test!"); 
				
				try {
					int movieRank = (int) movieRankSpinner.getValue();
					Movie currentMovie = movieList.getMovieByRank(movieRank);
					
					
					for (int i = 0; i < movieGenres.length; ++i) {
						// movieGenres[i] == currentMovie.genre would not work here
						// equals is used for passing objects like this example
				        if (movieGenres[i].equals(currentMovie.getGenre())) {
							movieGenreOptions.setSelectedIndex(i);
				        }
				    }
					
	                movieNameTextField.setText(currentMovie.getMovieName());
	                yearReleasedSpinner.setValue(currentMovie.getYearReleased());
	                directorNameTextField.setText(currentMovie.getDirector());
	                fileLabel.setText(currentMovie.getPosterPath());
	                actorOneTextField.setText(currentMovie.getActor1());
	                actorTwoTextField.setText(currentMovie.getActor2());
	                actorThreeTextField.setText(currentMovie.getActor3());
	                roleOneTextField.setText(currentMovie.getRole1());
	                roleTwoTextField.setText(currentMovie.getRole2());
	                roleThreeTextField.setText(currentMovie.getRole3());
				} catch (NullPointerException nullError) {
					System.out.println("Movie does not exist");
					// reset labels
					movieGenreOptions.setSelectedIndex(-1); // -1 indicates no selection
	                movieNameTextField.setText("");
	                yearReleasedSpinner.setValue(2000);
	                directorNameTextField.setText("");
	                fileLabel.setText("Nothing selected...");
	                actorOneTextField.setText("");
	                actorTwoTextField.setText("");
	                actorThreeTextField.setText("");
	                roleOneTextField.setText("");
	                roleTwoTextField.setText("");
	                roleThreeTextField.setText("");
				} catch (Exception unknownError) {
					unknownError.printStackTrace();
            		JOptionPane.showMessageDialog(mainEditFrame,"Error! Contact system admin!");  
				}
			}
	    });
        
        // < -------------------------------------------------- >
        // setting configuration of the JFrame
        mainEditFrame.getContentPane().add(mainEditPanel);// add the JPanel to the correct JFrame
        mainEditFrame.setTitle("Add a movie!"); // set the title to be relevant to its usage
        mainEditFrame.setVisible(true); // see it to be visible to the user
        mainEditFrame.setSize(740, 600); // set the size so all components fit on the JFrame
        mainEditFrame.setLocationRelativeTo(null); // this places the frame into the centre of the screen
        mainEditFrame.setResizable(false); //getAddMovieForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
