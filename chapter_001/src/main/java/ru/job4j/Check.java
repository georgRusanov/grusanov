package ru.job4j;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Check {


    boolean contains(String origin, String sub) {
        char[] word = origin.toCharArray();
        char[] letters = sub.toCharArray();
        int lettersIndex = 0;
        boolean result = false;
        for (int index = 0; index != word.length; index++) {
            if (letters[lettersIndex] == word[index]) {
                if (lettersIndex < letters.length - 1) {
                    lettersIndex++;
                } else {
                    result = true;
                    break;
                }
            } else {
                lettersIndex = 0;
            }
        }
        return result;
    }
}
