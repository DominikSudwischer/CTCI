package chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Implement a set of stacks with a fixed maximum size each. When one stack is full,
 * another one should be created. The operations "pop" and "push" should act just as
 * if it was a single stack.
 * 
 * Also implement a method "popAtIndex(int index)" that removes the i-th element in
 * the total stack.
 * 
 * For simplicity, I will implement this for integers. However, using generic types
 * is easily doable.
 * 
 * @author Dominik Sudwischer
 *
 */

public class StackOfPlates {

	private final int MAX_SIZE;
	private int size;
	private List<Stack<Integer>> stacks;

	public StackOfPlates(int maxSize) {
		MAX_SIZE = maxSize;
		stacks = new ArrayList<Stack<Integer>>();
	}

	/**
	 * Adds an element to the back of the stack. If the previous stack is full, a new
	 * one will be created
	 * 
	 * @param The
	 *            value to add.
	 */
	public void push(int value) {
		if (stacks.isEmpty()) {
			stacks.add(new Stack<Integer>());
		}
		else if (stacks.get(stacks.size() - 1).size() >= MAX_SIZE) {
			stacks.add(new Stack<Integer>());
		}
		stacks.get(stacks.size() - 1).push(value);
		size++;
	}

	/**
	 * Pops the top value in the stack, i.e. the top value in the last stack.
	 * 
	 * @return The popped value.
	 */
	public int pop() {
		int value = stacks.get(stacks.size() - 1).pop();
		size--;
		if (stacks.get(stacks.size() - 1).isEmpty()) {
			// If the last stack is empty, remove it
			stacks.remove(stacks.size() - 1);
		}
		return value;
	}

	public int size() {
		return size;
	}

	/**
	 * This method pops the element at the specified index. It finds the stack that
	 * contains the element with the specified element and pops it from that stack.
	 * If that stack is empty afterwards, it will be removed.
	 * 
	 * @param index The index of the element to pop.
	 * @return The popped value.
	 */
	public int popAtIndex(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Stack<Integer> stackWithElementAtIndex = null;
		int numValuesSeen = 0;
		int i = 0;
		for (Stack<Integer> stack : stacks) {
			numValuesSeen += stack.size();
			if (numValuesSeen > index) {
				stackWithElementAtIndex = stack;
				break;
			}
			i++;
		}
		int value = stackWithElementAtIndex
		        .remove(index - numValuesSeen + stackWithElementAtIndex.size());
		size--;
		if (stackWithElementAtIndex.isEmpty()) {
			stacks.remove(i);
		}
		return value;
	}

	/**
	 * A helper method to visualize the current stack(s).
	 */
	public void print() {
		StringBuilder sb = new StringBuilder();
		for (Stack<Integer> stack : stacks) {
			sb.append("Stack: ").append(stack.toString()).append("\t");
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		StackOfPlates sop = new StackOfPlates(4);
		sop.push(1);
		sop.print();
		sop.push(1);
		sop.print();
		sop.push(1);
		sop.print();
		sop.push(1);
		sop.print();
		sop.push(1);
		sop.print();
		sop.pop();
		sop.print();
		sop.pop();
		sop.print();
		sop.push(1);
		sop.print();
		sop.push(1);
		sop.print();

		sop = new StackOfPlates(2);
		sop.push(1);
		sop.push(2);
		sop.push(3);
		sop.push(4);
		sop.print();
		sop.popAtIndex(2);
		sop.print();
		sop.popAtIndex(2);
		sop.print();
		sop.pop();
		sop.print();
	}
}
