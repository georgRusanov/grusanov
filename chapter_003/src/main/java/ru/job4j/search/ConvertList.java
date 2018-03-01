package ru.job4j.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConvertList {

    public List<Integer> toList (int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] out : array) {
            for (int in : out) {
                list.add(in);
            }
        }
        return list;
    }

    public int[][] toArray (List<Integer> list, int rows) {
        int size = list.size();
        if (size % rows != 0) {
            size += (rows - size % rows);
        }
        int[][] array = new int[rows][size / rows];
        int row = 0;
        int column = 0;
        for (Integer number : list) {
            array[row][column] = number;
            if (column == size / rows - 1) {
                row++;
                column = 0;
            } else {
                column++;
            }
        }
        for (int i = size % rows + 1; i < size / rows; i++) {
            array[row][i] = 0;
        }
        return array;
    }

    public List<Integer> convert (List<int[]> list) {
        List<Integer> answer = new ArrayList<>();

        for (int[] out : list) {
            for (int in : out) {
                answer.add(in);
            }
        }
        return  answer;
    }

}
