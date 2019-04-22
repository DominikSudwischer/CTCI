package util;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent nodes in a graph.
 * 
 * @author Dominik Sudwischer
 *
 * @param <T> Type of values to store
 */
public class GraphNode<T> {

	// For simplicity, everything is public
	public List<GraphNode<T>> neighbors;
	public T value;
	
	public GraphNode(T value) {
		this.value = value;
		neighbors = new ArrayList<GraphNode<T>>();
	}
}
