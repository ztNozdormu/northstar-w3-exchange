package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

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
