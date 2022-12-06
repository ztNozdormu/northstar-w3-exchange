package com.w3.exchange.binance.examples.subaccount;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class ManagedSubWithdraw {
    private ManagedSubWithdraw() {
    }
    private static final double amount = 0.01;

    private static final Logger logger = LoggerFactory.getLogger(ManagedSubWithdraw.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("fromEmail", "");
        parameters.put("asset", "USDT");
        parameters.put("amount", amount);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().managedSubWithdraw(parameters);
        logger.info(result);
    }
}
