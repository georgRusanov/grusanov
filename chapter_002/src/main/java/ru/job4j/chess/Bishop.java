package ru.job4j.chess;

public class Bishop extends Figure{

    public Bishop(int x, int y) {
        super(x, y);
    }

    @Override
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(dest.x - source.x) != Math.abs(dest.y - source.y)) {
            throw new ImpossibleMoveException("Слон так не ходит");
        } else {
            Cell[] route = new Cell[Math.abs(dest.x - source.x)];

            return route;
        }
    }

    public Figure copy(Cell dest) {
        return new Bishop(dest.x, dest.y);
    }
}
