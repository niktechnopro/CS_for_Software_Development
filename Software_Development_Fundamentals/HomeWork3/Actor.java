import java.util.ArrayList;

public class Actor {
	private String name;
	private ArrayList<Movie> movies;
	
	public Actor() {//constructor to initialize variables
		this.name = "";
		this.movies = new ArrayList<>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
}
