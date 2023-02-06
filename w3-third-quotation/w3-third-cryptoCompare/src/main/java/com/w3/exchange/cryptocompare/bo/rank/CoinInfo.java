package com.w3.exchange.cryptocompare.bo.rank;

import lombok.Builder;
import lombok.Data;

@Data
public class CoinInfo {

    public CoinInfo(){

    }
    public CoinInfo(String Id,String Name,String FullName,String Internal,String ImageUrl,String Url,String Algorithm
            ,String ProofType,Rating Rating,String  NetHashesPerSecond,String  BlockNumber
            ,String  BlockTime,String  BlockReward,String  AssetLaunchDate
            , String  MaxSupply,String  Type,String  DocumentType){
        this.Id = Id;
        this.Name = Name;
        this.FullName = FullName;
        this.AssetLaunchDate = AssetLaunchDate;
        this.MaxSupply = MaxSupply;
        this.Type = Type;
        this.DocumentType = DocumentType;
    }
    /**
     * 7605
     */
    private String Id;
    /**
     * ETH
     */
    private String Name;
    /**
     * Ethereum
     */
    private String FullName;
    /**
     * ETH
     */
    private String Internal;
    /**
     * /media/37746238/eth.png
     */
    private String ImageUrl;
    /**
     * /coins/eth/overview
     */
    private String Url;
    /**
     * Ethash
     */
    private String Algorithm;
    /**
     * PoS
     */
    private String ProofType;
    /**
     *
     */
    private Rating Rating;
    /**
     * 0
     */
    private String  NetHashesPerSecond;
    /**
     * 16189079
     */
    private String  BlockNumber;
    /**
     * 12.070420567276791
     */
    private String  BlockTime;
    /**
     * 0.28197768698142733
     */
    private String  BlockReward;
    /**
     * 2015-07-30
     */
    private String  AssetLaunchDate;
    /**
     * -1
     */
    private String  MaxSupply;
    /**
     * 1
     */
    private String  Type;
    /**
     * Webpagecoinp
     */
    private String  DocumentType;
    /**
     * 排名序号
     */
    private Integer index;
}
