package com.w3.exchange.cryptocompare.bo.rank;

import lombok.Data;

/**
 * 排名数据结果业务对象
 */
@Data
public class CoinInfoRankBO {
    /**
     * 7605
     */
    private String id;
    /**
     * ETH
     */
    private String Name;
    /**
     * Ethereum
     */
    private String fullName;
    /**
     * ETH
     */
    private String internal;
    /**
     * /media/37746238/eth.png
     */
    private String imageUrl;
    /**
     * /coins/eth/overview
     */
    private String url;
    /**
     * Ethash
     */
    private String algorithm;
    /**
     * PoS
     */
    private String proofType;
    /**
     *
     */
    private Rating rating;
    /**
     * 0
     */
    private String  netHashesPerSecond;
    /**
     * 16189079
     */
    private String  blockNumber;
    /**
     * 12.070420567276791
     */
    private String  blockTime;
    /**
     * 0.28197768698142733
     */
    private String  blockReward;
    /**
     * 2015-07-30
     */
    private String  assetLaunchDate;
    /**
     * -1
     */
    private String  maxSupply;
    /**
     * 1
     */
    private String  type;
    /**
     * Webpagecoinp
     */
    private String  documentType;
}
