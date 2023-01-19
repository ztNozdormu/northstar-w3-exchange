package com.w3.exchange.common.utils;

import com.w3.exchange.common.bo.MetaDataBO;
import com.w3.exchange.common.bo.RateLimitBO;
import lombok.Data;

import java.util.List;

/**
 * CryptoCompare行情服务商接口返回结果基本数据结构
 */
@Data
public class ExCompareResult<T> {
    /**
     * 返回消息
     */
    public String Message;
    /**
     * 返回状态类型 100
     */
    public int Type;
    /**
     * 提示信息
     */
    public List SponsoredData;
    /**
     * 元数据
     */
    public MetaDataBO MetaData;

    /**
     * 元数据
     */
    public RateLimitBO RateLimit;

    /**
     * 返回数据
     */
    public T Data;

    public ExCompareResult() {
    }

    public ExCompareResult(int type,String message, List sponsoredData,RateLimitBO rateLimit,MetaDataBO metaData,T data) {
        this.Type = type;
        this.Message = message;
        this.SponsoredData = sponsoredData;
        this.RateLimit = rateLimit;
        this.MetaData = metaData;
        this.Data = data;
    }

}
