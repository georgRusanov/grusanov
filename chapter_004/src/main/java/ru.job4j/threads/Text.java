package ru.job4j.threads;

public class Text {

    public static final class Calculate implements Runnable {
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

    public static void main(String[] args) {
        String string = "qwe asd asd";
        new Thread(new Calculate(string, "word")).start();
        new Thread(new Calculate(string, "gap")).start();
    }
}