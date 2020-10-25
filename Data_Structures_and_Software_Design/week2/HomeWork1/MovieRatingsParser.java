/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
//		System.out.println("allUsersRatings incoming list: " + allUsersRatings);
		/* IMPLEMENT THIS METHOD! */
//		create and populate TreeMap
		TreeMap <String, PriorityQueue<Integer>> myTreeMap = new TreeMap<>();
		if(allUsersRatings.isEmpty() || allUsersRatings == null) return myTreeMap;
		String mo = "";
		for(UserMovieRating userMovie : allUsersRatings) {
			if(userMovie != null && userMovie.getMovie() != null && !userMovie.getMovie().isEmpty() && userMovie.getUserRating() >= 0){
				//case insensitive
				mo = userMovie.getMovie().toLowerCase();
				if(!myTreeMap.containsKey(mo)) {//not in TreeMap yet
					PriorityQueue<Integer> myPrQueue = new PriorityQueue<>();
					myPrQueue.add(userMovie.getUserRating());
					myTreeMap.put(mo, myPrQueue);
				}else {
					PriorityQueue<Integer> existingPrQueue = myTreeMap.remove(mo);
					existingPrQueue.add(userMovie.getUserRating());
					myTreeMap.put(mo, existingPrQueue);
				}
			}
		}
		
		return myTreeMap; // this line is here only so this code will compile if you don't modify it
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Let's read the file and create a List of UserMovieRating objects");
		String line;
		String movie;
		int rating;
		List<UserMovieRating> movieRatings = new ArrayList<>();
		try {
			BufferedReader ratingReader = new BufferedReader(new FileReader("movieratings.txt"));
			while((line = ratingReader.readLine()) != null) {
				movie = line.split("(?<=\\D)(?=\\d)")[0].trim();
				rating = Integer.parseInt(line.split("(?<=\\D)(?=\\d)")[1].trim());
				UserMovieRating movieRating = new UserMovieRating(movie, rating);
				movieRatings.add(movieRating);
			}
			parseMovieRatings(movieRatings);
			ratingReader.close();
		}catch(Exception ex) {
			System.out.println("we had an error reading file " + ex.getMessage());
		}
		
		
	}

}
