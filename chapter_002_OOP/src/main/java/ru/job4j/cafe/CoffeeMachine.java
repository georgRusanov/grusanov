package ru.job4j.cafe;

public class CoffeeMachine {
    private int[] coins = {10, 5, 2, 1};

    int[] changes(int value, int price) {
        int[] change = new int[100];
        int counter = 0;
        int money = value - price;
        for (int i = 0; i < coins.length; i++) {
            if (money == 0) {
                break;
            }
            if (money / coins[i] > 0) {
                change[counter++] = coins[i];
                money -= coins[i];
                i--;
            }
        }
        int[] answer = new int[counter];
        System.arraycopy(change, 0, answer, 0, counter);
        return answer;
    }
}
