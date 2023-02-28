package io.github.ztnozdormu.binance.examples.mining;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CancelHashrateResaleConfig {
    private CancelHashrateResaleConfig() {
    }
    private static final int configId = 123;

    private static final Logger logger = LoggerFactory.getLogger(CancelHashrateResaleConfig.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", configId);
        parameters.put("userName", "");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMining().cancelHashrateResaleConfig(parameters);
        logger.info(result);
    }
}
