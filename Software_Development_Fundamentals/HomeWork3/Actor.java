package moviedatabase;

import java.util.ArrayList;

public class Actor {
	
	// Class instances
	private String name; //the full name of the actor or actress
	private ArrayList<Movie> movies; //an Arraylist with movies this actor has acted in
	
	
	/**
	 * The constructor for the Actor object
	 * @param name	The String for the actor's name
	 * @param movies The ArrayList with the movies the
	 * 				 actor has been in
	 */
	public Actor(String name, ArrayList<Movie> movies) {
		this.name = name;
		this.movies = new ArrayList<>(movies);
	}
	
	
	/**
	 * An empty constructor for Actor
	 */
	public Actor() {
        this.name = "";
        this.movies = new ArrayList<>();
    }
	
	
	/**
	 * Getter for the name of the actor
	 * @return the String for the actor's name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Setter for the actor's name
	 * @param name The String with the actor's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * Getter for the list of movies the actor has been in
	 * @return The ArrayList of movies with actor in
	 */
	public ArrayList<Movie> getMovies() {
		return movies;
	}

	
	/**
	 * Setter for the list of movies with actor in
	 * @param movies The ArrayList of movies to add with
	 * 				 actor in
	 */
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
}