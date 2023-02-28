package io.github.ztnozdormu.okx.examples.websocket;


import io.github.ztnozdormu.okx.impl.OKXWebsocketClientImpl;

public final class KlineStream {
    private KlineStream() {
    }

    public static void main(String[] args) {
        OKXWebsocketClientImpl client = new OKXWebsocketClientImpl();
        client.klineStream("BTC-USDT-SWAP", "MIN1", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));

    }
}
