package com.w3.exchange.binance.unit.market;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.unit.MockData;
import com.w3.exchange.binance.unit.MockWebServerDispatcher;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestKlines {
    private MockWebServer mockWebServer;
    private String baseUrl;
    private final long startTime = System.currentTimeMillis();
    private final long endTime = startTime + 1000;

    private final int limit = 1000;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testKlinesWithoutSymbol() {
        String path = "/api/v3/klines?interval=1m";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("interval", "1m");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(null, null, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createMarket().klines(parameters));
    }

    @Test
    public void testKlinesWithoutInterval() {
        String path = "/api/v3/klines?symbol=BNBUSDT";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(null, null, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createMarket().klines(parameters));
    }

    @Test
    public void testKlines() {
        String path = "/api/v3/klines?symbol=BNBUSDT&interval=1m";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("interval", "1m");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(null, null, baseUrl);
        String result = client.createMarket().klines(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }

    @Test
    public void testKlinesWithParameters() {
        String path = String.format("/api/v3/klines?symbol=BNBUSDT&interval=1m&limit=1000&startTime=%s&endTime=%s", startTime, endTime);
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("interval", "1m");
        parameters.put("limit", limit);
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(null, null, baseUrl);
        String result = client.createMarket().klines(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
