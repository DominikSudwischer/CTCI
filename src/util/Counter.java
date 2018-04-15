package util;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple class to count elements of an array with a HashMap.
 * 
 * @author Dominik Sudwischer
 *
 */
public class Counter<T> {

	private Map<T, Integer> counter;

	public Counter() {
		counter = new HashMap<T, Integer>();
	}

	public void count(T[] arr) {
		for (T element : arr) {
			if (counter.containsKey(element)) {
				int count = counter.get(element);
				counter.put(element, count + 1);
			}
			else {
				counter.put(element, 1);
			}
		}
	}

	public int size() {
		return counter.size();
	}

	public int getNumAppearances(T key) {
		if (counter.containsKey(key)) {
			return counter.get(key);
		}
		else {
			return 0;
		}
	}

	public boolean equals(Counter<T> other) {
		if (size() != other.size()) {
			return false;
		}
		for (T element : counter.keySet()) {
			if (other.getNumAppearances(element) != getNumAppearances(element)) {
				return false;
			}
		}
		return true;
	}

	// For proper usage, a custom hashCode function is recommended.
}
