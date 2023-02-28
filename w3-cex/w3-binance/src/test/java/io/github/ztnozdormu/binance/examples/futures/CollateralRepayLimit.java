package io.github.ztnozdormu.binance.examples.futures;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CollateralRepayLimit {
    private CollateralRepayLimit() {
    }

    private static final Logger logger = LoggerFactory.getLogger(CollateralRepayLimit.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "USDT");
        parameters.put("collateralCoin", "BUSD");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createFutures().collateralRepayLimit(parameters);
        logger.info(result);
    }
}
