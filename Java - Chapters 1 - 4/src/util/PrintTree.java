package util;

public class PrintTree<T> {

	public static void print(TreeNode<?> root) {
		TreeNode<?> curNode = root;
		if (curNode != null) {
			System.out.println("Node:");
			System.out.println(curNode.value);
			if (curNode.leftChild != null) {
				System.out.println("Left:");
				System.out.println(curNode.leftChild.value);
			}
			if (curNode.rightChild != null) {
				System.out.println("Right:");
				System.out.println(curNode.rightChild.value);
			}
		}
		if (curNode.leftChild != null) {
			print(curNode.leftChild);
		}
		if (curNode.rightChild != null) {
			print(curNode.rightChild);
		}
	}

}
