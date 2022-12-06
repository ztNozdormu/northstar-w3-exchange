package com.w3.exchange.binance.examples.staking;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class ProductList {
    private ProductList() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductList.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("product", "STAKING");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createStaking().productList(parameters);
        logger.info(result);
    }
}
