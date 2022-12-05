package com.w3.exchange.binance.unit.cryptoloans;

import com.w3.exchange.binance.impl.BISpotClientImpl;
import com.w3.exchange.binance.unit.MockData;
import com.w3.exchange.binance.unit.MockWebServerDispatcher;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

public class TestLoanBorrow {
    private MockWebServer mockWebServer;
    private String baseUrl;

    private static final double loanAmount = 100.5;
    private static final int loanTerm = 7;

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testLoanBorrowWithoutParameters() {
        String path = "/sapi/v1/loan/borrow";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        assertThrows(ExchangeConnectorException.class, () -> client.createCryptoLoans().loanBorrow(parameters));
    }

    @Test
    public void testLoanBorrow() {
        String path = "/sapi/v1/loan/borrow?loanCoin=BUSD&collateralCoin=BNB&loanAmount=100.5&loanTerm=7&";

        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("loanCoin", "BUSD");
        parameters.put("collateralCoin", "BNB");
        parameters.put("loanAmount", loanAmount);
        parameters.put("loanTerm", loanTerm);

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.POST, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        BISpotClientImpl client = new BISpotClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.createCryptoLoans().loanBorrow(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
