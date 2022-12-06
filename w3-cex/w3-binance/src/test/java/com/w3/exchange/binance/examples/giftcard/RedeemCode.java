package com.w3.exchange.binance.examples.giftcard;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class RedeemCode {
    private RedeemCode() {
    }

    private static final Logger logger = LoggerFactory.getLogger(RedeemCode.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("code", "");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createGiftCard().redeemCode(parameters);
        logger.info(result);
    }
}
