package io.github.ztnozdormu.binance.examples.giftcard;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CreateCode {
    private CreateCode() {
    }
    private static final double amount = 0.01;

    private static final Logger logger = LoggerFactory.getLogger(CreateCode.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("token", "");
        parameters.put("amount", amount);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createGiftCard().createCode(parameters);
        logger.info(result);
    }
}
