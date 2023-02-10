package com.w3.exchange.okx.examples.websocket;


import com.w3.exchange.okx.impl.OKXWebsocketClientImpl;

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
