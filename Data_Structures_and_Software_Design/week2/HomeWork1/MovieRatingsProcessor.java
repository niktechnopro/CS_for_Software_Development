import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.LinkedList;

public class MovieRatingsProcessor {
	
	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
//		System.out.println("getAlphabeticalMovies method: " + movieRatings);
		/* IMPLEMENT THIS METHOD! */
		List<String> moviesAlphabetic = new ArrayList<>();
        if (movieRatings == null || movieRatings.isEmpty()) return moviesAlphabetic;
		if(!movieRatings.isEmpty() && movieRatings != null) {
			for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
//				System.out.println(entry.getKey());
				moviesAlphabetic.add(entry.getKey());
			}
		}
		return moviesAlphabetic; // this line is here only so this code will compile if you don't modify it
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
//		System.out.println("input in second method: " +  movieRatings + "rating: " + rating); 
		/* IMPLEMENT THIS METHOD! */ //All movies must be with higher rating
		List<String> alphMovAboveRating = new ArrayList<>();
        if (movieRatings == null || movieRatings.isEmpty()) return alphMovAboveRating;
		if(!movieRatings.isEmpty() && movieRatings != null) {
			for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
//				
//				if(entry.getValue().peek() == 100) {
//					System.out.println(entry.getKey() + " : " + entry.getValue().peek());
//				}
				
				if(entry.getValue().peek() != null && entry.getValue().peek() > rating){
					alphMovAboveRating.add(entry.getKey());
				}			
			}
		}
		return alphMovAboveRating; // this line is here only so this code will compile if you don't modify it
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		/* IMPLEMENT THIS METHOD! */
		TreeMap <String, Integer> myTreeMap = new TreeMap<>();
        if (movieRatings == null || movieRatings.isEmpty()) return myTreeMap;
        List<String> toBeRemoved = new LinkedList<>();
		movieRatings.forEach((title, ratings) -> {//another way to iterate
			System.out.println(title + ratings);
			if (ratings.peek() < rating) {
				toBeRemoved.add(title);
			}
            if (! toBeRemoved.isEmpty()) {
				for (String name : toBeRemoved) {
					int count = 0;

					// remove all the ratings that are below the input rating from the queue
					PriorityQueue<Integer> rtgs = movieRatings.get(name);
					int head = ratings.peek();
					while (head < rating && head > -1) {
						ratings.poll();
						count++;
						if (ratings.isEmpty()) {
							head = -1;
						} else  {
							head = rtgs.peek();
						}
					}

					// if the queue is now empty, remove its entry from the treemap
					if (ratings.isEmpty()) {
						movieRatings.remove(name);
					}

					// add the title to the list of removed movie ratings
					if (count > 0) {
						myTreeMap.put(name, count);
					}
				}
			}
		});
		System.out.println(myTreeMap.values());
		return myTreeMap; // this line is here only so this code will compile if you don't modify it
	}

}
