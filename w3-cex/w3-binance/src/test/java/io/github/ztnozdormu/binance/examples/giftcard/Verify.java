package io.github.ztnozdormu.binance.examples.giftcard;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class Verify {
    private Verify() {
    }

    private static final Logger logger = LoggerFactory.getLogger(Verify.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("referenceNo", "");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createGiftCard().verify(parameters);
        logger.info(result);
    }
}
