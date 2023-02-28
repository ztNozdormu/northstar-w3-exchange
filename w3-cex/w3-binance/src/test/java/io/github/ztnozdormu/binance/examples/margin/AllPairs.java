package io.github.ztnozdormu.binance.examples.margin;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AllPairs {
    private AllPairs() {
    }

    private static final Logger logger = LoggerFactory.getLogger(AllPairs.class);
    public static void main(String[] args) {
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createMargin().allPairs();
        logger.info(result);
    }
}
