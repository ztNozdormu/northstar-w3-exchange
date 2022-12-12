package com.w3.exchange.okx.impl.okxSpot;

import com.w3.exchange.common.domain.Market;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import com.w3.exchange.common.utils.JSONParser;
import com.w3.exchange.common.utils.ParameterChecker;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 公共数据功能模块下的API接口不需要身份验证。
 *
 * <h2>Market Endpoints</h2>
 * All endpoints under the
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data">Market Data Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXPDMarket extends Market {

    String  passPhrase;

    boolean isSimluate;

    public OKXPDMarket(String baseUrl, String apiKey, String secertKey, String passPhrase, boolean isSimluate) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
        this.isSimluate = isSimluate;
    }
    // OKX没有该接口 忽略
    private final String PING = "/api/v3/ping";
    /**
     * Test connectivity to the Rest API.
     * <br><br>
     * GET /api/v3/ping
     * <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#test-connectivity">
     *     https://binance-docs.github.io/apidocs/spot/en/#test-connectivity</a>
     */
    public String ping() {
        return requestHandler.sendPublicRequest(baseUrl, PING, null, HttpMethod.GET, showLimitUsage);
    }

    private final String TIME = "/api/v5/public/time";
    /**
     * Test connectivity to the Rest API and get the current server time.
     * 获取系统时间 Unix时间戳的毫秒数格式，如 1597026383085
     * <br><br>
     * GET /api/v5/public/time
     * <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-system-time">
     *     https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-system-time</a>
     */
    public String time() {
        return requestHandler.sendPublicRequest(baseUrl, TIME, null, HttpMethod.GET, showLimitUsage);
    }

    private final String EXCHANGE_INFO = "/api/v3/exchangeInfo";
    /**
     * OKX没有该接口 忽略
     * Current exchange trading rules and symbol information.
     * <br><br>
     * GET /api/v3/exchangeinfo
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string <br>
     * symbols -- optional/ArrayList <br>
     * permissions -- optional/ArrayList -- support single or multiple values (e.g. "SPOT", ["MARGIN","LEVERAGED"]) <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#exchange-information">
     *     https://binance-docs.github.io/apidocs/spot/en/#exchange-information</a>
     */
    public String exchangeInfo(LinkedHashMap<String, Object> parameters) {
        if (parameters.containsKey("symbol") && parameters.containsKey("symbols")) {
            throw new ExchangeConnectorException("symbol and symbols cannot be sent together.");
        }
        if ((parameters.containsKey("symbol") && parameters.containsKey("permissions"))
             || parameters.containsKey("symbols") && parameters.containsKey("permissions")) {
            throw new ExchangeConnectorException("permissions cannot be sent together with symbol or symbols.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        }
        if (parameters.containsKey("permissions")) {
            ParameterChecker.checkParameterType(parameters.get("permissions"), ArrayList.class, "permissions");
            parameters.put("permissions", JSONParser.getJSONArray(
                                    (ArrayList<?>) parameters.get("permissions"), "permissions"));
        }
        return requestHandler.sendPublicRequest(baseUrl, EXCHANGE_INFO, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String DEPTH = "/api/v5/market/books";
    /**
     * 获取产品深度
     * GET /api/v5/market/books
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- mandatory/string <br>
     * limit -- optional/integer -- limit the results
     *            Default 100; max 5000. Valid limits:[5, 10, 20, 50, 100, 500, 1000, 5000] <br>
     * @return String
     * @see <a href=" https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-order-book">
     *     获取产品深度 https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-order-book</a>
     */
    public String depth(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, DEPTH, parameters, HttpMethod.GET, showLimitUsage);
    }
    //  TODO 获取产品轻量深度 https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-order-lite-book

    private final String TRADES = "/api/v5/market/trades";
    /**
     * 获取交易产品公共成交数据(交易记录)
     * Get recent trades.
     * <br><br>
     * GET /api/v5/market/trades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * param.add("instId","BTC-USDT");
     * param.add("limit","10");
     * instId -- mandatory/string <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-trades">
     *     https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-trades</a>
     */
    public String trades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }
    // 获取交易产品公共历史成交数据
    // TODO 获取期权品种公共成交数据 https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-option-trades
    private final String HISTORICAL_TRADES = "/api/v5/market/history-trades";
    /**
     * 获取交易产品公共历史成交数据
     * Get older market trades.
     * <br><br>
     * GET /api/v5/market/history-trades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- mandatory/string <br>
     * limit -- optional/integer -- limit the result Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-trades-history">
     *     https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-trades-history</a>
     *
     */
    public String historicalTrades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendWithApiKeyRequest(baseUrl, HISTORICAL_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String AGG_TRADES = "/api/v3/aggTrades";
    /**
     * Get compressed, aggregate trades. Trades that fill at the time, from the same order,
     * with the same price will have the quantity aggregated.
     * <br><br>
     * GET /api/v3/aggTrades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * fromId -- optional/long -- id to get aggregate trades from INCLUSIVE <br>
     * startTime -- optional/long -- Timestamp in ms to get aggregate trades from INCLUSIVE <br>
     * endTime -- optional/long -- Timestamp in ms to get aggregate trades until INCLUSIVE <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#compressed-aggregate-trades-list">
     *     https://binance-docs.github.io/apidocs/spot/en/#compressed-aggregate-trades-list</a>
     */
    public String aggTrades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, AGG_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    // 获取交易产品K线数据
    private final String KLINES = "/api/v5/market/candles";
    /**
     * Kline/candlestick bars for a symbol.
     * Klines are uniquely identified by their open time.
     * <br><br>
     * GET /api/v5/market/candles
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     *
     * instId -- mandatory/string <br>
     * interval -- mandatory/string <br>
     * startTime -- optional/long <br>
     * endTime -- optional/long <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String k线图 List<List<>> 内层的一个list 代表一个柱子
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-candlesticks">
     *     https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-candlesticks</a>
     */
    public String klines(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
//        ParameterChecker.checkParameter(parameters, "interval", String.class);
        return requestHandler.sendPublicRequest(baseUrl, KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String UIKLINES = "/api/v3/uiKlines";
    /**
     * The request is similar to klines having the same parameters and response.
     * uiKlines return modified kline data, optimized for presentation of candlestick charts.
     * <br><br>
     * GET /api/v3/uiKlines
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * interval -- mandatory/string <br>
     * startTime -- optional/long <br>
     * endTime -- optional/long <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#uiklines">
     *     https://binance-docs.github.io/apidocs/spot/en/#uiklines</a>
     */
    public String uiKlines(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "interval", String.class);
        return requestHandler.sendPublicRequest(baseUrl, UIKLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String AVG_PRICE = "/api/v5/public/mark-price";
    /**
     * 获取标记价格
     * 为了防止个别用户恶意操控市场导致合约价格波动剧烈，我们根据现货指数和合理基差设定标记价格
     *
     * Current average price for a symbol.
     * <br><br>
     * GET /api/v5/public/mark-price
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- mandatory/string -- the trading pair <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-mark-price">
     *     https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-mark-price</a>
     */
    public String averagePrice(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, AVG_PRICE, parameters, HttpMethod.GET, showLimitUsage);
    }
    // 获取所有产品行情信息 TODO 需要进一步细分
    private final String TICKER_24H = "/api/v5/market/tickers";
    /**
     * 24 hour rolling window price change statistics. Careful when accessing this with no symbol.
     * <br><br>
     * GET /api/v5/market/tickers
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * symbols -- optional/string <br>
     * type -- optional/enum -- Supported values: FULL or MINI. If none provided, the default is FULL <br>
     * instType FUTURES
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-tickers">
     *     https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-tickers</a>
     */
    public String ticker24H(LinkedHashMap<String, Object> parameters) {
        if (parameters.containsKey("symbol") && parameters.containsKey("symbols")) {
            throw new ExchangeConnectorException("symbol and symbols cannot be sent together.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        }
        return requestHandler.sendPublicRequest(baseUrl, TICKER_24H, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String TICKER_SYMBOL = "/api/v3/ticker/price";
    /**
     * Latest price for a symbol or symbols.
     * <br><br>
     * GET /api/v3/ticker/price
     * <br>
     * https://binance-docs.github.io/apidocs/spot/en/#24hr-ticker-price-change-statistics
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * symbols -- optional/string <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#symbol-price-ticker">
     *     https://binance-docs.github.io/apidocs/spot/en/#symbol-price-ticker</a>
     */
    public String tickerSymbol(LinkedHashMap<String, Object> parameters) {
        if (parameters.containsKey("symbol") && parameters.containsKey("symbols")) {
            throw new ExchangeConnectorException("symbol and symbols cannot be sent together.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        }
        return requestHandler.sendPublicRequest(baseUrl, TICKER_SYMBOL, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String BOOK_TICKER = "/api/v3/ticker/bookTicker";
    /**
     * Best price/qty on the order book for a symbol or symbols.
     * <br><br>
     * GET /api/v3/ticker/bookTicker
     * <br>
     * https://binance-docs.github.io/apidocs/spot/en/#24hr-ticker-price-change-statistics
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * symbols -- optional/string <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#symbol-order-book-ticker">
     *     https://binance-docs.github.io/apidocs/spot/en/#symbol-order-book-ticker</a>
     */
    public String bookTicker(LinkedHashMap<String, Object> parameters) {
        if (parameters.containsKey("symbol") && parameters.containsKey("symbols")) {
            throw new ExchangeConnectorException("symbol and symbols cannot be sent together.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        }
        return requestHandler.sendPublicRequest(baseUrl, BOOK_TICKER, parameters, HttpMethod.GET, showLimitUsage);
    }
    // 获取单个产品行情信息
    private final String TICKER = "/api/v5/market/ticker";
    /**
     * The window used to compute statistics will be no more than 59999ms from the requested windowSize.
     * openTime for /api/v3/ticker always starts on a minute, while the closeTime is the current time of the request.
     * As such, the effective window will be up to 59999ms wider than windowSize.
     * <br><br>
     * GET /api/v5/market/ticker
     * <br>
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-ticker
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- mandatory/string -- Either symbol or symbols must be provided
     * Examples of accepted format for the symbols parameter:
     * ["BTCUSDT","BNBUSDT"]
     * or
     * %5B%22BTCUSDT%22,%22BNBUSDT%22%5D <br>
     * instId -- optional/string -- The maximum number of symbols allowed in a request is 100. <br>
     * windowSize -- optional/enum -- Defaults to 1d if no parameter provided <br>
     * type -- optional/enum -- Supported values: FULL or MINI. If none provided, the default is FULL <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-ticker">
     *     https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-ticker</a>
     */
    public String ticker(LinkedHashMap<String, Object> parameters) {
        if (parameters.containsKey("instId") && parameters.containsKey("symbols")) {
            throw new ExchangeConnectorException("symbol and symbols cannot be sent together.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        } else {
            ParameterChecker.checkParameter(parameters, "symbol", String.class);
        }
        return requestHandler.sendPublicRequest(baseUrl, TICKER, parameters, HttpMethod.GET, showLimitUsage);
    }
}