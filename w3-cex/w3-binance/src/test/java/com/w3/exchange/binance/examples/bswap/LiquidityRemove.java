package com.w3.exchange.binance.examples.bswap;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class LiquidityRemove {
    private LiquidityRemove() {
    }
    private static final long poolId = 32L;
    private static final double shareAmount = 0.1;

    private static final Logger logger = LoggerFactory.getLogger(LiquidityRemove.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("poolId", poolId);
        parameters.put("type", "COMBINATION");
        parameters.put("shareAmount", shareAmount);


        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBswap().liquidityRemove(parameters);
        logger.info(result);
    }
}
