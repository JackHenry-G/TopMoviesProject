package top.movies.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import top.movies.backend.Constants;

import java.util.ArrayList;
import javax.swing.JButton;

public class AnimationPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = -6998742416401670741L;
	
	// background colour
	Color dark_blue = new Color(18, 14, 70);
	// button colour
	Color gold = new Color(255, 215, 0);
	// button border
    Border buttonsBorder = BorderFactory.createLineBorder(gold, 2);
	
	// defining the maximum and minimum values for where the popcorn image can spawn, for calculating random values between these integers
	int minXStartValue = -100; // Farthest left an image can go
	int maxXStartValue = 250; // Farthest right an image can go
	int minYStartValue = -30; // lowest an image can spawn
	int maxYStartValue = -1000; // highest an image can spawn

	// defining the popcorn image's current x and y position
    int popcornImageXPosition; // current position on x axis
	int popcornImageYPosition; // current position on y axis
	
	// defining where the popcorn image should stop at the bottom of the panel to create illusion of box filling up
	int minYStopValue = 300; // highest point it should stop
	int maxYStopValue = 400; // lowest point it should stop
	int stopFallingYValue; // actual value where it should stop. Random value between the minYStopValue and maxYStopValue integers.

	// list to hold each popcorn object/image - must be initialised here so it can be accessed globally
	ArrayList<Popcorn> popcornObjectList;
	
	// timer to start when the loading has begun and end when the information is loaded, timer is necessary
	// each time the timer refreshes, the actionListener is activated, executing the painting code
	Timer timer;
	
	// defining the image to be used
	BufferedImage popcornImageBuffered;
	
	// constructor - public so that it can be accessed outside of package
	public AnimationPanel() {
		// creating the instance of the popcornObjectList
		popcornObjectList = new ArrayList<Popcorn>();
		
		// creating button with text to signal to use this is a loading screen
		JButton loadingScreenText = new JButton("Loading movies, grabbing popcorn..."); // define JButton with this title
		loadingScreenText.setBounds(0, 461, 280, 28); // set the position of the Jbutton
		loadingScreenText.setFont(new Font("Javanese Text", Font.PLAIN, 14)); // set font type
		loadingScreenText.setBackground(Color.DARK_GRAY); // set background color to Java's dark gray
		loadingScreenText.setForeground(gold); // set foreground to the gold color we have already defined
		loadingScreenText.setBorder(buttonsBorder); // set colour/type of border to our preset style
		add(loadingScreenText); // addd JButton to panel
		
		// ----------------------------------------------------
		// set size and background colour of panel
		this.setPreferredSize(new Dimension(280, 500)); 
		this.setBackground(dark_blue); 
		
		// 'this' refers to the action listener linked to this panel
		// at each time interval, the action listerer will be activated i.e. after each 10 miliseconds the actionListener linked to this class will be called
		// which will repaint the screen
		timer = new Timer(10, this);
		//set layout of screen to be null, so we can place labels and such anywhere we want
		setLayout(null);
		
		// add image at the bottom of the screen (popcorn box)
		JLabel popcornBoxImageLabel = new JLabel("Popcorn image");
		popcornBoxImageLabel.setBounds(-83, 192, 431, 628);
		popcornBoxImageLabel.setIcon(new ImageIcon(AnimationPanel.class.getResource("/posterImages/popcorn_box.png")));
		add(popcornBoxImageLabel);
		
		// image of a single popcorn kernel
		try {
			popcornImageBuffered = ImageIO.read(getClass().getResource("/posterImages/single_popcorn_1.png"));
		} catch (IOException retrieveImageException) {
			retrieveImageException.printStackTrace();
		}
		
		// now that everything has been initialised, start the timer, in turn, the loading animation
		timer.start();
		
		// ----------------------------------------------------
		// creating the popcorn objects that will be falling on the screen, we will create 250 as its a short loading screen
		// each time the i variable iterates by one, it will create a new image (object of the PopcornOnLoadingScreen class) with random x and y coordinates as well as a constant y-velocity
		// it will then add this new object to a list, where it will be stored for later access
		for (int i = 0; i < 250; i++) {
			popcornImageXPosition = (int) (Math.random() * (maxXStartValue - minXStartValue + 1) + minXStartValue); 
			popcornImageYPosition = (int) (Math.random() * (maxYStartValue - minYStartValue + 1) + minYStartValue); 
			popcornObjectList.add(new Popcorn(popcornImageXPosition, popcornImageYPosition));
	     }
	}
	
	// rotation integer to initially be zero so that with each new animation thread the rotation is reset so that the animation begins again
	int rotationDegrees = 0;
	public synchronized void paint(Graphics g) {
		// this will paint the background for us
		super.paint(g);
		
		// call method to rotate image by a number that continuously increments
		// this means each time it is called the image will rotate further than last time to give the impression of constant spinning
		AffineTransformOp rotateOp = getRotateOp(popcornImageBuffered, rotationDegrees++);
		
		// get the graphics object associated with the JPanel
		Graphics2D g2D = (Graphics2D) g;
		
		// ----------------------------------------------------
		// iterate through our list that holds details (x and y coordinates, velocity) of each popcorn image on the screen
		// at each value in the list, draw the popcorn image at the x and y value held as part of that object
		for (int i = 0; i < 250; i++) {
			// get the x and y value of the object and the current element of the arrayList
			// draw the image (rotated) at that new x and y value on the screen
			g2D.drawImage(popcornImageBuffered, rotateOp, popcornObjectList.get(i).getxPosition(), popcornObjectList.get(i).getyPosition());
	     }
		
	}
	
	// method to stop the timer, once all the information has loaded and therefore the loading screen can be stopped
	public synchronized void stopTimer() {
		timer.stop();
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		
		// ----------------------------------------------------
		// iterate through our list that holds details (x and y coordinates, velocity) of each popcorn image on the screen
		// get the current y-position and update it, so that the popcorn moves down the screen
		for (int i = 0; i < 250; i++) {

			// need to retrieve current y value of the object in the current element of the arrayList
			popcornImageYPosition = popcornObjectList.get(i).getyPosition();
			
			// y-value to stop popcorn falling (have box fill up)
			stopFallingYValue = (int) (Math.random() * (maxYStopValue - minYStopValue + 1) + minYStopValue);  
			
			// if the popcorn image has reached a certain y value, meaning it has reached the bottom of the panel, stop updating the y-value
			if (popcornObjectList.get(i).getyPosition() < stopFallingYValue) {
				// increment popcornImageYPosition by our set velocity (2) so that the image displays further down teh screen
				popcornImageYPosition += Constants.YVELOCITY;
				// set the object's y value to our new Y value
				popcornObjectList.get(i).setyPosition(popcornImageYPosition);
			}
	     }
		
		// repaint the screen, meaning clear everything on the screen and repaint it with updated details to give impression of animation
		repaint();
	}
	
	// credit to Steve Dalton for rotation of image method
	// passes in a bufferedimage and returns the bufferedImage but rotated by the integer parameter passed in
	private synchronized AffineTransformOp getRotateOp(BufferedImage image, int degrees) {
        // generates an Affine Transformation Operator
        double radians = Math.toRadians(degrees);
        
        // where the centre point is on the image
        double xCentre = image.getWidth() / 2;
        double yCentre = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(radians, xCentre, yCentre);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op;
    }
}
