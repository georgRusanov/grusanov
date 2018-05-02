package ru.job4j.comparator;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int answer = 0;
        char[] leftArr = left.toCharArray();
        char[] rightArr = right.toCharArray();
        int leftLength = left.length();
        int rightLength = right.length();
        int minLength = leftLength > rightLength ? rightLength : leftLength;
        for (int i = 0; i < minLength; i++) {
            if (leftArr[i] > rightArr[i]) {
                answer = 1;
                break;
            } else if (leftArr[i] < rightArr[i]) {
                answer = -1;
                break;
            }
        }
        if (answer == 0 && leftLength != rightLength) {
            answer = leftLength > rightLength ? 1 : -1;
        }
        return answer;
    }
}
