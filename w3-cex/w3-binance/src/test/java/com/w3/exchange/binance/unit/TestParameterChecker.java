package com.w3.exchange.binance.unit;

import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import com.w3.exchange.common.utils.ParameterChecker;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class TestParameterChecker {

    private final String mockObject = "mockObject";
    private final String emptyString = "";


    @Test
    public void testcheckParameter() {
        ParameterChecker.checkParameter(MockData.MOCK_PARAMETERS, "key1", String.class);
        ParameterChecker.checkParameter(MockData.MOCK_PARAMETERS, "key2", Integer.class);
    }

    @Test
    public void testcheckParameterNoKey() {
        assertThrows(ExchangeConnectorException.class, () -> ParameterChecker.checkRequiredParameter(MockData.MOCK_PARAMETERS, "InvalidKey"));
    }

    @Test
    public void testcheckParameterWrongType() {
        assertThrows(ExchangeConnectorException.class, () -> ParameterChecker.checkParameterType(mockObject, Integer.class, "mockObject"));
    }

    @Test
    public void testcheckEmptyString() {
        assertThrows(ExchangeConnectorException.class, () -> ParameterChecker.checkParameterType(emptyString, String.class, "mockObject"));
    }

    @Test
    public void testcheckNull() {
        assertThrows(ExchangeConnectorException.class, () -> ParameterChecker.checkParameterType(null, String.class, "mockObject"));
    }

}
