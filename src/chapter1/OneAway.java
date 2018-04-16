package chapter1;

/**
 * Given two strings, check whether they are at most one insertion, deletion or
 * replacement away from each other.
 * 
 * @author Dominik Sudwischer
 * 
 */

public class OneAway {

	/*
	 * We start with two observations: 1) It is equivalent to get from string1 to
	 * string2 with one insertion and to get from string2 to string1 with one
	 * deletion. 2) If both strings have the same size, we can only transform one to
	 * the other by replacement because the other functions will change the size of
	 * one string. Time complexity in non-trivial case: O(n) where n = maximum length
	 * of string1 and string2.
	 */
	public static boolean isOneAway(String string1, String string2) {
		// Without loss of generality, we will assume that string1 is at most as long
		// as string2
		if (string1.length() > string2.length()) {
			return isOneAway(string2, string1);
		}
		// If the strings differ in size by more than one, one operation is not
		// sufficient.
		if (string2.length() - string1.length() > 1) {
			return false;
		}
		char[] arr1 = string1.toCharArray();
		char[] arr2 = string2.toCharArray();
		return isOneAway(arr1, arr2);
	}

	private static boolean isOneAway(char[] arr1, char[] arr2) {
		// It is assumed that arr1 has at most as many elements as arr2 and that arr1
		// is no more than one element less in size than arr2.
		if (arr1.length < arr2.length) {
			return isOneInsertionAway(arr1, arr2);
		}
		else {
			return isOneReplacementAway(arr1, arr2);
		}
	}

	private static boolean isOneReplacementAway(char[] arr1, char[] arr2) {
		// arr1 and arr2 are assumed to have the same number of characters
		short mismatches = 0;
		for (int i = 0; i < arr1.length; i++) {
			// We just have to count the number of mismatches. It must not exceed 1.
			if (arr1[i] != arr2[i]) {
				mismatches++;
				if (mismatches > 1) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isOneInsertionAway(char[] shortArr, char[] longArr) {
		// We assume shortArr to have exactly one character less than longArr
		short insertionUsed = 0;
		for (int i = 0; i < shortArr.length; i++) {
			// If we find a mismatch, we will use an insertion at that position. No
			// more mismatches are allowed after that.
			if (shortArr[i] != longArr[i + insertionUsed]) {
				insertionUsed++;
				if (insertionUsed > 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isOneAway("pale", "ple"));
		System.out.println(isOneAway("pale", "pales"));
		System.out.println(isOneAway("pale", "bale"));
		System.out.println(isOneAway("pale", "bake"));
	}
}
