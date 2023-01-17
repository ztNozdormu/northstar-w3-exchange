package com.w3.exchange.cryptocompare.examples;

import com.w3.exchange.cryptocompare.impl.CompareClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashMap;

@Slf4j
public class CompareRankTest {

    /**
     * 过去24小时内,币种在所有市场（交易所）总交易量排行榜
     */
    @Test
    public void marketVolRankTest() {
        CompareClientImpl client =  CompareClientImpl.builder()
                        .baseUrl("https://min-api.cryptocompare.com")
                        .apiKey("")
                        .build();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        // 获取以USDT交易的总交易量前十币种列表
        parameters.put("limit", 10);
        parameters.put("tsym", "USDT");
        String result = client.createRank().marketVolRank(parameters);
        log.info("过去24小时内,币种在所有市场（交易所）,用{}币种交易的总交易量前{}，币种排行榜列表:{} br",parameters.get("tsym") ,parameters.get("limit") ,result);
    }
}
