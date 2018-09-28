package chapter4;

import util.TreeNode;

/**
 * Write a method to check if a given binary tree is balanced, that is, no two
 * subtrees of any node differ in height by more than 1.
 * 
 * Idea: Recursively compute the depth of each subtree of a node. If a node has no
 * children (i.e. that node is a leaf), return 0, else return the maximum depth of
 * both subtrees plus one. At each step, compare the depths of the left and right
 * subtree. If they differ by more than one, return -1 as error code, indicating that
 * the tree is not balanced.
 * 
 * If n is the number of elements in the tree, the runtime is O(n).
 * 
 * @author Dominik Sudwischer
 *
 */

public class CheckBalanced {

	public static int depthCheck(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int depthLeft = depthCheck(root.leftChild);
		if (depthLeft == -1) {
			return -1;
		}
		int depthRight = depthCheck(root.rightChild);
		if (depthRight == -1) {
			return -1;
		}
		if (Math.abs(depthLeft - depthRight) > 1) {
			return -1;
		}
		else {
			return Math.max(depthLeft, depthRight) + 1;
		}
	}

	public static boolean isBalanced(TreeNode<Integer> root) {
		return depthCheck(root) >= 0;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		MinimalTree tree = new MinimalTree(arr);
		System.out.println(depthCheck(tree.root)); // 4

		TreeNode<Integer> node0 = new TreeNode<Integer>(null, 0);
		System.out.println(depthCheck(node0)); // 1

		TreeNode<Integer> node1 = new TreeNode<Integer>(node0, 0);
		TreeNode<Integer> node2 = new TreeNode<Integer>(node1, 0);
		node0.leftChild = node1;
		node1.leftChild = node2;
		TreeNode<Integer> node3 = new TreeNode<Integer>(node0, 0);
		node0.rightChild = node3;
		System.out.println(depthCheck(node0)); // 3

		TreeNode<Integer> node4 = new TreeNode<Integer>(node2, 0);
		node2.rightChild = node4;
		System.out.println(depthCheck(node0)); // -1
	}

}
