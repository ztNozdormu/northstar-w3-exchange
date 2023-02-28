package io.github.ztnozdormu.binance.examples.subaccount;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class SubAccountFuturesAccount {
    private SubAccountFuturesAccount() {
    }

    private static final Logger logger = LoggerFactory.getLogger(SubAccountFuturesAccount.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().futuresAccount(parameters);
        logger.info(result);
    }
}
