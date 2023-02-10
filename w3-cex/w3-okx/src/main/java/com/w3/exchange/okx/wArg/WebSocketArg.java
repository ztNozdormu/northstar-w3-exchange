package com.w3.exchange.okx.wArg;

import lombok.Data;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * okx webSocket 请求参数封装
 */
@Data
public class WebSocketArg {
    /**
     * @param opEnum subscribe unsubscribe
     * @param instId
     * @return
     */

    public static String buildSubTickers(OpEnum opEnum, String instId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("op", opEnum.name);
        List<JSONObject> args = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("channel", "tickers");
        object.put("instId", instId);
        args.add(object);
        jsonObject.put("args", args);
        return jsonObject.toString();
    }

    /**
     * opEnum subscribe unsubscribe
     *
     * @param channel candle3M candle1M
     *                candle1W
     *                candle1D candle2D candle3D candle5D
     *                candle12H candle6H candle4H candle2H candle1H
     *                candle30m candle15m candle5m candle3m candle1m
     *                candle3Mutc candle1Mutc candle1Wutc candle1Dutc candle2Dutc candle3Dutc candle5Dutc candle12Hutc candle6Hutc
     * @param instId
     * @return
     */
    public static String buildKline(OpEnum opEnum, CandleEnum candleEnum, String instId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("op", opEnum.name);
        List<JSONObject> args = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("channel", candleEnum.name);
        object.put("instId", instId);
        args.add(object);
        jsonObject.put("args", args);
        return jsonObject.toString();
    }

    /**
     * @param opEnum subscribe unsubscribe
     * @return
     */
    public static String buildStatus(OpEnum opEnum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("op", opEnum.name);
        List<JSONObject> args = new ArrayList<>();
        JSONObject object = new JSONObject();
        object.put("channel", "status");
        args.add(object);
        jsonObject.put("args", args);
        return jsonObject.toString();
    }

    /**
     * 订阅/取消订阅枚举
     */
    public enum OpEnum {
        SUB("subscribe"),
        UN_SUB("unsubscribe");
        private String name;

        OpEnum(String name) {
            this.name = name;
        }
    }

    /**
     * kline周期枚举
     */
    public enum CandleEnum {

        /**
         * 分钟
         */
        MIN1("candle1m"),
        MIN3("candle3m"),
        MIN5("candle5m"),
        MIN15("candle15m"),
        MIN30("candle30m"),

        /**
         * 小时
         */
        H1("candle1H"),
        H2("candle2H"),
        H4("candle4H"),
        H6("candle6H"),
        H12("candle12H"),

        H6UTC("candle6Hutc"),
        H12UTC("candle12Hutc"),

        /**
         * 日
         */
        D1("candle1D"),
        D2("candle2D"),
        D3("candle3D"),
        D5("candle5D"),

        D1UTC("candle1Dutc"),
        D2UTC("candle2Dutc"),
        D3UTC("candle3Dutc"),
        D5UTC("candle5Dutc"),

        /**
         * 周
         */
        W1UTC("candle1Wutc"),

        /**
         * 月
         */
        M1UTC("candle1Mutc"),
        M3UTC("candle3Mutc");

        private String name;

        CandleEnum(String name) {
            this.name = name;
        }
    }
}
