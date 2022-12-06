package com.w3.exchange.binance.examples.blvt;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class Subscribe {
    private Subscribe() {
    }
    private static final double cost = 0.01;

    private static final Logger logger = LoggerFactory.getLogger(Subscribe.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("tokenName", "BTCDOWN");
        parameters.put("cost", cost);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBlvt().subscribe(parameters);
        logger.info(result);
    }
}
