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
     * 最新交易信号 由智能公司IntoTheBlock提供支持，该公司利用机器学习和高级统计数据为加密资产提取智能信号。
     */
    private final String TRADING_SIGNAL_LATEST = "/data/tradingsignals/intotheblock/latest";

    /**
     * 由智能公司IntoTheBlock提供支持，该公司利用机器学习和高级统计数据为加密资产提取智能信号。 <br>
     * tradingSignalLatest 最新交易信号 <br>
     * Trading Signals Latest <br>
     * Powered by IntoTheBlock, an intelligence company that leverages machine
     * learning and advanced statistics to extract intelligent signals for crypto-assets.
     * <br><br>
     * <p>
     * GET /data/tradingsignals/intotheblock/latest
     * <br>
     * API KEY in URL - just append ?  or &api_key={your_api_key} the the end of your request url
     * API KEY in HEADER - add the following header to your request: authorization: Apikey {your_api_key}.
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   fsym -- BTC 交易信号所属币种
     * @return String
     * @see <a href="https://min-api.cryptocompare.com/documentation?key=TradingSignals&cat=tradingSignalsIntoTheBlockLatest">
     * https://min-api.cryptocompare.com/documentation?key=TradingSignals&cat=tradingSignalsIntoTheBlockLatest</a>
     * <br>
     * examples <br>
     *   https://min-api.cryptocompare.com/data/tradingsignals/intotheblock/latest?fsym=BTC <br>
     */
    public String tradingSignalLatest(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, TRADING_SIGNAL_LATEST, parameters, HttpMethod.GET, showLimitUsage);
    }
}
