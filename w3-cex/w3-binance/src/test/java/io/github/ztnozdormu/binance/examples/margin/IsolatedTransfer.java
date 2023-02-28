package io.github.ztnozdormu.binance.examples.margin;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class IsolatedTransfer {
    private IsolatedTransfer() {
    }
    private static final double amount = 1;

    private static final Logger logger = LoggerFactory.getLogger(IsolatedTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol", "BNBUSDT");
        parameters.put("asset", "USDT");
        parameters.put("transFrom", "SPOT");
        parameters.put("transTo", "ISOLATED_MARGIN");
        parameters.put("amount", amount);

        String result = client.createMargin().isolatedTransfer(parameters);
        logger.info(result);
    }
}
