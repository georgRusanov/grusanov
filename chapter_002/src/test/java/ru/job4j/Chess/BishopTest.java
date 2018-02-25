package ru.job4j.Chess;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    Board board= new Board();

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     * Проверяем создание фигуры
     */
    @Test
    public void whenCreateFigureThenItInFigures() {
        board.add(new Bishop(new Cell(4,4)));
        assertThat(board.figures[0].position.x, is(4));
    }
    /**
     * При правильной конечной точки хода получим массив шагов
     */
    @Test
    public void whenRightWayThanReceiveArrayOfMoves() {
        board.add(new Bishop(new Cell(4,4)));
        Cell[] way = board.figures[0].way(board.figures[0].position, new Cell(6,6));
        Cell[] expected = new Cell[] {new Cell(5,5), new Cell(6,6)};
        assertThat(
                String.format("%d %d %d %d", expected[0].x, expected[0].y, expected[1].x, expected[1].y),
                is(String.format("%d %d %d %d", way[0].x, way[0].y, way[1].x, way[1].y)));
    }

    /**
     * При неправильной конечной точки хода получим массив шагов
     */
    @Test
    public void whenRightWayThanReceiveArrayOfMoves() {
        board.add(new Bishop(new Cell(4,4)));
        Cell[] way = board.figures[0].way(board.figures[0].position, new Cell(6,6));
        Cell[] expected = new Cell[] {new Cell(5,5), new Cell(6,6)};
        assertThat(
                String.format("%d %d %d %d", expected[0].x, expected[0].y, expected[1].x, expected[1].y),
                is(String.format("%d %d %d %d", way[0].x, way[0].y, way[1].x, way[1].y)));
    }


}
