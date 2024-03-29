package io.github.ztnozdormu.binance.unit.mining;


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

public class TestHashrateResaleList {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final int pageIndex = 1;
    private final int pageSize = 1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testHashrateResaleList() {
        String path = "/sapi/v1/mining/hash-transfer/config/details/list?pageIndex=1&pageSize=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("pageIndex", pageIndex);
        parameters.put("pageSize", pageSize);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createMining().hashrateResaleList(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
