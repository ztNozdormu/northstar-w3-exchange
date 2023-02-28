package io.github.ztnozdormu.binance.impl.biSpot;

import io.github.ztnozdormu.common.domain.Rebate;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Rebate Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#rebate-endpoints">Rebate Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class BIRebate extends Rebate {

    public BIRebate(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String TAX_QUERY = "/sapi/v1/rebate/taxQuery";
    /**
     * GET /sapi/v1/rebate/taxQuery
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * startTime -- optional/long <br>
     * endTime -- optional/long <br>
     * page -- optional/int -- 	Default 1 <br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#get-spot-rebate-history-records-user_data">
     *     https://binance-docs.github.io/apidocs/spot/en/#get-spot-rebate-history-records-user_data</a>
     */
    public String taxQuery(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, TAX_QUERY, parameters, HttpMethod.GET, showLimitUsage);
    }
}
