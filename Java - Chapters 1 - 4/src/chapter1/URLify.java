package chapter1;

/**
 * Given a char[], replace all occurrences of ' ' with "%20" inplace. You may assume
 * that the array has enough space in the back to to all replacements inplace and
 * that you are given the length of the "real" string.
 *
 * @author Dominik Sudwischer
 *
 */
public class URLify {

	/*
	 * Idea: Scan the array to find the number of whitespaces, say k. Then, beginning
	 * at the back of the array, start shifting every character to the right by 2*k.
	 * Each time you encounter a whitespace while shifting, reduce the shift amount
	 * by 2. Time complexity: O(n) where n = size of the real string.
	 */
	public static void urlify(char[] charArr, int size) {
		int numWhitespaces = countOccurences(charArr, size, ' ');
		int numShiftSpaces = 2 * numWhitespaces;
		for (int i = size - 1; i >= 0; i--) {
			if (numShiftSpaces == 0) {
				return;
			}
			if (charArr[i] == ' ') {
				numShiftSpaces -= 2;
				int posOfNextPercentSymbol = i + numShiftSpaces;
				insertReplacement(charArr, posOfNextPercentSymbol);
			}
			else {
				charArr[i + numShiftSpaces] = charArr[i];
			}
		}
	}

	private static int countOccurences(char[] charArr, int size, char toFind) {
		int numOccurrences = 0;
		for (int i = 0; i < size; i++) {
			if (charArr[i] == toFind) {
				numOccurrences++;
			}
		}
		return numOccurrences;
	}

	private static void insertReplacement(char[] charArr,
	        int startIndexOfReplacement) {
		charArr[startIndexOfReplacement] = '%';
		charArr[startIndexOfReplacement + 1] = '2';
		charArr[startIndexOfReplacement + 2] = '0';
	}

	public static void main(String[] args) {
		int size = 50;
		String string = "Nichts ist getan wenn noch etwas zu tun uebrig ist"
		        + "                             ";
		char[] charArr = string.toCharArray();
		urlify(charArr, size);
		System.out.println(charArr);
	}
}
