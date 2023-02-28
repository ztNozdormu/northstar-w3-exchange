package io.github.ztnozdormu.binance.examples.futures;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CollateralRepayResult {
    private CollateralRepayResult() {
    }

    private static final Logger logger = LoggerFactory.getLogger(CollateralRepayResult.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("quoteId", "test");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createFutures().collateralRepayResult(parameters);
        logger.info(result);
    }
}
