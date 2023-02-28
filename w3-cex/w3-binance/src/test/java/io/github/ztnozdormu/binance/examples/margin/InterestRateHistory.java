package io.github.ztnozdormu.binance.examples.margin;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class InterestRateHistory {
    private InterestRateHistory() {
    }

    private static final Logger logger = LoggerFactory.getLogger(InterestRateHistory.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "USDT");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().interestRateHistory(parameters);
        logger.info(result);
    }
}
