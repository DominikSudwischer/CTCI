package chapter3;

import java.util.EmptyStackException;

/**
 * Implement a class that utilizes a single array to simulate three stacks.
 * 
 * @author Dominik Sudwischer
 *
 */

public class ThreeInOne {

	private int numStacks = 3;
	public int[] array; // or whatever type is needed
	public int[] stackStartIndex;
	public int[] stackSize;
	int currentSize;

	public ThreeInOne(int maxSize) {
		if (maxSize < numStacks) {
			throw new IllegalArgumentException();
		}
		stackStartIndex = new int[numStacks];
		for (int i = 0; i < numStacks; i++) {
			stackStartIndex[i] = maxSize / numStacks * i;
		}
		stackSize = new int[numStacks];
		array = new int[maxSize];
	}

	/*
	 * Compute the free space in each single stack as the difference between the last
	 * element in each stack and the neighbor stack's first element.
	 */
	private int[] computeFreeSpaces() {
		int[] freeSpaces = new int[numStacks];
		for (int i = 0; i < numStacks; i++) {
			freeSpaces[i] = ((stackStartIndex[(i + 1) % numStacks]
			        - stackStartIndex[i] - stackSize[i]) % array.length
			        + array.length) % array.length;
		}
		return freeSpaces;
	}

	/*
	 * Copy stack #stackNum to the right by numSteps units.
	 */
	private void copyOver(int stackNum, int numSteps) {
		if (numSteps < 0) {
			throw new IllegalArgumentException();
		}
		else if (numSteps == 0) {
			return;
		}
		int indexStartValue = stackStartIndex[stackNum] + stackSize[stackNum] - 1;
		int loopStopCondition = stackStartIndex[stackNum];
		print("copying over stack " + stackNum + " by " + numSteps + " steps.");
		print("before: ");
		print(array);
		for (int i = indexStartValue; i >= loopStopCondition; i--) {
			array[(i + numSteps) % array.length] = array[i % array.length];

		}
		stackStartIndex[stackNum] = stackStartIndex[stackNum] + numSteps;
		print("increasing stackStartIndex[" + stackNum + "] by" + numSteps);
		print("stackStartindex after copy " + stackStartIndex[stackNum]);
		print("after: ");
		print(array);
	}

	/*
	 * Add a new element to the specified stack
	 */
	public void push(int stackNum, int item) {
		if (currentSize >= array.length) {
			throw new StackOverflowError();
		}
		else {
			print("stackSize " + stackSize[stackNum]);
			print("stack start index "
			        + stackStartIndex[(stackNum + 1) % numStacks]);
			print("condition: " + (stackStartIndex[(stackNum + 1) % numStacks]
			        - stackStartIndex[stackNum] + array.length) % array.length);
			if (stackSize[stackNum] >= (stackStartIndex[(stackNum + 1) % numStacks]
			        - stackStartIndex[stackNum] + array.length) % array.length) {
				print("rearring");
				rearrangeRightNeighbors(computeFreeSpaces(), getNextStack(stackNum),
				        1);
			}
			array[stackStartIndex[stackNum] + stackSize[stackNum]] = item;
			stackSize[stackNum] = stackSize[stackNum] + 1;
			currentSize++;
		}
	}

	/*
	 * Return the ID of the right neighbor stack
	 */
	public int getNextStack(int stackNum) {
		return (stackNum + 1) % numStacks;
	}

	/*
	 * Recursively (oh well, this method implicitly uses a stack as well!) rearrange
	 * the neighbors to the right of the given neighbor so that it has enough space
	 * to its right to be shifted over.
	 */
	public void rearrangeRightNeighbors(int[] freeSpaces, int stackNum,
	        int numSteps) {
		if (freeSpaces[stackNum] < numSteps) {
			rearrangeRightNeighbors(freeSpaces, getNextStack(stackNum),
			        numSteps - freeSpaces[stackNum]);
		}
		copyOver(stackNum, numSteps);
	}

	/*
	 * Return the element on top of the selected stack
	 */
	public int peek(int stackNum) {
		if (stackSize[stackNum] == 0) {
			throw new EmptyStackException();
		}
		return array[stackStartIndex[stackNum] + stackSize[stackNum] - 1];
	}

	public static void print(Object o) {
		System.out.println(o);
	}

	public static void print(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]).append(", ");
		}
		sb.append("]");
		print(sb.toString());
	}

	public static void main(String[] args) {

		ThreeInOne tio = new ThreeInOne(10);
		tio.push(0, 1);
		tio.push(1, 10);
		tio.push(2, 20);
		tio.push(0, 2);
		tio.push(1, 11);
		tio.push(0, 3);
		tio.push(1, 12);
		print(tio.array);
		tio.push(0, 4);
		print(tio.array);
		print(tio.peek(0));
		print(tio.peek(1));
		print(tio.peek(2));
	}
}
