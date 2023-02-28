package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

public final class DiffDepthStream {
    private DiffDepthStream() {
    }
    private static final int speed = 100;

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.diffDepthStream("btcusdt", speed, ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
