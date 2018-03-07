package ru.job4j.chess;

public class Bishop extends Figure {

    public Bishop(int x, int y) {
        super(x, y);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(dest.x - source.x) != Math.abs(dest.y - source.y)) {
            throw new ImpossibleMoveException("Слон так не ходит");
        } else {
            Cell[] route = new Cell[Math.abs(dest.x - source.x)];
            if (dest.x > source.x) {
                if (dest.y > source.y) {
                    for (int i = 0; i < route.length; i++) {
                        route[i] = new Cell(source.x + 1 + i, source.y + 1 + i);
                    }
                } else {
                    for (int i = 0; i < route.length; i++) {
                        route[i] = new Cell(source.x + 1 + i, source.y - 1 - i);
                    }
                }
            } else {
                if (dest.y > source.y) {
                    for (int i = 0; i < route.length; i++) {
                        route[i] = new Cell(source.x - 1 - i, source.y + 1 + i);
                    }
                } else {
                    for (int i = 0; i < route.length; i++) {
                        route[i] = new Cell(source.x - 1 - i, source.y - 1 - i);
                    }
                }
            }
            return route;
        }
    }

    public Figure copy(Cell dest) {
        return new Bishop(dest.x, dest.y);
    }
}
