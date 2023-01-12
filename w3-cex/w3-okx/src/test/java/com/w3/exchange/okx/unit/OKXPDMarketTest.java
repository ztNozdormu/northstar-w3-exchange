package com.w3.exchange.okx.unit;

import com.w3.exchange.okx.impl.OKXSpotClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * 公共数据(Public data)功能模块下的API接口不需要身份验证。
 * 测试案例
 */
@Slf4j
public class OKXPDMarketTest  {

    @Test
    public void convertContractCoin() {
       OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instId", "BTC-USDT-SWAP");
        parameters.put("sz", "1000");
        String result = client.createPubMarket().convertContractCoin(parameters);
        log.info("张币转换:由币转换为张或者张转换为币:{}", result);
    }

    @Test
    public void fundingRate() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      instType -- String 是 产品类型 SPOT：币币 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instId", "ETH-USDT-SWAP");
        String result = client.createPubMarket().fundingRate(parameters);
        log.info("获取当前资金费率:{}", result);
    }

    @Test
    public void instruments() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      SPOT：币币 MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instType", "SPOT");
        String result = client.createPubMarket().exchangeInfo(parameters);
        log.info("获取所有可交易产品的信息列表:{}", result);
    }

    @Test
    public void priceLimit() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      SPOT：币币 MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instId", "BTC-USDT-SWAP");
        String result = client.createPubMarket().priceLimit(parameters);
        log.info("查询单个交易产品的最高买价和最低卖价:{}", result);
    }

    @Test
    public void insuranceFund() {
        OKXSpotClientImpl client = new OKXSpotClientImpl();
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
//      SPOT：币币 MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
        parameters.put("instType", "SWAP");
        parameters.put("instFamily","BTC-USDT");
        String result = client.createPubMarket().insuranceFund(parameters);
        log.info("获取风险准备金余额 :{}", result);
    }
}
