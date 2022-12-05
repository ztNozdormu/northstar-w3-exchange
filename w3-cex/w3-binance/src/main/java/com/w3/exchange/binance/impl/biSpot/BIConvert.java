package com.w3.exchange.binance.impl.biSpot;


import com.w3.exchange.common.domain.Convert;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.ParameterChecker;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Convert Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#convert-endpoints">Convert Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class BIConvert extends Convert {

    public BIConvert(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String TRADE_FLOW = "/sapi/v1/convert/tradeFlow";
    /**
     * GET /sapi/v1/convert/tradeFlow
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * startTime -- mandatory/long <br>
     * endTime -- mandatory/long <br>
     * limit -- optional/int -- Default 100, Max 1000 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#get-convert-trade-history-user_data">
     *     https://binance-docs.github.io/apidocs/spot/en/#get-convert-trade-history-user_data</a>
     */
    public String tradeFlow(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "startTime", Long.class);
        ParameterChecker.checkParameter(parameters, "endTime", Long.class);
        return requestHandler.sendSignedRequest(baseUrl, TRADE_FLOW, parameters, HttpMethod.GET, showLimitUsage);
    }
}
