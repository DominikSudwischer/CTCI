package chapter3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * An animal shelter operates on a strict fifo system. There are dogs and cats.
 * Implement a fifo queue that has 4 methods: popDog, popCat, popAny and enqueue.
 * 
 * Idea: Have two queues (one for each type of animal). popDog and popCat are
 * straightforward. popAny looks at both queues and returns the older one.
 * 
 * @author Dominik Sudwischer
 *
 */
public class AnimalShelter {

	private enum AnimalType {
		DOG, CAT
	};

	private Map<AnimalType, Queue<Animal>> queues;
	// private int size;
	private long nextId;

	public AnimalShelter() {
		queues = new HashMap<AnimalType, Queue<Animal>>(AnimalType.values().length);
		for (AnimalType type : AnimalType.values()) {
			queues.put(type, new LinkedList<Animal>());
		}
		// size = 0;
	}

	/**
	 * Generates an id incoming animals
	 * @return The next free id.
	 */
	private long generateId() {
		return nextId++;
	}

	/**
	 * Enqueues a new animal instance in its specific queue.
	 * @param type The type of the animal to enqueue
	 * @param name The name of the animal to enqueue
	 */
	public void enqueue(AnimalType type, String name) {
		Animal animal = new Animal(type, generateId(), name);
		queues.get(animal.getType()).add(animal);
		// size++;
	}

	/**
	 * Returns the first dog in the queue.
	 * @return The first dog in the queue
	 * @throws NoSuchElementException
	 */
	public Animal popDog() throws NoSuchElementException {
		// size--;
		return queues.get(AnimalType.DOG).remove();
	}

	/*
	 * See documentation for popDog
	 */
	public Animal popCat() throws NoSuchElementException {
		// size--;
		return queues.get(AnimalType.CAT).remove();
	}

	/**
	 * Looks through all animal queues and decided which type of animal should be popped by popAny
	 * @return The type of animal to remove next by popAny.
	 */
	private AnimalType getTypeToPop() {
		AnimalType typeToPop = null;
		long currentLowestId = Long.MAX_VALUE;
		for (AnimalType type : AnimalType.values()) {
			Animal frontAnimal = queues.get(type).peek();
			if (frontAnimal != null && frontAnimal.getId() < currentLowestId) {
				typeToPop = type;
				currentLowestId = frontAnimal.getId();
			}
		}
		return typeToPop;
	}

	/**
	 * Pops the animal (of any type) that has the first position in the queue.
	 * @return The animal that has the minimum id. It has waited the longest.
	 * @throws NoSuchElementException
	 */
	public Animal popAny() throws NoSuchElementException {
		AnimalType typeToPop = getTypeToPop();
		switch (typeToPop) {
		case DOG: {
			return popDog();
		}
		case CAT: {
			return popCat();
		}
		default: {
			return null;
		}
		}
	}

	/**
	 * An animal class that stores the type of animal.
	 * 
	 * @author Dominik Sudwischer
	 *
	 */
	public static class Animal {
		private final AnimalType type;
		private final Long id;
		private final String name;
		// If there were some real animal classes involved, we could place a pointer
		// to that animal instance here.

		public Animal(AnimalType type, Long id, String name) {
			this.type = type;
			this.id = id;
			this.name = name;
		}

		public AnimalType getType() {
			return type;
		}

		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	public static void main(String[] args) {
		AnimalShelter shelter = new AnimalShelter();
		shelter.enqueue(AnimalType.DOG, "Dog1");
		shelter.enqueue(AnimalType.DOG, "Dog2");
		shelter.enqueue(AnimalType.CAT, "Cat1");
		shelter.enqueue(AnimalType.CAT, "Cat2");
		shelter.enqueue(AnimalType.CAT, "Cat3");
		shelter.enqueue(AnimalType.DOG, "Dog2");
		// Sequence: D1, D2, C1, C2, C3, D3
		System.out.println(shelter.popAny().getName()); // Should be Dog1
		System.out.println(shelter.popCat().getName()); // Should be Cat1
		System.out.println(shelter.popAny().getName()); // Should be Dog2
		System.out.println(shelter.popDog().getName()); // Should be Dog3
		System.out.println(shelter.popAny().getName()); // Should be Cat2
		shelter.enqueue(AnimalType.DOG, "Dog4");
		System.out.println(shelter.popAny().getName()); // Should be Cat3
		System.out.println(shelter.popDog().getName()); // Should be Dog4
	}
}
