package com.w3.exchange.cryptocompare.impl.compareSpot;

import com.w3.exchange.common.domain.third.TradingSignal;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

public class CompareTradingSignal extends TradingSignal {

    public CompareTradingSignal(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
    }


    /**
     * 过去24小时内,币种在所有市场（交易所）总交易量排行榜
     */
    private final String MARKET_VOL_RANK = "/data/top/totalvolfull";

    /**
     * marketVolRank 过去24小时内，通过所有市场的总交易量币种排行榜  Market Volume ranking
     * Toplist by 24H Volume Full Data
     * Get a number of top coins by their total volume across all markets in the last 24 hours.
     * Default value is first page (0) and the top 10 coins.
     * <br><br>
     * <p>
     * GET /data/top/totalvolfull
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   limit -- int 默认10
     *                   tsym -- USDT 统计/计算币种
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=TradingSignals&cat=tradingSignalsIntoTheBlockLatest">
     * https://min-api.cryptocompare.com/documentation?key=TradingSignals&cat=tradingSignalsIntoTheBlockLatest</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/top/totalvolfull?limit=10&tsym=USDT <br>
     *   https://min-api.cryptocompare.com/data/top/totalvolfull?tsym=USD&page=2 <br>
     */
    public String marketVolRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, MARKET_VOL_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }
}
