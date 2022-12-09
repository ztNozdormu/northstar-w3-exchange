package com.w3.exchange.common.enums.trade;

/**
 * 金融产品类型
 * Types of financial products ProdFinaceType
 */
public enum ProdFinaceType {
    /**
     * 币币
     */
    SPOT,
    /**
     * 永续合约
     */
    SWAP,
    /**
     * 交割合约
     */
    FUTURES,
    /**
     * 期权
     */
    OPTION;
}
