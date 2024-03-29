package io.github.ztnozdormu.binance.examples.c2c;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class ListUserOrderHistory {
    private ListUserOrderHistory() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ListUserOrderHistory.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("tradeType", "BUY");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createC2C().listUserOrderHistory(parameters);
        logger.info(result);
    }
}
