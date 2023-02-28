package io.github.ztnozdormu.binance.unit.margin;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.utils.UrlBuilder;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.util.LinkedHashMap;

public class TestIsolatedAccount {
    private MockWebServer mockWebServer;
    private String baseUrl;
    
    private final int endTime = 12345679;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }


    @Test
    public void testIsolatedAccount() {
        String path = String.format("/sapi/v1/margin/isolated/account?symbols=%s",
                UrlBuilder.urlEncode("BNBUSDT,BTCUSDT"));
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbols", "BNBUSDT,BTCUSDT");
        parameters.put("endTime", endTime);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createMargin().isolatedAccount(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
