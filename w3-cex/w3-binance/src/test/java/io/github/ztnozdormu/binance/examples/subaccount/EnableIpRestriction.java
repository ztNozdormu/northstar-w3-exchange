package io.github.ztnozdormu.binance.examples.subaccount;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class EnableIpRestriction {
    private EnableIpRestriction() {
    }

    private static final Logger logger = LoggerFactory.getLogger(EnableIpRestriction.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "");
        parameters.put("subAccountApiKey", "");
        parameters.put("ipRestrict", true);

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().enableIpRestriction(parameters);
        logger.info(result);
    }
}
