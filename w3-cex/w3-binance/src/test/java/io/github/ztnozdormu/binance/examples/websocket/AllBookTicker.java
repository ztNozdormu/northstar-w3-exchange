package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

public final class AllBookTicker {
    private AllBookTicker() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.allBookTickerStream(((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
