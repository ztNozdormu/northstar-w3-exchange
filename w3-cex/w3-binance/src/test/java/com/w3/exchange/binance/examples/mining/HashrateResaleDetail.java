package com.w3.exchange.binance.examples.mining;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class HashrateResaleDetail {
    private HashrateResaleDetail() {
    }
    private static final int configId = 123;

    private static final Logger logger = LoggerFactory.getLogger(HashrateResaleDetail.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", configId);
        parameters.put("userName", "");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMining().hashrateResaleDetail(parameters);
        logger.info(result);
    }
}
