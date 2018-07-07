package chapter3;

import java.util.EmptyStackException;

import util.CtCiStack;

/**
 * Implement a (fifo) Queue using only two Stacks with the operations isEmpy(),
 * pop(), peek() and push()
 * 
 * For simplicity, we will use integer values.
 * 
 * Idea: Assume some number of elements n has been added to one stack. To release the
 * first element in the stack, shift over all elements to the other stack by popping
 * and pushing. That will change the order of the elements. Popping from the target
 * stack will then release the first element in the queue. Any number of pop commands
 * can be executed then. When the next push command is executed, simply shift
 * everything back to the first stack.
 * 
 * Time complexity: If n is the number of elements in the queue, then the worst case
 * runtime for enqueue, popFirst and peek is O(n) while isEmpty is always O(1). If
 * the queue is already in the correct "mode" for enqueueing or popping, subsequent
 * operations will be O(1).
 * 
 * @author Dominik Sudwischer
 *
 */

public class QueueViaStack {

	private CtCiStack<Integer> popStack;
	private CtCiStack<Integer> pushStack;
	private boolean popMode;

	public QueueViaStack() {
		popStack = new CtCiStack<Integer>();
		pushStack = new CtCiStack<Integer>();
		popMode = false;
	}

	/**
	 * Shifts all elements from one stack to another
	 * 
	 * @param from
	 *            The stack to take elements from
	 * @param to
	 *            The target stack
	 */
	private void shiftOver(CtCiStack<Integer> from, CtCiStack<Integer> to) {
		while (!from.isEmpty()) {
			to.push(from.pop());
		}
	}

	/**
	 * Activates push mode by switching all elements to the push stack.
	 */
	private void activatePushMode() {
		if (popMode) {
			shiftOver(popStack, pushStack);
			popMode = false;
		}
	}

	/**
	 * Activate pop mode by switching all elements to the pop stack.
	 */
	private void activatePopMode() {
		if (!popMode) {
			shiftOver(pushStack, popStack);
			popMode = true;
		}
	}

	/**
	 * Checks if the stack is empty.
	 * 
	 * @return true if the stack is empty, else false.
	 */
	public boolean isEmpty() {
		return popStack.isEmpty() && pushStack.isEmpty();
	}

	/**
	 * Enqueues a value in the queue.
	 * 
	 * @param v
	 *            The value to enqueue.
	 */
	public void enqueue(int v) {
		activatePushMode();
		pushStack.push(v);
	}

	/**
	 * Looks at the first element in the queue (i.e. the next element to be extracted
	 * from the queue).
	 * 
	 * @return The first element in the queue.
	 * @throws EmptyStackException
	 */
	public int peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		activatePopMode();
		return popStack.peek();
	}

	/**
	 * Pops the first element of the queue and returns it.
	 * 
	 * @return The first element in the queue.
	 * @throws EmptyStackException
	 */
	public int popFirst() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		activatePopMode();
		return popStack.pop();
	}

	public static void main(String[] args) {
		QueueViaStack q = new QueueViaStack();
		q.enqueue(1);
		System.out.println(q.peek());
		q.enqueue(2);
		q.enqueue(3);
		System.out.println(q.popFirst());
		System.out.println(q.popFirst());
		q.enqueue(4);
		q.enqueue(5);
		while (!q.isEmpty()) {
			System.out.println(q.popFirst());
		}
		q.enqueue(6);
		System.out.println(q.peek());
	}
}
