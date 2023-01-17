package com.w3.exchange.cryptocompare.impl.compareSpot;

import com.w3.exchange.common.domain.third.Rank;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * 排行榜接口
 */
public class CompareRank extends Rank {

    public CompareRank(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
    }

    /**
     * 币种市值排行榜
     */
    private final String MARKET_CAP_RANK = "/data/top/mktcapfull";

    /**
     * marketCapRank 币种市值排行榜 Market capitalization ranking
     * Toplist by Market Cap Full Data
     * Get a number of top coins by their market cap. Default value is first page (0) and the top 10 coins.
     * <br><br>
     * <p>
     * GET /data/top/mktcapfull
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   limit -- int 默认10
     *                   tsym -- USDT 统计/计算币种
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=TopTotalMktCapEndpointFull">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=TopTotalMktCapEndpointFull</a>
     *
     */
    public String marketCapRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, MARKET_CAP_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 币种在所有市场（交易所）总交易量排行榜
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
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=TopTotalVolumeEndpointFull">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=TopTotalVolumeEndpointFull</a>
     *
     */
    public String marketVolRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, MARKET_VOL_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 币种在前20个市场（交易所）总交易量排行榜
     */
    private final String MARKET20_VOL_RANK = "/data/top/totaltoptiervolfull";

    /**
     * marketVolRank 过去24小时内前20个交易所市场的币种总交易量币种排行  Market Volume ranking
     * Toplist by 24H Top Tier Volume Full Data
     * Get a number of top coins by their total top tier volume based on the top 20 markets in the last 24 hours.
     * Default value is first page (0) and the top 10 coins.
     * <br><br>
     * <p>
     * GET /data/top/totaltoptiervolfull
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   limit -- int 默认10
     *                   tsym -- USDT 统计/计算币种
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topTotalTopTierVolumeEndpointFull">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topTotalTopTierVolumeEndpointFull</a>
     *
     */
    public String market20VolRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, MARKET20_VOL_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }


    /**
     * 交易对最高交易量交易所排行榜
     */
    private final String EXCHANGE_VOL_RANK = "/data/top/exchanges";

    /**
     * exchangeVolRank 交易对最高交易量交易所排行榜 Market Volume ranking
     * Top Exchanges Volume Data by Pair
     * Get top exchanges by volume for a currency pair. The number of exchanges you get is the minimum
     * of the limit you set (default 5) and the total number of exchanges available
     * <br><br>
     * <p>
     * GET /data/top/exchanges
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   fsym -- BTC   统计的目标币种
     *                   tsym -- USDT 统计/计算币种
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topExchangesEndpoint">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topExchangesEndpoint</a>
     *
     */
    public String exchangeVolRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, EXCHANGE_VOL_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 交易对最高交易量交易所排行榜
     */
    private final String EXCHANGE_FULL_VOL_RANK = "/data/top/exchanges/full";

    /**
     *
     * Top Exchanges Full Data By Pair
     * Get top exchanges by volume for a currency pair plus the full CCCAGG data. The number of exchanges
     * you get is the minimum of the limit you set (default 5) and the total number of exchanges available
     * <br><br>
     * <p>
     * GET /data/top/exchanges/full
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   fsym -- BTC   统计的目标币种
     *                   tsym -- USDT 统计/计算币种
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topExchangesFullEndpoint">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topExchangesFullEndpoint</a>
     *
     */
    public String exchangeFullVolRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, EXCHANGE_FULL_VOL_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }


}