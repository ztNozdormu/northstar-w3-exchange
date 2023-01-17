package com.w3.exchange.cryptocompare.impl.compareSpot;

import com.w3.exchange.common.domain.third.Rank;
import com.w3.exchange.common.utils.RequestHandler;

/**
 * 排行榜接口
 */
public class CompareRank extends Rank {

    public CompareRank(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
    }

    /**
     * 排行榜接口
     */

}
