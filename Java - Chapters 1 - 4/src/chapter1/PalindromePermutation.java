package chapter1;

import java.util.Map.Entry;

import util.Counter;
import util.StringUtil;

/**
 * Given a string, find out whether it is a permutation of a palindrome. The
 * palindrome does not need to be a dictionary word.
 * 
 * @author Dominik Sudwischer
 *
 */

public class PalindromePermutation {

	/*
	 * A string is a permutation of a palindrome if and only if each character
	 * appears and even number of times with at most one exception. Time complexity:
	 * O(n) where n = string.length()
	 */
	public static boolean isPalindromePermutation(String string) {
		Counter<Character> counter = new Counter<Character>();
		counter.count(StringUtil.asCharacterArray(string));
		short numOddCounts = 0;
		for (Entry<Character, Integer> entry : counter.entrySet()) {
			if (entry.getValue() % 2 == 1) {
				numOddCounts++;
				if (numOddCounts > 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPalindromePermutation("toto"));
		System.out.println(isPalindromePermutation("aarrcce"));
		System.out.println(isPalindromePermutation("clara"));
	}
}
