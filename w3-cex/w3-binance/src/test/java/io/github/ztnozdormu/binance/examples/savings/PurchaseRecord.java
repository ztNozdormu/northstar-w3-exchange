package io.github.ztnozdormu.binance.examples.savings;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class PurchaseRecord {
    private PurchaseRecord() {
    }

    private static final Logger logger = LoggerFactory.getLogger(PurchaseRecord.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("lendingType", "DAILY");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSavings().purchaseRecord(parameters);
        logger.info(result);
    }
}
