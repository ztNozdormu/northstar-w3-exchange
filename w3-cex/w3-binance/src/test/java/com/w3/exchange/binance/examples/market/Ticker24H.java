package com.w3.exchange.binance.examples.market;

import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class Ticker24H {
    private Ticker24H() {
    }

    private static final Logger logger = LoggerFactory.getLogger(Ticker24H.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol", "BNBUSDT");
        parameters.put("type", "MINI");
        String result = client.createMarket().ticker24H(parameters);
        logger.info(result);
        parameters.clear();

        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        symbols.add("BNBUSDT");
        parameters.put("symbols", symbols);
        result = client.createMarket().ticker24H(parameters);
        logger.info(result);

        BISpotClientImpl clientNoKey = new BISpotClientImpl(PrivateConfig.PROD_URL_1);
        LinkedHashMap<String,Object> parameters1 = new LinkedHashMap<>();
        ArrayList<String> symbols1 = new ArrayList<>();
        symbols1.add("BTCUSDT");
        symbols1.add("BNBUSDT");
        parameters1.put("symbols", symbols1);
        result = clientNoKey.createMarket().ticker24H(parameters1);
        logger.info(result);
    }
}
