package com.w3.exchange.binance.unit.bswap;


import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.unit.MockData;
import com.w3.exchange.binance.unit.MockWebServerDispatcher;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;

public class TestLiquidityRemove {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final long poolId = 1L;
    private final double shareAmount = 1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testLiquidityRemoveWithoutParameters() {
        String path = "/sapi/v1/bswap/liquidityRemove";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);

        assertThrows(ExchangeConnectorException.class, () -> client.createBswap().liquidityRemove(parameters));
    }

    @Test
    public void testLiquidityRemove() {
        String path = "/sapi/v1/bswap/liquidityRemove?poolId=1&type=SINGLE&shareAmount=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("poolId", poolId);
        parameters.put("type", "SINGLE");
        parameters.put("shareAmount", shareAmount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createBswap().liquidityRemove(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
