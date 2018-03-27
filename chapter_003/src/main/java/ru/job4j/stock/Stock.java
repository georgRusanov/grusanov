package ru.job4j.stock;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Stock {
    private int commonId = 0;
    Map<String, Glass> glasses = new HashMap<>();

    void addRequest(Request request) {
        String book = request.getBook();
        if (!glasses.containsKey(book)) {
            glasses.put(book, new Glass());
        }
        glasses.get(book).addReq(request);
    }


    class Request implements Comparable<Request> {
        private int id, price, volume;
        private String book, type, action;

        public Request(String book, String action, String type, int price, int volume) {
            this.book = book;
            this.action = action;
            this.type = type;
            this.price = price;
            this.volume = volume;
            this.id = commonId++;
        }

        public String getBook() {
            return book;
        }

        public String getAction() {
            return action;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public int compareTo(Request request) {
            return request.getPrice() - price;
        }
    }
}

class Glass {
    TreeSet<Stock.Request> bid = new TreeSet<>();
    TreeSet<Stock.Request> ask = new TreeSet<>();

    void addReq(Stock.Request request) {
        if (request.getAction().equals("bid")) {
            bid.add(request);
        } else if (request.getAction().equals("ask")) {
            ask.add(request);
        }
    }
}
