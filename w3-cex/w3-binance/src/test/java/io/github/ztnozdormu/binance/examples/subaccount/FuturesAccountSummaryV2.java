package io.github.ztnozdormu.binance.examples.subaccount;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class FuturesAccountSummaryV2 {
    private FuturesAccountSummaryV2() {
    }
    private static final int futuresType = 1;

    private static final Logger logger = LoggerFactory.getLogger(FuturesAccountSummaryV2.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("futuresType", futuresType);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().futuresAccountSummaryV2(parameters);
        logger.info(result);
    }
}
