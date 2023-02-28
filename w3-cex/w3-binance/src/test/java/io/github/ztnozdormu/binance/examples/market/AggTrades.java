package io.github.ztnozdormu.binance.examples.market;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class AggTrades {
    private AggTrades() {
    }

    private static final Logger logger = LoggerFactory.getLogger(AggTrades.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol", "BNBUSDT");
        String result = client.createMarket().aggTrades(parameters);
        logger.info(result);
    }
}
