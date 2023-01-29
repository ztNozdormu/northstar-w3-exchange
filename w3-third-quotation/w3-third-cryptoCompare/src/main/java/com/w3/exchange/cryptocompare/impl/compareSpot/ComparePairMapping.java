package com.w3.exchange.cryptocompare.impl.compareSpot;

import com.w3.exchange.common.domain.third.PairMapping;
import com.w3.exchange.common.domain.third.Rank;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * 交易所交易对信息接口
 */
public class ComparePairMapping extends PairMapping {

    public ComparePairMapping(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
    }


    /**
     *  通过symbol(token名称),获取不同交易所token交易对基本信息
     */
    private final String PAIR_MAPPING_FSYM = "/data/v2/pair/mapping/fsym";

    /**
     * Mapping - From Symbol 通过symbol(token名称),获取不同交易所token交易对基本信息  Mapping - From Symbol
     * Returns the current pair mapping by mapped symbol.
     * <br><br>
     * <p>
     * GET /data/v2/pair/mapping/fsym
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   fsym -- BTC 查询的币种
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=PairMapping&cat=pairMappingMappedSymbolEndpoint">
     * https://min-api.cryptocompare.com/documentation?key=PairMapping&cat=pairMappingMappedSymbolEndpoint</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/v2/pair/mapping/fsym?fsym=BTC <br>
     */
    public String pairMappingFsym(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, PAIR_MAPPING_FSYM, parameters, HttpMethod.GET, showLimitUsage);
    }

    /**
     *  通过交易所获取交易所已经上架的币种交易对
     */
    private final String PAIR_MAPPING_EXCHANGE = "/data/v2/pair/mapping/exchange";

    /**
     * Mapping - Exchange 按exchange返回当前pair映射。如果e参数中没有传递任何交换，则将获得一个包含所有交换的对象，否则将获得一个包含该特定交换的映射对象的数组  Mapping - Exchange
     * Returns the current pair mapping by exchange. If no exchange is passed in the e parameter,
     * you will get an object with all the exchanges,
     * otherwise you get an array with mapping object for that specific exchange
     * <br><br>
     * <p>
     * GET /data/v2/pair/mapping/exchange
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   e -- Binance 查询的交易所
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=PairMapping&cat=pairMappingExchangeEndpoint">
     * https://min-api.cryptocompare.com/documentation?key=PairMapping&cat=pairMappingExchangeEndpoint</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/v2/pair/mapping/exchange?e=Binancec <br>
     */
    public String pairMappingExchange(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, PAIR_MAPPING_EXCHANGE, parameters, HttpMethod.GET, showLimitUsage);
    }

}
