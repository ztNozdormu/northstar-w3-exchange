package io.github.ztnozdormu.cryptocompare.impl.compareSpot;

import io.github.ztnozdormu.common.domain.third.Rank;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.utils.RequestHandler;

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
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=TopTotalVolumeEndpointFull">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=TopTotalVolumeEndpointFull</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/top/totalvolfull?limit=10&tsym=USDT <br>
     *   https://min-api.cryptocompare.com/data/top/totalvolfull?tsym=USD&page=2 <br>
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
     * <br>
     * examples  <br>
     * https://min-api.cryptocompare.com/data/top/totaltoptiervolfull?tsym=USD&page=2 <br>
     * https://min-api.cryptocompare.com/data/top/totaltoptiervolfull?limit=10&tsym=USDT <br>
     * https://min-api.cryptocompare.com/data/top/totaltoptiervolfull?limit=30&page=3&tsym=EUR&extraParams=your_app_name
     */
    public String market20VolRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, MARKET20_VOL_RANK, parameters, HttpMethod.GET, showLimitUsage);
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
     * <br>
     * examples https://min-api.cryptocompare.com/data/top/mktcapfull?tsym=USD&page=2
     */
    public String marketCapRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, MARKET_CAP_RANK, parameters, HttpMethod.GET, showLimitUsage);
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
     * <br>
     * examples https://min-api.cryptocompare.com/data/top/exchanges?fsym=BTC&tsym=USD&limit=20
     */
    public String exchangeVolRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, EXCHANGE_VOL_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 交易对交易量全部数据(包括CCCAGG数据)交易所排行榜
     */
    private final String EXCHANGE_FULL_VOL_RANK = "/data/top/exchanges/full";

    /**
     * 交易对交易量全部数据(包括CCCAGG数据)交易所排行榜
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
     * <br>
     * examples https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=BTC&tsym=USD&limit=20
     */
    public String exchangeFullVolRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, EXCHANGE_FULL_VOL_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 获取volume24hto和总供应(如果可用)
     */
    private final String VOL24_SUPPLY = "/data/top/volumes";

    /**
     * 获取volume24hto和总供应(如果可用)
     * Toplist by Pair Volume
     * Get top coins by volume for the to currency. It returns volume24hto and total supply (where available).
     * The number of coins you get is the minimum of the limit you set (default 50)
     * and the total number of coins available
     * <br><br>
     * <p>
     * GET /data/top/volumes
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   limit -- 10
     *                   tsym -- BTC   统计/计算币种
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topExchangesVolumes">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topExchangesVolumes</a>
     *
     * <br>
     * examples https://min-api.cryptocompare.com/data/top/volumes?tsym=BTC&limit=100
     */
    public String vol24Supply(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, VOL24_SUPPLY, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 按交易量获取一种货币的各个交易对排行榜(总是使用我们的汇总数据)
     */
    private final String CURRENCY_PAIR_RANK = "/data/top/pairs";

    /**
     * 按交易量获取一种货币的各个交易对排行榜(总是使用我们的汇总数据)
     * Toplist of Trading Pairs
     * Get top pairs by volume for a currency (always uses our aggregated data).
     * The number of pairs you get is the minimum of the limit you set (default 5)
     * and the total number of pairs available
     * <br><br>
     * <p>
     * GET /data/top/pairs
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   fsym -- BTC  统计目标币种
     *                   limit -- 20  返回结果条数
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topPairsEndpoint">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=topPairsEndpoint</a>
     * <br>
     * examples https://min-api.cryptocompare.com/data/top/pairs?fsym=BTC&limit=20
     */
    public String currencyPairsRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, CURRENCY_PAIR_RANK, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     * 货币与指定交易所的资产之间的往来(转账交易量)排行榜
     * 资产转入/转出交易所量的排行
     */
    private final String CURRENCY_TRANSFER_EXCHANGE = "/data/exchange/top/volume";

    /**
     * 货币与指定交易所的资产之间的往来(转账交易量)排行榜
     * 资产转入/转出交易所量的排行
     * Exchange Top Symbols By Volume
     * Top to or from assets on a specific exchange.
     * <br><br>
     * <p>
     * GET /data/exchange/top/volume
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   e -- Binance  交易所名称
     *                   direction -- to/out  转入/转出
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Toplists&cat=exchangeSymbolsByVolumeEndpoint">
     * https://min-api.cryptocompare.com/documentation?key=Toplists&cat=exchangeSymbolsByVolumeEndpoint</a>
     * <br>
     * examples https://min-api.cryptocompare.com/data/exchange/top/volume?e=Binance&direction=TO
     */
    public String currencyTransferExchangeRank(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, CURRENCY_TRANSFER_EXCHANGE, parameters, HttpMethod.GET, showLimitUsage);
    }
}
