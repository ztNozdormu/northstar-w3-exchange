package io.github.ztnozdormu.cryptocompare.impl;

import io.github.ztnozdormu.common.client.third.ThirdAbstractClient;
import io.github.ztnozdormu.common.domain.third.TradingSignal;
import io.github.ztnozdormu.cryptocompare.impl.compareSpot.CompareRank;
import io.github.ztnozdormu.cryptocompare.impl.compareSpot.CompareTradingSignal;
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

    @Override
    public TradingSignal createTradingSignal() { return new CompareTradingSignal(baseUrl,apiKey);}
}
