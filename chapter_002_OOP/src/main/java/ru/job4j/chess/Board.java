package ru.job4j.chess;

public class Board {

    Figure[] figures = new Figure[32];
    int position = 0;

    public void add(Figure figure) {
        this.figures[position++] = figure;
    }

    public void move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int index = indexOf(source);
        if (index == this.figures.length) {
            throw new FigureNotFoundException("Нечем делать ход");
        }
        Cell[] route = figures[index].way(source, dest);
        for (Figure figure : figures) {
            if (figure != null) {
                for (Cell step : route) {
                    if (figure.position.x == step.x && figure.position.y == step.y) {
                        throw new OccupiedWayException("На пути помеха");
                    }
                }
            }
        }
        figures[index] = figures[index].copy(dest);
    }

    int indexOf(Cell cell) {
        int answer = this.figures.length;
        for (int i = 0; i < this.figures.length; i++)  {
            if (this.figures[i] != null && this.figures[i].position.x == cell.x && this.figures[i].position.y == cell.y) {
                answer = i;
                break;
            }
        }
        return answer;
    }
}
