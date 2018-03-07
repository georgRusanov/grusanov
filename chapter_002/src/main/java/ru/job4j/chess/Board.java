package ru.job4j.chess;

public class Board {

    Figure[] figures = new Figure[32];
    int position = 0;

    public void add(Figure figure){
        this.figures[position++] = figure;
    }

    public void move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int index = 0;
        for (int i = 0; i < figures.length; i++)  {
            if (figures[i] != null && figures[i].position.x == source.x && figures[i].position.y == source.y) {
                index = i;
                break;
            } else {
                throw new FigureNotFoundException("Нечем делать ход");
            }
        }
        Cell[] route = figures[index].way(source, dest);
        for (int i = 0; i < figures.length; i++)  {
            if (figures[i] != null) {
                for (Cell step : route) {
                    if (figures[i].position.x == step.x && figures[i].position.y == step.y) {
                        throw new OccupiedWayException("На пути помеха");
                    }
                }
            }
        }
        figures[index] = figures[index].copy(dest);
    }
}
