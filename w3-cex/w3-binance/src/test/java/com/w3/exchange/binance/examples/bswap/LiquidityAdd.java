package com.w3.exchange.binance.examples.bswap;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class LiquidityAdd {
    private LiquidityAdd() {
    }
    private static final long poolId = 32L;
    private static final double quantity = 0.1;

    private static final Logger logger = LoggerFactory.getLogger(LiquidityAdd.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("poolId", poolId);
        parameters.put("asset", "USDT");
        parameters.put("quantity", quantity);


        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBswap().liquidityAdd(parameters);
        logger.info(result);
    }
}
