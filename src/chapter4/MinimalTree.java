package chapter4;

import util.PrintTree;
import util.TreeNode;

/**
 * Given a sorted (strictly increasing) array of integers, write an algorithm to make
 * create a binary search tree of minimal height.
 * 
 * Idea: The tree does not, according to the terminology in the book, have to be
 * complete or full. Thus, we can simply use the middle element as the root and then
 * recursively build the left and right subtrees on the other halves of the array.
 * 
 * @author Dominik Sudwischer
 *
 */

public class MinimalTree {

	public TreeNode<Integer> root;

	/**
	 * This function recursively builds subtrees that fulfill the binary tree
	 * ordering property. It splits the (sorted) array in halves to construct left
	 * and right subtrees. During each step of the recursion, the middle point of the
	 * array is the new node.
	 * 
	 * @param arr The sorted array of distinct integers (increasing)
	 * @param minIndex The start index of the sub array in that step (inclusive)
	 * @param maxIndex The end index of the sub array in that step (inclusive)
	 * @param parentNode The parent node of the new subtree
	 * @return 
	 */
	private TreeNode<Integer> buildSubtree(int[] arr, int minIndex, int maxIndex,
	        TreeNode<Integer> parentNode) {
		if (maxIndex < minIndex) {
			return null;
		}
		int middleIndex = minIndex + (maxIndex - minIndex) / 2;
		TreeNode<Integer> newNodeWithMiddleValue = new TreeNode<Integer>(parentNode,
		        arr[middleIndex]);
		newNodeWithMiddleValue.leftChild = buildSubtree(arr, minIndex,
		        middleIndex - 1, newNodeWithMiddleValue);
		newNodeWithMiddleValue.rightChild = buildSubtree(arr, middleIndex + 1,
		        maxIndex, newNodeWithMiddleValue);
		return newNodeWithMiddleValue;
	}

	public MinimalTree(int[] arr) {
		root = buildSubtree(arr, 0, arr.length - 1, null);
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		MinimalTree tree = new MinimalTree(arr);
		PrintTree.print(tree.root);
	}

}
