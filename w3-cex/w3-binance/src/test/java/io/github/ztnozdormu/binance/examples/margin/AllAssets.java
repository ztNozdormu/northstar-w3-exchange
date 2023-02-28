package io.github.ztnozdormu.binance.examples.margin;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AllAssets {
    private AllAssets() {
    }

    private static final Logger logger = LoggerFactory.getLogger(AllAssets.class);
    public static void main(String[] args) {
        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,PrivateConfig.BASE_URL);
        String result = client.createMargin().allAssets();
        logger.info(result);
    }
}
