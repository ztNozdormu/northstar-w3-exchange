package com.w3.exchange.cryptocompare.impl.compareSpot;

import com.w3.exchange.common.domain.third.HistoryData;
import com.w3.exchange.common.domain.third.PairMapping;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * 历史交易数据
 */
public class CompareHistoryData extends HistoryData {

    public CompareHistoryData(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
    }


    /**
     *  获取每分钟的开、高、低、收、量和量的历史数据。此数据仅存储7天，如果需要更长的时间，请使用每小时或每日路径。
     */
    private final String HISTORY_DATA_MINUTES = "/data/v2/histominute";

    /**
     * Minute Pair OHLCV 获取每分钟的开、高、低、收、量和量的历史数据。此数据仅存储7天，如果需要更长的时间，请使用每小时或每日路径。
     * Get open, high, low, close, volumefrom and volumeto from the each minute historical data.
     * This data is only stored for 7 days, if you need more, use the hourly or daily path.If e=CCCAGG
     * and tryConversion=true, it attempts conversion through BTC or ETH to determine the best possible path.
     * The conversion type and symbol used are appended per historical point. If you want to get all the available historical data,
     * you can use limit=2000 and keep going back in time using the toTs param.
     * You can then keep requesting batches using: &limit=2000&toTs={the earliest timestamp received}.
     * <br><br>
     * <p>
     * GET /data/v2/histominute
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   fsym -- BTC 查询的币种
     *                   tsym -- USDT 交易结算币种(一般为稳定币)
     *                   limit -- 10 数据条数
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Historical&cat=dataHistominute">
     * https://min-api.cryptocompare.com/documentation?key=Historical&cat=dataHistominute</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/v2/histominute?fsym=BTC&tsym=USDT&limit=10 <br>
     */
    public String historyDataMinutes(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, HISTORY_DATA_MINUTES, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     *  获得每小时历史数据的开、高、低、收、量和量
     */
    private final String HISTORY_DATA_HOUR = "/data/v2/histohour";

    /**
     * Hourly Pair OHLCV 获得每小时历史数据的开、高、低、收、量和量。如e=CCCAGG并且tryConversion=true，
     * 则尝试通过BTC或ETH进行转换，以确定最佳可能路径。所使用的转换类型和符号附加在每个历史点上。
     * 如果希望获得所有可用的历史数据，可以使用limit=2000，
     * 并使用toTs参数继续回溯。然后，您可以使用:&limit=2000&toTs={接收到的最早时间戳}继续请求批处理。
     * Get open, high, low, close, volumefrom and volumeto from the hourly historical data.
     * If e=CCCAGG and tryConversion=true, it attempts conversion through BTC or ETH to determine
     * the best possible path. The conversion type and symbol used are appended per historical point.
     * If you want to get all the available historical data, you can use limit=2000 and keep going back
     * in time using the toTs parameter.
     * You can then keep requesting batches using: &limit=2000&toTs={the earliest timestamp received}.
     * <br><br>
     * <p>
     * GET /data/v2/histohour
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                    fsym -- BTC 查询的币种
     *                    tsym -- USDT 交易结算币种(一般为稳定币)
     *                    limit -- 10 数据条数
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=Historical&cat=dataHistohour">
     * https://min-api.cryptocompare.com/documentation?key=Historical&cat=dataHistohour</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/v2/histohour?fsym=BTC&tsym=USDT&limit=10 <br>
     */
    public String historyDataHour(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, HISTORY_DATA_HOUR, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     *  根据token名称获取所有交易对以及交易对所在交易所信息。
     */
    private final String PAIR_MAPPING_FSYMS = "/data/v2/pair/mapping/exchange/fsym";

    /**
     * Exchange From Symbol 根据token名称获取所有交易对以及交易对所在交易所信息
     * Returns the current pair mapping by exchange symbol.
     * <br><br>
     * <p>
     * GET /data/v2/pair/mapping/exchange/fsym
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   exchangeFsym -- BTC 查询的token名称
     *
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=PairMapping&cat=pairMappingExchangeSymbolEndpoint">
     * https://min-api.cryptocompare.com/documentation?key=PairMapping&cat=pairMappingExchangeSymbolEndpoint</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/v2/pair/mapping/exchange/fsym?exchangeFsym=BTC <br>
     */
    public String pairMappingFsyms(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, PAIR_MAPPING_FSYMS, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     *  返回所有计划的pair重映射事件。交易所上币计划？
     */
    private final String PAIR_MAPPING_UPDATES = "/data/pair/mapping/planned/updates";
    /**
     *
     * Planned Pair Mapping Updates 返回所有计划的pair重映射事件。交易所上币计划？
     * Returns all planned pair re-mapping events.
     * <br><br>
     * <p>
     * GET /data/pair/mapping/planned/updates
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=PairMapping&cat=plannedPairRemappingEndpoint">
     * https://min-api.cryptocompare.com/documentation?key=PairMapping&cat=plannedPairRemappingEndpoint</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/pair/mapping/planned/updates <br>
     */
    public String pairMappingUpdates(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, PAIR_MAPPING_UPDATES, parameters, HttpMethod.GET, showLimitUsage);
    }

}
