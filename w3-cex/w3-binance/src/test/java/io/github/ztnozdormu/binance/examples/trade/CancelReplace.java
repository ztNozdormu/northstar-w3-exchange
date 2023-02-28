package io.github.ztnozdormu.binance.examples.trade;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.common.exceptions.ExchangeClientException;
import io.github.ztnozdormu.common.exceptions.ExchangeConnectorException;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CancelReplace {
    private CancelReplace() {
    }
    private static final double quantity = 0.01;
    private static final double price = 30000;
    private static final long cancelOrderId = 1234567;

    private static final Logger logger = LoggerFactory.getLogger(CancelReplace.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, PrivateConfig.BASE_URL);

        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("type", "LIMIT");
        parameters.put("cancelReplaceMode", "STOP_ON_FAILURE");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", quantity);
        parameters.put("price", price);
        parameters.put("cancelOrderId", cancelOrderId);

        try {
            String result = client.createTrade().cancelReplace(parameters);
            logger.info(result);
        } catch (ExchangeConnectorException e) {
            logger.error("fullErrMessage: {}", e.getMessage(), e);
        } catch (ExchangeClientException e) {
            logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
        }
    }
}
