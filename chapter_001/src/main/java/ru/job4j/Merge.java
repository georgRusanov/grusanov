package ru.job4j;

public class Merge {

    public static int[] mergeTwoArrays(int[] first, int[] second) {
        int firstSize = first.length;
        int secondSize = second.length;
        int[] answer = new int[firstSize + secondSize];
        int firstIndex = 0, secondIndex = 0;
        for (int i = 0; i < answer.length; i++) {
            if (firstIndex < firstSize && secondIndex < secondSize) {
                answer[i] = first[firstIndex] < second[secondIndex] ? first[firstIndex++] : second[secondIndex++];
            } else {
                answer[i] = firstIndex == firstSize ? second[secondIndex++] : first[firstIndex++];
            }
        }
        return  answer;
    }
}
