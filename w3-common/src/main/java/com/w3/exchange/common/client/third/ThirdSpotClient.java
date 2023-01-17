package com.w3.exchange.common.client.third;

import com.w3.exchange.common.domain.third.Rank;

/**
 * 客户端
 */
public interface ThirdSpotClient {

    /**
     * 排行榜
     * @return
     */
    Rank createRank();

}
