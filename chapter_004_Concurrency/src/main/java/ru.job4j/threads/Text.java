package ru.job4j.threads;

public class Text {

    public static void main(String[] args) {
        String line = "qwe asd asd";
        Thread before = new Thread(() -> System.out.println("About"));
        Thread tw = new Thread(new CalculateWords(line));
        Thread tg = new Thread(new CalculateGaps(line));
        Thread after = new Thread(() -> System.out.println("After all"));
        before.start();
        try {
            before.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        tw.start();
        tg.start();
        try {
            tw.join();
            tg.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        after.start();
    }


}

abstract class Calculate implements Runnable {
    private final  String line;

    public Calculate(String line) {
        this.line = line;
    }
    public void run() {
        System.out.println(quantity(line));
    }

    abstract int quantity(String string);
}

class CalculateWords extends Calculate {

    public CalculateWords(String line) {
        super(line);
    }

    @Override
    int quantity(String string) {
        int words = 0;
        char[] symbols = string.toCharArray();
        if (symbols[0] != ' ') {
            words++;
        }
        for (int i = 1; i < symbols.length; i++) {
            if (symbols[i - 1] == ' ') {
                words++;
            }
        }
        return words;
    }
}

class CalculateGaps extends Calculate {

    public CalculateGaps(String line) {
        super(line);
    }

    @Override
    int quantity(String string) {
        int gaps = 0;
        char[] symbols = string.toCharArray();
        if (symbols[0] == ' ') {
            gaps++;
        }
        for (char symbol : symbols) {
            if (symbol == ' ') {
                gaps++;
            }
        }
        return gaps;
    }
}