package util;

/**
 * Some utility for strings.
 * 
 * @author Dominik Sudwischer
 *
 */

public class StringUtil {

	/**
	 * Returns a Character[] from a given String.
	 * 
	 * @param string
	 *            The String to convert
	 * @return The corresponding Character[]
	 */
	public static Character[] asCharacterArray(String string) {
		Character[] characterArray = new Character[string.length()];
		for (int i = 0; i < string.length(); i++) {
			characterArray[i] = (Character) string.charAt(i);
		}
		return characterArray;
	}
}
