package chapter1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Implement an algorithm to check if a String has all unique values. What if you
 * cannot use additional data structures?
 * 
 * @author Dominik Sudwischer
 *
 */
public class IsUnique {

	/*
	 * Iterate through the string as a character array. Keep track of characters that
	 * have been found using a HashSet. Time complexity: O(n) with n = string.length
	 */
	public static boolean isUnique1(String string) {
		Set<Character> charsInString = new HashSet<Character>();
		for (char c : string.toCharArray()) {
			if (charsInString.contains(c)) {
				return false;
			}
			else {
				charsInString.add(c);
			}
		}
		return true;
	}

	/*
	 * If no additional data structures are available, sort the corresponding char
	 * array and check if to adjacent characters are equal. Time complexity: O(n *
	 * log(n)) with n = string.length
	 */
	public static boolean isUnique2(String string) {
		char[] charArr = string.toCharArray();
		Arrays.sort(charArr);
		for (int i = 1; i < charArr.length; i++) {
			if (charArr[i] == charArr[i - 1]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isUnique1("hello world"));
		System.out.println(isUnique1("helo wrd"));
		System.out.println(isUnique2("hello world"));
		System.out.println(isUnique2("helo wrd"));
	}
}
