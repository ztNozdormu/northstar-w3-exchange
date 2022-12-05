package com.w3.exchange.binance.impl;


import com.w3.exchange.binance.impl.biSpot.*;
import com.w3.exchange.common.client.SpotClient;
import com.w3.exchange.common.enums.DefaultUrls;

/**
 * 一般交易场景
 */
public class BISpotClientImpl implements SpotClient {
    private final String apiKey;
    private final String secretKey;
    private final String baseUrl;
    private boolean showLimitUsage = false;

    public BISpotClientImpl() {
        this.apiKey = null;
        this.secretKey = null;
        this.baseUrl = DefaultUrls.PROD_URL;
    }

    public BISpotClientImpl(String baseUrl) {
        this.apiKey = null;
        this.secretKey = null;
        this.baseUrl = baseUrl;
    }

    public BISpotClientImpl(String baseUrl, boolean showLimitUsage) {
        this.apiKey = null;
        this.secretKey = null;
        this.baseUrl = baseUrl;
        this.showLimitUsage = showLimitUsage;
    }

    public BISpotClientImpl(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.baseUrl = DefaultUrls.PROD_URL;
    }

    public BISpotClientImpl(String apiKey, String secretKey, String baseUrl) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.baseUrl = baseUrl;
    }

    public void setShowLimitUsage(boolean showLimitUsage) {
        this.showLimitUsage = showLimitUsage;
    }

    @Override
    public BIBlvt createBlvt() {
        return new BIBlvt(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIBSwap createBswap() {
        return new BIBSwap(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIC2C createC2C() {
        return new BIC2C(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIConvert createConvert() {
        return new BIConvert(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BICryptoLoans createCryptoLoans() {
        return new BICryptoLoans(baseUrl, apiKey, secretKey, showLimitUsage); }

    @Override
    public BIFiat createFiat() {
        return new BIFiat(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIFutures createFutures() {
        return new BIFutures(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIGiftCard createGiftCard() {
        return new BIGiftCard(baseUrl, apiKey, secretKey, showLimitUsage); }

    @Override
    public BIMargin createMargin() {
        return new BIMargin(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIMarket createMarket() {
        return new BIMarket(baseUrl, apiKey, showLimitUsage);
    }

    @Override
    public BIMining createMining() {
        return new BIMining(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BINFT createNFT() {
        return new BINFT(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIPay createPay() {
        return new BIPay(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIPortfolioMargin createPortfolioMargin() {
        return new BIPortfolioMargin(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIRebate createRebate() {
        return new BIRebate(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BISavings createSavings() {
        return new BISavings(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIStaking createStaking() {
        return new BIStaking(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BISubAccount createSubAccount() {
        return new BISubAccount(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BITrade createTrade() {
        return new BITrade(baseUrl, apiKey, secretKey, showLimitUsage);
    }

    @Override
    public BIUserData createUserData() {
        return new BIUserData(baseUrl, apiKey, showLimitUsage);
    }

    @Override
    public BIWallet createWallet() {
        return new BIWallet(baseUrl, apiKey, secretKey, showLimitUsage);
    }
}
