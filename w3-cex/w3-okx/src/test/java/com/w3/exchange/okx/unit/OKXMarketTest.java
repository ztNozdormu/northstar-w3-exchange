package com.w3.exchange.okx.unit;

import com.w3.exchange.okx.impl.OKXSpotClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashMap;

@Slf4j
public class OKXMarketTest {


    @Test
    public void tickers() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instType", "SWAP");
        parameters.put("instFamily", "BTC-USDT");
        String result = client.createMarket().tickers(parameters);
        log.info("tickers数据为:{}", result);
    }

    @Test
    public void ticker() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instId", "ETH-USDT-SWAP");
        String result = client.createMarket().ticker(parameters);
        log.info("ticker数据为:{}", result);
    }
    @Test
    public void depth() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instId", "BTC-USDT");
        parameters.put("sz",20);
        String result = client.createMarket().depth(parameters);
        log.info("产品深度列表:{}", result);
    }

    @Test
    public void blockTicker24H() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instId", "BTC-USDT-SWAP");
        String result = client.createMarket().blockTicker24H(parameters);
        log.info("获取最近24小时大宗交易量:{}", result);
    }

    @Test
    public void blockTickers24H() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instType", "SWAP");
        String result = client.createMarket().blockTickers24H(parameters);
        log.info("获取最近24小时所有产品大宗交易量:{}", result);
    }
    @Test
    public void blockTrades() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instId", "BTC-USDT");
        String result = client.createMarket().blockTrades(parameters);
        log.info("市场上的成交信息数据:{}", result);
    }

    @Test
    public void klines() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instId", "BTC-USDT");
        String result = client.createMarket().klines(parameters);
        log.info("交易产品K线数据获取K线数据:{}", result);
    }

    @Test
    public void markPrice() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instType", "SWAP");
        parameters.put("instId","BTC-USDT-SWAP");
        String result = client.createMarket().markPrice(parameters);
        log.info("获取标记价格 :{}", result);
    }

    @Test
    public void platform24Volume() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
//        parameters.put("instType", "SWAP");
        String result = client.createMarket().platform24Volume(parameters);
        log.info("获取平台24小时总成交量 :{}", result);
    }

    @Test
    public void status() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        String result = client.createMarket().status();
        log.info("获取平台24小时总成交量 :{}", result);
    }
}