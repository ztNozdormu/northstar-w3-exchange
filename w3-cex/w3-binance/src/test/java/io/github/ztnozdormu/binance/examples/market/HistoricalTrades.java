package io.github.ztnozdormu.binance.examples.market;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class HistoricalTrades {
    private HistoricalTrades() {
    }

    private static final Logger logger = LoggerFactory.getLogger(HistoricalTrades.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, null);

        parameters.put("symbol", "BTCUSDT");
        parameters.put("limit", "10");
        String result = client.createMarket().historicalTrades(parameters);
        logger.info(result);
    }
}
