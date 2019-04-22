package chapter3;

import util.CtCiStack;

/**
 * Write a class that sorts a stack such that the smallest elements are on top. You
 * may use a temporary stack for storage.
 * 
 * For simplicity, we will use integers, but it can be extended to all objects that
 * implement the Comparable interface.
 * 
 * Idea: Use a temporary stack that stays sorted (with largest elements at the top)
 * at all times. Pop the top element from the main stack and insert into into the
 * right position of the temporary stack. To do so, pop all elements that are larger
 * than the current element and put them onto the main stack. Then put the current
 * element onto the temporary stack and put all elements that have been moved from
 * the temporary stack to the main stack before back to the temporary stack. Repeat
 * this until the main stack is empty. Afterwards, pop all elements from the
 * temporary stack and push them to the main stack.
 * 
 * @author Dominik Sudwischer
 *
 */
public class SortStack {

	/**
	 * Performs sorting on a stack such that the smallest elements are on top.
	 * @param mainStack The stack to sort.
	 */
	public static void sortStack(CtCiStack<Integer> mainStack) {
		CtCiStack<Integer> tempStack = new CtCiStack<Integer>();
		while (!mainStack.isEmpty()) {
			int currentItem = mainStack.pop();
			sortInto(tempStack, mainStack, currentItem);
		}
		while(!tempStack.isEmpty()) {
			shiftOnce(tempStack, mainStack);
		}
	}

	/**
	 * Shifts over all elements that are larger than a given threshold from one stack
	 * to another and returns the number of shifts done.
	 * 
	 * @param from
	 *            The stack to remove from.
	 * @param to
	 *            The stack to push onto.
	 * @param threshold
	 * @return
	 */
	private static int shiftLarger(CtCiStack<Integer> from, CtCiStack<Integer> to,
	        int threshold) {
		int numPops = 0;
		while (!from.isEmpty()) {
			if (from.peek() > threshold) {
				shiftOnce(from, to);
				numPops++;
			}
			else {
				return numPops;
			}
		}
		return numPops;
	}

	/**
	 * Pops some elements from one stack and pushes them to another stack.
	 * 
	 * @param from
	 *            The stack to remove from
	 * @param to
	 *            The stack to push onto
	 * @param numPops
	 *            The number of elements to move
	 * @return true if all numPops shifts could be done, else false.
	 */
	private static boolean shiftMultipleTimes(CtCiStack<Integer> from,
	        CtCiStack<Integer> to, int numPops) {
		for (int i = 0; i < numPops; i++) {
			boolean shiftSuccess = shiftOnce(from, to);
			if (!shiftSuccess) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Pops one element from one stack and pushes it onto another stack
	 * 
	 * @param from
	 *            The stack to remove from
	 * @param to
	 *            The stack to push onto
	 * @return true if the element could be moved, else false.
	 */
	private static boolean shiftOnce(CtCiStack<Integer> from,
	        CtCiStack<Integer> to) {
		if (!from.isEmpty()) {
			to.push(from.pop());
			return true;
		}
		return false;
	}

	/**
	 * Inserts a value into a sorted (largest at top) stack.
	 * 
	 * @param sortedStack
	 *            The sorted stack
	 * @param storageStack
	 *            An additional stack used as storage
	 * @param value
	 *            The value to sort into the sorted stack
	 */
	private static void sortInto(CtCiStack<Integer> sortedStack,
	        CtCiStack<Integer> storageStack, int value) {
		int numShifts = shiftLarger(sortedStack, storageStack, value);
		sortedStack.push(value);
		shiftMultipleTimes(storageStack, sortedStack, numShifts);
	}

	public static void main(String[] args) {
		CtCiStack<Integer> stack = new CtCiStack<Integer>();
		stack.push(3);
		stack.push(5);
		stack.push(2);
		stack.push(1);
		stack.push(3);
		// This should be 5, 3, 3, 2, 1 after sorting (from bottom to top)
		sortStack(stack);
		// Popping from the top, so this should print the content in increasing order
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
}

