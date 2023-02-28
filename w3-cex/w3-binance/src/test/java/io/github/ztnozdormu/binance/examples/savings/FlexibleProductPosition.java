package io.github.ztnozdormu.binance.examples.savings;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class FlexibleProductPosition {
    private FlexibleProductPosition() {
    }

    private static final Logger logger = LoggerFactory.getLogger(FlexibleProductPosition.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSavings().flexibleProductPosition(parameters);
        logger.info(result);
    }
}
