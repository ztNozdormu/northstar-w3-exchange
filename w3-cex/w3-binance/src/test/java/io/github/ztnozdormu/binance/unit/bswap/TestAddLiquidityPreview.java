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

public class TestAddLiquidityPreview {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final long poolId = 1L;
    private final double quoteQty = 1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testAddLiquidityPreviewWithoutParameters() {
        String path = "/sapi/v1/bswap/addLiquidityPreview";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createBswap().addLiquidityPreview(parameters));
    }

    @Test
    public void testAddLiquidityPreview() {
        String path = "/sapi/v1/bswap/addLiquidityPreview?poolId=1&type=SINGLE&quoteAsset=USDT&quoteQty=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("poolId", poolId);
        parameters.put("type", "SINGLE");
        parameters.put("quoteAsset", "USDT");
        parameters.put("quoteQty", quoteQty);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createBswap().addLiquidityPreview(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
