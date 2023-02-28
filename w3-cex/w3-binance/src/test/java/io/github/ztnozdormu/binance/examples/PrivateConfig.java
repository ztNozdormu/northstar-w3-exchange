package io.github.ztnozdormu.binance.examples;

import lombok.Data;

@Data
public final class PrivateConfig {
    public PrivateConfig() {
    }

    public static final String API_KEY = "";
    public static final String SECRET_KEY = "";
    public static final String  PROD_URL_1 = "https://api1.binance.com";
    public static final String  PROD_URL_2 = "https://api2.binance.com";
    public static final String  PROD_URL_3 = "https://api3.binance.com";
    public static final String BASE_URL = "https://testnet.binance.vision";
    public static final String TESTNET_API_KEY = "";
    public static final String TESTNET_SECRET_KEY = "";

}
