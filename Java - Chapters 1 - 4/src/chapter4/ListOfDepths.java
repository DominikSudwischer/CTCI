package chapter4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import util.TreeNode;

/**
 * Given a binary tree of depth D, design an algorithm which returns D linked lists,
 * where the i-th linked list contains the nodes at depth i.
 * 
 * For simplicity, assume that tree stores integers.
 * 
 * Idea: A modification of BFS. Store the number of nodes at each depth in variable
 * while doing BFS. Store that number of nodes from the queue in a linked list. When
 * that number is exceeded, start with the next level.
 * 
 * @author Dominik Sudwischer
 *
 */
public class ListOfDepths {

	public static List<LinkedList<TreeNode<Integer>>> getLists(
	        TreeNode<Integer> root) {
		if (root == null) {
			return null;
		}
		LinkedList<LinkedList<TreeNode<Integer>>> listOfDepths = new LinkedList<LinkedList<TreeNode<Integer>>>();
		listOfDepths.add(new LinkedList<TreeNode<Integer>>());
		Queue<TreeNode<Integer>> q = new LinkedList<TreeNode<Integer>>();
		TreeNode<Integer> curNode = root;
		int nodesLeftInDepth = 1;
		q.add(root);
		while (!q.isEmpty()) {
			curNode = q.remove();
			listOfDepths.getLast().add(curNode);
			nodesLeftInDepth--;
			if (curNode.leftChild != null) {
				q.add(curNode.leftChild);
			}
			if (curNode.rightChild != null) {
				q.add(curNode.rightChild);
			}
			if (nodesLeftInDepth == 0 && !q.isEmpty()) {
				listOfDepths.addLast(new LinkedList<TreeNode<Integer>>());
				nodesLeftInDepth = q.size();
			}
		}
		return listOfDepths;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		MinimalTree tree = new MinimalTree(arr);
		List<LinkedList<TreeNode<Integer>>> depthList = getLists(tree.root);
		// By convention, we will refer to the root level as depth 0 for this.
		int currentLevel = 0;
		for (LinkedList<TreeNode<Integer>> levelList : depthList) {
			System.out.println("Depth " + currentLevel++);
			for (TreeNode<Integer> node : levelList) {
				System.out.println("\t" + node.value);
			}
		}
	}

}
