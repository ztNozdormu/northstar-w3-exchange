package io.github.ztnozdormu.binance.unit.userdata;


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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestCloseListenKey {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCloseListenKeyWithoutParameters() {
        String path = "/api/v3/userDataStream";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createUserData().closeListenKey(parameters));
    }

    @Test
    public void testCloseListenKey() {
        String path = "/api/v3/userDataStream?listenKey=test";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("listenKey", "test");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.DELETE, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createUserData().closeListenKey(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
