package io.github.ztnozdormu.binance.examples.websocket;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.impl.BIWebsocketClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import io.github.ztnozdormu.common.enums.DefaultUrls;
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
