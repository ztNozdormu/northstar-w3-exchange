package com.w3.exchange.binance.examples.margin;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class GetMarginOpenOrders {
    private GetMarginOpenOrders() {
    }

    private static final Logger logger = LoggerFactory.getLogger(GetMarginOpenOrders.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().getOpenOrders(parameters);
        logger.info(result);
    }
}
