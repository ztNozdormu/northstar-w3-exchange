package com.w3.exchange.binance.examples.websocket;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.impl.BIWebsocketClientImpl;
import com.w3.exchange.common.enums.DefaultUrls;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.json.JSONObject;

public final class UserStream {
    private UserStream() {
    }

    public static void main(String[] args) {
        BIWebsocketClientImpl wsClient = new BIWebsocketClientImpl(DefaultUrls.TESTNET_WSS_URL);
        BISpotClientImpl spotClient = new BISpotClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, DefaultUrls.TESTNET_URL);
        JSONObject obj = new JSONObject(spotClient.createUserData().createListenKey());
        String listenKey = obj.getString("listenKey");
        System.out.println("listenKey:" + listenKey);
        wsClient.listenUserStream(listenKey, ((event) -> {
            System.out.println(event);
            wsClient.closeAllConnections();
        }));
    }
}
