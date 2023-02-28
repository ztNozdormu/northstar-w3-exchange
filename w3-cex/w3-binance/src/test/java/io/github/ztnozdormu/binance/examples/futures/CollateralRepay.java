package io.github.ztnozdormu.binance.examples.futures;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CollateralRepay {
    private CollateralRepay() {
    }

    private static final Logger logger = LoggerFactory.getLogger(CollateralRepay.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("quoteId", "test");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createFutures().collateralRepay(parameters);
        logger.info(result);
    }
}
