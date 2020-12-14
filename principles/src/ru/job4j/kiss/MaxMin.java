package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        if (value.size() == 0) {
            return null;
        }
        T maxElement = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(value.get(i), maxElement) > 0) {
                maxElement = value.get(i);
            }
        }
        return maxElement;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        if (value.size() == 0) {
            return null;
        }
        T minElement = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (comparator.compare(value.get(i), minElement) < 0) {
                minElement = value.get(i);
            }
        }
        return minElement;
    }
}
