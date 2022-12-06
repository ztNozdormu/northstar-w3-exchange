package com.w3.exchange.binance.examples.websocket;


import com.w3.exchange.binance.impl.BIWebsocketClientImpl;

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
