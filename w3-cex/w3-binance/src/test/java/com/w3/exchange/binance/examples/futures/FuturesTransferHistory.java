package com.w3.exchange.binance.examples.futures;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class FuturesTransferHistory {
    private FuturesTransferHistory() {
    }
    private static final long startTime = 0L;

    private static final Logger logger = LoggerFactory.getLogger(FuturesTransferHistory.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");
        parameters.put("startTime", startTime);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createFutures().futuresTransferHistory(parameters);
        logger.info(result);
    }
}
