package io.github.ztnozdormu.binance.impl.biSpot;


import io.github.ztnozdormu.common.domain.PortfolioMargin;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Portfolio Margin Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#portfolio-margin-endpoints">PortfolioMargin Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class BIPortfolioMargin extends PortfolioMargin {

    public BIPortfolioMargin(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String ACCOUNT = "/sapi/v1/portfolio/account";
    /**
     * GET /sapi/v1/portfolio/account
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#get-portfolio-margin-account-info-user_data">
     *     https://binance-docs.github.io/apidocs/spot/en/#get-portfolio-margin-account-info-user_data</a>
     */
    public String getAccount(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, ACCOUNT, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String COLLATERAL_RATE = "/sapi/v1/portfolio/collateralRate";
    /**
     * Portfolio Margin Collateral Rate
     * <br><br>
     * GET /sapi/v1/portfolio/collateralRate
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#portfolio-margin-collateral-rate-market_data">
     *     https://binance-docs.github.io/apidocs/spot/en/#portfolio-margin-collateral-rate-market_data</a>
     */
    public String collateralRate(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, COLLATERAL_RATE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String PM_LOAN = "/sapi/v1/portfolio/pmLoan";
    /**
     * Query Portfolio Margin Bankruptcy Loan Record
     * <br><br>
     * GET /sapi/v1/portfolio/pmLoan
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#query-portfolio-margin-bankruptcy-loan-record-user_data">
     *     https://binance-docs.github.io/apidocs/spot/en/#query-portfolio-margin-bankruptcy-loan-record-user_data</a>
     */
    public String bankruptcyLoanRecord(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, PM_LOAN, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String REPAY = "/sapi/v1/portfolio/repay";
    /**
     * Repay Portfolio Margin Bankruptcy Loan
     * <br><br>
     * POST /sapi/v1/portfolio/repay
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * recvWindow -- optional/long <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#portfolio-margin-bankruptcy-loan-repay">
     *     https://binance-docs.github.io/apidocs/spot/en/#portfolio-margin-bankruptcy-loan-repay</a>
     */
    public String repay(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, REPAY, parameters, HttpMethod.POST, showLimitUsage);
    }
}
