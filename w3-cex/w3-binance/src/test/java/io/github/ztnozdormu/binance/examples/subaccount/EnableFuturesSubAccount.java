package io.github.ztnozdormu.binance.examples.subaccount;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class EnableFuturesSubAccount {
    private EnableFuturesSubAccount() {
    }

    private static final Logger logger = LoggerFactory.getLogger(EnableFuturesSubAccount.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "another_virtual@q6c1dsmxnoemail.com");

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createSubAccount().enableFutures(parameters);
        logger.info(result);
    }
}
