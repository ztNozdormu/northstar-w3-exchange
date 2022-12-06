package com.w3.exchange.binance.examples.portfoliomargin;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class Repay {
    private Repay() {
    }

    private static final Logger logger = LoggerFactory.getLogger(GetAccount.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createPortfolioMargin().repay(parameters);
        logger.info(result);
    }
}
