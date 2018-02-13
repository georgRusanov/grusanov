package ru.job4j.loop;

/**
 * @author Georg Rusanov  (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * Вычисляет факториал
     * @param n основание факториала
     * @return факториал числа n
     */
    public int calc(int n) {
        int factorial = 1;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                factorial *= i;
            }
        }
        return factorial;
    }
}
