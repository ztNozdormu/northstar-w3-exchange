package io.github.ztnozdormu.binance.examples.userdata;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CreateIsolatedMarginListenKey {
    private CreateIsolatedMarginListenKey() {
    }

    private static final Logger logger = LoggerFactory.getLogger(CreateIsolatedMarginListenKey.class);
    public static void main(String[] args) {
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BTCUSDT");

        String result = client.createUserData().createIsolatedMarginListenKey(parameters);
        logger.info(result);
    }
}
