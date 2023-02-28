package io.github.ztnozdormu.binance.examples.subaccount;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class SubAccountFuturesInternalTransfer {
    private SubAccountFuturesInternalTransfer() {
    }
    private static final double amount = 0.01;
    private static final int futuresType = 1;

    private static final Logger logger = LoggerFactory.getLogger(SubAccountFuturesInternalTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("fromEmail", "");
        parameters.put("toEmail", "");
        parameters.put("futuresType", futuresType);
        parameters.put("asset", "USDT");
        parameters.put("amount", amount);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().futuresInternalTransfer(parameters);
        logger.info(result);
    }
}
