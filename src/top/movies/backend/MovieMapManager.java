package top.movies.backend;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MovieMapManager implements Serializable {
	
	private static final long serialVersionUID = 4694650548248425171L;
	
	// < -------------------------------------------------- >
	// by making it private, it is only used inside this class
	// use a hashmap over an array list because with an arrayList, movies would be held in a random order without an assigned key like with a key-value hashmap
	// if an arrayList was used, then to execute get movie at rank 66 for example, we would have to loop through each object looking at the rank until we find the correct value
	// if the movie is at the end of the list, this could prove inefficient compared to a hashmap where we could get the movie by rank/key as we can know specifically where it is stored
	// private access modifier as we only want it do be edit by the methods in this class
	private Map<Integer, Movie> mapOfMovies = new HashMap<Integer, Movie>();
	
	// < -------------------------------------------------- >
	// populates the hashmap with all the movies that were retrieved from the data saved to disk
	// args represents a list of arguments passed in that has no preset length value. e.g. application could pass 1 argument in, it could pass 1000 in
	// can pass as many arguments, but they must all be of the type 'Movie' class
	// a for loop then says, for each movie from the arguments pass in, put them in the hashmap with the key by the rank defined in the object
	// movie object itself is the value, the attribute movie.Rank is the key in this key-value pair data structure
	public void createInitialMovieList(Movie... args) {
		for (Movie movie: args) {
			mapOfMovies.put(movie.getMovieRanking(), movie);
		}
	}
	
	// < -------------------------------------------------- >
	// add the movie passed as an argument to the hashmap, with the key as the ranking defined in the object
	// movie object itself is the value, the attribute movie.Rank is the key in this key-value pair data structure
	// will overwrite if there is already something in the hashmap with this key
	public void addMovie(Movie movie) {
		mapOfMovies.put(movie.getMovieRanking(), movie);
	}
	
	// < -------------------------------------------------- >
	// search by rank number
	public Movie getMovieByRank(int rank) {
		return mapOfMovies.get(rank);
	}
	
	// < -------------------------------------------------- >
	// method used to 'slot in' movie
	// if user tries to slot in a movie at rank 5, for example, and there is already a movie at rank 5, 6 and 7 but not at 8 and there is a movie at rank 9 e.g.
	// movies at rank 5, 6, 7 will move to 6, 7, 8 respectively. 8 doesn't need to be moved as there is nothing there. Movie at rank 9 is unaffected
	// rank 5 is now empty, so the orginial movie the user tried to enter can therefore be placed at rank 5
	public void moveAllMoviesUpOneRank(Movie movieToSlotIn) {
		
		// convert all keys of the map into a set
		Set<Integer> allRanks = mapOfMovies.keySet(); // set - unordered collection of items in which there can be no duplicates (no duplicate ranks and order is not important)
		Integer highestRankedMovie = Collections.max(allRanks); // highest ranked movie allows us to iterate down from a set variable, rather than an arbitrary number - this will increase efficiencey
		
		// iterate down from 100, must be done in reverse because if you did it in usual order then you would be attempting to
		// modify the object that you just moved up one, meaning you are editing the same object
		// e.g. 2: Jurassic Park is moved to 3: Jurassic Park. Essentially removing the 3rd movie, and having another jurassic park in
		// this would keep duplicating or cause an error
		for (int i = highestRankedMovie + 1; i >= movieToSlotIn.getMovieRanking(); i--) {
			// store the movie thats about to be moved in a variable so its not deleted from memory
			Movie storeOfMovieToSlot = mapOfMovies.get(i);
			// check that its not null, e.g. user may have only added their top 10, when there are 100 slots
			if (storeOfMovieToSlot != null) {
				Movie checkMovieBeforeIsNotNull = mapOfMovies.get(i - 1); // if the movie before is null then we dont need to movie the current one
				if (checkMovieBeforeIsNotNull != null) { // if the movie before is null, then the current movie does not need to be moved up one as there is pace for the others
					// remove the movie as map keys cannot be modified
					mapOfMovies.remove(i);
					// put it back in with 1 higher rank
					mapOfMovies.put(i + 1, storeOfMovieToSlot);
					// edit the rank property in the movie object to be one higher too
					storeOfMovieToSlot.setMovieRanking(storeOfMovieToSlot.getMovieRanking() + 1);
				}
			}
		}
	}
}

