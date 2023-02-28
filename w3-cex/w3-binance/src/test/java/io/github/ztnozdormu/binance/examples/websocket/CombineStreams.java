package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;

import java.util.ArrayList;

public final class CombineStreams {
    private CombineStreams() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        ArrayList<String> streams = new ArrayList<>();
        streams.add("btcusdt@trade");
        streams.add("bnbusdt@trade");

        client.combineStreams(streams, ((event) -> {
            System.out.println(event);
        }));

        client.closeAllConnections();
    }
}
