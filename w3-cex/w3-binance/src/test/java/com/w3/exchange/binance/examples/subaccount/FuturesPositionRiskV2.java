package com.w3.exchange.binance.examples.subaccount;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class FuturesPositionRiskV2 {
    private FuturesPositionRiskV2() {
    }
    private static final int futuresType = 1;

    private static final Logger logger = LoggerFactory.getLogger(FuturesPositionRiskV2.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "");
        parameters.put("futuresType", futuresType);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().futuresPositionRiskV2(parameters);
        logger.info(result);
    }
}
