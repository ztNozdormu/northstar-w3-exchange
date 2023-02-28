package io.github.ztnozdormu.binance.unit.subaccount;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.utils.UrlBuilder;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;


import java.util.LinkedHashMap;
import static org.junit.Assert.assertEquals;

public class TestSubAccountStatus {
    private MockWebServer mockWebServer;
    private String baseUrl;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testAccountStatus() {
        String path = String.format("/sapi/v1/sub-account/status?email=%s",
                UrlBuilder.urlEncode("alice@test.com"));
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("email", "alice@test.com");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSubAccount().accountStatus(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
