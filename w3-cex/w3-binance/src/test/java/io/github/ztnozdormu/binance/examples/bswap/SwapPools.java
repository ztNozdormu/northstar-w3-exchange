package io.github.ztnozdormu.binance.examples.bswap;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SwapPools {
    private SwapPools() {
    }

    private static final Logger logger = LoggerFactory.getLogger(SwapPools.class);
    public static void main(String[] args) {
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBswap().swapPools();
        logger.info(result);
    }
}
