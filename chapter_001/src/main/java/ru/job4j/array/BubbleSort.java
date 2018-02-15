package ru.job4j.array;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {

    public int[] sort(int[] array){
        int numOfMoves, temp;
        for (int indexOut = 0; indexOut != array.length - 1; indexOut++) {
            numOfMoves = 0;
            for (int index = 0; index != array.length - 1; index++) {
                if (array[index] > array[index + 1]) {
                    temp =  array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = temp;
                    numOfMoves++;
                }
            }
            if (numOfMoves == 0) {
                    break;
            }
        }
        return array;
    }
}
