package ru.job4j.chess;

abstract class Figure  {
    final Cell position;


    public Figure(int x, int y) {
        this.position = new Cell(x, y);
    }

    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    abstract Figure copy(Cell dest);
}
