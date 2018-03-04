package ru.job4j.chess;

public class Figure {
    final Cell position;


    public Figure(int x, int y) {
        this.position = new Cell(x, y);
    }
}
