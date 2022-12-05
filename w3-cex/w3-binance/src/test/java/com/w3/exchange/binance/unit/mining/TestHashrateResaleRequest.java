package com.w3.exchange.binance.unit.mining;


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

public class TestHashrateResaleRequest {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final long endDate = 1234567L;
    private final long startDate = 1234566L;
    private final long hashRate = 1111L;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testHashrateResaleRequestWithoutParameters() {
        String path = "/sapi/v1/mining/hash-transfer/config";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createMining().hashrateResaleRequest(parameters));
    }

    @Test
    public void testHashrateResaleRequest() {
        String path = "/sapi/v1/mining/hash-transfer/config?userName=test&algo=sha256&endDate=1234567&startDate=1234566&toPoolUser=test&hashRate=1111";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("userName", "test");
        parameters.put("algo", "sha256");
        parameters.put("endDate", endDate);
        parameters.put("startDate", startDate);
        parameters.put("toPoolUser", "test");
        parameters.put("hashRate", hashRate);


        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createMining().hashrateResaleRequest(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
