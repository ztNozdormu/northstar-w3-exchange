package com.w3.exchange.binance.examples.market;

import com.w3.exchange.binance.impl.BISpotClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerTime {
    private ServerTime() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ServerTime.class);
    public static void main(String[] args) {

        BISpotClientImpl client = new BISpotClientImpl();
        String result = client.createMarket().time();
        logger.info(result);
    }
}
