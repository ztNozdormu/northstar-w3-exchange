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

public class TestRemoveLiquidityPreview {
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
    public void testRemoveLiquidityPreviewWithoutParameters() {
        String path = "/sapi/v1/bswap/removeLiquidityPreview";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createBswap().removeLiquidityPreview(parameters));
    }

    @Test
    public void testRemoveLiquidityPreview() {
        String path = "/sapi/v1/bswap/removeLiquidityPreview?poolId=1&type=SINGLE&quoteAsset=USDT&shareAmount=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("poolId", poolId);
        parameters.put("type", "SINGLE");
        parameters.put("quoteAsset", "USDT");
        parameters.put("shareAmount", shareAmount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createBswap().removeLiquidityPreview(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
