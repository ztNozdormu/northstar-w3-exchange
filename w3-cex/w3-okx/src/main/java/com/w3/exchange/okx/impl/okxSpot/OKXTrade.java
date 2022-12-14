package com.w3.exchange.okx.impl.okxSpot;

import com.w3.exchange.common.domain.Account;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.ParameterChecker;
import com.w3.exchange.common.utils.RequestHandler;

import java.lang.reflect.Array;
import java.util.LinkedHashMap;

/**
 * <h2>Trade Endpoints</h2>
 * 交易
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade">Trade Endpoint</a>
 * 交易功能模块下的API接口需要身份验证。
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXTrade extends Account {

    String passPhrase;

    boolean isSimluate;

    public OKXTrade(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }


    private final String ORDER = "/api/v5/trade/order";

    /**
     * 下单
     * 只有当您的账户有足够的资金才能下单。
     *
     * <br><br>
     * GET /api/v5/trade/order
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USD-190927-5000-C <br>
     *                   tdMode -- String	是	交易模式
     *                   保证金模式：isolated：逐仓 ；cross：全仓
     *                   非保证金模式：cash：非保证金 <br>
     *                   ccy -- String	否	保证金币种，仅适用于单币种保证金模式下的全仓杠杆订单 <br>
     *                   clOrdId -- String	否	客户自定义订单ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。<br>
     *                   tag -- String	否	订单标签
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字，且长度在1-16位之间。 <br>
     *                   side -- String	是	订单方向
     *                   buy：买， sell：卖 <br>
     *                   posSide -- String	可选	持仓方向
     *                   在双向持仓模式下必填，且仅可选择 long 或 short。 仅适用交割、永续。 <br>
     *                   ordType -- String	是	订单类型
     *                   market：市价单
     *                   limit：限价单
     *                   post_only：只做maker单
     *                   fok：全部成交或立即取消
     *                   ioc：立即成交并取消剩余
     *                   optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续） <br>
     *                   sz -- String	是	委托数量 <br>
     *                   px -- String	可选	委托价格，仅适用于limit、post_only、fok、ioc类型的订单 <br>
     *                   reduceOnly -- Boolean	否	是否只减仓，true 或 false，默认false
     *                   仅适用于币币杠杆，以及买卖模式下的交割/永续
     *                   仅适用于单币种保证金模式和跨币种保证金模式 <br>
     *                   tgtCcy -- String	否	市价单委托数量sz的单位，仅适用于币币市价订单
     *                   base_ccy: 交易货币 ；quote_ccy：计价货币
     *                   买单默认quote_ccy， 卖单默认base_ccy <br>
     *                   banAmend -- Boolean	否	是否禁止币币市价改单，true 或 false，默认false
     *                   为true时，余额不足时，系统不会改单，下单会失败，仅适用于币币市价单 <br>
     *                   tpTriggerPx -- String	否	止盈触发价，如果填写此参数，必须填写 止盈委托价 <br>
     *                   tpOrdPx -- String	否	止盈委托价，如果填写此参数，必须填写 止盈触发价
     *                   委托价格为-1时，执行市价止盈 <br>
     *                   slTriggerPx -- String	否	止损触发价，如果填写此参数，必须填写 止损委托价 <br>
     *                   slOrdPx -- String	否	止损委托价，如果填写此参数，必须填写 止损触发价
     *                   委托价格为-1时，执行市价止损 <br>
     *                   tpTriggerPxType -- String	否	止盈触发价类型
     *                   last：最新价格
     *                   index：指数价格
     *                   mark：标记价格
     *                   默认为last <br>
     *                   slTriggerPxType -- String	否	止损触发价类型
     *                   last：最新价格
     *                   index：指数价格
     *                   mark：标记价格
     *                   默认为last <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-place-order">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-place-order</a>
     */
    public String order(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "tdMode", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "ordType", String.class);
        ParameterChecker.checkParameter(parameters, "sz", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String BATCH_ORDERS = "/api/v5/trade/batch-orders";

    /**
     * 批量下单
     * 每次最多可以批量提交20个新订单。请求参数应该按数组格式传递。
     *
     * <br><br>
     * GET /api/v5/trade/batch-orders
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USD-190927-5000-C <br>
     *                   tdMode -- String	是	交易模式
     *                   保证金模式：isolated：逐仓 ；cross：全仓
     *                   非保证金模式：cash：非保证金 <br>
     *                   ccy -- String	否	保证金币种，仅适用于单币种保证金模式下的全仓杠杆订单 <br>
     *                   clOrdId -- String	否	客户自定义订单ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。<br>
     *                   tag -- String	否	订单标签
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-16位之间。 <br>
     *                   side -- String	是	订单方向 buy：买， sell：卖 <br>
     *                   posSide -- String	可选	持仓方向 <br>
     *                   在双向持仓模式下必填，且仅可选择 long 或 short。 仅适用交割、永续。 <br>
     *                   ordType -- String	是	订单类型
     *                   market：市价单
     *                   limit：限价单
     *                   post_only：只做maker单
     *                   fok：全部成交或立即取消
     *                   ioc：立即成交并取消剩余
     *                   optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续） <br>
     *                   sz -- String	是	委托数量 <br>
     *                   px -- String	否	委托价格，仅适用于limit、post_only、fok、ioc类型的订单 <br>
     *                   reduceOnly -- Boolean	否	是否只减仓，true 或 false，默认false
     *                   仅适用于币币杠杆，以及买卖模式下的交割/永续
     *                   仅适用于单币种保证金模式和跨币种保证金模式 <br>
     *                   tgtCcy -- String	否	市价单委托数量sz的单位，仅适用于币币市价订单
     *                   base_ccy: 交易货币 ；quote_ccy：计价货币
     *                   买单默认quote_ccy， 卖单默认base_ccy <br>
     *                   banAmend -- Boolean	否	是否禁止币币市价改单，true 或 false，默认false
     *                   为true时，余额不足时，系统不会改单，下单会失败，仅适用于币币市价单 <br>
     *                   tpTriggerPx -- String	否	止盈触发价，如果填写此参数，必须填写 止盈委托价 <br>
     *                   tpOrdPx -- String	否	止盈委托价，如果填写此参数，必须填写 止盈触发价
     *                   委托价格为-1时，执行市价止盈 <br>
     *                   slTriggerPx -- String	否	止损触发价，如果填写此参数，必须填写 止损委托价 <br>
     *                   slOrdPx -- String	否	止损委托价，如果填写此参数，必须填写 止损触发价
     *                   委托价格为-1时，执行市价止损 <br>
     *                   tpTriggerPxType -- String	否	止盈触发价类型
     *                   last：最新价格
     *                   index：指数价格
     *                   mark：标记价格
     *                   默认为last <br>
     *                   slTriggerPxType -- String	否	止损触发价类型
     *                   last：最新价格
     *                   index：指数价格
     *                   mark：标记价格
     *                   默认为last <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-place-multiple-orders">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-place-multiple-orders</a>
     */
    public String batchOrders(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "tdMode", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "ordType", String.class);
        ParameterChecker.checkParameter(parameters, "sz", String.class);
        return requestHandler.sendSignedRequest(baseUrl, BATCH_ORDERS, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String CANCEL_ORDER = "/api/v5/trade/cancel-order";

    /**
     * 撤单
     * 撤销之前下的未完成订单。
     *
     * <br><br>
     * GET /api/v5/trade/cancel-order
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USD-190927 <br>
     *                   ordId --	String	可选	订单ID， ordId和clOrdId必须传一个，若传两个，以ordId为主 <br>
     *                   clOrdId -- String	可选	用户自定义ID <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-cancel-order">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-cancel-order</a>
     */
    public String cancelOrder(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, CANCEL_ORDER, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String CANCEL_BATCH_ORDERS = "/api/v5/trade/cancel-batch-orders";

    /**
     * 批量撤单
     * 撤销未完成的订单，每次最多可以撤销20个订单。请求参数应该按数组格式传递。
     *
     * <br><br>
     * GET /api/v5/trade/cancel-batch-orders
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USD-190927 <br>
     *                   ordId --	String	可选	订单ID， ordId和clOrdId必须传一个，若传两个，以ordId为主 <br>
     *                   clOrdId -- String	可选	用户自定义ID <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-cancel-multiple-orders">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-cancel-multiple-orders</a>
     */
    public String cancelBatchOrders(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, CANCEL_BATCH_ORDERS, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String AMEND_ORDER = "/api/v5/trade/amend-order";

    /**
     * 修改订单
     * 修改当前未成交的挂单
     *
     * <br><br>
     * GET /api/v5/trade/amend-order
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID <br>
     *                   cxlOnFail --	Boolean	否	false：不自动撤单 true：自动撤单 当订单修改失败时，该订单是否需要自动撤销。默认为false <br>
     *                   ordId --	String	可选	订单ID， ordId和clOrdId必须传一个，若传两个，以ordId为主 <br>
     *                   clOrdId -- String	可选	用户自定义order ID <br>
     *                   reqId --	String	否	用户自定义修改事件ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。<br>
     *                   newSz --	String	可选	修改的新数量，newSz和newPx不可同时为空。对于部分成交订单，该数量应包含已成交数量。<br>
     *                   newPx --	String	可选	修改的新价格 <br>
     * @return String <br>
     * note: newSz
     * 修改的数量<=该笔订单已成交数量时，该订单的状态会修改为完全成交状态。<br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-amend-order">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-amend-order</a>
     */
    public String amendOrder(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, AMEND_ORDER, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String AMEND_BATCH_ORDER = "/api/v5/trade/amend-batch-orders";

    /**
     * 批量修改订单
     * 修改未完成的订单，一次最多可批量修改20个订单。请求参数应该按数组格式传递。
     *
     * <br><br>
     * GET /api/v5/trade/amend-batch-orders
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID <br>
     *                   cxlOnFail --	Boolean	否	false ：不自动撤单 true：自动撤单 当订单修改失败时，该订单是否需要自动撤销，默认为false <br>
     *                   ordId --	String	可选	订单ID， ordId和clOrdId必须传一个，若传两个，以ordId为主 <br>
     *                   clOrdId -- String	可选	用户自定义order ID <br>
     *                   reqId --	String	否	用户自定义修改事件ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。 <br>
     *                   newSz --	String	可选	修改的新数量，newSz和newPx不可同时为空。对于部分成交订单，该数量应包含已成交数量。<br>
     *                   newPx --	String	可选	修改的新价格 <br>
     * @return String  <br>
     * note: newSz修改的数量<=该笔订单已成交数量时，该订单的状态会修改为完全成交状态。 <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-amend-multiple-orders">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-amend-multiple-orders</a>
     */
    public String amendBatchOrder(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, AMEND_BATCH_ORDER, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String CLOSE_POSITION = "/api/v5/trade/close-position";

    /**
     * 市价仓位全平
     * 市价平掉指定交易产品的持仓
     *
     * <br><br>
     * GET /api/v5/trade/close-position
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID <br>
     *                   posSide -- String	可选	持仓方向
     *                   单向持仓模式下：可不填写此参数，默认值net，如果填写，仅可以填写net
     *                   双向持仓模式下： 必须填写此参数，且仅可以填写 long：平多 ，short：平空 <br>
     *                   mgnMode -- String	是	保证金模式
     *                   cross：全仓 ； isolated：逐仓 <br>
     *                   ccy -- String	可选	保证金币种，单币种保证金模式的全仓币币杠杆平仓必填 <br>
     *                   autoCxl -- Boolean	否	当市价全平时，平仓单是否需要自动撤销,默认为false.
     *                   false：不自动撤单 true：自动撤单 <br>
     *                   clOrdId -- String	否	客户自定义ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。 <br>
     *                   tag -- String	否	订单标签
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字，且长度在1-16位之间。 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-close-positions">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-close-positions</a>
     */
    public String closePosition(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, CLOSE_POSITION, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String ORDER_DETAILS = "/api/v5/trade/order";

    /**
     * 获取订单信息
     * 查订单信息
     *
     * <br><br>
     * GET /api/v5/trade/order
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID ，如BTC-USD-190927 <br>
     *                   ordId --	String	可选	订单ID ， ordId和clOrdId必须传一个，若传两个，以ordId为主 <br>
     *                   clOrdId -- String	可选	用户自定义ID <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-order-details">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-order-details</a>
     */
    public String orderDetails(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER_DETAILS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ORDER_PENDING = "/api/v5/trade/orders-pending";

    /**
     * 获取未成交订单列表
     * 获取当前账户下所有未成交订单信息
     *
     * <br><br>
     * GET /api/v5/trade/orders-pending
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	否	产品类型
     *                   SPOT：币币
     *                   MARGIN：币币杠杆
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   uly -- String	否	标的指数 <br>
     *                   instFamily -- String	否	交易品种
     *                   适用于交割/永续/期权 <br>
     *                   instId -- String	否	产品ID，如BTC-USD-200927 <br>
     *                   ordType -- String	否	订单类型
     *                   market：市价单
     *                   limit：限价单
     *                   post_only：只做maker单
     *                   fok：全部成交或立即取消
     *                   ioc：立即成交并取消剩余
     *                   optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续） <br>
     *                   state -- String	否	订单状态
     *                   live：等待成交
     *                   partially_filled：部分成交 <br>
     *                   after --	String	否	请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的ordId <br>
     *                   before -- String	否	请求此ID之后（更新的数据）的分页内容，传的值为对应接口的ordId <br>
     *                   limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-order-list">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-order-list</a>
     */
    public String orderPending(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER_PENDING, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ORDER_HISTORY_7DAYS = "/api/v5/trade/orders-history";

    /**
     * 获取历史订单记录（近七天）
     * 获取最近7天的已经完结状态的订单数据，已经撤销的未成交单 只保留2小时
     *
     * <br><br>
     * GET /api/v5/trade/orders-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   SPOT：币币
     *                   MARGIN：币币杠杆
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   uly -- String	否	标的指数 <br>
     *                   instFamily -- String	否	交易品种
     *                   适用于交割/永续/期权 <br>
     *                   instId -- String	否	产品ID，如BTC-USD-190927 <br>
     *                   ordType -- String	否	订单类型
     *                   market：市价单
     *                   limit：限价单
     *                   post_only：只做maker单
     *                   fok：全部成交或立即取消
     *                   ioc：立即成交并取消剩余
     *                   optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续） <br>
     *                   state --	String	否	订单状态
     *                   canceled：撤单成功
     *                   filled：完全成交 <br>
     *                   category -- String	否	订单种类
     *                   twap：TWAP自动换币
     *                   adl：ADL自动减仓
     *                   full_liquidation：强制平仓
     *                   partial_liquidation：强制减仓
     *                   delivery：交割
     *                   ddh：对冲减仓类型订单 <br>
     *                   after --	String	否	请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的ordId <br>
     *                   before -- String	否	请求此ID之后（更新的数据）的分页内容，传的值为对应接口的ordId <br>
     *                   begin --	String	否	筛选的开始时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   end -- String	否	筛选的结束时间戳，Unix 时间戳为毫秒数格式，如 1597027383085 <br>
     *                   limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-order-history-last-7-days">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-order-history-last-7-days</a>
     */
    public String ordersHistory7Days(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER_HISTORY_7DAYS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ORDER_HISTORY_3MONTHS = "/api/v5/trade/orders-history-archive";

    /**
     * 获取历史订单记录（近三个月）
     * 获取最近3个月的已经完结状态的订单数据
     *
     * <br><br>
     * GET /api/v5/trade/orders-history-archive
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   SPOT：币币
     *                   MARGIN：币币杠杆
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   uly -- String	否	标的指数 <br>
     *                   instFamily -- String	否	交易品种
     *                   适用于交割/永续/期权 <br>
     *                   instId -- String	否	产品ID，如BTC-USD-200927 <br>
     *                   ordType -- String	否	订单类型
     *                   market：市价单
     *                   limit：限价单
     *                   post_only：只做maker单
     *                   fok：全部成交或立即取消
     *                   ioc：立即成交并取消剩余
     *                   optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续） <br>
     *                   state --	String	否	订单状态
     *                   canceled：撤单成功
     *                   filled：完全成交 <br>
     *                   category -- String	否	订单种类
     *                   twap：TWAP自动换币
     *                   adl：ADL自动减仓
     *                   full_liquidation：强制平仓
     *                   partial_liquidation：强制减仓
     *                   delivery：交割
     *                   ddh：对冲减仓类型订单 <br>
     *                   after --	String	否	请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的ordId <br>
     *                   before -- String	否	请求此ID之后（更新的数据）的分页内容，传的值为对应接口的ordId <br>
     *                   begin --	String	否	筛选的开始时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   end -- String	否	筛选的结束时间戳，Unix 时间戳为毫秒数格式，如 1597027383085 <br>
     *                   limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-order-history-last-3-months">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-order-history-last-3-months</a>
     */
    public String ordersHistory3MONTHS(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER_HISTORY_3MONTHS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String FILLS_HISTORY3_DAYS = "/api/v5/trade/fills";

    /**
     * 获取成交明细（近三天）
     * 获取近3天的订单成交明细信息
     *
     * <br><br>
     * GET /api/v5/trade/fills
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	否	产品类型
     *                   SPOT：币币
     *                   MARGIN：币币杠杆
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   uly -- String	否	标的指数 <br>
     *                   instFamily -- String	否	交易品种
     *                   适用于交割/永续/期权 <br>
     *                   instId -- String	否	产品 ID，如BTC-USD-190927 <br>
     *                   ordId --	String	否	订单 ID <br>
     *                   after --	String	否	请求此 ID 之前（更旧的数据）的分页内容，传的值为对应接口的billId <br>
     *                   before -- String	否	请求此 ID 之后（更新的数据）的分页内容，传的值为对应接口的billId <br>
     *                   begin --	String	否	筛选的开始时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   end -- String	否	筛选的结束时间戳，Unix 时间戳为毫秒数格式，如 1597027383085 <br>
     *                   limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-transaction-details-last-3-days">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-transaction-details-last-3-days</a>
     */
    public String fillsHistory3DAYS(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, FILLS_HISTORY3_DAYS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String FILLS_HISTORY_3MONTHS = "/api/v5/trade/fills-history";

    /**
     * 获取成交明细（近三个月）
     * 获取近3个月订单成交明细信息
     *
     * <br><br>
     * GET /api/v5/trade/fills-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   SPOT：币币
     *                   MARGIN：币币杠杆
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   uly -- String	否	标的指数 <br>
     *                   instFamily -- String	否	交易品种
     *                   适用于交割/永续/期权 <br>
     *                   instId -- String	否	产品 ID，如BTC-USD-190927 <br>
     *                   ordId --	String	否	订单 ID <br>
     *                   after --	String	否	请求此 ID 之前（更旧的数据）的分页内容，传的值为对应接口的billId <br>
     *                   before -- String	否	请求此 ID 之后（更新的数据）的分页内容，传的值为对应接口的billId <br>
     *                   begin --	String	否	筛选的开始时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   end -- String	否	筛选的结束时间戳，Unix 时间戳为毫秒数格式，如 1597027383085 <br>
     *                   limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-transaction-details-last-3-months">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-transaction-details-last-3-months</a>
     */
    public String fillsHistory3MONTHS(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, FILLS_HISTORY_3MONTHS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ORDER_ALGO = "/api/v5/trade/order-algo";

    /**
     * 策略委托下单
     * 提供单向止盈止损委托、双向止盈止损委托、计划委托、冰山委托、时间加权委托、移动止盈止损委托
     *
     * <br><br>
     * GET /api/v5/trade/order-algo
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USD-190927-5000-C <br>
     *                   tdMode -- String	是	交易模式
     *                   保证金模式 isolated：逐仓，cross：全仓
     *                   非保证金模式 cash：非保证金 <br>
     *                   ccy -- String	否	保证金币种
     *                   仅适用于单币种保证金模式下的全仓杠杆订单 <br>
     *                   side -- String	是	订单方向
     *                   buy：买
     *                   sell：卖 <br>
     *                   posSide -- String	可选	持仓方向
     *                   在双向持仓模式下必填，且仅可选择 long 或 short <br>
     *                   ordType -- String	是	订单类型
     *                   conditional：单向止盈止损
     *                   oco：双向止盈止损
     *                   trigger：计划委托
     *                   move_order_stop：移动止盈止损
     *                   iceberg：冰山委托
     *                   twap：时间加权委托 <br>
     *                   sz -- String	可选	委托数量
     *                   sz和closeFraction必填且只能填其一 <br>
     *                   tag -- String	否	订单标签
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字，且长度在1-16位之间 <br>
     *                   tgtCcy -- String	否	委托数量的类型
     *                   base_ccy: 交易货币 ；quote_ccy：计价货币
     *                   仅适用于币币单向止盈止损市价买单
     *                   默认买为计价货币，卖为交易货币 <br>
     *                   reduceOnly -- Boolean	否	是否只减仓，true 或 false，默认false
     *                   仅适用于币币杠杆，以及买卖模式下的交割/永续
     *                   仅适用于单币种保证金模式和跨币种保证金模式 <br>
     *                   clOrdId -- String	否	客户自定义订单ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间 <br>
     *                   closeFraction --	String	可选	策略委托触发时，平仓的百分比。1 代表100%
     *                   现在系统只支持全部平仓，唯一接受参数为1
     *                   仅适用于交割或永续
     *                   仅适用于买卖模式 posSide = net
     *                   仅适用于减仓订单 reduceOnly = true
     *                   仅适用于止盈止损 ordType = conditional 或 oco
     *                   仅适用于止盈止损市价订单
     *                   sz和closeFraction必填且只能填其一 <br>
     *                   <p>
     *                   止盈止损 <br>
     *                   参数名	类型	是否必须	描述 <br>
     *                   tpTriggerPx -- String	否	止盈触发价，如果填写此参数，必须填写 止盈委托价 <br>
     *                   tpTriggerPxType -- String	否	止盈触发价类型
     *                   last：最新价格
     *                   index：指数价格
     *                   mark：标记价格
     *                   默认为last <br>
     *                   tpOrdPx -- String	否	止盈委托价，如果填写此参数，必须填写 止盈触发价
     *                   委托价格为-1时，执行市价止盈 <br>
     *                   slTriggerPx -- String	否	止损触发价，如果填写此参数，必须填写 止损委托价 <br>
     *                   slTriggerPxType -- String	否	止损触发价类型
     *                   last：最新价格
     *                   index：指数价格
     *                   mark：标记价格
     *                   默认为last <br>
     *                   slOrdPx -- String	否	止损委托价，如果填写此参数，必须填写 止损触发价
     *                   委托价格为-1时，执行市价止损 <br>
     *                   <p>
     *                   计划委托 <br>
     *                   参数名	类型	是否必须	描述 <br>
     *                   triggerPx --	String	是	计划委托触发价格 <br>
     *                   orderPx -- String	是	委托价格
     *                   委托价格为-1时，执行市价委托 <br>
     *                   triggerPxType --	String	否	计划委托触发价格类型
     *                   last：最新价格
     *                   index：指数价格
     *                   mark：标记价格
     *                   默认为last <br>
     *                   <p>
     *                   移动止盈止损 <br>
     *                   参数名	类型	是否必须	描述 <br>
     *                   callbackRatio --	String	可选	回调幅度的比例，如 0.05
     *                   callbackRatio和callbackSpread只能传入一个 <br>
     *                   callbackSpread -- String	可选	回调幅度的价距 <br>
     *                   activePx -- String	否	激活价格 <br>
     *                   <p>
     *                   冰山委托 <br>
     *                   参数名	类型	是否必须	描述 <br>
     *                   pxVar --	String	可选	挂单价距离盘口的比例
     *                   pxVar和pxSpread只能传入一个 <br>
     *                   pxSpread -- String	可选	挂单价距离盘口的价距 <br>
     *                   szLimit -- String	是	单笔数量 <br>
     *                   pxLimit -- String	是	挂单限制价 <br>
     *                   <p>
     *                   时间加权 <br>
     *                   参数名	类型	是否必须	描述 <br>
     *                   pxVar --	String	可选	吃单价优于盘口的比例
     *                   pxVar和pxSpread只能传入一个 <br>
     *                   pxSpread -- String	可选	吃单单价优于盘口的价距 <br>
     *                   szLimit -- String	是	单笔数量 <br>
     *                   pxLimit -- String	是	挂单限制价 <br>
     *                   timeInterval -- String	是	下单间隔 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-place-algo-order">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-place-algo-order</a>
     */
    public String orderAlgo(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "tdMode", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "ordType", String.class);

        return requestHandler.sendSignedRequest(baseUrl, ORDER_ALGO, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String ORDER_ALGO_CANCEL = "/api/v5/trade/cancel-algos";

    /**
     * 撤销策略委托订单
     * 撤销策略委托订单（不包含冰山委托、时间加权、移动止盈止损等高级策略订单），每次最多可以撤销10个策略委托单
     *
     * <br><br>
     * GET /api/v5/trade/cancel-algos
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   algoId -- String	是	策略委托单ID <br>
     *                   instId -- String	是	产品ID 如 BTC-USDT <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-cancel-algo-order">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-cancel-algo-order</a>
     */
    public String orderAlgoCancel(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algoId", String.class);
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER_ALGO_CANCEL, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String ORDER_ADVANCE_ALGO_CANCEL = "/api/v5/trade/cancel-advance-algos";

    /**
     * 撤销高级策略委托订单
     * 撤销冰山委托、时间加权、移动止盈止损委托订单，每次最多可以撤销10个策略委托单
     *
     * <br><br>
     * GET /api/v5/trade/cancel-advance-algos
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   algoId -- String	是	策略委托单ID <br>
     *                   instId -- String	是	产品ID 如 BTC-USDT <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-cancel-advance-algo-order">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-cancel-advance-algo-order</a>
     */
    public String orderAdvanceAlgoCancel(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algoId", String.class);
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER_ADVANCE_ALGO_CANCEL, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String ORDER_ALGO_PENDING = "/api/v5/trade/orders-algo-pending";

    /**
     * 获取未完成策略委托单列表
     * 获取当前账户下未触发的策略委托单列表
     *
     * <br><br>
     * GET /api/v5/trade/orders-algo-pending
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   algoId -- String	否	策略委托单ID <br>
     *                   instType -- String	否	产品类型
     *                   SPOT：币币
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   MARGIN：杠杆 <br>
     *                   instId -- String	否	产品ID，BTC-USD-190927 <br>
     *                   ordType -- String	是	订单类型
     *                   conditional：单向止盈止损
     *                   oco：双向止盈止损
     *                   trigger：计划委托
     *                   move_order_stop：移动止盈止损
     *                   iceberg：冰山委托
     *                   twap：时间加权委托 <br>
     *                   after --	String	否	请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的algoId <br>
     *                   before -- String	否	请求此ID之后（更新的数据）的分页内容，传的值为对应接口的algoId <br>
     *                   limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     *                   clOrdId -- String	否	客户自定义订单ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-algo-order-list">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-algo-order-list</a>
     */
    public String orderAlgoPending(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "algoId", String.class);
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER_ALGO_PENDING, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ORDERS_ALGO_HISTORY_3MONTHS = "/api/v5/trade/orders-algo-history";

    /**
     * 获取历史策略委托单列表
     * 获取最近3个月当前账户下所有策略委托单列表
     *
     * <br><br>
     * GET /api/v5/trade/orders-algo-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ordType --	String	是	订单类型
     *                   conditional：单向止盈止损
     *                   oco：双向止盈止损
     *                   trigger：计划委托
     *                   move_order_stop：移动止盈止损
     *                   iceberg：冰山委托
     *                   twap：时间加权委托 <br>
     *                   state --	String	可选	订单状态
     *                   effective：已生效
     *                   canceled：已经撤销
     *                   order_failed：委托失败
     *                   【state和algoId必填且只能填其一】 <br>
     *                   algoId -- String	可选	策略委托单ID 【state和algoId必填且只能填其一】 <br>
     *                   instType -- String	否	产品类型
     *                   SPOT：币币
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   MARGIN：杠杆 <br>
     *                   instId -- String	否	产品ID，BTC-USD-190927 <br>
     *                   after --	String	否	请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的algoId <br>
     *                   before -- String	否	请求此ID之后（更新的数据）的分页内容，传的值为对应接口的algoId <br>
     *                   limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-algo-order-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-algo-order-history</a>
     */
    public String ordersAlgoHistory3MONTHS(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ordType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDERS_ALGO_HISTORY_3MONTHS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String EASY_CONVERT_CURRENCY_LIST = "/api/v5/trade/easy-convert-currency-list";

    /**
     * 获取一键兑换主流币币种列表
     * 获取小币一键兑换主流币币种列表。仅可兑换余额在 $10 以下小币币种。
     *
     * <br><br>
     * GET /api/v5/trade/easy-convert-currency-list
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   无参数  <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-easy-convert-currency-list">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-easy-convert-currency-list</a>
     */
    public String easyConvertCurrencyList(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, EASY_CONVERT_CURRENCY_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String EASY_CONVERT = "/api/v5/trade/easy-convert";

    /**
     * 一键兑换主流币交易
     * 进行小币一键兑换主流币交易。仅可兑换余额在 $10 以下小币币种。
     *
     * <br><br>
     * GET /api/v5/trade/easy-convert
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   fromCcy -- Array	是	小币支付币种
     *                   单次最多同时选择5个币种，如有多个币种则用逗号隔开 <br>
     *                   toCcy --	String	是	兑换的主流币
     *                   只选择一个币种，且不能和小币支付币种重复 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-place-easy-convert-rest-api-trade-easy-convert">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-place-easy-convert-rest-api-trade-easy-convert</a>
     */
    public String easyConvert(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "fromCcy", Array.class);
        ParameterChecker.checkParameter(parameters, "toCcy", String.class);
        return requestHandler.sendSignedRequest(baseUrl, EASY_CONVERT, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String EASY_CONVERT_HISTORY = "/api/v5/trade/easy-convert-history";

    /**
     * 获取一键兑换主流币历史记录
     * 查询一键兑换主流币的历史记录与进度状态。
     *
     * <br><br>
     * GET /api/v5/trade/easy-convert-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   after -- String	否	查询在此之前的内容，值为时间戳，Unix时间戳为毫秒数格式，如1597026383085 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix时间戳为毫秒数格式，如1597026383085 <br>
     *                   limit --	String	否	返回的结果集数量，默认为100，最大为100 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-easy-convert-history-rest-api-trade-easy-convert-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-easy-convert-history-rest-api-trade-easy-convert-history</a>
     */
    public String easyConvertHistory(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, EASY_CONVERT_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String EASY_REPAY_CURRENCY_LIST = "/api/v5/trade/one-click-repay-currency-list";

    /**
     * 获取一键还债币种列表
     * 查询一键还债币种列表。负债币种包括全仓负债和逐仓负债。
     *
     * <br><br>
     * GET /api/v5/trade/one-click-repay-currency-list
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   debtType	String	否	负债类型
     *                   cross: 全仓负债
     *                   isolated: 逐仓负债 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-one-click-repay-currency-list-rest-api-trade-one-click-repay-currency-list">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-one-click-repay-currency-list-rest-api-trade-one-click-repay-currency-list</a>
     */
    public String easyRepayCurrencyList(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, EASY_REPAY_CURRENCY_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String EASY_REPAY = "/api/v5/trade/one-click-repay";

    /**
     * 一键还债交易
     * 交易一键偿还小额全仓债务。不支持逐仓负债的偿还。根据资金和交易账户的剩余可用余额为最大偿还数量。
     *
     * <br><br>
     * GET /api/v5/trade/one-click-repay
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   debtCcy	Array	是	负债币种
     *                   单次最多同时选择5个币种，如有多个币种则用逗号隔开 <br>
     *                   repayCcy	String	是	偿还币种
     *                   只选择一个币种，且不能和负债币种重复 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-trade-one-click-repay-rest-api-trade-one-click-repay">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-trade-one-click-repay-rest-api-trade-one-click-repay</a>
     */
    public String easyRepay(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "debtCcy", Array.class);
        ParameterChecker.checkParameter(parameters, "repayCcy", String.class);
        return requestHandler.sendSignedRequest(baseUrl, EASY_REPAY, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String EASY_REPAY_HISTORY = "/api/v5/trade/one-click-repay-history";

    /**
     * 获取一键还债历史记录
     * 查询一键还债的历史记录与进度状态。
     *
     * <br><br>
     * GET /api/v5/trade/one-click-repay-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   after -- String	否	查询在此之前的内容，值为时间戳，Unix时间戳为毫秒数格式，如1597026383085 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix时间戳为毫秒数格式，如1597026383085 <br>
     *                   limit --	String	否	返回的结果集数量，默认为100，最大为100 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-trade-get-one-click-repay-history-rest-api-trade-one-click-repay-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-trade-get-one-click-repay-history-rest-api-trade-one-click-repay-history</a>
     */
    public String easyRepayHistory(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, EASY_REPAY_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

}
