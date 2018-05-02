package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        List<Iterator<Integer>> iterators = new ArrayList<>();
        while (it.hasNext()) {
            iterators.add(it.next());
        }
        return new Iterator<Integer>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                boolean answer = false;
                for (; index < iterators.size(); index++) {
                    if (iterators.get(index).hasNext()) {
                        answer = true;
                        break;
                    }
                }
                return answer;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                return iterators.get(index).next();
            }
        };
    }
}
