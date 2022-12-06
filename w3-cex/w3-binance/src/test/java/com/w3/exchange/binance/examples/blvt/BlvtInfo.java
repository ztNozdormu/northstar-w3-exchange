package com.w3.exchange.binance.examples.blvt;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class BlvtInfo {
    private BlvtInfo() {
    }

    private static final Logger logger = LoggerFactory.getLogger(BlvtInfo.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBlvt().blvtInfo(parameters);
        logger.info(result);
    }
}
