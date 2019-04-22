package chapter2;

import java.util.HashSet;
import java.util.Set;

import util.CtciSinglyLinkedList;
import util.CtciSinglyLinkedListNode;

/**
 * Write code to remove duplicates from an unordered doubly linked list.
 * 
 * @author Dominik Sudwischer
 *
 */
public class RemoveDups {

	/*
	 * Keep track of content in the linked list with a HashSet and delete nodes that
	 * are contained in the set. Requires only one pass of the linked list. Time
	 * complexity: O(n) where n = number of elements in the linked list.
	 * 
	 * This problem could also be solved by storing another pointer in each node.
	 * This pointer stores a reference to the next node that contains the same value.
	 * Getting those references will take O(n^2) time where n = number of elements in
	 * the linked list. After that, delete every node for that this pointer does not
	 * store a null reference. After that, only the last node with each particular
	 * value will stay. The total time complexity is still O(n^2).
	 */
	public static <T> void removeDups(CtciSinglyLinkedList<T> linkedList) {
		Set<T> seenValues = new HashSet<T>();
		CtciSinglyLinkedListNode<T> currentNode = linkedList.getNode(0);
		CtciSinglyLinkedListNode<T> prevNotRemovedNode = null;
		while (currentNode.getNext() != null) {
			if (!checkAndDelete(seenValues, linkedList, currentNode, prevNotRemovedNode)) {
				seenValues.add(currentNode.getValue());
				prevNotRemovedNode = currentNode;
			}
			currentNode = currentNode.getNext();
		}
		// Don't forget the tail
		checkAndDelete(seenValues, linkedList, currentNode, prevNotRemovedNode);

	}

	private static <T> boolean checkAndDelete(Set<T> seenValues,
			CtciSinglyLinkedList<T> linkedList,
			CtciSinglyLinkedListNode<T> currentNode,
			CtciSinglyLinkedListNode<T> prevNode) {
		if (seenValues.contains(currentNode.getValue())) {
			linkedList.removeNode(currentNode, prevNode);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		CtciSinglyLinkedList<Integer> linkedList = new CtciSinglyLinkedList<Integer>();
		linkedList.appendBack(0);
		linkedList.appendBack(0);
		linkedList.appendBack(0);
		linkedList.appendBack(2);
		linkedList.appendBack(1);
		linkedList.appendBack(3);
		linkedList.appendBack(1);
		linkedList.appendBack(4);
		linkedList.appendBack(102);
		linkedList.appendBack(102);
		linkedList.appendBack(0);
		System.out.println(linkedList.toString());
		removeDups(linkedList);
		System.out.println(linkedList.toString());
	}
}
