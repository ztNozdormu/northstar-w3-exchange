package io.github.ztnozdormu.binance.examples.bswap;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class RemoveLiquidityPreview {
    private RemoveLiquidityPreview() {
    }
    private static final long poolId = 2L;
    private static final double shareAmount = 10000;

    private static final Logger logger = LoggerFactory.getLogger(RemoveLiquidityPreview.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("poolId", poolId);
        parameters.put("type", "COMBINATION");
        parameters.put("quoteAsset", "USDT");
        parameters.put("shareAmount", shareAmount);


        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);
        String result = client.createBswap().removeLiquidityPreview(parameters);
        logger.info(result);
    }
}
