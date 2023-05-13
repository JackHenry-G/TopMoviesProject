package top.movies.backend;

import java.io.Serializable;

public class Movie implements Serializable{
	
	private static final long serialVersionUID = -1864949790589280463L;
	
	// define all attributes to be stored about a Movie object
	private int movieRanking;
	private String movieName;
	private String genre;
	private int yearReleased;
	private String director;
	private String actor1;
	private String actor2;
	private String actor3;
	private String role1;
	private String role2;
	private String role3;
	private String posterPath;

	// constructor class that holds all the properties of the new movie object that has been created
	// must be public so classes outside of this package can access it
	public Movie (int rank, String movieName, String genre, int yearReleased, String director, String actor1, String role1, String actor2, String role2, String actor3, String role3, String posterPath) {
		this.movieRanking = rank;
		this.movieName = movieName;
		this.genre = genre;
		this.yearReleased = yearReleased;
		this.director = director;
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.actor3 = actor3;
		this.role1 = role1;
		this.role2 = role2;
		this.role3 = role3; 
		this.posterPath = posterPath; 
	}
	
	// < -------------------------------------------------- >
	// getters and setters - retrieve and modify values of the object
	public int getMovieRanking() {
		return movieRanking;
	}



	public void setMovieRanking(int movieRanking) {
		this.movieRanking = movieRanking;
	}



	public String getMovieName() {
		return movieName;
	}



	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}



	public String getGenre() {
		return genre;
	}



	public void setGenre(String genre) {
		this.genre = genre;
	}



	public int getYearReleased() {
		return yearReleased;
	}



	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}



	public String getDirector() {
		return director;
	}



	public void setDirector(String director) {
		this.director = director;
	}



	public String getActor1() {
		return actor1;
	}



	public void setActor1(String actor1) {
		this.actor1 = actor1;
	}



	public String getActor2() {
		return actor2;
	}



	public void setActor2(String actor2) {
		this.actor2 = actor2;
	}



	public String getActor3() {
		return actor3;
	}



	public void setActor3(String actor3) {
		this.actor3 = actor3;
	}



	public String getRole1() {
		return role1;
	}



	public void setRole1(String role1) {
		this.role1 = role1;
	}



	public String getRole2() {
		return role2;
	}



	public void setRole2(String role2) {
		this.role2 = role2;
	}



	public String getRole3() {
		return role3;
	}



	public void setRole3(String role3) {
		this.role3 = role3;
	}



	public String getPosterPath() {
		return posterPath;
	}



	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	
	// < -------------------------------------------------- >
	// toString() method to represent the objecta as a string
	// printing this object, Java will invoke this method. Overriding the toString() method
	// helps greatly when debugging and testing code
	@Override
	public String toString() {
			return "Ranking: " + movieRanking + "\nName: " + movieName + "\nGenre: " + genre + "\nYear released: " + yearReleased + "\nDirector: " + director + "\nActor 1: "
					+ actor1 + ", Role: " + role1 + "\nActor 2: " + actor2 + ", Role: " + role2 + "\nActor 3: " + actor3 + ", Role: " + role3 + "\nImage path: " + posterPath;
	}
	
}
