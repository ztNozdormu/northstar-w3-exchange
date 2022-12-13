package com.w3.exchange.okx.impl.okxSpot;

import com.w3.exchange.common.domain.Market;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.ParameterChecker;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * 公共数据(Public data)功能模块下的API接口不需要身份验证。
 *
 * <h2>Public Data Market Endpoints</h2>
 * All endpoints under the
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data">Public Data Market Data Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXPDMarket extends Market {

    String passPhrase;

    boolean isSimluate;

    public OKXPDMarket(String baseUrl, String apiKey, String secertKey, String passPhrase, boolean isSimluate) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
        this.isSimluate = isSimluate;
    }

    private final String INSTRUMENTS = "/api/v5/public/instruments";

    /**
     * 获取交易产品基础信息
     * 获取所有可交易产品的信息列表。
     * <br><br>
     * GET /api/v5/public/instruments
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType	-- String	是	产品类型 SPOT：币币 MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权 <br>
     *                   uly      -- String	可选	标的指数，仅适用于交割/永续/期权，期权必填 <br>
     *                   instFamily -- String	否	交易品种，仅适用于交割/永续/期权 <br>
     *                   instId -- String	否	产品ID <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-instruments">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-instruments</a>
     */
    public String instruments(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, INSTRUMENTS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String DELIVERY_EXERCISE_HISTORY = "/api/v5/public/delivery-exercise-history";

    /**
     * 获取交割和行权记录
     * 获取3个月内的交割合约的交割记录和期权的行权记录。
     *
     * <br><br>
     * GET /api/v5/public/delivery-exercise-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   uly -- String	可选	标的指数 uly与instFamily必须传一个,若传两个，以instFamily为主 <br>
     *                   instFamily -- String	可选	交易品种 uly与instFamily必须传一个,若传两个，以instFamily为主 <br>
     *                   after  -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     *                   limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-delivery-exercise-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-delivery-exercise-history</a>
     */
    public String deliveryExerciseHistory(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
//        ParameterChecker.checkParameter(parameters, "instFamily", String.class);
        return requestHandler.sendPublicRequest(baseUrl, DELIVERY_EXERCISE_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String OPEN_INTEREST = "/api/v5/public/open-interest";

    /**
     * 获取持仓总量
     * 查询单个交易产品的市场的持仓总量
     *
     * <br><br>
     * GET /api/v5/public/open-interest
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   uly -- String	可选	标的指数 适用于交割/永续/期权 期权情况下，uly和instFamily必须传一个 <br>
     *                   instFamily -- String	可选	交易品种 适用于交割/永续/期权 期权情况下，uly和instFamily必须传一个 <br>
     *                   instId -- String	否	产品ID，如 BTC-USD-180216 仅适用于交割/永续/期权 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-open-interest">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-open-interest</a>
     */
    public String openInterest(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
//        ParameterChecker.checkParameter(parameters, "instFamily", String.class);
        return requestHandler.sendPublicRequest(baseUrl, OPEN_INTEREST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String FUNDING_RATE = "/api/v5/public/funding-rate";

    /**
     * 获取永续合约当前资金费率
     * 获取当前资金费率
     *
     * <br><br>
     * GET /api/v5/public/funding-rate
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID ，如 BTC-USD-SWAP 仅适用于永续 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-funding-rate">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-funding-rate</a>
     */
    public String fundingRate(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, FUNDING_RATE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String FUNDING_RATE_HISTORY = "/api/v5/public/funding-rate-history";

    /**
     * 获取永续合约历史资金费率
     * 获取最近3个月的历史资金费率
     *
     * <br><br>
     * GET /api/v5/public/funding-rate-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID ，如 BTC-USD-SWAP 仅适用于永续 <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的fundingTime <br>
     *                   after -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的fundingTime <br>
     *                   limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-funding-rate-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-funding-rate-history</a>
     */
    public String fundingRateHistory(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, FUNDING_RATE_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String PRICE_LIMIT = "/api/v5/public/price-limit";

    /**
     * 获取限价
     * 查询单个交易产品的最高买价和最低卖价
     *
     * <br><br>
     * GET /api/v5/public/price-limit
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID，如 BTC-USDT-SWAP 仅适用于交割/永续/期权 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-limit-price">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-limit-price</a>
     */
    public String priceLimit(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, PRICE_LIMIT, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String OPT_SUMMARY = "/api/v5/public/opt-summary";

    /**
     * 获取期权定价
     * 查询期权详细信息
     *
     * <br><br>
     * GET /api/v5/public/opt-summary
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   uly -- String	可选	标的指数，仅适用于期权 uly与instFamily必须传一个,若传两个，以instFamily为主 <br>
     *                   instFamily -- String	可选	交易品种，仅适用于期权 uly与instFamily必须传一个,若传两个，以instFamily为主 <br>
     *                   expTime --  String	否	合约到期日，格式为"YYMMDD"，如 "200527" <br>
     *                   instId -- String	是	产品ID，如 BTC-USDT-SWAP 仅适用于交割/永续/期权 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-option-market-data">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-option-market-data</a>
     */
    public String optSummary(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, OPT_SUMMARY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ESTIMATED_PRICE = "/api/v5/public/estimated-price";

    /**
     * 获取预估交割/行权价格
     * 获取交割合约和期权预估交割/行权价。交割/行权预估价只有交割/行权前一小时才有返回值
     *
     * <br><br>
     * GET /api/v5/public/estimated-price
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instId -- String	是	产品ID， 如 BTC-USD-200214 仅适用于交割/期权 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-estimated-delivery-exercise-price">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-estimated-delivery-exercise-price</a>
     */
    public String estimatedPrice(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        return requestHandler.sendPublicRequest(baseUrl, ESTIMATED_PRICE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String DISCOUNT_RATE_INTEREST = "/api/v5/public/discount-rate-interest-free-quota";

    /**
     * 获取免息额度和币种折算率等级
     * <br><br>
     * GET /api/v5/public/discount-rate-interest-free-quota
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	否	币种 <br>
     *                   discountLv -- String	否	折算率等级
     *                   1:第一档
     *                   2:第二档
     *                   3:第三档
     *                   4:第四档
     *                   5:第五档
     *                   <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-discount-rate-level-and-interest-free-quota">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-discount-rate-level-and-interest-free-quota</a>
     */
    public String discountRateInterest(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, DISCOUNT_RATE_INTEREST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String LIQUIDATION_ORDERS = "/api/v5/public/liquidation-orders";

    /**
     * 获取平台公共爆仓单信息
     * 最近1天的爆仓单信息
     * <br><br>
     * GET /api/v5/public/liquidation-orders
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   MARGIN：币币杠杆
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   mgnMode -- String	否	保证金模式
     *                   cross：全仓
     *                   isolated：逐仓 <br>
     *                   instId -- String	否	产品ID，仅适用于币币杠杆 <br>
     *                   ccy -- String	否	币种 ，仅适用于币币杠杆（全仓） <br>
     *                   uly -- String	可选	标的指数 交割/永续/期权情况下，uly与instFamily必须传一个，若传两个，以instFamily为主 <br>
     *                   instFamily -- String	可选	交易品种 交割/永续/期权情况下，uly与instFamily必须传一个，若传两个，以instFamily为主 <br>
     *                   alias -- String	可选	this_week：本周
     *                   next_week：次周
     *                   quarter：季度
     *                   next_quarter：次季度
     *                   交割合约情况下，该参数必填 <br>
     *                   state -- String	否	状态
     *                   unfilled：未成交
     *                   filled：已成交
     *                   默认为unfilled
     *                   交割/永续合约情况下，该参数必填 <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     *                   after -- String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     *                   limit -- String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-liquidation-orders">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-liquidation-orders</a>
     */
    public String liquidationOrders(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, LIQUIDATION_ORDERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String POSITION_TIERS = "/api/v5/public/position-tiers";

    /**
     * 获取衍生品仓位档位
     * 全部仓位档位对应信息，当前最高可开杠杆倍数由您的借币持仓和保证金率决定
     * <br><br>
     * GET /api/v5/public/position-tiers
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   MARGIN：币币杠杆
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   tdMode -- String	是	保证金模式
     *                   isolated：逐仓 ；cross：全仓 <br>
     *                   uly -- String	可选	标的指数，支持多uly，半角逗号分隔，最大不超过3个
     *                   当产品类型是永续、交割、期权 之一时，uly与instFamily必须传一个，若传两个，以instFamily为主
     *                   当产品类型是 MARGIN 时忽略 <br>
     *                   instFamily -- String	可选	交易品种，支持多instFamily，半角逗号分隔，最大不超过5个
     *                   当产品类型是永续、交割、期权 之一时，uly与instFamily必须传一个，若传两个，以instFamily为主 <br>
     *                   instId -- String	可选	产品ID，支持多instId，半角逗号分隔，最大不超过5个
     *                   仅适用币币杠杆，instId和ccy必须传一个，若传两个，以instId为主 <br>
     *                   ccy -- String	可选	保证金币种
     *                   仅适用杠杆全仓，该值生效时，返回的是跨币种保证金模式和组合保证金模式下的借币量 <br>
     *                   tier -- String	否	查指定档位 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-position-tiers">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-position-tiers</a>
     */
    public String positionTiers(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, POSITION_TIERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String INTEREST_RATE_LOAN = "/api/v5/public/interest-rate-loan-quota";

    /**
     * 获取市场借币杠杆利率和借币限额
     * <br><br>
     * GET /api/v5/public/interest-rate-loan-quota
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   basic --	Array	基础利率和借币限额
     *                   > ccy --	String	币种
     *                   > rate -- String	基础杠杆日利率
     *                   > quota -- String	基础借币限额 <br>
     *                   vip -- Array	专业用户
     *                   > level -- String	账户交易手续费等级，如 VIP1
     *                   > loanQuotaCoef -- String	借币限额系数
     *                   > irDiscount -- String	利率的折扣率 <br>
     *                   regular -- Array	普通用户
     *                   > level -- String	账户交易手续费等级，如 Lv1
     *                   > loanQuotaCoef -- String	借币限额系数
     *                   > irDiscount -- String	利率的折扣率 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-interest-rate-and-loan-quota-for-market-loans">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-interest-rate-and-loan-quota-for-market-loans</a>
     */
    public String interestRateLoan(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, INTEREST_RATE_LOAN, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String VIP_INTEREST_RATE_LOAN = "/api/v5/public/vip-interest-rate-loan-quota";

    /**
     * 获取尊享借币杠杆利率和借币限额
     * <br><br>
     * GET /api/v5/public/vip-interest-rate-loan-quota
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	币种 <br>
     *                   rate -- String	基础杠杆日利率 <br>
     *                   quota --	String	基础借币限额 <br>
     *                   levelList --	Array	不同VIP等级下的限额信息
     *                   > level -- String	用户VIP等级，如 VIP5
     *                   > loanQuota -- String	借币限额 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-interest-rate-and-loan-quota-for-vip-loans">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-interest-rate-and-loan-quota-for-vip-loans</a>
     */
    public String vipInterestRateLoan(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, VIP_INTEREST_RATE_LOAN, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String UNDERLYING = "/api/v5/public/underlying";

    /**
     * 获取衍生品标的指数
     * <br><br>
     * GET /api/v5/public/underlying
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-underlying">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-underlying</a>
     */
    public String underlying(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, UNDERLYING, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String INSURANCE_FUND = "/api/v5/public/insurance-fund";

    /**
     * 获取风险准备金余额
     * 通过该接口获取系统风险准备金余额信息
     * <br><br>
     * GET /api/v5/public/insurance-fund
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   instType -- String	是	产品类型
     *                   MARGIN：币币杠杆
     *                   SWAP：永续合约
     *                   FUTURES：交割合约
     *                   OPTION：期权 <br>
     *                   type -- String	否	风险准备金类型
     *                   liquidation_balance_deposit：强平注入 ；bankruptcy_loss：穿仓亏损 ；platform_revenue：平台收入注入
     *                   默认返回全部类型 <br>
     *                   uly -- String	可选	标的指数 <br>
     *                   交割/永续/期权情况下，uly与instFamily必须传一个，若传两个，以instFamily为主 <br>
     *                   instFamily -- String	可选	交易品种
     *                   交割/永续/期权情况下，uly与instFamily必须传一个，若传两个，以instFamily为主 <br>
     *                   ccy -- String	可选	币种， 仅适用币币杠杆，且必填写 <br>
     *                   before -- String	否	请求此时间戳之后（更新的数据）的分页内容，传的值为对应接口的ts <br>
     *                   after --	String	否	请求此时间戳之前（更旧的数据）的分页内容，传的值为对应接口的ts <br>
     *                   limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-insurance-fund">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-insurance-fund</a>
     */
    public String insuranceFund(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendPublicRequest(baseUrl, INSURANCE_FUND, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String CONVERT_CONTRACT_COIN = "/api/v5/public/convert-contract-coin";

    /**
     * 张币转换
     * 由币转换为张，或者张转换为币。
     * <br><br>
     * GET /api/v5/public/convert-contract-coin
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   type -- String	否	转换类型
     *                   1: 币转张，当张为小数时，会进一取整
     *                   2: 张转币
     *                   默认为 1 <br>
     *                   instId -- String	是	产品ID，仅适用于交割/永续/期权 <br>
     *                   sz -- String	是	数量，币转张时，为币的数量，张转币时，为张的数量。
     *                   张的数量，只能是正整数 <br>
     *                   px -- String	可选	委托价格
     *                   币本位合约的张币转换时必填；
     *                   U本位合约，usdt 与张的转换时，必填；coin 与张的转换时，可不填；
     *                   期权的张币转换时，可不填。 <br>
     *                   unit -- String	否	币的单位，coin: 币，usds: usdt 或者 usdc
     *                   仅适用于交割和永续合约的U本位合约 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-unit-convert">
     * https://www.okx.com/docs-v5/zh/#rest-api-public-data-get-unit-convert</a>
     */
    public String convertContractCoin(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "sz", String.class);
        return requestHandler.sendPublicRequest(baseUrl, CONVERT_CONTRACT_COIN, parameters, HttpMethod.GET, showLimitUsage);
    }


    private final String LENDING_RATE_SUMMARY = "/api/v5/asset/lending-rate-summary";

    /**
     * 获取市场借贷信息（公共）
     * 公共接口无须鉴权
     * <br><br>
     * GET /api/v5/asset/lending-rate-summary
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	否	币种，如 BTC<br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-public-borrow-info-public">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-public-borrow-info-public</a>
     */
    public String lendingRateSummary(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, LENDING_RATE_SUMMARY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String LENDING_RATE_HISTORY = "/api/v5/asset/lending-rate-history";

    /**
     * 获取市场借贷历史（公共）
     * 公共接口无须鉴权
     * <br><br>
     * GET /api/v5/asset/lending-rate-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	否	币种，如 BTC <br>
     *                   after --	String	否	查询在此之前的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-public-borrow-history-public">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-public-borrow-history-public</a>
     */
    public String lendingRateHistory(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, LENDING_RATE_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }
}
