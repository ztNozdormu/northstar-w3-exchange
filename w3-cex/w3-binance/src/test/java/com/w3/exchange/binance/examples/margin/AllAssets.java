package com.w3.exchange.binance.examples.margin;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AllAssets {
    private AllAssets() {
    }

    private static final Logger logger = LoggerFactory.getLogger(AllAssets.class);
    public static void main(String[] args) {
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,PrivateConfig.BASE_URL);
        String result = client.createMargin().allAssets();
        logger.info(result);
    }
}
