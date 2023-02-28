package io.github.ztnozdormu.okx.examples.websocket;


import io.github.ztnozdormu.okx.impl.OKXWebsocketClientImpl;

public final class SymbolTicker {
    private SymbolTicker() {
    }

    public static void main(String[] args) {
        OKXWebsocketClientImpl client = new OKXWebsocketClientImpl();
        client.symbolTicker("BTC-USDT-SWAP", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
