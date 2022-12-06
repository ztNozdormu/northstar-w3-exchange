package com.w3.exchange.binance.examples.wallet;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class DustTransfer {
    private DustTransfer() {
    }

    private static final Logger logger = LoggerFactory.getLogger(DustTransfer.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        ArrayList<String> assets = new ArrayList<>();
        assets.add("CHR");
        assets.add("CTSI");
        parameters.put("asset", assets);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createWallet().dustTransfer(parameters);
        logger.info(result);
    }
}
