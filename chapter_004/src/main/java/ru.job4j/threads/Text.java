package ru.job4j.threads;

public class Text {

    public static void main(String[] args) {
        String string = "qwe asd asd";
        Thread before = new Thread(() -> System.out.println("About"));
        Thread tw = new Thread(new Calculate(string, "word"));
        Thread tg = new Thread(new Calculate(string, "gap"));
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

class Calculate implements Runnable {
    private String string, search;

    public Calculate(String string, String search) {
        this.string = string;
        this.search = search;
    }
    public void run() {
        System.out.println(quantity(string, search));
    }

    int quantity(String string, String search) {
        int gaps = 0;
        int words = 0;
        char[] chars = string.toCharArray();
        if (chars[0] == ' ') {
            gaps++;
        } else { words++;}
        for(int i = 1; i < chars.length; i++) {
            if (chars[i] == ' ') {
                gaps++;
            } else if (chars[i - 1] == ' ') { words++;}
        }
        return search.equals("gap") ? gaps : words;
    }
}