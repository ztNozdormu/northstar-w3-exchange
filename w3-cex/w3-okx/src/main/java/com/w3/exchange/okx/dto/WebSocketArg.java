package com.w3.exchange.okx.dto;

import lombok.Data;
import org.json.JSONObject;

import java.util.List;

/**
 *  {
 *                           "op": "subscribe",
 *                           "args": [
 *                             {
 *                               "channel": "tickers",
 *                               "instId": ""
 *                             }
 *                           ]
 *  }
 */
@Data
public class WebSocketArg {
    /**
     * examples: subscribe
     */
    String op;
    /**
     * 参数: [
     *         {
     *           "channel": "tickers",
     *           "instId": ""
     *         }
     *      ]
     */
    List<JSONObject> args;
}
