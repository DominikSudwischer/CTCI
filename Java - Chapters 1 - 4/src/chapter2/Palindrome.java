package chapter2;

import java.util.Stack;

import util.CtciSinglyLinkedList;
import util.CtciSinglyLinkedListNode;

/**
 * Given a (singly) linkedList, find out whether it is a palindrome.
 * 
 * @author Dominik Sudwischer
 *
 */
public class Palindrome {

	/*
	 * Idea: Push the first half of the list onto a stack, then compare the remaining
	 * part of the list to the values in the stack. The stack represents the first
	 * half of the list and by comparing the popped values from the stack to the
	 * second half of the linked list in forward order, we effectively iterate from
	 * the inside out. Time complexity: O(n) where n is the size of the list.
	 */
	public static <T> boolean isPalindrome(CtciSinglyLinkedList<T> linkedList) {
		Stack<T> stack = new Stack<T>();
		int middleNodeIndex = linkedList.getSize() / 2 - 1; // We only check up to
		                                                    // here
		CtciSinglyLinkedListNode<T> currentNode = linkedList.getHeadNode();
		for (int i = 0; i <= middleNodeIndex; i++) {
			stack.add(currentNode.getValue());
			currentNode = currentNode.getNext();
		}
		if (linkedList.getSize() % 2 == 1) { // We have to advance by one if the list
		                                     // has an uneven number of elements in
		                                     // it
			currentNode = currentNode.getNext();
		}
		while (currentNode != null) {
			if (!currentNode.getValue().equals(stack.pop())) {
				return false;
			}
			currentNode = currentNode.getNext();
		}
		return true;
	}

	public static void main(String[] args) {
		CtciSinglyLinkedList<Integer> linkedList = new CtciSinglyLinkedList<Integer>();
		linkedList.appendBack(3);
		linkedList.appendBack(2);
		linkedList.appendBack(1);
		linkedList.appendBack(0);
		linkedList.appendBack(1);
		linkedList.appendBack(2);
		linkedList.appendBack(3);
		System.out.println(isPalindrome(linkedList));

		linkedList = new CtciSinglyLinkedList<Integer>();
		linkedList.appendBack(3);
		linkedList.appendBack(2);
		linkedList.appendBack(1);
		linkedList.appendBack(1);
		linkedList.appendBack(2);
		linkedList.appendBack(3);
		System.out.println(isPalindrome(linkedList));

		linkedList = new CtciSinglyLinkedList<Integer>();
		linkedList.appendBack(1);
		linkedList.appendBack(0);
		linkedList.appendBack(2);
		System.out.println(isPalindrome(linkedList));
	}
}
