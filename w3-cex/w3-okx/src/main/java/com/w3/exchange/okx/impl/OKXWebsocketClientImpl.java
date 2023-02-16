package com.w3.exchange.okx.impl;


import com.w3.exchange.common.client.WebsocketClient;
import com.w3.exchange.common.enums.DefaultUrls;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import com.w3.exchange.common.utils.*;
import com.w3.exchange.okx.wArg.WebSocketArg;
import lombok.Builder;
import lombok.Data;
import okhttp3.Request;
import okio.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * <h2>Websocket Streams</h2>
 * All stream endpoints under the
 * <a href="https://www.okx.com/docs-v5/zh/#websocket-api-overview">websocket api overview</a>
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#websocket-market-streams">Websocket Market Streams</a> and
 * <a href="https://binance-docs.github.io/apidocs/spot/en/#user-data-streams">User Data Streams</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned as callback.
 */
public class OKXWebsocketClientImpl implements WebsocketClient {
    private final String baseUrl;
    private final Map<Integer, WebSocketConnection> connections = new HashMap<>();
    private final WebSocketCallback noopCallback = msg -> {
    };
    private static final Logger logger = LoggerFactory.getLogger(OKXWebsocketClientImpl.class);

    public OKXWebsocketClientImpl() {
        this.baseUrl = DefaultUrls.WS_URL_OKX;
    }

