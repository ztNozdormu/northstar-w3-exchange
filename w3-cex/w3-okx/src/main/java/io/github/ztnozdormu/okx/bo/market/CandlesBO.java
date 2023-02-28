package io.github.ztnozdormu.okx.bo.market;

import lombok.Data;

/**
 * K线数据结果对象
 */
@Data
public class CandlesBO extends CandleBaseBO{

    /**
     * 交易量，以张为单位
     *     如果是衍生品合约，数值为合约的张数。
     *     如果是币币/币币杠杆，数值为交易货币的数量。
     *
     */
    private String vol;
    /**
     * 交易量，以币为单位
     *     如果是衍生品合约，数值为交易货币的数量。
     *     如果是币币/币币杠杆，数值为计价货币的数量。
     */
    private	String	volCcy;
    /**
     * 	交易量，以计价货币为单位
     * 如：BTC-USDT 和 BTC-USDT-SWAP, 单位均是 USDT；
     * BTC-USD-SWAP 单位是 USD
     */
    private String volCcyQuote;

}
