package ru.job4j.bomber;

import java.util.concurrent.locks.ReentrantLock;

public class Bomber {

        final Hero hero;
        final ReentrantLock[][] board = new ReentrantLock[5][5];

    public Bomber(Hero hero) {
        this.hero = hero;
        board[0][0].lock();
    }

    void moveHero() {
        boolean moved = false;
        int x = hero.getX();
        int y = hero.getY();
        int stepX = 0;
        int stepY = 0;
        while (!moved) {
            stepX += ((int) (Math.random() * 2) - 1);
            stepY += ((int) (Math.random() * 2) - 1);
            if (!(stepX == 0 && stepY == 0) && !isBound(x + stepX, y + stepY)) {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() < startTime + 500) {
                    if (board[x + stepX][y + stepY].tryLock()) {
                        board[x][y].unlock();
                        hero.setX(x + stepX);
                        hero.setY(y + stepY);
                        moved = true;
                        break;
                    }
                }
            }
        }
    }

    boolean isBound(int x, int y) {
        boolean result = false;
        if (x == board.length || x == -1 || y == board.length || y == -1) {
            result = true;
        }
        return result;
    }

    public static void main(String[] args) {
        Hero hero = new Hero();
        Bomber bomber = new Bomber(hero);
        boolean condition = true;

        new Thread(() -> {
            while (condition) {
                bomber.moveHero();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

class Hero {
    private int x = 0, y = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}