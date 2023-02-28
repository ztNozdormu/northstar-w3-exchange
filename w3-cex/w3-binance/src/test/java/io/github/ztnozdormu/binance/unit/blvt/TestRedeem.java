package io.github.ztnozdormu.binance.unit.blvt;

import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.exceptions.ExchangeConnectorException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;

public class TestRedeem {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final double amount = 1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testRedeemWithoutParameters() {
        String path = "/sapi/v1/blvt/redeem";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createBlvt().redeem(parameters));
    }

    @Test
    public void testRedeem() {
        String path = "/sapi/v1/blvt/redeem?tokenName=USDT&amount=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("tokenName", "USDT");
        parameters.put("amount", amount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createBlvt().redeem(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
