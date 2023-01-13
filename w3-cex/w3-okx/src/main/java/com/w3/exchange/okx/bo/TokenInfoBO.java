package com.w3.exchange.okx.bo;

import lombok.Data;

/**
 * token基本信息业务对象
 */
@Data
public class TokenInfoBO {

    /**
     * {"code":"0","data":[
     * {"alias":"","baseCcy":"BTC","category":"1","ctMult":"","ctType":"","ctVal":"","ctValCcy":"","expTime":"",
     * "instFamily":"","instId":"BTC-USDT","instType":"SPOT",
     * "lever":"10","listTime":"1548133413000","lotSz":"0.00000001",
     * "maxIcebergSz":"9999999999","maxLmtSz":"9999999999",
     * "maxMktSz":"1000000","maxStopSz":"1000000",
     * "maxTriggerSz":"9999999999","maxTwapSz":"9999999999",
     * "minSz":"0.00001","optType":"","quoteCcy":"USDT","settleCcy":"",
     * "state":"live","stk":"","tickSz":"0.1","uly":""}
     * ],"msg":""}
     */
    /**
     * 合约日期别名
     * this_week：本周
     * next_week：次周
     * quarter：季度
     * next_quarter：次季度
     * 仅适用于交割
     */
    private String alias;
    /**
     * 交易货币币种，如 BTC-USDT 中的 BTC ，仅适用于币币/币币杠杆
     */
    private String baseCcy;
    /**
     * 币种类别，注意：此参数已废弃
     */
    private String category;
    /**
     * 合约乘数，仅适用于交割/永续/期权
     */
    private String ctMult;
    /**
     * 上线日期
     * Unix时间戳的毫秒数格式，如 1597026383085
     */
    private String listTime;
    /**
     * 合约面值，仅适用于交割/永续/期权
     */
    private String ctVal;
    /**
     * 合约面值计价币种，仅适用于交割/永续/期权
     */
    private String ctValCcy;
    /**
     * 交割/行权日期，仅适用于交割 和 期权
     * Unix时间戳的毫秒数格式，如 1597026383085
     */
    private String expTime;
    /**
     * 交易品种，如 BTC-USD，仅适用于交割/永续/期权
     */
    private String instFamily;
    /**
     * 产品id， 如 BTC-USD-SWAP
     */
    private String instId;
    /**
     * 产品类型
     */
    private String instType;
    /**
     * 该instId支持的最大杠杆倍数，不适用于币币、期权
     */
    private String lever;
    /**
     * 下单数量精度，如 BTC-USDT-SWAP：1
     */
    private String lotSz;
    /**
     * 合约或现货冰山委托的单笔最大委托数量
     */
    private String maxIcebergSz;
    /**
     *
     */
    private String maxLmtSz;
    /**
     * 合约或现货市价单的单笔最大委托数量
     */
    private String maxMktSz;
    /**
     * 合约或现货止盈止损委托的单笔最大委托数量
     */
    private String maxStopSz;
    /**
     * 合约或现货计划委托委托的单笔最大委托数量
     */
    private String maxTriggerSz;
    /**
     * 合约或现货时间加权单的单笔最大委托数量
     */
    private String maxTwapSz;
    /**
     * 最小下单数量
     */
    private String minSz;
    /**
     * 期权类型，C或P 仅适用于期权
     */
    private String optType;
    /**
     * 计价货币币种，如 BTC-USDT 中的USDT ，仅适用于币币/币币杠杆
     */
    private String quoteCcy;
    /**
     * 盈亏结算和保证金币种，如 BTC 仅适用于交割/永续/期权
     */
    private String settleCcy;
    /**
     * 产品状态
     * live：交易中
     * suspend：暂停中
     * preopen：预上线
     * test：测试中（测试产品，不可交易）
     * 当合约预上线时，状态变更为预上线（即新生成一个合约，新合约会处于预上线状态）；
     * 当产品下线的时候（如交割合约被交割的时候，期权合约被行权的时候），查询不到该产品
     */
    private String state;
    /**
     * 行权价格，仅适用于期权
     */
    private String stk;
    /**
     *  下单价格精度，如 0.0001
     */
    private String tickSz;
    /**
     * 标的指数，如 BTC-USD，仅适用于交割/永续/期权
     */
    private String uly;


}
