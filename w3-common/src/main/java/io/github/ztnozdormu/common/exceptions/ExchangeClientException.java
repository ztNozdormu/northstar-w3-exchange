package io.github.ztnozdormu.common.exceptions;

public class ExchangeClientException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final int ERROR_CODE_0 = 0;
    private final int httpStatusCode;
    private final int errorCode;
    private String errMsg;

    public ExchangeClientException(String fullErrMsg, int httpStatusCode) {
        super(fullErrMsg);
        this.httpStatusCode = httpStatusCode;
        this.errorCode = ERROR_CODE_0;
    }

    public ExchangeClientException(String fullErrMsg, String errMsg, int httpStatusCode, int errorCode) {
        super(fullErrMsg);
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.errMsg =  errMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
