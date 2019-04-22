package chapter2;

import util.CtciSinglyLinkedList;
import util.CtciSinglyLinkedListNode;

/**
 * Given two linked lists that represent a non-negative integer in reverse order
 * where each node is a single digit (e.g. 1->2->3 = 321), add them up and return a
 * linked list that corresponds to the sum (in reverse order).
 * 
 * @author Dominik Sudwischer
 *
 */

public class SumLists {

	/*
	 * Idea: To get the number from the linked list, we can just do some modulo
	 * operations and divisions. For example, (617 mod 10) / 1 = first digit for the
	 * list, (617 mod 100) / 10 = second digit for the list and so on.
	 */
	public static int getNumber(CtciSinglyLinkedList<Integer> linkedList) {
		int result = 0;
		int currentPower = 1;
		CtciSinglyLinkedListNode<Integer> currentNode = linkedList.getHeadNode();
		for (@SuppressWarnings("unused")
		int i = 0; currentNode != null; i++) {
			result += currentNode.getValue() * currentPower;
			currentPower *= 10;
			currentNode = currentNode.getNext();
		}
		return result;
	}

	public static CtciSinglyLinkedList<Integer> buildListFromNumber(int number) {
		CtciSinglyLinkedList<Integer> linkedList = new CtciSinglyLinkedList<Integer>();
		int mod = 10;
		int divisor = 1;
		while (divisor <= number) {
			int digit = (number % mod) / divisor;
			linkedList.appendBack(digit);
			divisor = mod;
			mod *= 10;
		}
		return linkedList;
	}
	
	/*
	 * Now suppose the digits are stored in forward order, i.e. 6->1->7 = 617. Repeat the above problem.
	 */
	public static int getNumberFollowUp(CtciSinglyLinkedList<Integer> linkedList) {
		CtciSinglyLinkedListNode<Integer> currentNode = linkedList.getHeadNode();
		int result = 0;
		while(currentNode != null) {
			result = result * 10 + currentNode.getValue();
			currentNode = currentNode.getNext();
		}
		return result;
	}
	
	public static CtciSinglyLinkedList<Integer> buildListFromNumberFollowUp(int number) {
		CtciSinglyLinkedList<Integer> linkedList = new CtciSinglyLinkedList<Integer>();
		int mod = 10;
		int divisor = 1;
		while (divisor <= number) {
			int digit = (number % mod) / divisor;
			linkedList.appendFront(digit);
			divisor = mod;
			mod *= 10;
		}
		return linkedList;
	}

	public static void main(String[] args) {
		CtciSinglyLinkedList<Integer> linkedList = new CtciSinglyLinkedList<Integer>();
		linkedList.appendBack(7);
		linkedList.appendBack(1);
		linkedList.appendBack(6);
		CtciSinglyLinkedList<Integer> linkedList2 = new CtciSinglyLinkedList<Integer>();
		linkedList2.appendBack(5);
		linkedList2.appendBack(9);
		linkedList2.appendBack(2);
		System.out.println(getNumber(linkedList));
		System.out.println(
		        buildListFromNumber(getNumber(linkedList) + getNumber(linkedList2)));
		System.out.println(getNumberFollowUp(linkedList));
		System.out.println(
		        buildListFromNumberFollowUp(getNumberFollowUp(linkedList) + getNumberFollowUp(linkedList2)));
	}
}
