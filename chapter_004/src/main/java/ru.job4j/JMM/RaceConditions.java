package ru.job4j.JMM;

public class RaceConditions {

    public static void main(String[] args) {
        Obj obj = new Obj();
        new Thread(new Check(obj)).start();
        new Thread(new Increment(obj)).start();
    }

}

class Check implements Runnable {
    Obj obj;

    public Check(Obj obj) {
        this.obj = obj;
    }
    public void run() {
        boolean stop = false;
        while (!stop) {
            if (obj.count > 5) {
                System.out.println(obj.count);
                stop = true;
            }
        }
    }

}

class Increment implements Runnable {
    Obj obj;

    public Increment(Obj obj) {
        this.obj = obj;
    }
    public void run() {
        while (obj.count < 100000) {
            obj.count++;
        }
    }

}

class Obj {
    int count = 0;
}