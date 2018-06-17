package chapter3;

import java.util.EmptyStackException;

/**
 * Implement a stack that has three operations: push, pop and min (which returns the
 * minimum element). All three operations should operate in O(1) time.
 * 
 * Note: For simplicity, we will implement this only for integers. It can, however,
 * easily be extended to class that implements the Comparable interface.
 * 
 * @author Dominik Sudwischer
 *
 */

public class StackMin {

	/*
	 * This class represents a stack node. It will not only store a pointer to the
	 * previous element in the stack but also the the smallest of the previous
	 * elements.
	 */
	private class StackMinNode {

		private int value;
		private StackMinNode prev;
		private StackMinNode smallestPrev;

		public StackMinNode(int value, StackMinNode prev,
		        StackMinNode smallestPrev) {
			this.value = value;
			this.prev = prev;
			this.smallestPrev = smallestPrev;
		}

		public int getValue() {
			return value;
		}
	}

	private StackMinNode top;
	private int size;

	/**
	 * Returns the size of the stack
	 * 
	 * @return The size of the stack
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Pops the top element.
	 * 
	 * @return The value that is contained in the top node.
	 * @throws EmptyStackException
	 *             if the stack is empty.
	 */
	public int pop() throws EmptyStackException {
		if (size <= 0) {
			throw new EmptyStackException();
		}
		else {
			size--;
			StackMinNode prevTop = top;
			top = top.prev;
			return prevTop.value;
		}
	}

	/**
	 * Returns the node with minimum value
	 * 
	 * @return The node with minimum value
	 */
	private StackMinNode getMinNode() {
		if (size <= 0) {
			return null;
		}
		else if (size == 1) {
			return top;
		}
		else {
			StackMinNode smallestPrev;
			if (top.smallestPrev.getValue() < top.getValue()) {
				smallestPrev = top.smallestPrev;
			}
			else {
				smallestPrev = top;
			}
			return smallestPrev;
		}
	}

	/**
	 * Adds a new entry to the stack
	 * 
	 * @param value
	 *            The value to add
	 */
	public void push(int value) {
		StackMinNode newNode = new StackMinNode(value, top, getMinNode());
		size++;
		top = newNode;
	}

	/**
	 * Returns the minimum value
	 * 
	 * @return The value of the minimum node
	 */
	public Integer min() {
		if (getMinNode() == null) {
			return null;
		}
		else {
			return getMinNode().getValue();
		}
	}

	/**
	 * Constructs an empty stack
	 */
	public StackMin() {
		size = 0;
		top = null;
	}

	public void printStackInfo() {
		System.out.println("MinValue: " + min());
	}

	public static void main(String[] args) {
		StackMin stack = new StackMin();
		stack.push(-1);
		stack.printStackInfo();
		stack.push(-5);
		stack.printStackInfo();
		stack.push(-2);
		stack.printStackInfo();
		stack.push(-4);
		stack.printStackInfo();
		stack.push(-7);
		stack.printStackInfo();
		stack.push(-9);
		stack.printStackInfo();
		stack.push(-2);
		stack.printStackInfo();
		stack.push(-4);
		stack.printStackInfo();
		stack.pop();
		stack.printStackInfo();
		stack.pop();
		stack.printStackInfo();
		stack.pop();
		stack.printStackInfo();
		stack.pop();
		stack.printStackInfo();
		stack.pop();
		stack.printStackInfo();
		stack.pop();
		stack.printStackInfo();
		stack.pop();
		stack.printStackInfo();
		stack.pop();
		stack.printStackInfo();
	}

}
