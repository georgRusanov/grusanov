package ru.job4j.stock;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

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
        if(glasses.containsKey(book)) {
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

class RequestVolumeComparator implements Comparator<Stock.Request> {

    /**
     * Сравнение по цене
     * @param first заявка
     * @param second заявка
     * @return
     */
    @Override
    public int compare(Stock.Request first, Stock.Request second) {
        return Integer.compare(second.getPrice(), first.getPrice());
    }
}

class RequestIdComparator implements Comparator<Stock.Request> {

    /**
     * Сравнение по id. Добавил чтобы трисет добавлял заявки с одинаковой ценой
     * @param first заявка
     * @param second заявка
     * @return
     */
    @Override
    public int compare(Stock.Request first, Stock.Request second) {
        return Integer.compare(first.getId(), second.getId());
    }
}

class Glass {
    TreeSet<Stock.Request> bid = new TreeSet<>(new RequestVolumeComparator().thenComparing(new RequestIdComparator()));
    TreeSet<Stock.Request> ask = new TreeSet<>(new RequestVolumeComparator().thenComparing(new RequestIdComparator()));

    /**
     * Добавление заявки в список соответсвующего действия
     * @param request добавляемая заявка
     */
    void addReq(Stock.Request request) {
        if (request.getAction().equals("bid")) {
            bid.add(request);
        } else if (request.getAction().equals("ask")) {
            ask.add(request);
        }
    }

    /**
     * Сокращение заявок на покупку и продажу с одинаковой ценой
     * @param request вновь добавленная заявка
     */
    void removeOpposite(Stock.Request request) {
        TreeSet<Stock.Request> opposite = this.bid;
        TreeSet<Stock.Request> list = this.ask;
        if (request.getAction().equals("bid")) {
            opposite = this.ask;
            list = this.bid;
        }
        for (Stock.Request oppositeRequest : opposite) {
            if (oppositeRequest.getPrice() == request.getPrice()) {
                int first = oppositeRequest.getVolume();
                int second = request.getVolume();
                if (first > second) {
                    oppositeRequest.setVolume(first - second);
                    list.remove(request);
                    removeOpposite(oppositeRequest);
                } else if (first < second){
                    request.setVolume(second - first);
                    opposite.remove(oppositeRequest);
                    removeOpposite(request);
                } else {
                    list.remove(request);
                    opposite.remove(oppositeRequest);
                }
                break;
            }
        }
    }

    /**
     * Возвращает строку в виде таблицы количества и цен заявок в стакане
     * Объединяет заявки с одинаковой ценой
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-10s %-10s\n", "Продажа", "Цена", "Покупка"));
        int price = 0, volume = 0;
        for(Stock.Request request : bid) {
            if (price == 0 && volume == 0) {
                price = request.getPrice();
                volume = request.getVolume();
            } else if (isSamePrice(request, price)) {
                volume = request.getVolume() + volume;
            } else {
                sb.append(String.format("%-10d %-10d\n", volume, price));
                volume = request.getVolume();
                price = request.getPrice();
            }
        }
        sb.append(String.format("%-10d %-10d\n", volume, price));
        price = 0;
        volume = 0;
        for(Stock.Request request : ask) {
            if (price == 0 && volume == 0) {
                price = request.getPrice();
                volume = request.getVolume();
            } else if (isSamePrice(request, price)) {
                volume = request.getVolume() + volume;
            } else {
                sb.append(String.format("%-10s %-10d %-10d\n", "",price, volume));
                volume = request.getVolume();
                price = request.getPrice();
            }
        }
        sb.append(String.format("%-10s %-10d %-10d\n", "",price, volume));
        return sb.toString();
    }

    /**
     * Проверка равенства цены у предыдущей и текущей заявки
     * @param request текущая заявка
     * @param price цена предыдущей заявки
     * @return true в случае равенства цены
     */
    private boolean isSamePrice(Stock.Request request, int price) {
        boolean answer = false;
        if (price == request.getPrice()) {
            answer = true;
        }
        return answer;
    }
}
