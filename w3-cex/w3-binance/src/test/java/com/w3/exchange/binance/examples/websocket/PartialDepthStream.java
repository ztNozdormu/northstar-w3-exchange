package com.w3.exchange.binance.examples.websocket;


import com.w3.exchange.binance.impl.BIWebsocketClientImpl;

public final class PartialDepthStream {
    private PartialDepthStream() {
    }
    private static final int speed = 100;
    private static final int levels = 5;

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.partialDepthStream("btcusdt", levels, speed, ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
