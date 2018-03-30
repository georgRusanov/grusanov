package ru.job4j.jmm;

public class Visibility implements Runnable {

    boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Visibility thr = new  Visibility();
        thr.run();
        Thread.sleep(1000);
        thr.stop = true;
        System.out.println(System.currentTimeMillis() + " stop is true");
    }

    @Override
    public void run() {
        while (!stop) {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }
    }
}
