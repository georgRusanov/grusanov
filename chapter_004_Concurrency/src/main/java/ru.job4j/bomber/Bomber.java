package ru.job4j.bomber;

import java.util.concurrent.locks.ReentrantLock;

public class Bomber {

    final Hero hero;
    final ReentrantLock[][] board = new ReentrantLock[5][5];

    public Bomber(Hero hero) {
        this.hero = hero;
        board[0][0].lock();
    }

    boolean moveHero(Cell dest) {
        boolean result = false;
        int sourceX = hero.getPosition().getX();
        int sourceY = hero.getPosition().getY();
        int destX = dest.getX();
        int destY = dest.getY();
        if (!(destX - sourceX == 0 && destY - sourceY == 0) && !isBound(destX, destY)) {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() < startTime + 500) {
                if (board[destX][destY].tryLock()) {
                    board[sourceX][sourceY].unlock();
                    hero.setPosition(dest);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    boolean isBound(int x, int y) {
        boolean result = false;
        if (x >= board.length || x < 0 || y >= board.length || y < 0) {
            result = true;
        }
        return result;
    }

    public static void main(String[] args) {
        Hero hero = new Hero();
        Bomber bomber = new Bomber(hero);
        boolean condition = true;

        new Thread(() -> {
            boolean isMoved;
            while (condition) {
                isMoved = false;
                while (isMoved) {
                    isMoved = bomber.moveHero(new Cell((int) (Math.random() * (bomber.board.length - 1)), (int) (Math.random() * (bomber.board.length - 1))));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

