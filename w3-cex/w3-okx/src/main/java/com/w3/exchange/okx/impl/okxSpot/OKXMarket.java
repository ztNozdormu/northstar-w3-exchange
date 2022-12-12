package com.w3.exchange.okx.impl.okxSpot;

import com.w3.exchange.common.domain.Market;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.ParameterChecker;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * 行情数据功能模块下的API接口不需要身份验证。
 * <h2>Market Endpoints</h2>
 * All endpoints under the
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data">Market Data Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXMarket extends Market {

    String passPhrase;

    boolean isSimluate;

    public OKXMarket(String baseUrl, String apiKey, String secertKey, String passPhrase, boolean isSimluate) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
        this.isSimluate = isSimluate;
    }

    private final String TIME = "/api/v5/public/time";

    /**
     * Test connectivity to the Rest API and get the current server time.
     * 获取系统时间 Unix时间戳的毫秒数格式，如 1597026383085
     * <p>
     * <br><br>
     * GET /api/v5/public/time
     * <br>
     *
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-system-time">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-system-time</a>
     */
    public String time() {
        return requestHandler.sendPublicRequest(baseUrl, TIME, null, HttpMethod.GET, showLimitUsage);
    }

    private final String TICKERS = "/api/v5/market/tickers";

    /**
     * 获取所有产品行情信息
     * <p>
     * <br><br>
     * GET /api/v5/market/tickers
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType	  -- String	是	产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权 <br>
     *                   uly	      -- String	否	标的指数 适用于交割/永续/期权，如 BTC-USD <br>
     *                   instFamily -- String	否	交易品种 适用于交割/永续/期权，如 BTC-USD <br>
     *                   <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-tickers">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-tickers</a>
     */
    public String tickers(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, TICKERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String TICKER = "/api/v5/market/ticker";

    /**
     * 获取单个产品行情信息
     * <p>
     * <br><br>
     * GET /api/v5/market/ticker
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USD-SWAP  <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-ticker">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-ticker</a>
     */
    public String ticker(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, TICKER, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String INDEX_TICKERS = "/api/v5/market/index-tickers";

    /**
     * 获取指数行情数据
     * <p>
     * <br><br>
     * GET /api/v5/market/index-tickers
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair<br>
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br>
     *                   quoteCcy -- String	可选	指数计价单位， 目前只有 USD/USDT/BTC为计价单位的指数，quoteCcy和instId必须填写一个 <br>
     *                   instId	  -- String	可选	指数，如 BTC-USD  <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-index-tickers">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-index-tickers</a>
     */
    public String indexTickers(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, INDEX_TICKERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String DEPTH = "/api/v5/market/books";

    /**
     * 获取产品深度
     * 获取产品深度列表
     * <p>
     * <br><br>
     * GET /api/v5/market/books
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USDT <br>
     *                   sz	    -- String	否	深度档位数量，最大值可传400，即买卖深度共800条
     *                   不填写此参数，默认返回1档深度数据 <br>
     * @return String
     * @see <a href=" https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-order-book">
     * 获取产品深度 https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-order-book</a>
     */
    public String depth(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, DEPTH, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String LITE_DEPTH = "/api/v5/market/books-lite";

    /**
     * 获取产品轻量深度
     * 可以更快地获取前25档的深度信息。
     * <p>
     * <br><br>
     * GET /api/v5/market/books-lite
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USDT mandatory/string
     *                   <br>
     *                   合约的asks和bids值数组举例说明： ["411.8","10", "0","4"] 411.8为深度价格，10为此价格的合约张数，0该字段已弃用(始终为0)，4为此价格的订单数量
     *                   现货/币币杠杆的asks和bids值数组举例说明： ["411.8","10", "0","4"] 411.8为深度价格，10为此价格的交易币的数量，0该字段已弃用(始终为0)，
     *                   4为此价格的订单数量 asks和bids值数组举例说明： ["411.8", "10", "0", "4"]
     *                   - 411.8为深度价格
     *                   - 10为此价格的数量 （合约交易为合约，现货/币币杠杆为交易币的数量）
     *                   - 0该字段已弃用(始终为0)
     *                   - 4为此价格的订单数量
     *                   <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-order-lite-book">
     * 获取产品轻量深度 https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-order-lite-book</a>
     */
    public String liteDepth(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, LITE_DEPTH, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String KLINES = "/api/v5/market/candles";

    /**
     * 获取交易产品K线数据
     * 获取K线数据。K线数据按请求的粒度分组返回，K线数据每个粒度最多可获取最近1,440条。
     * <p>
     * <br><br>
     * GET /api/v5/market/candles
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   <p>
     *                   instId -- String	是	产品ID，如BTC-USD-190927-5000-C <br>
     *                   bar    -- String	否	时间粒度，默认值1m 如 [1m/3m/5m/15m/30m/1H/2H/4H]
     *                   香港时间开盘价k线：[6H/12H/1D/2D/3D/1W/1M/3M/6M/1Y]
     *                   UTC时间开盘价k线：[/6Hutc/12Hutc/1Dutc/2Dutc/3Dutc/1Wutc/1Mutc/3Mutc/6Mutc/1Yutc] <br>
     *                   after  -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     *                   limit  -- String	否	分页返回的结果集数量，最大为300，不填默认返回100条 <br>
     * @return String k线图 List<List<>> 内层的一个list 代表一个柱子
     * 返回的第一条K线数据可能不是完整周期k线，返回值数组顺序分别为是：[ts,o,h,l,c,vol,volCcy,volCcyQuote,confirm]
     * 对于当前周期的K线数据，没有成交时，开高收低默认都取上一周期的收盘价格。<br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-candlesticks">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-candlesticks</a>
     */
    public String klines(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String HISTORY_KLINES = "/api/v5/market/history-candles";

    /**
     * 获取交易产品历史K线数据
     * 获取最近几年的历史k线数据。
     * <p>
     * <br><br>
     * GET /api/v5/market/history-candles
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   <p>
     *                   instId -- String	是	产品ID，如BTC-USD-200927 <br>
     *                   bar    -- String	否	时间粒度，默认值1m 如 [1m/3m/5m/15m/30m/1H/2H/4H]
     *                   香港时间开盘价k线：[6H/12H/1D/2D/3D/1W/1M/3M/6M/1Y]
     *                   UTC时间开盘价k线：[/6Hutc/12Hutc/1Dutc/2Dutc/3Dutc/1Wutc/1Mutc/3Mutc/6Mutc/1Yutc] <br>
     *                   after  -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     *                   limit  -- String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String note:返回值数组顺序分别为是：[ts,o,h,l,c,vol,volCcy,confirm]  <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-candlesticks-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-candlesticks-history</a>
     */
    public String historyKlines(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, HISTORY_KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }


    private final String INDEX_KLINES = "/api/v5/market/index-candles";

    /**
     * 获取指数K线数据
     * 指数K线数据每个粒度最多可获取最近1,440条。 <br>
     * <p>
     * <br><br>
     * GET /api/v5/market/history-index-candles
     * <br>
     * instId -- String	是	现货指数，如BTC-USD <br>
     * after  -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     * before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     * bar    -- String	否	时间粒度，默认值1m 如 [1m/3m/5m/15m/30m/1H/2H/4H]
     * 香港时间开盘价k线：[6H/12H/1D/1W/1M/3M/6M/1Y]
     * UTC时间开盘价k线：[/6Hutc/12Hutc/1Dutc/1Wutc/1Mutc/3Mutc/6Mutc/1Yutc] <br>
     * limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     *
     * @return String
     * note: 返回的第一条K线数据可能不是完整周期k线，返回值数组顺序分别为是：[ts,o,h,l,c,confirm] <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-index-candlesticks">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-index-candlesticks</a>
     */
    public String indexKlines(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, INDEX_KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String HISTORY_INDEX_KLINES = "/api/v5/market/history-index-candles";

    /**
     * 获取指数历史K线数据
     * 获取最近几年的指数K线数据。
     * <p>
     * <br><br>
     * GET /api/v5/market/history-index-candles
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	现货指数，如BTC-USD <br>
     *                   after  -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     *                   bar    -- String	否	时间粒度，默认值1m
     *                   如 [1m/3m/5m/15m/30m/1H/2H/4H]
     *                   香港时间开盘价k线：[6H/12H/1D/1W/1M/3M/6M/1Y]
     *                   UTC时间开盘价k线：[/6Hutc/12Hutc/1Dutc/1Wutc/1Mutc/3Mutc/6Mutc/1Yutc] <br>
     *                   limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * note :返回值数组顺序分别为是：[ts,o,h,l,c,confirm] <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-index-candlesticks-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-index-candlesticks-history</a>
     */
    public String historyIndexKlines(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, HISTORY_INDEX_KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MARK_PRICE = "/api/v5/public/mark-price";

    /**
     * 获取标记价格
     * 为了防止个别用户恶意操控市场导致合约价格波动剧烈，我们根据现货指数和合理基差设定标记价格
     * <p>
     * Current average price for a symbol.
     * <br><br>
     * GET /api/v5/public/mark-price
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- mandatory/string -- the trading pair <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-mark-price">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-mark-price</a>
     */
    public String markPrice(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, MARK_PRICE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MARK_PRICE_CANDLES = "/api/v5/market/mark-price-candles";

    /**
     * 获取标记价格K线数据
     * 标记价格K线数据每个粒度最多可获取最近1,440条。
     * <br><br>
     * GET /api/v5/market/mark-price-candles
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如BTC-USD-SWAP <br>
     *                   after  -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     *                   bar	  -- String	否	时间粒度，默认值1m 如 [1m/3m/5m/15m/30m/1H/2H/4H]
     *                   香港时间开盘价k线：[6H/12H/1D/1W/1M]
     *                   UTC时间开盘价k线：[6Hutc/12Hutc/1Dutc/1Wutc/1Mutc] <br>
     *                   limit  -- String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     *                   note: 返回的第一条K线数据可能不是完整周期k线，返回值数组顺序分别为是：[ts,o,h,l,c,confirm]<br>
     * @param parameters
     * @return
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-mark-price-candlesticks-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-mark-price-candlesticks-history</a>
     */
    public String markPriceCandles(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, MARK_PRICE_CANDLES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String HISTORY_MARK_PRICE_CANDLES = "/api/v5/market/history-mark-price-candles";

    /**
     * 获取标记价格历史K线数据
     * 获取最近几年的标记价格K线数据
     * <br><br>
     * GET /api/v5/market/history-mark-price-candles
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如BTC-USD-SWAP <br>
     *                   after  -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     *                   bar	  -- String	否	时间粒度，默认值1m 如 [1m/3m/5m/15m/30m/1H/2H/4H]
     *                   香港时间开盘价k线：[6H/12H/1D/1W/1M]
     *                   UTC时间开盘价k线：[6Hutc/12Hutc/1Dutc/1Wutc/1Mutc] <br>
     *                   limit  --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     *                   note: 返回值数组顺序分别为是：[ts,o,h,l,c,confirm] <br>
     * @param parameters
     * @return
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-mark-price-candlesticks-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-mark-price-candlesticks-history</a>
     */
    public String historyMarkPriceCandles(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, HISTORY_MARK_PRICE_CANDLES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String TRADES = "/api/v5/market/trades";

    /**
     * 获取交易产品公共成交数据(近期交易记录)
     * Get recent trades.
     * <br><br>
     * GET /api/v5/market/trades
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   param.add("instId","BTC-USDT");
     *                   param.add("limit","10");
     *                   instId -- mandatory/string <br>
     *                   limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-trades">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-trades</a>
     */
    public String trades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String HISTORY_TRADES = "/api/v5/market/history-trades";

    /**
     * 获取交易产品公共历史成交数据
     * Get older market trades.
     * <br><br>
     * GET /api/v5/market/history-trades
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USDT <br>
     *                   type   -- String	否	分页类型 ,如 1：tradeId 分页 2：时间戳分页 默认为 1：tradeId 分页 <br>
     *                   after  -- String	否	请求此 ID 或 ts 之前的分页内容，传的值为对应接口的 tradeId 或 ts <br>
     *                   before -- String	否	请求此ID之后（更新的数据）的分页内容，传的值为对应接口的 tradeId。不支持时间戳分页。<br>
     *                   limit  -- String	否 -- 分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-trades-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-trades-history</a>
     */
    public String historicalTrades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendWithApiKeyRequest(baseUrl, HISTORY_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }


    private final String INSTRUMENT_FAMILY_TRADES = "/api/v5/market/option/instrument-family-trades";

    /**
     * 获取期权品种公共成交数据
     * 查询期权同一个交易品种下的成交信息数据，最多返回100条。
     * <br><br>
     * GET /api/v5/market/option/instrument-family-trades
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instFamily -- String	是	交易品种，如 BTC-USD，适用于期权 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-option-trades">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-option-trades</a>
     */
    public String instrumentFamilyTrades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instFamily", String.class);
        return requestHandler.sendPublicRequest(baseUrl, INSTRUMENT_FAMILY_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String PLATFORM_VOL_24H = "/api/v5/market/platform-24-volume";

    /**
     * 获取平台24小时总成交量
     * 24小时成交量滚动计算，以USD为计价单位，包括大宗交易。
     * <br><br>
     * GET /api/v5/market/platform-24-volume
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   volUsd --	String	24小时平台总成交量，以美元为单位 <br>
     *                   volCny --	String	24小时平台总成交量，以人民币为单位 <br>
     *                   blockVolUsd --	String	24小时平台场外交易总量，以美元为单位 <br>
     *                   blockVolCny --	String	24小时平台场外交易总量，以人民币为单位 <br>
     *                   ts	String	接口返回数据时间 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-24h-total-volume">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-24h-total-volume</a>
     */
    public String platform24Volume(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, PLATFORM_VOL_24H, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ORACLE_PRICE = "/api/v5/market/open-oracle";

    /**
     * Oracle 上链交易数据
     * 获取使用 Open Oracle 智能合约签名后加密资产价格.
     * <br><br>
     * GET /api/v5/market/open-oracle
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   messages -- String	数组包含对[ kind，timestamp，key，value]进行ABI编码的值，
     *                   其中kind恒等于prices，timestamp是获取价格的时间戳，key是加密资产（例如，ETH），value是资产价格 <br>
     *                   prices -- String	价格 <br>
     *                   signatures -- String	每个消息的以太坊兼容ECDSA签名的数组 <br>
     *                   timestamp --	String	获取最新数据点的时间,Unix时间戳,如 1597026387 <br>
     *                   note : 欧易 Oracle公钥是 85615b076615317c80f14cbad6501eec031cd51c <br>
     * @return String <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-oracle">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-oracle </a>
     */
    public String oraclePrice(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, ORACLE_PRICE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String FIAT_CURRENCY_EXCHANGE_RATE = "/api/v5/market/exchange-rate";

    /**
     * 获取法币汇率
     * 该接口提供的是2周的平均汇率数据
     * <br><br>
     * GET /api/v5/market/exchange-rate
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   usdCny -- String	人民币兑美元汇率 <br>
     * @return String <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-exchange-rate">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-exchange-rate </a>
     */
    public String fcurrencyExchangeRate(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, FIAT_CURRENCY_EXCHANGE_RATE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String INDEX_COMPONENTS = "/api/v5/market/index-components";

    /**
     * 获取指数成分数据
     * 查询市场上的指数成分信息数据
     * <br><br>
     * GET /api/v5/market/index-components
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   index --	String	是	指数，如 BTC-USDT <br>
     * @return String <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-index-components">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-index-components </a>
     */
    public String indexComponents(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "index", String.class);
        return requestHandler.sendPublicRequest(baseUrl, INDEX_COMPONENTS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String BLOCK_TICKERS_24H = "/api/v5/market/block-tickers";

    /**
     * 获取大宗交易所有产品行情信息
     * 获取最近24小时大宗交易量
     * <br><br>
     * GET /api/v5/market/block-tickers
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType   -- String	是	产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权 <br>
     *                   uly        -- String	否	标的指数 适用于交割/永续/期权，如 BTC-USD <br>
     *                   instFamily -- String	否	交易品种 适用于交割/永续/期权，如 BTC-USD <br>
     * @return String <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-block-tickers">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-block-tickers </a>
     */
    public String blockTickers24H(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, BLOCK_TICKERS_24H, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String BLOCK_TICKER_24H = "/api/v5/market/block-ticker";

    /**
     * 获取大宗交易单个产品行情信息
     * 获取最近24小时大宗交易量
     * <br><br>
     * GET /api/v5/market/block-ticker
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USD-SWAP <br>
     * @return String <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-block-ticker">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-block-ticker </a>
     */
    public String blockTicker24H(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, BLOCK_TICKER_24H, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String BLOCK_TRADES = "/api/v5/market/block-trades";

    /**
     * 获取大宗交易公共成交数据
     * 查询市场上的成交信息数据,根据 tradeId 倒序排序。
     * <br><br>
     * GET /api/v5/market/block-trades
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId	String	是	产品ID，如 BTC-USDT <br>
     * @return String note: 最多获取最近500条历史公共成交数据<br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-block-trades">
     * https://www.okx.com/docs-v5/zh/#rest-api-market-data-get-block-trades </a>
     */
    public String blockTrades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, BLOCK_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

}
