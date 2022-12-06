package com.w3.exchange.binance.examples.websocket;


import com.w3.exchange.binance.impl.BIWebsocketClientImpl;

public final class SymbolTicker {
    private SymbolTicker() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.symbolTicker("btcusdt", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
