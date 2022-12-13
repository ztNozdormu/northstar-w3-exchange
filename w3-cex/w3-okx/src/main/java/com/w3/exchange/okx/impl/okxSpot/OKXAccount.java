package com.w3.exchange.okx.impl.okxSpot;

import com.w3.exchange.common.domain.Account;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.ParameterChecker;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Account Endpoints</h2>
 * 账户
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-account">Account Endpoint</a>
 * 账户功能模块下的API接口需要身份验证
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXAccount extends Account {

    String passPhrase;

    boolean isSimluate;

    public OKXAccount(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    // TODO 账号相关接口

    private final String BALANCE = "/api/v5/account/balance";
    /**
     * 查看账户余额
     * 获取交易账户中资金余额信息。
     * <br><br>
     * GET /api/v5/account/balance
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ccy -- String	否	币种，如 BTC 支持多币种查询（不超过20个），币种之间半角逗号分隔 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-balance">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-balance</a>
     */
    public String balance(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, BALANCE, parameters, HttpMethod.GET, showLimitUsage);
    }
}
