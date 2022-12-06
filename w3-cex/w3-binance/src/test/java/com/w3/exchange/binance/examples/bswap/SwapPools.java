package com.w3.exchange.binance.examples.bswap;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SwapPools {
    private SwapPools() {
    }

    private static final Logger logger = LoggerFactory.getLogger(SwapPools.class);
    public static void main(String[] args) {
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBswap().swapPools();
        logger.info(result);
    }
}
