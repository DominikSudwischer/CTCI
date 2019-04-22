package chapter1;

/**
 * A class to perform basic string compression using character counts, e.g.
 * aabcccccaaa -> a2b1c5a3. If the compressed string would not be shorter than the
 * uncompressed string, return the original string. It is assumed that only A-Z and
 * a-z are used.
 * 
 * @author Dominik Sudwischer
 *
 */
public class StringCompression {

	/*
	 * The idea is to count streaks in the string. Whenever a streak is finished, add
	 * the last observed character and its streak length to a StringBuilder. Time
	 * complexity: O(n) where n = length of the string to compress.
	 */
	public static String compress(String string) {
		if (string.length() <= 1) {
			return string;
		}
		StringBuilder stringBuilder = buildCompressedStringBuilder(string);
		if (stringBuilder.length() < string.length()) {
			return stringBuilder.toString();
		}
		else {
			return string;
		}
	}

	private static StringBuilder buildCompressedStringBuilder(String string) {
		StringBuilder stringBuilder = new StringBuilder();
		int index = 1;
		int streak = 1;
		while (index < string.length()) {
			if (string.charAt(index) == string.charAt(index - 1)) {
				streak++;
			}
			else {
				stringBuilder.append(string.charAt(index - 1)).append(streak);
				streak = 1;
			}
			index++;
		}
		stringBuilder.append(string.charAt(index - 1)).append(streak);
		return stringBuilder;
	}

	public static void main(String[] args) {
		System.out.println("Uncompressed: " + "aabcccccaaa" + "\nCompressed: "
		        + compress("aabcccccaaa"));
		System.out.println("Uncompressed: " + "" + "\nCompressed: " + compress(""));
		System.out
		        .println("Uncompressed: " + "a" + "\nCompressed: " + compress("a"));
		System.out.println(
		        "Uncompressed: " + "ABCDEF" + "\nCompressed: " + compress("ABCDEF"));
		System.out.println(
		        "Uncompressed: " + "AABBCC" + "\nCompressed: " + compress("AAABCC"));
		System.out.println("Uncompressed: " + "AABBCC" + "\nCompressed: "
		        + compress("AAABBCC"));
	}
}
