package io.github.ztnozdormu.binance.unit.wallet;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;


import java.util.LinkedHashMap;
import static org.junit.Assert.assertEquals;

public class TestAssetDividend {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testAssetDividend() {
        String path = "/sapi/v1/asset/assetDividend?asset=BNB&startTime=1591063000087&endTime=1591063000087&limit=10";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "BNB");
        parameters.put("startTime", "1591063000087");
        parameters.put("endTime", "1591063000087");
        parameters.put("limit", "10");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createWallet().assetDividend(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
