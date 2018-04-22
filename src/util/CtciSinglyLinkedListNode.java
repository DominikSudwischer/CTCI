package util;

public class CtciSinglyLinkedListNode<T> {

	private CtciSinglyLinkedListNode<T> next;
	private T value;

	public CtciSinglyLinkedListNode(CtciSinglyLinkedListNode<T> next, T value) {
		this.next = next;
		this.value = value;
	}

	public CtciSinglyLinkedListNode(T value) {
		next = null;
		this.value = value;
	}

	public CtciSinglyLinkedListNode<T> getNext() {
		return next;
	}

	public T getValue() {
		return value;
	}

	public void setNext(CtciSinglyLinkedListNode<T> newNext) {
		next = newNext;
	}

	public void setValue(T newValue) {
		value = newValue;
	}

	public static <T> CtciSinglyLinkedListNode<T> advanceBy(
	        CtciSinglyLinkedListNode<T> currentNode, int numSteps) {
		CtciSinglyLinkedListNode<T> advancedNode = currentNode;
		for (int i = 0; i < numSteps; i++) {
			try {
				advancedNode = advancedNode.getNext();
			}
			catch (NullPointerException e) {
				return null;
			}
		}
		return advancedNode;
	}
}
