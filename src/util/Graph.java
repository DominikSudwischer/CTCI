package util;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple graph class for graph exercises.
 * 
 * @author Dominik Sudwischer
 *
 * @param <T> The type of values to store
 */
public class Graph<T> {
	
	// For simplicity, this is public
	public List<GraphNode<T>> nodes;
	
	public Graph() {
		nodes = new ArrayList<GraphNode<T>>();
	}
}
