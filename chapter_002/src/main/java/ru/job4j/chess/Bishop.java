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
            int xCompare = Integer.compare(dest.x, source.x);
            int yCompare = Integer.compare(dest.y, source.y);
            route[0] = new Cell(dest.x - (xAbs - 1) * xCompare, dest.y - (xAbs - 1) * yCompare);
            for (int i = 1; i < xAbs; i++) {
                route[i] = new Cell(route[i - 1].x + xCompare, route[i - 1].y + yCompare);
            }
            return route;
        }
    }

    public Figure copy(Cell dest) {
        return new Bishop(dest.x, dest.y);
    }
}
