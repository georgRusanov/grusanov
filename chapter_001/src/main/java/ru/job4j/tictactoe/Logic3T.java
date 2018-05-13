package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean answer = false;
        int countH = 0;
        int countV = 0;
        int countD = 0;
        int countDReverse = 0;
        for (int out = 0; out < table.length; out++) {
            for (int in = 0; in < table.length; in++) {
                if (table[out][in].hasMarkX()) {
                    countH++;
                }
                if (table[in][out].hasMarkX()) {
                    countV++;
                }
                if (in == out) {
                    if (table[out][in].hasMarkX()) {
                        countD++;
                    }
                }
                if (countH == 3 || countV == 3 || countD == 3) {
                    answer = true;
                    break;
                }
            }
            countH = 0;
            countV = 0;
            if (table[out][table.length - out - 1].hasMarkX()) {
                countDReverse++;
            }
        }
        if (countDReverse == 3) {
            answer = true;
        }

        return answer;
    }

    public boolean isWinnerO() {
        boolean answer = false;
        int countH = 0;
        int countV = 0;
        int countD = 0;
        int countDReverse = 0;
        for (int out = 0; out < table.length; out++) {
            for (int in = 0; in < table.length; in++) {
                if (table[out][in].hasMarkO()) {
                    countH++;
                }
                if (table[in][out].hasMarkO()) {
                    countV++;
                }
                if (in == out) {
                    if (table[in][out].hasMarkO()) {
                        countD++;
                    }
                }
                if (countH == 3 || countV == 3 || countD == 3) {
                    answer = true;
                    break;
                }
            }
            countH = 0;
            countV = 0;
            if (table[out][table.length - out - 1].hasMarkO()) {
                countDReverse++;
            }
        }
        if (countDReverse == 3) {
            answer = true;
        }
        return answer;
    }

    public boolean hasGap() {
        boolean answer = false;
        for (Figure3T[] figOut : table) {
            for (Figure3T figure : figOut) {
                if (!figure.hasMarkO() && !figure.hasMarkX()) {
                    answer = true;
                }
            }
        }
        return answer;
    }
}