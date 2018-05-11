package ru.job4j.stock;

import java.util.TreeSet;

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
                } else if (first < second) {
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
        for (Stock.Request request : bid) {
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
        for (Stock.Request request : ask) {
            if (price == 0 && volume == 0) {
                price = request.getPrice();
                volume = request.getVolume();
            } else if (isSamePrice(request, price)) {
                volume = request.getVolume() + volume;
            } else {
                sb.append(String.format("%-10s %-10d %-10d\n", "", price, volume));
                volume = request.getVolume();
                price = request.getPrice();
            }
        }
        sb.append(String.format("%-10s %-10d %-10d\n", "", price, volume));
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