import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * This is an implementation of Breadth First Search (BFS) on a graph.
 * It was already implemented
 */

public class BreadthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;
	protected Map<Node, Integer> distance; // Added to keep track of distance by me

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		distance = new HashMap<Node, Integer>();
		graph = graphToSearch;
	}
	
	/**
	 * This method was discussed in the lesson
	 */
	public boolean bfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
				return false;
		}
		if (start.getElement().equals(elementToFind)) {
			distance.put(start, 0);
			return true;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		distance.put(start, 0);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					distance.put(neighbor, distance.get(current) + 1);
					if (neighbor.getElement().equals(elementToFind)) {
						return true;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}
		return false;
	}
	

}