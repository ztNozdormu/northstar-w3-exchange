package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

public final class MiniTickerStream {
    private MiniTickerStream() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.miniTickerStream("btcusdt", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
