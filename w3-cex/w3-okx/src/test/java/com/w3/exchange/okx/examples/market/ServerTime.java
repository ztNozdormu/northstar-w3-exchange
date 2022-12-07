package com.w3.exchange.okx.examples.market;

import com.w3.exchange.okx.impl.OKXSpotClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerTime {
    private ServerTime() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ServerTime.class);
    public static void main(String[] args) {

        OKXSpotClientImpl client = new OKXSpotClientImpl();
        String result = client.createMarket().time();
        logger.info(result);
    }
}
