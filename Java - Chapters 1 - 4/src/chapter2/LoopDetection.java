package chapter2;

import util.CtciSinglyLinkedListNode;

/**
 * Given a singly linked list, find out whether it has a circle. If so, find out
 * where it starts. Note: Since this is a corrupted linked list, we will not use our
 * previously created class.
 * 
 * @author Dominik Sudwischer
 */
public class LoopDetection {

	/*
	 * Idea: Let us assume there is a loop that consists of L nodes and it starts
	 * after K "normal" nodes. Then use a pointer that moves twice as fast as the
	 * next-pointer. After K steps, when the next-pointer enters the loop, the fast
	 * pointer will have moved 2K steps. Since the loop has size L, it will be K%L
	 * steps into the loop. That means the next-pointer is L - K%L steps ahead of the
	 * fast pointer. Since the fast pointer catches up by one step per move, the
	 * pointers will collide L - K%L steps into the loop. If you then go K steps from
	 * that collision point, you will be (L - K%L + K)%L = 0 steps into the loop,
	 * i.e. you are at the starting node. Thus, we will do two steps:First, compute
	 * the collision point. Second, start a pointer from that point and a pointer
	 * from the head at the same speed. When they collide, it will be the beginning
	 * node of the loop. Time complexity: O(L+K).
	 */
	public static <T> int detectLoop(CtciSinglyLinkedListNode<T> head) {
		CtciSinglyLinkedListNode<T> slowPointer = head;
		CtciSinglyLinkedListNode<T> fastPointer = head;
		while(true) {
			try {
				fastPointer = fastPointer.getNext().getNext();
				slowPointer = slowPointer.getNext();
			}
			catch(NullPointerException e) {
				return -1; // In this case, we reached the end an there is no loop.
			}
			if(fastPointer == slowPointer) {// Equality by reference
				// This is the collision point.
				CtciSinglyLinkedListNode<T> pathPointer = head;
				int loopStart = 0;
				while(pathPointer != slowPointer) {
					pathPointer = pathPointer.getNext();
					slowPointer = slowPointer.getNext();
					loopStart++;
				}
				return loopStart;
			}
		}
	}
	
	/*
	 * A helper method to construct a general test case. Use L = 0 if no loop is present.
	 */
	public static CtciSinglyLinkedListNode<Integer> buildLoopedList(int K, int L) {
		CtciSinglyLinkedListNode<Integer> prevNode = new CtciSinglyLinkedListNode<Integer>(0);
		CtciSinglyLinkedListNode<Integer> head = prevNode;
		CtciSinglyLinkedListNode<Integer> loopStartNode = null;
		for(int i = 1; i < K + L; i++) {
			CtciSinglyLinkedListNode<Integer> nextNode = new CtciSinglyLinkedListNode<Integer>(i);
			prevNode.setNext(nextNode);
			prevNode = nextNode;
			if(i == K) {
				loopStartNode = nextNode;
			}
			if(i == K + L - 1) {
				nextNode.setNext(loopStartNode);
			}
		}
		return head;
	}
	
	public static void main(String[] args) {
		int K = 5;
		int L = 0;
		System.out.println(detectLoop(buildLoopedList(K, L)));
		K = 8;
		L = 20;
		System.out.println(detectLoop(buildLoopedList(K, L)));
		K = 20;
		L = 8;
		System.out.println(detectLoop(buildLoopedList(K, L)));
	}
}
