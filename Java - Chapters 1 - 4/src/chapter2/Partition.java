package chapter2;

import util.CtciSinglyLinkedList;
import util.CtciSinglyLinkedListNode;

/**
 * Given a (singly) linked list of instances of a class that implements the
 * 'comparable' interface and another instance of said class as a pivot, re-order the
 * list such that two partitions are built. All elements in the left partition shall
 * be less than the pivot and all elements in the right partition are at least as
 * large as the pivot. The relative ordering does not need to be kept.
 * 
 * @author Dominik Sudwischer
 *
 */

public class Partition {

	/*
	 * Idea: This is trivial if we do it with O(n) space, where n = size of the
	 * linked list (just build a new list by scanning the given list and append each
	 * element smaller than pivot to the left and each element greater than or equal
	 * to the pivot to the right). Thus, we are doing this with O(1) space
	 * requirement. Whenever we find an element that is greater than or equal to the
	 * pivot, we remove it from that place and put it to the end of the list. Time
	 * complexity: O(n).
	 */
	public static <T extends Comparable<T>> void partition(
	        CtciSinglyLinkedList<T> linkedList, T pivot) {
		CtciSinglyLinkedListNode<T> currentNode = linkedList.getHeadNode();
		CtciSinglyLinkedListNode<T> prevNode = null;
		int n = linkedList.getSize();
		for(int i = 0; i < n && currentNode != null; i++) {
			if(currentNode.getValue().compareTo(pivot) >= 0) {
				linkedList.removeNode(currentNode, prevNode);
				linkedList.appendBack(currentNode.getValue());
			}
			else {
				prevNode = currentNode;
			}
			currentNode = currentNode.getNext();
		}
	}
	
	public static void main(String[] args) {
		CtciSinglyLinkedList<Integer> linkedList = new CtciSinglyLinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			linkedList.appendBack(i);
		}
		System.out.println("LinkedList: " + linkedList);
		partition(linkedList, 5);
		System.out.println("LinkedList: " + linkedList);
		linkedList = new CtciSinglyLinkedList<Integer>();
		for (int i = 9; i >= 0; i--) {
			linkedList.appendBack(i);
		}
		System.out.println("LinkedList: " + linkedList);
		partition(linkedList, 6);
		System.out.println("LinkedList: " + linkedList);
		linkedList = new CtciSinglyLinkedList<Integer>();
		linkedList.appendBack(0);
		linkedList.appendBack(0);
		linkedList.appendBack(5);
		linkedList.appendBack(2);
		linkedList.appendBack(5);
		linkedList.appendBack(2);
		System.out.println("LinkedList: " + linkedList);
		partition(linkedList, 3);
		System.out.println("LinkedList: " + linkedList);
	}
}
