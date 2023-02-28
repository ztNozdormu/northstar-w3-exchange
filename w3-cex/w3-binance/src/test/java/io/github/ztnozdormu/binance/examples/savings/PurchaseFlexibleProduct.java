package io.github.ztnozdormu.binance.examples.savings;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class PurchaseFlexibleProduct {
    private PurchaseFlexibleProduct() {
    }
    private static final double amount = 0.01;

    private static final Logger logger = LoggerFactory.getLogger(PurchaseFlexibleProduct.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("productId", "TKO001");
        parameters.put("amount", amount);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSavings().purchaseFlexibleProduct(parameters);
        logger.info(result);
    }
}
