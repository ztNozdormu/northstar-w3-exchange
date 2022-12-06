package com.w3.exchange.binance.examples.market;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.impl.biSpot.BIMarket;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class Ticker {
    private Ticker() {
    }

    private static final Logger logger = LoggerFactory.getLogger(Ticker.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        BIMarket market = client.createMarket();

        parameters.put("symbol", "BNBUSDT");
        parameters.put("type", "MINI");
        String result = market.ticker(parameters);
        logger.info(result);
        parameters.clear();

        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        symbols.add("BNBUSDT");
        parameters.put("symbols", symbols);
        result = market.ticker(parameters);
        logger.info(result);
    }
}
