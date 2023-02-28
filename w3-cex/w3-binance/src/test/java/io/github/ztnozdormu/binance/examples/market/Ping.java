package io.github.ztnozdormu.binance.examples.market;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Ping {
    private Ping() {
    }

    private static final Logger logger = LoggerFactory.getLogger(Ping.class);
    public static void main(String[] args) {

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMarket().ping();
        logger.info(result);
    }
}
