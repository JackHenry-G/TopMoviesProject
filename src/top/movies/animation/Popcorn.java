package top.movies.animation;

public class Popcorn {
	
	// x and y value of the popcorn object. Represents the x and y coordinates of the image to be painted to the screen
	int xPosition;
	int yPosition;
	
	// constructor class - pass in x and y values, then set them to the current object in question
	Popcorn(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	// < -------------------------------------------------- >
	// getters and setters - retrieve and modify values of the object
	public int getxPosition() {
		return xPosition;
	}
	
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	public int getyPosition() {
		return yPosition;
	}
	
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	// < -------------------------------------------------- >
	// toString() method to represent the objecta as a string
	// printing this object, Java will invoke this method. Overriding the toString() method
	// helps greatly when debugging and testing code
	@Override
	public String toString() {
			return "X-coordinate - " + xPosition + ". Y-coordinate - " + yPosition;
	}
}
