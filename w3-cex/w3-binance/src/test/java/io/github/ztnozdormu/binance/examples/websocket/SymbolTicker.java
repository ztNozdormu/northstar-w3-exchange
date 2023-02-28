package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

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
