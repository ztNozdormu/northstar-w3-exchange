package com.w3.exchange.binance.examples.market;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.impl.biSpot.BIMarket;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class ExchangeInfo {
    private ExchangeInfo() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ExchangeInfo.class);
    public static void main(String[] args) {

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY,PrivateConfig.SECRET_KEY,PrivateConfig.PROD_URL_3);
        BIMarket market = client.createMarket();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        String result = market.exchangeInfo(parameters);
        logger.info(result);

        parameters.put("symbol", "BTCUSDT");
        result = market.exchangeInfo(parameters);
        logger.info(result);
        logger.info("24小时线:"+market.ticker24H(parameters));
        parameters.clear();

        ArrayList<String> symbols = new ArrayList<>();
        symbols.add("BTCUSDT");
        symbols.add("BNBUSDT");
        parameters.put("symbols", symbols);
        result = market.exchangeInfo(parameters);
        logger.info(result);
        parameters.clear();

//        ArrayList<String> permissions = new ArrayList<>();
//        permissions.add("SPOT");
//        permissions.add("MARGIN");
//        parameters.put("permissions", permissions);
//        result = market.exchangeInfo(parameters);
//        logger.info(result);

    }

}
