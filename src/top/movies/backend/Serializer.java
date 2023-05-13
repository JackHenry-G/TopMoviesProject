package top.movies.backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// https://www.tutorialspoint.com/java/java_serialization.htm
// code adapted from the webpage above
// Serializer class amended to pass in the 'movieList' object that the user is in current use of
// this way the same object is updated and serialized so that it stays consistent over time

// Deserializer then returns this object so that the other objects can use that up to date version of the movie list object

/*
 * Streams in Java
 * "An I/O Stream represents an input source or an output destination. 
 * A stream can represent many different kinds of sources and destinations, including disk files, devices, 
 * other programs, and memory arrays. Streams support many different kinds of data, including simple bytes, 
 * primitive data types, localized characters, and objects. Some streams simply pass on data; others manipulate 
 * and transform the data in useful ways. No matter how they work internally, all streams present the same 
 * simple model to programs that use them: A stream is a sequence of data. A program uses an input stream to 
 * read data from a source, one item at a time:" - https://docs.oracle.com/javase/tutorial/essential/io/streams.html
 *  
 *  
 */

public class Serializer {
	// < -------------------------------------------------- >
	// serialize the movielist (hashmap) object to disk, effectively saves the list of movies to memory
	public static void serializeMovieList(MovieMapManager movieList) {

		try {
			// "A program uses an output stream to write data to a destination, one item at time:" - https://docs.oracle.com/javase/tutorial/essential/io/streams.html
			FileOutputStream fileOut = new FileOutputStream("listOfMovies.ser"); // preps the file as an output stream
			ObjectOutputStream out = new ObjectOutputStream(fileOut); // passes the file in to use as an object output stream for writing our 'movieList' object to
			
			out.writeObject(movieList); // write movie to the output stream (listOfMovies.ser file)
			out.close(); // close the stream to minimize any future potential issues
			fileOut.close(); // close the file stream to minimize any future potential issues
			
			System.out.println(movieList + " - Serialize successful!");
		} catch (FileNotFoundException fileError) {
			fileError.printStackTrace();
		} catch (IOException IOError) {
			IOError.printStackTrace();
		}
		
	}
	
	// < -------------------------------------------------- >
	// deerialize the movielist (hashmap) object that is saved to disk, effectively retrieving the data that is saved to memory
	public static MovieMapManager deserializeMovieList() {
		MovieMapManager movieList = null; // define the movieList object for later use in the method

		try {
			// A program uses an input stream to read data from a source, one item at a time - https://docs.oracle.com/javase/tutorial/essential/io/streams.html
			FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/listOfMovies.ser"); // prep the file as an input source
			ObjectInputStream in = new ObjectInputStream(fileIn); // convert the file into an input stream that can be read and converted to an object

			// cast this as our User data type
			// the class's definition ("class file") itself is not recorded which is wy we cast it to the specific data type of that object
			movieList = (MovieMapManager) in.readObject(); // read the file and cast it to a MovieMapManager object called movieList

			// close streams for safety purposes
			in.close(); 
			fileIn.close();
			return movieList; // return the object that was deserialized from the file (input stream)
		} catch (FileNotFoundException fileError) {
			fileError.printStackTrace();
		} catch (IOException IOError) {
			IOError.printStackTrace();
		} catch (ClassNotFoundException classError) {
			classError.printStackTrace();
		}

		return movieList; // will return null if it cannot be deserialized
	}
				
}
