package io.github.ztnozdormu.binance.examples.futures;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class AdjustCollateral {
    private AdjustCollateral() {
    }
    private static final double amount = 2;

    private static final Logger logger = LoggerFactory.getLogger(AdjustCollateral.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "USDT");
        parameters.put("collateralCoin", "BUSD");
        parameters.put("amount", amount);
        parameters.put("direction", "ADDITIONAL");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createFutures().adjustCollateral(parameters);
        logger.info(result);
    }
}
