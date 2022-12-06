package com.w3.exchange.binance.examples.trade;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.common.exceptions.ExchangeClientException;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import com.w3.exchange.binance.examples.PrivateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;

public final class GetOcoOrders {
    private GetOcoOrders() {
    }
    private static final int fromId = 1;
    private static final int limit = 10;

    private static final Logger logger = LoggerFactory.getLogger(GetOcoOrders.class);
    public static void main(String[] args) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        BISpotClientImpl client = new BISpotClientImpl(PrivateConfig.TESTNET_API_KEY, PrivateConfig.TESTNET_SECRET_KEY, PrivateConfig.BASE_URL);

        parameters.put("fromId", fromId);
        parameters.put("limit", limit);

        try {
            String result = client.createTrade().getOCOOrders(parameters);
            logger.info(result);
        } catch (ExchangeConnectorException e) {
            logger.error("fullErrMessage: {}", e.getMessage(), e);
        } catch (ExchangeClientException e) {
            logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
                    e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
        }
    }
}
