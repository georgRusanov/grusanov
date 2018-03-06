package ru.job4j.chess;

public class Board {

    Figure[] figures = new Figure[32];
    int position = 0;

    public void add(Figure figure){
        this.figures[position++] = figure;
    }

    boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean answer = true;
        return answer;
    }
}
