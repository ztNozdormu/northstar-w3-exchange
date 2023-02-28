package io.github.ztnozdormu.common.client.third;

import io.github.ztnozdormu.common.domain.third.Rank;
import io.github.ztnozdormu.common.domain.third.TradingSignal;

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
