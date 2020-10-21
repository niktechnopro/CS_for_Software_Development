package moviedatabase;

import java.util.ArrayList;

public class Movie {
	
	// Class instances
	private String name; // the name of the movie
	private ArrayList<Actor> actors; // an ArrayList of the actors in the movie
	private double rating; //a freshness rating from rotten tomatoes
	
	
	/**
     * The constructor for a Movie object
     * @param name The String of the name of the movie
     * @param actors The ArrayList with the actors 
     * 				 in the movie
     */
	public Movie(String name, ArrayList<Actor> actors) {
		this.name = name;
		this.actors = new ArrayList<>(actors);
	}
	
	
	/**
     * Getter for the movies Name
     * @return String for the name of movie
     */
	public String getName() {
		return name;
	}


	/**
	 * Setter for the movies Name
	 * @param name String for the name of movie
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
     * Getter for the movie's list of actors
     * @return ArrayList of Actor objects
     */
	public ArrayList<Actor> getActors() {
		return actors;
	}


	/**
	 * Setter for the actor Name
	 * @param actors The ArrayList with the actors 
	 * 				 added
	 */
	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}


	/**
	 * Getter for the rating of a movie
	 * @return The double rating of a movie
	 */
	public double getRating() {
		return rating;
	}


	/**
	 * Setter for the rating of a movie
	 * @param rating The double rating to add
	 * 				 to a movie
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
}