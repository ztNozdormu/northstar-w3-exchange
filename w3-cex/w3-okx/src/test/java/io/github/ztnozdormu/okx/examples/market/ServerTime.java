package io.github.ztnozdormu.okx.examples.market;

import io.github.ztnozdormu.okx.impl.OKXSpotClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerTime {
    private ServerTime() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ServerTime.class);
    public static void main(String[] args) {

        OKXSpotClientImpl client = OKXSpotClientImpl.builder().baseUrl("https://www.okx.com").build();
        String result = client.createMarket().time();
        logger.info(result);
    }
}
