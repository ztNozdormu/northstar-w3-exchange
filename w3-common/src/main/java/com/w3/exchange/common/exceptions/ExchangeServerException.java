package com.w3.exchange.common.exceptions;

public class ExchangeServerException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final int invalidStatusCode = -1;
    private final int httpStatusCode;

    public ExchangeServerException(String fullErrMsg) {
        super(fullErrMsg);
        this.httpStatusCode = invalidStatusCode;
    }

    public ExchangeServerException(String fullErrMsg, int httpStatusCode) {
        super(fullErrMsg);
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
