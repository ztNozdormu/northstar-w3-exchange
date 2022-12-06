package com.w3.exchange.binance.examples.nft;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class TransactionsHistory {
    private TransactionsHistory() {
    }
    private static final int orderType = 0;

    private static final Logger logger = LoggerFactory.getLogger(TransactionsHistory.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("orderType", orderType);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createNFT().transactionsHistory(parameters);
        logger.info(result);
    }
}
