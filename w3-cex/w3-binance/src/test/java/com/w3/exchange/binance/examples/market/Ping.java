package com.w3.exchange.binance.examples.market;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ping {
    private Ping() {
    }

    private static final Logger logger = LoggerFactory.getLogger(Ping.class);
    public static void main(String[] args) {

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMarket().ping();
        logger.info(result);
    }
}
