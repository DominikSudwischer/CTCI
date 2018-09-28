package util;

/**
 * A very simple tree node class for tree exercises. This even lacks a proper constructor!
 * 
 * @author Dominik Sudwischer
 *
 * @param <T>
 */
public class TreeNode<T> {
	
	// For simplicity, everything is public
	public TreeNode<T> parent;
	public TreeNode<T> leftChild;
	public TreeNode<T> rightChild;
	public T value;
	
	public TreeNode(TreeNode<T> parent, T value) {
		this.parent = parent;
		this.leftChild = null;
		this.rightChild = null;
		this.value = value;
	}
}
