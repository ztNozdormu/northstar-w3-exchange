package com.w3.exchange.binance.impl.biSpot;

import com.w3.exchange.common.domain.Pay;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Pay Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#pay-endpoints">Pay Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class BIPay extends Pay {

    public BIPay(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String TRANSACTIONS = "/sapi/v1/pay/transactions";
    /**
     * GET /sapi/v1/pay/transactions
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * startTime -- optional/long <br>
     * endTime -- optional/long <br>
     * limit -- optional/int -- default 100, max 100 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#get-pay-trade-history-user_data">
     *     https://binance-docs.github.io/apidocs/spot/en/#get-pay-trade-history-user_data</a>
     */
    public String transactions(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, TRANSACTIONS, parameters, HttpMethod.GET, showLimitUsage);
    }
}
