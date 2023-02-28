package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

public final class BookTicker {
    private BookTicker() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.bookTicker("btcusdt", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
