package com.w3.exchange.common.exceptions;

public class ExchangeConnectorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExchangeConnectorException(String fullErrMsg) {
        super(fullErrMsg);
    }

}
