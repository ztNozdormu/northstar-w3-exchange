package io.github.ztnozdormu.binance.unit.bswap;


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

public class TestSwap {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final double quoteQty = 1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testSwapWithoutParameters() {
        String path = "/sapi/v1/bswap/swap";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createBswap().swap(parameters));
    }

    @Test
    public void testSwap() {
        String path = "/sapi/v1/bswap/swap?quoteAsset=BUSD&baseAsset=USDT&quoteQty=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("quoteAsset", "BUSD");
        parameters.put("baseAsset", "USDT");
        parameters.put("quoteQty", quoteQty);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createBswap().swap(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
