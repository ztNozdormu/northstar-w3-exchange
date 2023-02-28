package io.github.ztnozdormu.binance.examples.wallet;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class QueryUniversalTransfer {
    private QueryUniversalTransfer() {
    }

    private static final Logger logger = LoggerFactory.getLogger(QueryUniversalTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_MARGIN");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createWallet().queryUniversalTransfer(parameters);
        logger.info(result);
    }
}
