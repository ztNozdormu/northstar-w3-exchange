package io.github.ztnozdormu.binance.examples.cryptoloans;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class LoanAdjustLTV {

    private LoanAdjustLTV() {
    }

    private static final long orderId = 100000001;
    private static final double amount = 0.01;
    private static final Logger logger = LoggerFactory.getLogger(LoanAdjustLTV.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", orderId);
        parameters.put("amount", amount);
        
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createCryptoLoans().loanAdjustLTV(parameters);
        logger.info(result);
    }
}