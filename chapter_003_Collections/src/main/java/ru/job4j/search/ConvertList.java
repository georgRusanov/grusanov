package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {

    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] out : array) {
            for (int in : out) {
                list.add(in);
            }
        }
        return list;
    }

    public int[][] toArray(List<Integer> list, int rows) {
        int columns = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[rows][columns];
        int row = 0;
        int column = 0;
        for (Integer number : list) {
            array[row][column] = number;
            if (column == columns - 1) {
                row++;
                column = 0;
            } else {
                column++;
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> answer = new ArrayList<>();

        for (int[] out : list) {
            for (int in : out) {
                answer.add(in);
            }
        }
        return  answer;
    }

}
