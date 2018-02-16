package ru.job4j.array;
import java.util.Arrays;
/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class ArrayDuplicate {

    public String[] remove(String[] array){
        int length = array.length;
        for (int out = 0; out != length; out++) {
            for (int in = out + 1; in != length; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[length - 1];
                    length--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, length);
    }
}

