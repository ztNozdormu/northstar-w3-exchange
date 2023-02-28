package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

public final class AllTickerStream {
    private AllTickerStream() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        client.allTickerStream(((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
