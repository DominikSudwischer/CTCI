package chapter1;

import util.Counter;
import util.StringUtil;

/**
 * Given two strings, check if one string is a permutation of the other one.
 * 
 * @author Dominik Sudwischer
 *
 */
public class CheckPermutation {

	/*
	 * Two strings are permutations of each other if and only if both contain exactly
	 * the same number of individual characters. Time complexity in the non-trivial
	 * case: O(n) where n = string1.length = string2.length. (If string1 and string2
	 * are not the same size, the runtime is constant).
	 */
	public static boolean isPermutation(String string1, String string2) {
		if (string1.length() != string2.length()) {
			return false;
		}
		Counter<Character> counter1 = new Counter<Character>();
		counter1.count(StringUtil.asCharacterArray(string1));
		Counter<Character> counter2 = new Counter<Character>();
		counter2.count(StringUtil.asCharacterArray(string2));
		return counter1.equals(counter2);
	}

	public static void main(String[] args) {
		String string1 = "Hello";
		String string2 = "elHol";
		System.out.println(isPermutation(string1, string2));

		String string3 = "1";
		String string4 = "2";
		System.out.println(isPermutation(string3, string4));

		String string5 = "He";
		String string6 = "elHol";
		System.out.println(isPermutation(string5, string6));
	}
}
