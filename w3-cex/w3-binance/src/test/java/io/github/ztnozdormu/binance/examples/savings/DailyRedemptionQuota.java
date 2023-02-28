package io.github.ztnozdormu.binance.examples.savings;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class DailyRedemptionQuota {
    private DailyRedemptionQuota() {
    }

    private static final Logger logger = LoggerFactory.getLogger(DailyRedemptionQuota.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "TKO001");
        parameters.put("type", "FAST");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSavings().dailyRedemptionQuota(parameters);
        logger.info(result);
    }
}
