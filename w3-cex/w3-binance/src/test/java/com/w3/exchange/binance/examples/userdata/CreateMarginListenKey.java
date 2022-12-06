package com.w3.exchange.binance.examples.userdata;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CreateMarginListenKey {
    private CreateMarginListenKey() {
    }

    private static final Logger logger = LoggerFactory.getLogger(CreateMarginListenKey.class);
    public static void main(String[] args) {
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        String result = client.createUserData().createMarginListenKey();
        logger.info(result);
    }
}
