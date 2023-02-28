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
import static org.junit.Assert.assertThrows;
import java.util.LinkedHashMap;

public class TestLiquidityAdd {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final long poolId = 1L;
    private final double quantity = 1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testLiquidityAddWithoutParameters() {
        String path = "/sapi/v1/bswap/liquidityAdd";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createBswap().liquidityAdd(parameters));
    }

    @Test
    public void testLiquidityAdd() {
        String path = "/sapi/v1/bswap/liquidityAdd?poolId=1&asset=USDT&quantity=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("poolId", poolId);
        parameters.put("asset", "USDT");
        parameters.put("quantity", quantity);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createBswap().liquidityAdd(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
