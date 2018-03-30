package ru.job4j.threads;

public class StopThread {
    public static void main(String[] args) {
    String string = "qwe a";

    Thread thread = new Thread(new CountChar(string));
    Thread time = new Thread(new Time(thread, 1));
    time.start();
    thread.start();
    }
}

class  CountChar implements Runnable {
    private String string;

    public  CountChar(String string) {
        this.string = string;
    }
    public void run() {
        int result = 0;
        try {
            char[] chars = string.toCharArray();
            int i = 0;
            do {
                if (!Thread.interrupted()) {
                    if (chars[i] != ' ') {
                        result++;
                    }
                    i++;
                } else {
                    throw new InterruptedException();
                }
            } while (i < chars.length);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return;
        }
        System.out.println(result);
    }
}

class Time implements Runnable {
    private Thread thread;
    private long limit;

    Time(Thread thread, long limit) {
        this.thread = thread;
        this.limit = limit;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(limit);
            thread.interrupt();
        } catch (InterruptedException e) {
            return;
        }
    }
}