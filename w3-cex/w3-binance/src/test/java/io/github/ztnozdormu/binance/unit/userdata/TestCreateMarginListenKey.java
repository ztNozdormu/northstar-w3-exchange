package io.github.ztnozdormu.binance.unit.userdata;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class TestCreateMarginListenKey {
    private MockWebServer mockWebServer;
    private String baseUrl;
    
    private final String MOCK_RESPONSE = "{\"listenKey\": \"value_1\", \"key_2\": \"value_2\"}";

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCreateListenKey() {
        String path = "/sapi/v1/userDataStream";

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createUserData().createMarginListenKey();
        assertEquals(MOCK_RESPONSE, result);
    }
}
