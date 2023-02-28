package io.github.ztnozdormu.binance.examples.userdata;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class ExtendMarginListenKey {
    private ExtendMarginListenKey() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ExtendMarginListenKey.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("listenKey", "");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        String result = client.createUserData().extendMarginListenKey(parameters);
        logger.info(result);
    }
}
