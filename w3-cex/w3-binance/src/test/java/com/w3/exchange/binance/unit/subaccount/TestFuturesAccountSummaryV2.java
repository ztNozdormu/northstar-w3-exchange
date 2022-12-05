package com.w3.exchange.binance.unit.subaccount;


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

public class TestFuturesAccountSummaryV2 {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final int futuresType = 1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testFuturesSummaryWithoutType() {
        String path = "/sapi/v2/sub-account/futures/accountSummary";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createSubAccount().futuresAccountSummaryV2(parameters));
    }

    @Test
    public void testFuturesSummary() {
        String path = "/sapi/v2/sub-account/futures/accountSummary?futuresType=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("futuresType", futuresType);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSubAccount().futuresAccountSummaryV2(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
