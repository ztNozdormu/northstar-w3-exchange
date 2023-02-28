package io.github.ztnozdormu.binance.unit.wallet;


import io.github.ztnozdormu.binance.impl.BISpotClientImpl;
import io.github.ztnozdormu.binance.unit.MockData;
import io.github.ztnozdormu.binance.unit.MockWebServerDispatcher;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.exceptions.ExchangeConnectorException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;


import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

public class TestWithdraw {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private final double amount = 0.01;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testWithdrawWithoutCoin() {
        String path = "/sapi/v1/capital/withdraw/apply?amount=0.01&address=add";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("amount", amount);
        parameters.put("address", "add");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createWallet().withdraw(parameters));
    }

    @Test
    public void testWithdrawWithoutAmount() {
        String path = "/sapi/v1/capital/withdraw/apply?coin=BNB&address=add";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BNB");
        parameters.put("address", "add");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createWallet().withdraw(parameters));
    }

    @Test
    public void testWithdrawWithoutAddress() {
        String path = "/sapi/v1/capital/withdraw/apply?coin=BNB&amount=0.01";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BNB");
        parameters.put("amount", amount);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createWallet().withdraw(parameters));
    }

    @Test
    public void testWithdraw() {
        String path = "/sapi/v1/capital/withdraw/apply?coin=BNB&amount=0.01&address=add";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("coin", "BNB");
        parameters.put("amount", amount);
        parameters.put("address", "add");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createWallet().withdraw(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
