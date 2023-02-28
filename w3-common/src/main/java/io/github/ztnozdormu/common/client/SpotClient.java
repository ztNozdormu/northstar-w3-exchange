package io.github.ztnozdormu.common.client;

import io.github.ztnozdormu.common.domain.*;

/**
 * 低频交易调用REST API 客户端
 */
public interface SpotClient {
    Blvt createBlvt();
    BSwap createBswap();
    C2C createC2C();
    Convert createConvert();
    CryptoLoans createCryptoLoans();
    Fiat createFiat();
    Futures createFutures();
    GiftCard createGiftCard();
    Market createMarket();
    Market createPubMarket();
    Margin createMargin();
    Mining createMining();
    NFT createNFT();
    Pay createPay();
    PortfolioMargin createPortfolioMargin();
    Rebate createRebate();
    Savings createSavings();
    Staking createStaking();
    SubAccount createSubAccount();
    Trade createTrade();
    UserData createUserData();
    Wallet createWallet();
}
