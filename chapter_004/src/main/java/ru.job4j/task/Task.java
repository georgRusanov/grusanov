package ru.job4j.task;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private List<Integer> array = new ArrayList<>();

    public void add(int number) throws InterruptedException {
        int remainder = number == 1 ? 1 : 0;
        while (array.size() < 70) {
            synchronized (array) {
                while (array.size() / 10 % 2 == remainder) {
                    array.wait();
                }
                for (int i = 0; i < 10; i++) {
                    array.add(number);
                    array.notify();
                }
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int s : array){
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread first = new Thread(() -> {
            try {
                task.add(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread second = new Thread(() -> {
            try {
                task.add(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(task.toString());
    }
}
