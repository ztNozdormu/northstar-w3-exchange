package com.w3.exchange.common.enums;

/**
 * 默认路由
 */
public final class DefaultUrls {
    public static final String PROD_URL = "https://api.binance.com";
    public static final String OKX_PROD_URL = "https://www.okx.com";
    public static final String WS_URL = "wss://stream.binance.com:9443";

    public static final String WS_URL_OKX = "wss://wsaws.okx.com:8443/ws/v5/public";

    public static final String TESTNET_URL = "https://testnet.binance.vision";
    public static final String TESTNET_WSS_URL = "wss://testnet.binance.vision";

    private DefaultUrls() {
    }
}
