package io.github.ztnozdormu.binance.examples.wallet;

import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SystemStatus {
    private SystemStatus() {
    }

    private static final Logger logger = LoggerFactory.getLogger(SystemStatus.class);
    public static void main(String[] args) {

        BISpotClientImpl client = new BISpotClientImpl();
        String result = client.createWallet().systemStatus();
        logger.info(result);
    }
}