    public OKXWebsocketClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * The Aggregate Trade Streams push trade information that is aggregated for a single taker order.
     * <br><br>
     * &lt;symbol&gt;@aggTrade
     * <br><br>
     * Update Speed: Real-time
     *
     * @param symbol Name of trading pair
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#aggregate-trade-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#aggregate-trade-streams</a>
     */
    @Override
    public int aggTradeStream(String symbol, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return aggTradeStream(symbol.toLowerCase(), noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #aggTradeStream(String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int aggTradeStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@aggTrade", baseUrl, symbol.toLowerCase()));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * The Trade Streams push raw trade information; each trade has a unique buyer and seller.
     * <br><br>
     * &lt;symbol&gt;@trade
     * <br><br>
     * Update Speed: Real-time
     *
     * @param symbol Name of trading pair
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#trade-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#trade-streams</a>
     */
    @Override
    public int tradeStream(String symbol, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return tradeStream(symbol.toLowerCase(), noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #tradeStream(String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int tradeStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@trade", baseUrl, symbol.toLowerCase()));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * The Kline/Candlestick Stream push updates to the current klines/candlestick every second.
     * <br><br>
     * &lt;symbol&gt;@kline_&lt;interval&gt;
     * <br><br>
     * Update Speed: Real-time
     *
     * @param symbol Name of trading pair
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#kline-candlestick-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#kline-candlestick-streams</a>
     */
    @Override
    public int klineStream(String symbol, String interval, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return klineStream(symbol.toUpperCase(), interval, noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #klineStream(String, String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param interval
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int klineStream(String symbol, String interval, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        String arg = WebSocketArg.buildKline(WebSocketArg.OpEnum.SUB,WebSocketArg.CandleEnum.valueOf(interval),symbol);
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/v5/public", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request,arg);
    }

    /**
     * 24hr rolling window mini-ticker statistics.
     * These are NOT the statistics of the UTC day, but a 24hr rolling window for the previous 24hrs.
     * <br><br>
     * &lt;symbol&gt;@miniTicker
     * <br><br>
     * Update Speed: Real-time
     *
     * @param symbol Name of trading pair
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#individual-symbol-mini-ticker-stream">
     * https://binance-docs.github.io/apidocs/spot/en/#individual-symbol-mini-ticker-stream</a>
     */
    @Override
    public int miniTickerStream(String symbol, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return miniTickerStream(symbol.toLowerCase(), noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #miniTickerStream(String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int miniTickerStream(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@miniTicker", baseUrl, symbol.toLowerCase()));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * WebSocket 频道分成两类： 公共频道 和 私有频道
     * 公共频道无需登录，包括行情频道，K线频道，交易数据频道，资金费率频道，限价范围频道，深度数据频道，标记价格频道等。
     * 私有频道需登录，包括用户账户频道，用户交易频道，用户持仓频道等。
     * 用户可以选择订阅一个或者多个频道，多个频道总长度不能超过4,096个字节。
     * <br><br>
     * !miniTicker@arr
     * <br><br>
     * Update Speed: Real-time
     *
     * @return int - Connection ID
     * @see <a href="https://www.okx.com/docs-v5/zh/#websocket-api-subscribe">
     * https://www.okx.com/docs-v5/zh/#websocket-api-subscribe</a>
     */
    @Override
    public int allMiniTickerStream(WebSocketCallback callback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/v5/public", baseUrl));
        return allMiniTickerStream(noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #allMiniTickerStream(WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int allMiniTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!miniTicker@arr", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * 24hr rolling window ticker statistics for a single symbol.
     * These are NOT the statistics of the UTC day, but a 24hr rolling window for the previous 24hrs.
     * <br><br>
     * &lt;symbol&gt;@ticker
     * <br><br>
     * Update Speed: Real-time
     *
     * @param symbol Name of trading pair
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#individual-symbol-ticker-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#individual-symbol-ticker-streams</a>
     */
    @Override
    public int symbolTicker(String symbol, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return symbolTicker(symbol.toUpperCase(), noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #symbolTicker(String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int symbolTicker(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        String arg = WebSocketArg.buildSubTickers(WebSocketArg.OpEnum.SUB,symbol);
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/v5/public", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request,arg);
    }

    /**
     * 24hr rolling window ticker statistics for all symbols that changed in an array.
     * These are NOT the statistics of the UTC day, but a 24hr rolling window for the previous 24hrs.
     * Note that only tickers that have changed will be present in the array.
     * <br><br>
     * !ticker@arr
     * <br><br>
     * Update Speed: Real-time
     *
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#all-market-tickers-stream">
     * https://binance-docs.github.io/apidocs/spot/en/#all-market-tickers-stream</a>
     */
    @Override
    public int allTickerStream(WebSocketCallback callback) {
        return allTickerStream(noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #allTickerStream(WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int allTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!ticker@arr", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Rolling window ticker statistics for a single symbol, computed over multiple windows.
     * <br><br>
     * &lt;symbol&gt;@ticker_&lt;window_size&gt;
     * <br><br>
     * Update Speed: Real-time
     *
     * @param symbol Name of trading pair
     * @param windowSize Window Sizes: 1h,4h
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#individual-symbol-rolling-window-statistics-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#individual-symbol-rolling-window-statistics-streams</a>
     */
    public int rollingWindowTicker(String symbol, String windowSize, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        ParameterChecker.checkParameterType(symbol, String.class, "windowSize");
        ArrayList<String> allowedWindowSize = new ArrayList<String>() {{
                add("1h");
                add("4h");
            }};
        if (!allowedWindowSize.contains(windowSize)) {
            throw new ExchangeConnectorException(String.format("\"%s\" is not a valid window size.", windowSize));
        }
        return rollingWindowTicker(symbol.toLowerCase(), windowSize, noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #rollingWindowTicker(String, String, WebSocketCallback)} (String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int rollingWindowTicker(String symbol, String windowSize, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        ParameterChecker.checkParameterType(symbol, String.class, "windowSize");
        ArrayList<String> allowedWindowSize = new ArrayList<String>() {{
                add("1h");
                add("4h");
            }};
        if (!allowedWindowSize.contains(windowSize)) {
            throw new ExchangeConnectorException(String.format("\"%s\" is not a valid window size.", windowSize));
        }
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@ticker_%s", baseUrl, symbol.toLowerCase(), windowSize));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Rolling window ticker statistics for all market symbols, computed over multiple windows.
     * Note that only tickers that have changed will be present in the array.
     * <br><br>
     * !ticker_&lt;window-size&gt;@arr
     * <br><br>
     * Update Speed: Real-time
     *
     * @param windowSize Window Sizes: 1h,4h
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#all-market-rolling-window-statistics-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#all-market-rolling-window-statistics-streams</a>
     */
    @Override
    public int allRollingWindowTicker(String windowSize, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(windowSize, String.class, "windowSize");
        ArrayList<String> allowedWindowSize = new ArrayList<String>() {{
                add("1h");
                add("4h");
            }};
        if (!allowedWindowSize.contains(windowSize.toLowerCase())) {
            throw new ExchangeConnectorException(String.format("\"%s\" is not a valid window size.", windowSize.toLowerCase()));
        }
        return allRollingWindowTicker(windowSize.toLowerCase(), noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #allRollingWindowTicker(String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param windowSize
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int allRollingWindowTicker(String windowSize, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(windowSize, String.class, "windowSize");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!ticker_%s@arr", baseUrl, windowSize.toLowerCase()));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Pushes any update to the best bid or ask's price or quantity in real-time for a specified symbol.
     * <br><br>
     * &lt;symbol&gt;@bookTicker
     * <br><br>
     * Update Speed: Real-time
     *
     * @param symbol Name of trading pair
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#individual-symbol-book-ticker-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#individual-symbol-book-ticker-streams</a>
     */
    @Override
    public int bookTicker(String symbol, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return bookTicker(symbol.toLowerCase(), noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #bookTicker(String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int bookTicker(String symbol, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@bookTicker", baseUrl, symbol.toLowerCase()));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Pushes any update to the best bid or ask's price or quantity in real-time for all symbols.
     * <br><br>
     * !bookTicker
     * <br><br>
     * Update Speed: Real-time
     *
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#all-book-tickers-stream">
     * https://binance-docs.github.io/apidocs/spot/en/#all-book-tickers-stream</a>
     */
    @Override
    public int allBookTickerStream(WebSocketCallback callback) {
        return allBookTickerStream(noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #allBookTickerStream(WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int allBookTickerStream(WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/!bookTicker", baseUrl));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Top bids and asks, Valid are 5, 10, or 20.
     * <br><br>
     * &lt;symbol&gt;@depth&lt;levels&gt;@&lt;speed&gt;ms
     * <br><br>
     * Update Speed: 1000ms or 100ms
     *
     * @param symbol Name of trading pair
     * @param levels Valid are 5, 10, or 20
     * @param speed  1000ms or 100ms
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#partial-book-depth-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#partial-book-depth-streams</a>
     */
    @Override
    public int partialDepthStream(String symbol, int levels, int speed, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return partialDepthStream(symbol.toLowerCase(), levels, speed, noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #partialDepthStream(String, int, int, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param levels
     * @param speed
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int partialDepthStream(String symbol, int levels, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@depth%s@%sms", baseUrl, symbol.toLowerCase(), levels, speed));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Order book price and quantity depth updates used to locally manage an order book.
     * <br><br>
     * &lt;symbol&gt;@depth@&lt;speed&gt;ms
     * <br><br>
     * Update Speed: 1000ms or 100ms
     *
     * @param symbol Name of trading pair
     * @param speed  1000ms or 100ms
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#diff-depth-stream">
     * https://binance-docs.github.io/apidocs/spot/en/#diff-depth-stream</a>
     */
    @Override
    public int diffDepthStream(String symbol, int speed, WebSocketCallback callback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        return diffDepthStream(symbol.toLowerCase(), speed, noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #diffDepthStream(String, int, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param symbol
     * @param speed
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int diffDepthStream(String symbol, int speed, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        ParameterChecker.checkParameterType(symbol, String.class, "symbol");
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s@depth@%sms", baseUrl, symbol.toLowerCase(), speed));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * User Data Streams are accessed at /ws/&lt;listenKey&gt;
     *
     * @param listenKey listen key obtained from this
     *                  <a href="https://binance-docs.github.io/apidocs/spot/en/#listen-key-spot">endpoint</a>
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#user-data-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#user-data-streams</a>
     */
    @Override
    public int listenUserStream(String listenKey, WebSocketCallback callback) {
        return listenUserStream(listenKey, noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #listenUserStream(String, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param listenKey
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int listenUserStream(String listenKey, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebsocketRequest(String.format("%s/ws/%s", baseUrl, listenKey));
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Combined streams are accessed at /stream?streams=&lt;streamName1&gt;/&lt;streamName2&gt;/&lt;streamName3&gt;
     *
     * @param streams A list of stream names to be combined <br>
     * @return int - Connection ID
     * @see <a href="https://binance-docs.github.io/apidocs/spot/en/#websocket-market-streams">
     * https://binance-docs.github.io/apidocs/spot/en/#websocket-market-streams</a>
     */
    @Override
    public int combineStreams(ArrayList<String> streams, WebSocketCallback callback) {
        return combineStreams(streams, noopCallback, callback, noopCallback, noopCallback);
    }

    /**
     * Same as {@link #combineStreams(ArrayList, WebSocketCallback)} plus accepts callbacks for all major websocket connection events.
     *
     * @param streams
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @return
     */
    @Override
    public int combineStreams(ArrayList<String> streams, WebSocketCallback onOpenCallback, WebSocketCallback onMessageCallback, WebSocketCallback onClosingCallback, WebSocketCallback onFailureCallback) {
        String url = UrlBuilder.buildStreamUrl(String.format("%s/stream", baseUrl), streams);
        Request request = RequestBuilder.buildWebsocketRequest(url);
        return createConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
    }

    /**
     * Closes a specific stream based on stream Id.
     *
     * @param connectionId
     */
    @Override
    public void closeConnection(int connectionId) {
        if (connections.containsKey(connectionId)) {
            connections.get(connectionId).close();
            logger.info("Closing Connection ID {}", connectionId);
            connections.remove(connectionId);
        } else {
            logger.info("Connection ID {} does not exist!", connectionId);
        }
    }

    /**
     * Closes all streams
     */
    @Override
    public void closeAllConnections() {
        if (!connections.isEmpty()) {
            logger.info("Closing {} connections(s)", connections.size());
            Iterator<Map.Entry<Integer, WebSocketConnection>> iter = connections.entrySet().iterator();
            while (iter.hasNext()) {
                WebSocketConnection connection = iter.next().getValue();
                connection.close();
                iter.remove();
            }
        }

        if (connections.isEmpty()) {
            OkHttpUtils.builder().okHttpClient.dispatcher().executorService().shutdown();
            logger.info("All connections are closed!");
        }
    }

    private int createConnection(
            WebSocketCallback onOpenCallback,
            WebSocketCallback onMessageCallback,
            WebSocketCallback onClosingCallback,
            WebSocketCallback onFailureCallback,
            Request request
    ) {
        WebSocketConnection connection = new WebSocketConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
        connection.connect();
        int connectionId = connection.getConnectionId();
        connections.put(connectionId, connection);
        return connectionId;
    }
    private int createConnection(
            WebSocketCallback onOpenCallback,
            WebSocketCallback onMessageCallback,
            WebSocketCallback onClosingCallback,
            WebSocketCallback onFailureCallback,
            Request request,
            String message
    ) {
        WebSocketConnection connection = new WebSocketConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request, message);
        connection.connect();
        int connectionId = connection.getConnectionId();
        connections.put(connectionId, connection);
        return connectionId;
    }

    public boolean isConnected() {
        logger.info("Closing {} connections(s)", connections.size());
        return connections.isEmpty();
    }
}
