package com.w3.exchange.binance.examples.savings;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class PurchaseProject {
    private PurchaseProject() {
    }
    private static final long lot = 1L;

    private static final Logger logger = LoggerFactory.getLogger(PurchaseProject.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("projectId", "TKO001");
        parameters.put("lot", lot);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSavings().purchaseProject(parameters);
        logger.info(result);
    }
}
