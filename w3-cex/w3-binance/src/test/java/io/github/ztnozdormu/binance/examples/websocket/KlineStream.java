package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

public final class KlineStream {
    private KlineStream() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.klineStream("btcusdt", "1h", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
