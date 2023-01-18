package com.w3.exchange.common.client.third;

import com.w3.exchange.common.domain.third.Rank;
import com.w3.exchange.common.domain.third.TradingSignal;

/**
 * 客户端
 */
public interface ThirdSpotClient {

    /**
     * 创建排行榜查询对象
     * @return
     */
    Rank createRank();

    /**
     * 创建交易信号查询对象
     */
    TradingSignal createTradingSignal();
}
