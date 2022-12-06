package com.w3.exchange.binance.examples.websocket;


import com.w3.exchange.binance.impl.BIWebsocketClientImpl;

public final class AllMiniTickerStream {
    private AllMiniTickerStream() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.allMiniTickerStream(((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
