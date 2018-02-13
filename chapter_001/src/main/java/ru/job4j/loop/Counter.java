package ru.job4j.loop;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {

    /**
     * Вычисляет сумму четных чисел в заданном диапазоне
     * @param start первое число диапазона
     * @param finish последнее число диапазона
     * @return сумму четных чисел
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
