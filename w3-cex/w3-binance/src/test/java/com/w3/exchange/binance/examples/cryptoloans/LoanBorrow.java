package com.w3.exchange.binance.examples.cryptoloans;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class LoanBorrow {
    private LoanBorrow() {
    }

    private static final int loanTerm = 7;
    private static final double loanAmount = 100.5;
    private static final Logger logger = LoggerFactory.getLogger(LoanBorrow.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BUSD");
        parameters.put("collateralCoin", "BNB");
        parameters.put("loanAmount", loanAmount);
        parameters.put("loanTerm", loanTerm);
        

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createCryptoLoans().loanBorrow(parameters);
        logger.info(result);
    }
}
