package com.w3.exchange.cryptocompare.impl;

import com.w3.exchange.common.client.third.ThirdAbstractClient;
import com.w3.exchange.cryptocompare.impl.compareSpot.CompareRank;
import lombok.Builder;
import lombok.Data;

/**
 * 第三方行情服务商CryptoCompare 接口客户端对象
 */
@Builder
@Data
public class CompareClientImpl extends ThirdAbstractClient {

    private final String apiKey;

    private final String baseUrl;

    @Override
    public CompareRank createRank() {
        return new CompareRank(baseUrl,apiKey);
    }
}
