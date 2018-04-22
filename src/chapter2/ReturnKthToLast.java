package chapter2;

import util.CtciSinglyLinkedList;
import util.CtciSinglyLinkedListNode;

/**
 * Given a singly linked list, return the k-th to last element. That is, the element
 * that is k steps away from the end of the list. For example, for k = 0, this will
 * return the last element, for k = 1 the one right before the last one.
 * 
 * @author Dominik Sudwischer
 *
 */
public class ReturnKthToLast {

	/*
	 * There is a trivial solution when the getSize() method can be used. Thus, we
	 * will pretend to not know the length of the list. Instead of scanning the list
	 * and counting the elements, we will to this is a single pass. Idea: Have a
	 * pointer point exactly k steps forward from the current node of the list. Then
	 * iterate through the list until that pointer hits the tail node. The current
	 * node will then be the k-th to last node. Time complexity: O(n) where n = size
	 * of the list.
	 */
	public static <T> T getKthToLastElement(CtciSinglyLinkedList<T> linkedList,
	        int k) throws IndexOutOfBoundsException {
		if (k < 0) {
			throw new IndexOutOfBoundsException();
		}
		CtciSinglyLinkedListNode<T> currentNode = linkedList.getHeadNode();
		CtciSinglyLinkedListNode<T> forwardPointerNode = currentNode;
		for (int i = 0; i < k; i++) {
			// Place the pointer k nodes ahead
			if (forwardPointerNode.getNext() != null) {
				forwardPointerNode = forwardPointerNode.getNext();
			}
			else {
				throw new IndexOutOfBoundsException();
			}
		}
		while (forwardPointerNode.getNext() != null) {
			forwardPointerNode = forwardPointerNode.getNext();
			currentNode = currentNode.getNext();
		}
		return currentNode.getValue();
	}

	public static void main(String[] args) {
		CtciSinglyLinkedList<Integer> linkedList = new CtciSinglyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			linkedList.appendBack(i);
		}
		System.out.println("LinkedList: " + linkedList);
		System.out.println("0th last element:");
		System.out.println(getKthToLastElement(linkedList, 0));
		System.out.println("2nd last element:");
		System.out.println(getKthToLastElement(linkedList, 2));
		System.out.println("9th last element:");
		System.out.println(getKthToLastElement(linkedList, 9));
		System.out.println("10th last element:");
		try {
			System.out.println(getKthToLastElement(linkedList, 10));
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Caught an IndexOutOfBoundsException!");
		}
		System.out.println("-1st last element:");
		try {
			System.out.println(getKthToLastElement(linkedList, -1));
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Caught an IndexOutOfBoundsException!");
		}
	}
}
