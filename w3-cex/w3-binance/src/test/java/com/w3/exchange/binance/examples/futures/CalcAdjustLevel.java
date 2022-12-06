package com.w3.exchange.binance.examples.futures;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CalcAdjustLevel {
    private CalcAdjustLevel() {
    }
    private static final double amount = 2;

    private static final Logger logger = LoggerFactory.getLogger(CalcAdjustLevel.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("collateralCoin", "BUSD");
        parameters.put("amount", amount);
        parameters.put("direction", "ADDITIONAL");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createFutures().calcAdjustLevel(parameters);
        logger.info(result);
    }
}
