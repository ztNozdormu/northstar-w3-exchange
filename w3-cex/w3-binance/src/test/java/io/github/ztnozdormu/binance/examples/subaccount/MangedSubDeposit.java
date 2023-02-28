package io.github.ztnozdormu.binance.examples.subaccount;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class MangedSubDeposit {
    private MangedSubDeposit() {
    }
    private static final double amount = 0.01;

    private static final Logger logger = LoggerFactory.getLogger(MangedSubDeposit.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "");
        parameters.put("asset", "USDT");
        parameters.put("amount", amount);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().managedSubDeposit(parameters);
        logger.info(result);
    }
}
