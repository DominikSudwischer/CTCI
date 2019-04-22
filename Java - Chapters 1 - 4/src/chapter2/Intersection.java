package chapter2;

import util.CtciSinglyLinkedList;
import util.CtciSinglyLinkedListNode;

/**
 * Given two (singly) linked lists, determine if they intersect, that is, there are
 * two non-negative integers i and j such that the i-th node of the first list equals
 * (by reference) the j-th node of the second list. Time complexity: O(n) where n is
 * the length of the longer list.
 * 
 * @author Dominik Sudwischer
 *
 */

public class Intersection {

	/*
	 * Idea: If the two linked lists have an intersection, their tails will be
	 * identical. Thus, to check whether two linked lists have an intersection, one
	 * only has to compare their tails by reference. If the tails are the same node,
	 * one traverses the list until one finds the first common node. In the case of a
	 * doubly linked list, one would start from the tail. In the case of a singly
	 * linked list, one starts in the beginning. If the lists have different length,
	 * the longer list has to skip a few nodes so that the sub-list of the longer
	 * list beginning at that node has the same length as the shorter of the two
	 * lists.
	 */
	public static <T> CtciSinglyLinkedListNode<T> findIntersection(
	        CtciSinglyLinkedList<T> linkedList1,
	        CtciSinglyLinkedList<T> linkedList2) {
		if (linkedList1.getTailNode() != linkedList2.getTailNode())
			// If there is no pointer to the tail node available, we would instead
			// traverse the lists.
			// This would also yield the individual sizes of the lists.
			return null;
		else {
			if (linkedList1.getSize() < linkedList2.getSize()) {
				// We will assume that the first list is as least as long as the
				// second list.
				return findIntersection(linkedList2, linkedList1);
			}
			int offset = linkedList1.getSize() - linkedList2.getSize();
			CtciSinglyLinkedListNode<T> currentNode1 = linkedList1.getHeadNode();
			CtciSinglyLinkedListNode<T> currentNode2 = linkedList2.getHeadNode();
			currentNode1 = CtciSinglyLinkedListNode.advanceBy(currentNode1, offset);
			while (currentNode1 != currentNode2) {
				currentNode1 = currentNode1.getNext();
				currentNode2 = currentNode2.getNext();
			}
			return currentNode1;
		}
	}

	public static void main(String[] args) {
		CtciSinglyLinkedListNode<Integer> node0 = new CtciSinglyLinkedListNode<Integer>(
		        0);
		CtciSinglyLinkedListNode<Integer> node1 = new CtciSinglyLinkedListNode<Integer>(
		        1);
		CtciSinglyLinkedListNode<Integer> node2 = new CtciSinglyLinkedListNode<Integer>(
		        2);
		CtciSinglyLinkedListNode<Integer> node3 = new CtciSinglyLinkedListNode<Integer>(
		        3);
		CtciSinglyLinkedListNode<Integer> node4 = new CtciSinglyLinkedListNode<Integer>(
		        4);
		CtciSinglyLinkedListNode<Integer> node5 = new CtciSinglyLinkedListNode<Integer>(
		        5);
		node0.setNext(node1);
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(null);
		node5.setNext(node2);
		CtciSinglyLinkedList<Integer> linkedList1 = new CtciSinglyLinkedList<Integer>(
		        node0, node4, 5);
		CtciSinglyLinkedList<Integer> linkedList2 = new CtciSinglyLinkedList<Integer>(
		        node5, node4, 4);
		CtciSinglyLinkedListNode<Integer> intersectionNode = findIntersection(
		        linkedList1, linkedList2);
		if (intersectionNode == null) {
			System.out.println("No intersection found");
		}
		else {
			System.out.println("Intersection found at node " + intersectionNode
			        + " with value " + intersectionNode.getValue());
		}
		CtciSinglyLinkedListNode<Integer> node6 = new CtciSinglyLinkedListNode<Integer>(
		        6);
		CtciSinglyLinkedListNode<Integer> node7 = new CtciSinglyLinkedListNode<Integer>(
		        7);
		CtciSinglyLinkedListNode<Integer> node8 = new CtciSinglyLinkedListNode<Integer>(
		        8);
		CtciSinglyLinkedListNode<Integer> node9 = new CtciSinglyLinkedListNode<Integer>(
		        9);
		CtciSinglyLinkedList<Integer> linkedList3 = new CtciSinglyLinkedList<Integer>(
		        node6, node7, 2);
		CtciSinglyLinkedList<Integer> linkedList4 = new CtciSinglyLinkedList<Integer>(
		        node8, node9, 2);
		CtciSinglyLinkedListNode<Integer> intersectionNode2 = findIntersection(
		        linkedList3, linkedList4);
		if (intersectionNode2 == null) {
			System.out.println("No intersection found");
		}
		else {
			System.out.println("Intersection found at node " + intersectionNode2
			        + " with value " + intersectionNode2.getValue());
		}
	}
}
