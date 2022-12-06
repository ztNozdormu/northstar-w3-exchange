package com.w3.exchange.binance.examples.margin;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class GetIsolatedTransfer {
    private GetIsolatedTransfer() {
    }

    private static final Logger logger = LoggerFactory.getLogger(GetIsolatedTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol", "BNBUSDT");

        String result = client.createMargin().getIsolatedTransfer(parameters);
        logger.info(result);
    }
}
