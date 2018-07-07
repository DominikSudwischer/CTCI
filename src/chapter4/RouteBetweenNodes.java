package chapter4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import util.Graph;
import util.GraphNode;

/**
 * Given a directed graph, check if a path from one node to another exists.
 * 
 * 
 * 
 * @author Dominik Sudwischer
 *
 */
public class RouteBetweenNodes extends Graph<Integer> {

	public RouteBetweenNodes() {
		super();
	}
	
	public boolean existsRoute(int i, int j) {
		if (nodes.size() <= Math.max(i, j) || i < 0 || j < 0) {
			throw new IndexOutOfBoundsException();
		}
		else if (i == j) {
			return true;
		}
		GraphNode<Integer> target = nodes.get(j);
		GraphNode<Integer> start = nodes.get(i);
		Set<GraphNode<Integer>> visited = new HashSet<GraphNode<Integer>>();
		Queue<GraphNode<Integer>> toExplore = new LinkedList<GraphNode<Integer>>();
		toExplore.add(start);
		visited.add(start);
		while (!toExplore.isEmpty()) {
			GraphNode<Integer> next = toExplore.poll();
			visited.add(next);
			for (GraphNode<Integer> neighbor : next.neighbors) {
				if (neighbor == target) {
					return true;
				}
				if (!visited.contains(neighbor)) {
					toExplore.add(neighbor);
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		RouteBetweenNodes graph = new RouteBetweenNodes();
		GraphNode<Integer> node0 = new GraphNode<Integer>(0);
		GraphNode<Integer> node1 = new GraphNode<Integer>(0);
		GraphNode<Integer> node2 = new GraphNode<Integer>(0);
		GraphNode<Integer> node3 = new GraphNode<Integer>(0);
		GraphNode<Integer> node4 = new GraphNode<Integer>(0);
		node0.neighbors.add(node1);
		node1.neighbors.add(node2);
		node2.neighbors.add(node0);
		node2.neighbors.add(node3);
		node3.neighbors.add(node2);
		graph.nodes.add(node0);
		graph.nodes.add(node1);
		graph.nodes.add(node2);
		graph.nodes.add(node3);
		graph.nodes.add(node4);
		System.out.println(graph.existsRoute(0, 3)); // true
		System.out.println(graph.existsRoute(4, 1)); // false
		System.out.println(graph.existsRoute(3, 1)); // true
		System.out.println(graph.existsRoute(0, 4)); // false
		System.out.println(graph.existsRoute(3, 3)); // true
	}
}
