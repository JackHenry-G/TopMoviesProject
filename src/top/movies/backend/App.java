package top.movies.backend;

import top.movies.screens.MainMenuScreen;

public class App {
	// start application via this main method
	public static void main(String args[]) {
		
		// the below line can be commented out or uncommented when needed
		// by uncommenting we call the method that will initialize all data we want to be in the application on start up
		// < --!!!!-- save date operation !!!! uncomment to reset program data --!!!!-- >
		//loadInitalDataToDisk(); 
		// < --!!!!-- save date operation !!!! uncomment to reset program data --!!!!-- >

		// de-serialize data from the last save and assign that data to the new movieList object
		MovieMapManager movieList = Serializer.deserializeMovieList();
		
		// call the movieGrid Graphical user interface and pass the movieList that holds all the current data - therefore load the GUI with the information last saved
		try {
			MainMenuScreen movieGrid = new MainMenuScreen(movieList);
		} catch (NullPointerException nullError) {
			nullError.printStackTrace();
			System.out.println("Movie list empty!");
		} catch (Exception deserializationError) {
			deserializationError.printStackTrace();
			System.out.println("Deserialization failed! Contact system adminstrator!");
		}
	}
	
	private static void loadInitalDataToDisk() {
		// create movie objects and pass all information we want to be stored about that movie
		Movie movie1 = new Movie(1, "Jurassic Park", "Fantasy", 1993, "Steven Spielberg", "Jeff Goldblum", "Ian Malcolm", "Sam Neill", "Alan Grant", "Laura Dern", "Ellie Sattler", "src/posterImages/jurassic-park.jpg");
		Movie movie2 = new Movie(2, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont", "Morgan Freeman", "Red", "Tim Robbins", "Andy Dufresne", "William Sadler", "Heywood", "src/posterImages/shawshank_redemption.jpg");
		Movie movie3 = new Movie(3, "Star Wars: Episode V - The Empire Strikes Back", "Sci-Fi", 1980 , "Irvin Kershner", "Mark Hamill", "Luke Skywalker", "Harrison Ford", "Han Solo", "Carrie Fisher", "Princess Leia", "src/posterImages/star_wars_main.jpg");
		Movie movie4 = new Movie(4, "Baby Driver", "Crime", 1997, "Edgar Wright", "Ansel Elgort", "Baby", "Kevin Spacey", "Doc", "Lily James", "Debora", "src/posterImages/baby_driver.jpg");
		Movie movie5 = new Movie(5, "Fight Club", "Drama", 1999, "David Fincher", "Edward Norton", "The Narrator", "Brad Pitt", "Tyler Durden", "Helena Bonham Carter", "Marla Singer", "src/posterImages/fight_club.jpg");
		Movie movie6 = new Movie(6, "Goodfellas", "Crime", 1990, "Martin Scorsese", "Robert De Niro", "James Conway", "Ray Liotta", "Henry Hill", "Joe Pesci", "Tommy DeVito", "src/posterImages/goodfellas-poster.jpg");
		Movie movie7 = new Movie(7, "Joker", "Drama", 2019, "Todd Phillips", "Robert De Niro", "Murray Franklin", "Zazie Beetz", "Sophie Dumond", "Jaoquin Phoenix", "Arthur Fleck", "src/posterImages/joker.jpg");
		Movie movie8 = new Movie(8, "Step Brothers", "Comedy", 2008, "Adam McKay", "Will Ferrell", "Brennan Huff", "John C. Reilly", "Dale Doback", "Richard Jenkins", "Dr. Robert Doback", "src/posterImages/step_brothers.jpg");
		Movie movie9 = new Movie(9, "The Green Book", "Drama", 2019, "Peter Farrelly", "Viggo Mortensen", "Tony Lip", "Mahershala Ali", "Dr. Donald Shirley", "Linda Cardellini", "Dolores", "src/posterImages/the_green_book.jpg");
		Movie movie10 = new Movie(10, "The Wolf of Wall Street", "Comedy", 2013, "Martin Scorsese", "Leonardo DiCaprio", "Jordan Belfort", "Jonah Hill", "Donnie Azoff", "Margot Robbie", "Naomi Lapaglia", "src/posterImages/the_wolf_of_wall_street.jpg");
		Movie movie12 = new Movie(12, "Django Unchained", "Action", 2012, "Quentin Tarantino", "Jaimie Foxx", "Django", "Leonardo DiCaprio", "Calvin Candle", "Christoph Waltz", "Dr. King Schultz", "src/posterImages/django_unchained.jpg");
		Movie movie26 = new Movie(26, "Gone girl", "Thriller", 2014, "David Fincher", "Ben Affleck", "Nick Dunne", "Rosamund Pike", "Amy Dunne", "Neil Patrick Harris", "Desi Collings", "src/posterImages/gone-girl.jpg");
		Movie movie33 = new Movie(33, "The Grand Budapest Hotel", "Comedy", 2014, "Wes Anderson", "Ralph Fiennes", "M. Gustave", "F. Murray Abraham", "Mr. Moustafa", "Willem Dafoe", "Jopling", "src/posterImages/grand-budapest.jpg");
		Movie movie38 = new Movie(38, "The Incredibles", "Action", 2004, "Brad Bird", "Craig T. Nelson", "Bob Parr/Mr. Incredible", "Holly Hunter", "Helen Parr/Elastigirl", "Samuel L. Jackson", "Lucius Best/Syndrom", "src/posterImages/Incredibles.jpg");
		Movie movie50 = new Movie(50, "The Hunger Games", "Action", 2012, "Gary Ross", "Jennifer Lawrence", "Katniss Everdeen", "Josh Hutcherson", "Peeta Mellark", "Woody Harrelson", "Haymitch Abernathy", "src/posterImages/hunger-games.jpg");
		
		// create new movieList object and add each movie object we created to that hashmap object
		MovieMapManager movieList = new MovieMapManager();
		movieList.addMovie(movie1);
		movieList.addMovie(movie2);
		movieList.addMovie(movie3);
		movieList.addMovie(movie4);
		movieList.addMovie(movie5);
		movieList.addMovie(movie6);
		movieList.addMovie(movie7);
		movieList.addMovie(movie8);
		movieList.addMovie(movie9);
		movieList.addMovie(movie10);
		movieList.addMovie(movie12);
		movieList.addMovie(movie26);
		movieList.addMovie(movie33);
		movieList.addMovie(movie38);
		movieList.addMovie(movie50);
		// serialize (effectively save to disk) that movieList object
		Serializer.serializeMovieList(movieList);
	}
}
