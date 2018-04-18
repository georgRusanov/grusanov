package ru.job4j.stock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StockTest {
    Stock stock = new Stock();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @Before
    public void addData() {
        stock.add(stock.new Request("qwe", "bid", "add", 13, 10));
        stock.add(stock.new Request("qwe", "bid", "add", 13, 10));
        stock.add(stock.new Request("qwe", "ask", "add", 12, 10));

    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void addTest() {
        stock.add(stock.new Request("qwe", "bid", "add", 10, 11));
        stock.add(stock.new Request("qwe", "bid", "add", 12, 9));
        Glass result = stock.glasses.get("qwe");
        assertThat(result.bid.size(), is(3));
        assertThat(result.ask.size(), is(1));
        assertThat(result.ask.first().getVolume(), is(1));
        Iterator<Stock.Request> iterator = result.bid.iterator();
        assertThat(iterator.next().getPrice(), is(13));
        assertThat(iterator.next().getPrice(), is(13));
        assertThat(iterator.next().getPrice(), is(10));
    }

    @Test
    public void showDataTest() {
        stock.showGlass("qwe");
        assertThat(
                this.out.toString(),
                is(
                        String.format("%-10s %-10s %-10s\n" +
                                "%-10d %-10d\n" +
                                "%-10s %-10d %-10d\n",
                                "Продажа", "Цена", "Покупка",
                                20, 13,
                                "", 12, 10)
                )
        );
    }
}