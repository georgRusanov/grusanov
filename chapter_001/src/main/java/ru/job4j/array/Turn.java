package ru.job4j.array;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Turn {

    public static int[] back(int[] array){
        for (int index = 0; index != array.length / 2; index++) {
            array[index] = array[array.length - 1 - index];
        }
        return array;
    }
}
