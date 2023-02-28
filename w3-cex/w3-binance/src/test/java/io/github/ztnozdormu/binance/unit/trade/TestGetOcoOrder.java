package io.github.ztnozdormu.binance.unit.trade;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.exceptions.ExchangeClientException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

public class TestGetOcoOrder {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final int orderListId = 123;
    private final int origClientOrderId = 456;
    private final int recvWindow = 1000;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testGetOcoOrderWithoutId() {
        String path = "/api/v3/orderList";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_CLIENT_ERROR);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeClientException.class, () -> client.createTrade().getOCOOrder(parameters));
    }

    @Test
    public void testCancelOpenOrders() {
        String path = "/api/v3/orderList?symbol=BNBUSDT&orderListId=123&origClientOrderId=456&recvWindow=1000";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("orderListId", orderListId);
        parameters.put("origClientOrderId", origClientOrderId);
        parameters.put("recvWindow", recvWindow);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createTrade().getOCOOrder(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
