package ru.job4j.bomber;

class Hero {
    private Cell position;

    public Hero() {
        this.position = new Cell(0, 0);
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }
}
