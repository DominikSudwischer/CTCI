package chapter2;

import util.CtciSinglyLinkedList;
import util.CtciSinglyLinkedListNode;

/**
 * Given any node of a singly linked list (except for head and tail), delete that
 * node from the list. You only have access to that node.
 * 
 * @author Dominik Sudwischer
 *
 */
public class DeleteMiddleNode {

	/*
	 * Idea: Copy the values from the the nodes after the node to delete to each
	 * node's respective predecessor. Then, pop the last node from the list. Time
	 * complexity: O(n) where n = size of the list.
	 */
	public static <T> void deleteMiddleNode(CtciSinglyLinkedList<T> linkedList, CtciSinglyLinkedListNode<T> nodeToDelete) {
		CtciSinglyLinkedListNode<T> currentNode = nodeToDelete;
		while(!currentNode.getNext().equals(linkedList.getTailNode())) {
			currentNode.setValue(currentNode.getNext().getValue());
			currentNode = currentNode.getNext();
		}
		// currentNode is now the node prior to the tail node
		currentNode.setValue(currentNode.getNext().getValue());
		linkedList.removeTailNode(currentNode);
	}
	
	public static void main(String[] args) {
		CtciSinglyLinkedList<Integer> linkedList = new CtciSinglyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			linkedList.appendBack(i);
		}
		CtciSinglyLinkedListNode<Integer> nodeToDelete = linkedList.getNode(4);
		System.out.println("LinkedList: " + linkedList);
		System.out.println("Deleting the node at index 4.");
		deleteMiddleNode(linkedList, nodeToDelete);
		System.out.println("LinkedList: " + linkedList);
		nodeToDelete = linkedList.getNode(1);
		System.out.println("Deleting the node at index 1.");
		deleteMiddleNode(linkedList, nodeToDelete);
		System.out.println("LinkedList: " + linkedList);
		nodeToDelete = linkedList.getNode(6);
		System.out.println("Deleting the node at index 6.");
		deleteMiddleNode(linkedList, nodeToDelete);
		System.out.println("LinkedList: " + linkedList);
	}
}
