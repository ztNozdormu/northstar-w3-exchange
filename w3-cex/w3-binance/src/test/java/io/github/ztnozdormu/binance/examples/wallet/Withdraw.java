package io.github.ztnozdormu.binance.examples.wallet;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class Withdraw {
    private Withdraw() {
    }

    private static final Logger logger = LoggerFactory.getLogger(Withdraw.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BNBUSDT");
        parameters.put("address", "");
        parameters.put("amount", "0.001");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createWallet().withdraw(parameters);
        logger.info(result);
    }
}
