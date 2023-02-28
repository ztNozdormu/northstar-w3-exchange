package io.github.ztnozdormu.binance.examples.wallet;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class UniversalTransfer {
    private UniversalTransfer() {
    }
    private static final double amount = 0.001;

    private static final Logger logger = LoggerFactory.getLogger(UniversalTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "MAIN_MARGIN");
        parameters.put("asset", "USDT");
        parameters.put("amount", amount);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createWallet().universalTransfer(parameters);
        logger.info(result);
    }
}
