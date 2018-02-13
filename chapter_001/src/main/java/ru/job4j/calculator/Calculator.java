package ru.job4j.calculator;

/**
 * Class Класс для вычисления арифметических действий.
 * @author Georg Rusanov (rusanovgeorgy@gmail.com).
 * @since 13.02.2018.
 * @version 1.
 */
public class Calculator {
    private double result;

    /**
     * Сложение.
     * @param first первое слогаемое.
     * @param second второе слагаемое.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Вычитание.
     * @param first уменьшаемое.
     * @param second вычитаемое.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Деление.
     * @param first делимое.
     * @param second делитель.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Умножение.
     * @param first первый множитель.
     * @param second второй множитель.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Получение результата.
     * @return результат арифметического действия.
     */
    public double getResult() {
        return this.result;
    }
}