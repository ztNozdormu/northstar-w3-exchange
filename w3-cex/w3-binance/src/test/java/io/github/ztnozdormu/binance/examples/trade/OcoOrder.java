package io.github.ztnozdormu.binance.examples.trade;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.common.exceptions.ExchangeClientException;
import io.github.ztnozdormu.common.exceptions.ExchangeConnectorException;
import io.github.ztnozdormu.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class OcoOrder {
    private OcoOrder() {
    }
    private static final double quantity = 0.01;
    private static final double price = 95000;
    private static final double stopPrice = 98000;

    private static final Logger logger = LoggerFactory.getLogger(OcoOrder.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, PrivateConfig.BASE_URL);

        parameters.put("symbol", "BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("stopPrice", stopPrice);
        parameters.put("quantity", quantity);
        parameters.put("price", price);

        try {
            String result = client.createTrade().ocoOrder(parameters);
            logger.info(result);
        } catch (ExchangeConnectorException e) {
            logger.error("fullErrMessage: {}", e.getMessage(), e);
        } catch (ExchangeClientException e) {
            logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
        }
    }
}
