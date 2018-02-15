package ru.job4j.array;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Square {
    public int[] calculate(int bound) {

        int[] rst = new int[bound];

        for (int index = 1; index <= bound; index++) {
             rst[index - 1] = index*index;
        }

        return rst;
    }
}
