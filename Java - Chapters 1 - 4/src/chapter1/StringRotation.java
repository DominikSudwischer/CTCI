package chapter1;

/**
 * Given a method isSubstring that checks if a string is a substring of another and
 * two strings s1 and s2, use the method isSubstring exactly once to find out if s2
 * is a rotation of s1. For example, "waterbottle" is a rotation of "erbottlewat".
 * 
 * @author Dominik Sudwischer
 *
 */

public class StringRotation {

	/*
	 * The idea is to take s1 twice in a row and check if s2 is a substring of s1+s1.
	 * Time complexity: Assuming that isSubstring is linear in the length of s2, n,
	 * the approach is O(n).
	 */
	public static boolean isRotation(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		String s3 = s1 + s1;
		return s3.contains(s2);
	}

	public static void main(String[] args) {
		String s1 = "erbottlewat";
		String s2 = "waterbottle";
		System.out.println(isRotation(s1, s2));
		s1 = "ionrotat";
		s2 = "rotation";
		System.out.println(isRotation(s1, s2));
		s1 = "ionrotat";
		s2 = "rotatio";
		System.out.println(isRotation(s1, s2));
		s1 = "test";
		s2 = "fail";
		System.out.println(isRotation(s1, s2));
	}
}
