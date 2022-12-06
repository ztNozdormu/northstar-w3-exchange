package com.w3.exchange.binance.examples.bswap;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class ClaimedHistory {
    private ClaimedHistory() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ClaimedHistory.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBswap().claimedHistory(parameters);
        logger.info(result);
    }
}
