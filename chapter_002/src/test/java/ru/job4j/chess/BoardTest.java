package ru.job4j.chess;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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


    @Test
    public void whenCreateFigureThenItInFigures() {
        board.add(new Bishop(6, 6));
        assertThat(true, is(board.figures[0].position.x == 6 && board.figures[0].position.y == 6));
    }

    @Test
    public void whenNoSuchFigure() {
        board.add(new Bishop(4, 4));
        try {
            board.move(new Cell(5, 5), new Cell(6, 6));
        } catch (FigureNotFoundException fnfe) {
            System.out.print(fnfe.getMessage());
        }
        MatcherAssert.assertThat(
                this.out.toString(),
                Matchers.is(
                        String.format("Нечем делать ход")
                )
        );
    }

    @Test
    public void whenWrongRoute() {
        board.add(new Bishop(4, 4));
        try {
            board.move(new Cell(4, 4), new Cell(7, 6));
        } catch (ImpossibleMoveException ime) {
            System.out.print(ime.getMessage());
        }
        MatcherAssert.assertThat(
                this.out.toString(),
                Matchers.is(
                        String.format("Слон так не ходит")
                )
        );
    }

    @Test
    public void whenAnotherFigureOnRoute() {
        board.add(new Bishop(4, 4));
        board.add(new Bishop(6, 6));
        try {
            board.move(new Cell(4, 4), new Cell(6, 6));
        } catch (OccupiedWayException owe) {
            System.out.print(owe.getMessage());
        }
        MatcherAssert.assertThat(
                this.out.toString(),
                Matchers.is(
                        String.format("На пути помеха")
                )
        );
    }

    @Test
    public void whenAllOkCreateNewFigure() {
        board.add(new Bishop(4, 4));
        board.add(new Bishop(5, 6));
        try {
            board.move(new Cell(4, 4), new Cell(6, 6));
        } catch (FigureNotFoundException fnfe) {
            System.out.print(fnfe.getMessage());
        } catch (ImpossibleMoveException ime) {
            System.out.print(ime.getMessage());
        } catch (OccupiedWayException owe) {
            System.out.print(owe.getMessage());
        }
        assertThat(true, is(board.figures[0].position.x == 6 && board.figures[0].position.y == 6));
    }
}
