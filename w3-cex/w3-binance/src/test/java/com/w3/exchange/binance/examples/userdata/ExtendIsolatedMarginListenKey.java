package com.w3.exchange.binance.examples.userdata;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class ExtendIsolatedMarginListenKey {
    private ExtendIsolatedMarginListenKey() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ExtendIsolatedMarginListenKey.class);
    public static void main(String[] args) {
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");
        parameters.put("listenKey", "");

        String result = client.createUserData().extendIsolatedMarginListenKey(parameters);
        logger.info(result);
    }
}
