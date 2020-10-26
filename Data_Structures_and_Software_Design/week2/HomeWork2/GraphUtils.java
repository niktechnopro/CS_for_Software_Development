

import java.util.List;
import java.util.Set;
import java.util.HashSet;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		/* IMPLEMENT THIS METHOD! */
		if(graph != null && src != null && dest != null && graph.containsElement(src)&& graph.containsElement(dest)) {
            //implement DFS method
            BreadthFirstSearch bfsearch = new BreadthFirstSearch(graph);// BFS for shortest path
            Node srcNode = graph.getNode(src);
            Node destNode = graph.getNode(dest);
            if(bfsearch.bfs(srcNode, dest)){//checks if there is a path from src to destination using BreadthFirstSearch algorithm
//                 System.out.println("boolean true: " + bfsearch.distance.get(graph.getNode(dest)));
                return bfsearch.distance.get(destNode);
            }
            return -1;
        }
		
		return -1;
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
        Set<String> mySet = new HashSet<>();
		/* IMPLEMENT THIS METHOD! */
        if(graph == null || src == null | !graph.containsElement(src) || distance < 1) return null;
		
        Set<Node> allNodes = graph.getAllNodes();
//         System.out.println("all nodes: " + allNodes);
        for (Node n : allNodes){
//             System.out.println("n element: " + minDistance(graph, src, n.getElement()));
            if(minDistance(graph, src, n.getElement())>0 && minDistance(graph, src, n.getElement())<=distance){
                mySet.add(n.getElement());
            }
        }
		return mySet; // this line is here only so this code will compile if you don't modify it
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		/* IMPLEMENT THIS METHOD! */
        // Check that no null input, List is cycle and
		// doesn't have more elements than nodes in graph
		if(g != null && values != null && !values.isEmpty() && values.get(0).equals(values.get(values.size() - 1)) && values.size() == g.getNumNodes() + 1) {
			
			for(int index = 0; index < values.size() - 1; index ++) {
				
				// Make sure value corresponds to a node
				if(!g.containsElement(values.get(index)) && !g.containsElement(values.get(index + 1))) return false;
				
				Node current = g.getNode(values.get(index));
				Node next = g.getNode(values.get(index + 1));
				
				// Make sure distance between contiguous nodes is 1
				if(minDistance(g, current.getElement(), next.getElement()) != 1) return false;
			}
			return true;
		}
		return false;
	}
	
}
