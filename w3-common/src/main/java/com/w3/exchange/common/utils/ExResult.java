package com.w3.exchange.common.utils;

import lombok.Data;

/**
 * 交易所接口返回结果基本数据结构
 */
@Data
public class ExResult<T> {
    /**
     * 返回状态
     */
    public int code;
    /**
     * 提示信息
     */
    public String msg;

    /**
     * 返回数据
     */
    public T data;

    public ExResult() {

    }
    public ExResult(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

}
