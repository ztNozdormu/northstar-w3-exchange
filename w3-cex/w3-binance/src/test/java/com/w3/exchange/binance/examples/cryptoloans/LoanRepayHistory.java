package com.w3.exchange.binance.examples.cryptoloans;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class LoanRepayHistory {

    private LoanRepayHistory() {
    }

    private static final long orderId = 100000001;
    private static final Logger logger = LoggerFactory.getLogger(LoanRepayHistory.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderId", orderId);
        parameters.put("loanCoin", "BUSD");
        
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createCryptoLoans().loanRepayHistory(parameters);
        logger.info(result);
    }
}
