package ru.job4j.stock;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private int commonId = 0;
    Map<String, Glass> glasses = new HashMap<>();

    /**
     * Если тип заявки add, то вызывает метод addRequest
     * @param request заявка, которую хотим добавить
     */
    void add(Request request) {
        if (request.getType().equals("add")) {
            addRequest(request);
        }
    }

    /**
     * Добавляет заявку в список соотвествующего эммитента,
     * В случае отсутствия такого эммитента создает новый список заявок.
     * Вызывает метод для сокращения противоположных заявок
     * @param request добавляемая заявка
     */
    void addRequest(Request request) {
        String book = request.getBook();
        if (!glasses.containsKey(book)) {
            glasses.put(book, new Glass());
        }
        glasses.get(book).addReq(request);
        glasses.get(book).removeOpposite(request);
    }

    /**
     * Выводит на печать заявки определенного эммитента
     * @param book эммитент
     */
    void showGlass(String book) {
        if (glasses.containsKey(book)) {
            System.out.print(glasses.get(book));
        }
    }


    class Request {
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

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public String getType() {
            return type;
        }

        public int getId() {
            return id;
        }
    }
}
