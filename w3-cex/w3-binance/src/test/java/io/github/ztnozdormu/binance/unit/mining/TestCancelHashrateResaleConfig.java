package io.github.ztnozdormu.binance.unit.mining;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.exceptions.ExchangeConnectorException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;


import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

public class TestCancelHashrateResaleConfig {
    private MockWebServer mockWebServer;
    private String baseUrl;
    
    private final int configId = 123;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCancelHashrateResaleConfigWithoutParameters() {
        String path = "/sapi/v1/mining/hash-transfer/config/cancel";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createMining().cancelHashrateResaleConfig(parameters));
    }

    @Test
    public void testCancelHashrateResaleConfig() {
        String path = "/sapi/v1/mining/hash-transfer/config/cancel?configId=123&userName=test";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("configId", configId);
        parameters.put("userName", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createMining().cancelHashrateResaleConfig(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
