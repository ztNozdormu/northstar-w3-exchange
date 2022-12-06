package com.w3.exchange.binance.examples.websocket;


import com.w3.exchange.binance.impl.BIWebsocketClientImpl;

public final class AggTradeStream {
    private AggTradeStream() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl client = new BIWebsocketClientImpl();
        int streamId1 = client.aggTradeStream("btcusdt", ((event) -> {
            System.out.println(event);
        }));
        int streamId2 = client.aggTradeStream("ethusdt", ((event) -> {
            System.out.println(event);
        }));
        client.closeConnection(streamId1);
        client.closeConnection(streamId2);
    }
}
