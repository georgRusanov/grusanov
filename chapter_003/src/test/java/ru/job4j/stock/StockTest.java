package ru.job4j.stock;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StockTest {
    Stock stock = new Stock();

    @Test
    public void addTest() {
        stock.addRequest(stock.new Request("qwe", "bid", "add", 13, 10));
        stock.addRequest(stock.new Request("qwe", "ask", "add", 12, 10));
        stock.addRequest(stock.new Request("qwe", "bid", "add", 10, 11));
        Glass result = stock.glasses.get("qwe");
        assertThat(result.bid.size(), is(2));
        assertThat(result.ask.size(), is(1));
        assertThat(result.bid.iterator().next().getPrice(), is(13));
        assertThat(result.bid.iterator().next().getPrice(), is(13));
    }
}