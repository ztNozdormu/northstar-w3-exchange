package io.github.ztnozdormu.binance.unit.subaccount;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.exceptions.ExchangeConnectorException;
import io.github.ztnozdormu.common.utils.UrlBuilder;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;


import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

public class TestSubAccountToSub {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final double amount = 0.1;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testSubToSubWithoutParameters() {
        String path = "/sapi/v1/sub-account/transfer/subToSub";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createSubAccount().subAccountToSubAccount(parameters));
    }

    @Test
    public void testSubToSub() {
        String path = String.format("/sapi/v1/sub-account/transfer/subToSub?toEmail=%s&asset=BNB&amount=0.1",
                UrlBuilder.urlEncode("alice@test.com"));
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("toEmail", "alice@test.com");
        parameters.put("asset", "BNB");
        parameters.put("amount", amount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createSubAccount().subAccountToSubAccount(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
