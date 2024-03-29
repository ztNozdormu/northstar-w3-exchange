package io.github.ztnozdormu.binance.impl.biSpot;


import io.github.ztnozdormu.common.domain.Fiat;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.utils.ParameterChecker;
import io.github.ztnozdormu.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Fiat Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#fiat-endpoints">Fiat Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class BIFiat extends Fiat {

    public BIFiat(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String ORDERS = "/sapi/v1/fiat/orders";
    /**
     * GET /sapi/v1/fiat/orders
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * transactionType -- mandatory/string -- 0-deposit,1-withdraw <br>
     * beginTime -- optional/long <br>
     * endTime -- optional/long <br>
     * page -- optional/int -- default 1 <br>
     * rows -- optional/int -- default 100, max 500 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#get-fiat-deposit-withdraw-history-user_data">
     *     https://binance-docs.github.io/apidocs/spot/en/#get-fiat-deposit-withdraw-history-user_data</a>
     */
    public String orders(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "transactionType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String PAYMENTS = "/sapi/v1/fiat/payments";
    /**
     * GET /sapi/v1/fiat/payments
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * transactionType -- mandatory/string -- 0-buy,1-sell <br>
     * beginTime -- optional/long <br>
     * endTime -- optional/long <br>
     * page -- optional/int -- default 1 <br>
     * rows -- optional/int -- default 100, max 500 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#get-fiat-payments-history-user_data">
     *     https://binance-docs.github.io/apidocs/spot/en/#get-fiat-payments-history-user_data</a>
     */
    public String payments(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "transactionType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, PAYMENTS, parameters, HttpMethod.GET, showLimitUsage);
    }
}
