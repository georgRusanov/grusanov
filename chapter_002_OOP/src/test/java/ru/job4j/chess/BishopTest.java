package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopTest {


    boolean equals(Cell[] first, Cell[] second) {
        boolean answer = true;
        for (int i = 0; i < first.length; i++) {
            if (first[i].x != second[i].x || first[i].y != second[i].y) {
                answer = false;
            }
        }
        return answer;
    }

    @Test
    public void whenTrueXYPlus() {
        Bishop bishop = new Bishop(4, 4);
        Cell[] expected = {new Cell(5, 5), new Cell(6, 6)};
        Cell[] result = bishop.way(bishop.position, new Cell(6, 6));
        assertThat(true, is(equals(result, expected)));
    }

    @Test
    public void whenTrueXYMinus() {
        Bishop bishop = new Bishop(5, 4);
        Cell[] expected = {new Cell(4, 3), new Cell(3, 2)};
        Cell[] result = bishop.way(bishop.position, new Cell(3, 2));
        assertThat(true, is(equals(result, expected)));
    }

    @Test
    public void whenTrueXPlusYMinus() {
        Bishop bishop = new Bishop(4, 4);
        Cell[] expected = {new Cell(5, 3), new Cell(6, 2)};
        Cell[] result = bishop.way(bishop.position, new Cell(6, 2));
        assertThat(true, is(equals(result, expected)));
    }

    @Test
    public void whenTrueXMinusYPlus() {
        Bishop bishop = new Bishop(4, 4);
        Cell[] expected = {new Cell(3, 5), new Cell(2, 6)};
        Cell[] result = bishop.way(bishop.position, new Cell(2, 6));
        assertThat(true, is(equals(result, expected)));
    }
}