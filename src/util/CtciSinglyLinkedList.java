package util;

public class CtciSinglyLinkedList<T> {

	private CtciSinglyLinkedListNode<T> head;
	private CtciSinglyLinkedListNode<T> tail;
	private int size;

	public CtciSinglyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/*
	 * For testing purposes
	 */
	public CtciSinglyLinkedList(CtciSinglyLinkedListNode<T> head, CtciSinglyLinkedListNode<T> tail, int size) {
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public CtciSinglyLinkedListNode<T> getHeadNode() {
		return head;
	}

	public CtciSinglyLinkedListNode<T> getTailNode() {
		return tail;
	}

	public CtciSinglyLinkedListNode<T> getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		CtciSinglyLinkedListNode<T> currentNode = head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	public T getValue(int index) {
		return getNode(index).getValue();
	}

	public void appendBack(T value) {
		CtciSinglyLinkedListNode<T> newNode = new CtciSinglyLinkedListNode<T>(value);
		if (getSize() == 0) {
			head = newNode;
			tail = newNode;
		}
		else {
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
	}

	public void appendFront(T value) {
		if (getSize() == 0) {
			appendBack(value);
			return;
		}
		CtciSinglyLinkedListNode<T> newNode = new CtciSinglyLinkedListNode<T>(value);
		newNode.setNext(head);
		head = newNode;
		size++;
	}

	public void insert(int index, T value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		else if (index == size) {
			appendBack(value);
		}
		else if (index == 0) {
			appendFront(value);
		}
		else {
			CtciSinglyLinkedListNode<T> prevNode = getNode(index - 1);
			CtciSinglyLinkedListNode<T> newNode = new CtciSinglyLinkedListNode<T>(
			        prevNode.getNext(), value);
			prevNode.setNext(newNode);
			size++;
		}
	}

	/*
	 * newTailNode has to be the former predecessor of the tail node.
	 */
	public void removeTailNode(CtciSinglyLinkedListNode<T> newTailNode) {
		if(newTailNode == null) { // In this case, the list only had at most one element
			size = 0;
			head = null;
			tail = null;
		}
		else {
			tail = newTailNode;
			newTailNode.setNext(null);
			size--;
		}
	}
	
	public void removeHeadNode() {
		if(getSize() <= 1) { // In this case, the list only had at most one element
			removeTailNode(null);
		}
		else { // In this case, the list has more than one element
			head = head.getNext();
			size--;
		}
	}

	/*
	 * prevNode is the predecessor of the node to remove.
	 */
	public void removeNode(CtciSinglyLinkedListNode<T> nodeToRemove,
	        CtciSinglyLinkedListNode<T> prevNode) {
		if(prevNode == null) { // In this case, we remove the head
			removeHeadNode();
		}
		else if(nodeToRemove == tail)
		{
			removeTailNode(prevNode);
		}
		else {
		prevNode.setNext(nodeToRemove.getNext());
		size--;
		}
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		CtciSinglyLinkedListNode<T> currentNode = getNode(0);
		while (currentNode.getNext() != null) {
			stringBuilder.append(currentNode.getValue()).append(" ");
			currentNode = currentNode.getNext();
		}
		stringBuilder.append(currentNode.getValue());
		return stringBuilder.toString();
	}
}
