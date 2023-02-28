package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

public final class RollingWindowTicker {
    private RollingWindowTicker() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.rollingWindowTicker("btcusdt", "1h", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
