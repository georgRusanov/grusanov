package ru.job4j.max;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Max {
    /**
     * Вычисляет максимальное из двух чисел.
     * @param first первое число.
     * @param second второе число.
     * @return максимальное из двух чисел.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     *
     * @param first первое число.
     * @param second второе число.
     * @param third третье число
     * @return макс из трех чисел
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
