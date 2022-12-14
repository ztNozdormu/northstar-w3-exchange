package com.w3.exchange.okx.impl.okxSpot;

import com.w3.exchange.common.domain.Account;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.ParameterChecker;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Trade Endpoints</h2>
 * 交易
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade">Trade Endpoint</a>
 * 交易功能模块下的API接口需要身份验证。
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXTrade extends Account {

    String passPhrase;

    boolean isSimluate;

    public OKXTrade(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }



}
