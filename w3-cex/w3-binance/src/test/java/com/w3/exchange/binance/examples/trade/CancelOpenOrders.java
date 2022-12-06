package com.w3.exchange.binance.examples.trade;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.common.exceptions.ExchangeClientException;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class CancelOpenOrders {
    private CancelOpenOrders() {
    }

    private static final Logger logger = LoggerFactory.getLogger(CancelOpenOrders.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, PrivateConfig.BASE_URL);

        parameters.put("symbol", "BTCUSDT");

        try {
            String result = client.createTrade().cancelOpenOrders(parameters);
            logger.info(result);
        } catch (ExchangeConnectorException e) {
            logger.error("fullErrMessage: {}", e.getMessage(), e);
        } catch (ExchangeClientException e) {
            logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
        }
    }
}
