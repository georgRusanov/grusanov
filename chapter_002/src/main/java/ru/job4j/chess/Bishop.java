package ru.job4j.chess;

public class Bishop extends Figure {

    public Bishop(int x, int y) {
        super(x, y);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int xAbs = Math.abs(dest.x - source.x);
        if (xAbs != Math.abs(dest.y - source.y)) {
            throw new ImpossibleMoveException("Слон так не ходит");
        } else {
            Cell[] route = new Cell[xAbs];
            for (int i = 0; i < xAbs; i++) {
                route[i] = new Cell(dest.x - (xAbs - i - 1) * Integer.compare(dest.x, source.x), dest.y - (xAbs - i - 1) * Integer.compare(dest.y, source.y));
            }
            return route;
        }
    }

    public Figure copy(Cell dest) {
        return new Bishop(dest.x, dest.y);
    }
}
