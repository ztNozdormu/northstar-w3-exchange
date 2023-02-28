package io.github.ztnozdormu.binance.unit.subaccount;


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

public class TestSubAccountTransferHist {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final int startTime = 100001;
    private final int endTime = 100002;
    private final int type = 1;
    private final int limit = 1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testTransferHist() {
        String path = "/sapi/v1/sub-account/transfer/subUserHistory?asset=BNB&type=1&startTime=100001&endTime=100002&limit=1";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("asset", "BNB");
        parameters.put("type", type);
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        parameters.put("limit", limit);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSubAccount().transferHistory(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
