package com.w3.exchange.common.properties;

import lombok.Data;

/**
 * 基础配置类
 */
@Data
public class BaseProperties {

    public String API_KEY;
    public String SECRET_KEY;
    public String PROD_URL = "https://api1.binance.com,https://api2.binance.com,https://api3.binance.com";
    public String BASE_URL = "https://testnet.binance.vision";
    public String TESTNET_API_KEY = "";
    public String TESTNET_SECRET_KEY = "";

    public String WS_URL = "wss://stream.binance.com:9443";
    public String TESTNET_WSS_URL = "wss://testnet.binance.vision";

    public boolean showLimitUsage;

    public String baseUrl;

    public String apiKey;

    public String secretKey;



}
