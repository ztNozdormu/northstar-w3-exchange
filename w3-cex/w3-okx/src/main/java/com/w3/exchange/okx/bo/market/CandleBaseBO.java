package com.w3.exchange.okx.bo.market;

import lombok.Data;

/**
 * K线数据基础对象
 */
@Data
public class CandleBaseBO {
    /**
     * 开始时间，Unix时间戳的毫秒数格式，如 1597026383085
     */
    private String ts;
    /**
     * 开盘价格
     */
    private String o;
    /**
     * 最高价格
     */
    private String h;
    /**
     * 最低价格
     */
    private String l;
    /**
     * 收盘价格
     */
    private String c;
    /**
     * K线状态
     * 0 代表 K 线未完结，1 代表 K 线已完结。
     */
    private String	confirm;
}
