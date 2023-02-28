package io.github.ztnozdormu.binance.examples.margin;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CancelMarginOcoOrder {
    private CancelMarginOcoOrder() {
    }

    private static final Logger logger = LoggerFactory.getLogger(CancelMarginOcoOrder.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol", "BTCUSDT");
        parameters.put("orderListId", "");

        String result = client.createMargin().cancelOcoOrder(parameters);
        logger.info(result);
    }
}
