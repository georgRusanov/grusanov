package ru.job4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Lambda {

    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> answers = new ArrayList<>();
        for (int i = start; i < end; i++) {
            answers.add(func.apply((double) i));
        }
        return answers;
    }
}
