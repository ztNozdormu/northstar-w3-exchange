package io.github.ztnozdormu.common.enums.trade;

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
     * 币币杠杆
     */
    MARGIN,
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
