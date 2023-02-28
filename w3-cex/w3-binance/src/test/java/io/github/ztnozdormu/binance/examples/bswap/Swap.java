package io.github.ztnozdormu.binance.examples.bswap;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class Swap {
    private Swap() {
    }
    private static final double quoteQty = 2;

    private static final Logger logger = LoggerFactory.getLogger(Swap.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("quoteAsset", "USDT");
        parameters.put("baseAsset", "BUSD");
        parameters.put("quoteQty", quoteQty);


        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBswap().swap(parameters);
        logger.info(result);
    }
}
