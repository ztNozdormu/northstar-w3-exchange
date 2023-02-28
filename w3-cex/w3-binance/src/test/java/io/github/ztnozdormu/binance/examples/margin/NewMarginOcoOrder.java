package io.github.ztnozdormu.binance.examples.margin;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class NewMarginOcoOrder {
    private NewMarginOcoOrder() {
    }
    private static final double quantity = 0.01;
    private static final double price = 95000;
    private static final double stopPrice = 98000;

    private static final Logger logger = LoggerFactory.getLogger(NewMarginOcoOrder.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("stopPrice", stopPrice);
        parameters.put("quantity", quantity);
        parameters.put("price", price);

        String result = client.createMargin().ocoOrder(parameters);
        logger.info(result);
    }
}
