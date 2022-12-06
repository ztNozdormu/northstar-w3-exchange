package com.w3.exchange.binance.examples.market;

import com.w3.exchange.binance.impl.BISpotClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class AveragePrice {
    private AveragePrice() {
    }

    private static final Logger logger = LoggerFactory.getLogger(AveragePrice.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl();

        parameters.put("symbol", "BNBUSDT");
        String result = client.createMarket().averagePrice(parameters);
        logger.info(result);
    }
}
