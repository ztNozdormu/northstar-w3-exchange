package io.github.ztnozdormu.binance.examples.bswap;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class Quote {
    private Quote() {
    }
    private static final double quoteQty = 10000;

    private static final Logger logger = LoggerFactory.getLogger(Quote.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("quoteAsset", "USDT");
        parameters.put("baseAsset", "BUSD");
        parameters.put("quoteQty", quoteQty);


        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBswap().quote(parameters);
        logger.info(result);
    }
}
