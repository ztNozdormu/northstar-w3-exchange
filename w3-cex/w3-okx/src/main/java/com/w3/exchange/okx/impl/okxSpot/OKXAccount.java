package com.w3.exchange.okx.impl.okxSpot;

import com.w3.exchange.common.domain.Account;
import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.utils.ParameterChecker;
import com.w3.exchange.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Account Endpoints</h2>
 * 账户
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-account">Account Endpoint</a>
 * 账户功能模块下的API接口需要身份验证
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXAccount extends Account {

    String passPhrase;

    boolean isSimluate;

    public OKXAccount(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String BALANCE = "/api/v5/account/balance";
    /**
     * 查看账户余额
     * 获取交易账户中资金余额信息。
     * <br><br>
     * GET /api/v5/account/balance
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ccy -- String	否	币种，如 BTC 支持多币种查询（不超过20个），币种之间半角逗号分隔 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-balance">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-balance</a>
     */
    public String balance(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, BALANCE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String POSITIONS = "/api/v5/account/positions";
    /**
     * 查看持仓信息
     * 获取该账户下拥有实际持仓的信息。账户为单向持仓模式会显示净持仓（net），账户为双向持仓模式下会分别返回多头（long）或空头（short）的仓位。按照仓位创建时间倒序排列。
     * <br><br>
     * GET /api/v5/account/positions
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- String	否	产品类型
     * MARGIN：币币杠杆
     * SWAP：永续合约
     * FUTURES：交割合约
     * OPTION：期权
     * instType和instId同时传入的时候会校验instId与instType是否一致。<br>
     * instId -- String	否	交易产品ID，如：BTC-USD-190927-5000-C
     * 支持多个instId查询（不超过10个），半角逗号分隔<br>
     * posId --	String	否	持仓ID
     * 支持多个posId查询（不超过20个），半角逗号分割<br>
     * @return String <br>
     * note: 如果该 instId 拥有过仓位且当前持仓量为0，传 instId 时，会返回仓位信息；不传 instId 时，仓位信息不返回。
     *  逐仓交易设置中，如果设置为自主划转模式，逐仓转入保证金后，会生成一个持仓量为0的仓位 <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-positions">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-positions</a>
     */
    public String positions(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, POSITIONS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String POSITIONS_HISTORY = "/api/v5/account/positions-history";
    /**
     * 查看历史持仓信息
     * 获取最近3个月有更新的仓位信息，按照仓位更新时间倒序排列。
     * <br><br>
     * GET /api/v5/account/positions-history
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- String	否	产品类型
     * MARGIN：币币杠杆
     * SWAP：永续合约
     * FUTURES：交割合约
     * OPTION：期权 <br>
     * instId -- String	否	交易产品ID，如：BTC-USD-SWAP <br>
     * mgnMode -- String	否	保证金模式
     * cross：全仓，isolated：逐仓
     * type -- String	否	平仓类型
     * 1：部分平仓;2：完全平仓;3：强平;4：强减; 5：ADL自动减仓;
     * 状态叠加时，以最新的平仓类型为准状态为准。 <br>
     * posId --	String	否	持仓ID <br>
     * after --	String	否	查询仓位更新 (uTime) 之前的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     * before -- String	否	查询仓位更新 (uTime) 之后的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     * limit --	String	否	分页返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-positions-history">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-positions-history</a>
     */
    public String positionsHistory(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, POSITIONS_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String POSITIONS_RISK = "/api/v5/account/account-position-risk";
    /**
     * 查看账户持仓风险
     * 查看账户整体风险。
     * 获取同一时间切片上的账户和持仓的基础信息
     * <br><br>
     * GET /api/v5/account/account-position-risk
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- String	否	产品类型
     * MARGIN：币币杠杆
     * SWAP：永续合约
     * FUTURES：交割合约
     * OPTION：期权 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-account-and-position-risk">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-account-and-position-risk</a>
     */
    public String positionsRisk(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, POSITIONS_RISK, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String BILLS_7DAYS = "/api/v5/account/bills";
    /**
     * 账单流水查询（近七天）
     * 帐户资产流水是指导致帐户余额增加或减少的行为。本接口可以查询最近7天的账单数据。
     * <br><br>
     * GET /api/v5/account/bills
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- String	否	产品类型
     * SPOT：币币
     * MARGIN：币币杠杆
     * SWAP：永续合约
     * FUTURES：交割合约
     * OPTION：期权 <br>
     * ccy -- String	否	账单币种
     * mgnMode -- String	否	仓位类型
     * isolated：逐仓
     * cross：全仓 <br>
     * ctType -- String	否	linear： 正向合约
     * inverse： 反向合约
     * 仅交割/永续有效 <br>
     * type -- String	否	账单类型
     * 1：划转 2：交易 3：交割 4：自动换币 5：强平 6：保证金划转 7：扣息 8：资金费 9：自动减仓 10：穿仓补偿 11：系统换币 12：策略划拨 13：对冲减仓 14: 大宗交易 22: 一键还债
     * <br>
     * subType -- String	否	账单子类型
     * 1：买入 2：卖出 3：开多 4：开空 5：平多 6：平空 9：市场借币扣息 11：转入 12：转出 14：尊享借币扣息 160：手动追加保证金 161：手动减少保证金 162：自动追加保证金 114：自动换币买入 115：自动换币卖出 118：系统换币转入 119：系统换币转出 100：强减平多 101：强减平空 102：强减买入 103：强减卖出 104：强平平多 105：强平平空 106：强平买入 107：强平卖出 110：强平换币转入 111：强平换币转出 125：自动减仓平多 126：自动减仓平空 127：自动减仓买入 128：自动减仓卖出 131：对冲买入 132：对冲卖出 170：到期行权 171：到期被行权 172：到期作废 112：交割平多 113：交割平空 117：交割/期权穿仓补偿 173：资金费支出 174：资金费收入 200:系统转入 201:手动转入 202:系统转出 203:手动转出 204: 大宗交易买 205: 大宗交易卖 206: 大宗交易开多 207: 大宗交易开空 208: 大宗交易平多 209: 大宗交易平空 224: 还债转入 225: 还债转出
     * <br>
     * after --	String	否	请求此id之前（更旧的数据）的分页内容，传的值为对应接口的billId <br>
     * before -- String	否	请求此id之后（更新的数据）的分页内容，传的值为对应接口的billId <br>
     * begin --	String	否	筛选的开始时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     * end -- String	否	筛选的结束时间戳，Unix 时间戳为毫秒数格式，如 1597027383085 <br>
     * limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-bills-details-last-7-days">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-bills-details-last-7-days</a>
     */
    public String bills7Days(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, BILLS_7DAYS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String BILLS_3MONTHS = "/api/v5/account/bills-archive";
    /**
     * 账单流水查询（近三月）
     * 帐户资产流水是指导致帐户余额增加或减少的行为。本接口可以查询最近3个月的账单数据。
     * <br><br>
     * GET /api/v5/account/bills-archive
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- String	否	产品类型
     * SPOT：币币
     * MARGIN：币币杠杆
     * SWAP：永续合约
     * FUTURES：交割合约
     * OPTION：期权 <br>
     * ccy -- String	否	账单币种 <br>
     * mgnMode -- String	否	仓位类型
     * isolated：逐仓 cross：全仓 <br>
     * ctType -- String	否	linear： 正向合约
     * inverse： 反向合约
     * 仅交割/永续有效 <br>
     * type -- String	否	账单类型
     * 1：划转 2：交易 3：交割 4：自动换币 5：强平 6：保证金划转 7：扣息 8：资金费 9：自动减仓 10：穿仓补偿 11：系统换币 12：策略划拨 13：对冲减仓 14: 大宗交易 22: 一键还债
     * <br>
     * subType -- String	否	账单子类型 <br>
     * 1：买入 2：卖出 3：开多 4：开空 5：平多 6：平空 9：市场借币扣息 11：转入 12：转出 14：尊享借币扣息 160：手动追加保证金 161：手动减少保证金 162：自动追加保证金 114：自动换币买入 115：自动换币卖出 118：系统换币转入 119：系统换币转出 100：强减平多 101：强减平空 102：强减买入 103：强减卖出 104：强平平多 105：强平平空 106：强平买入 107：强平卖出 110：强平换币转入 111：强平换币转出 125：自动减仓平多 126：自动减仓平空 127：自动减仓买入 128：自动减仓卖出 131：对冲买入 132：对冲卖出 170：到期行权 171：到期被行权 172：到期作废 112：交割平多 113：交割平空 117：交割/期权穿仓补偿 173：资金费支出 174：资金费收入 200:系统转入 201:手动转入 202:系统转出 203:手动转出 204: 大宗交易买 205: 大宗交易卖 206: 大宗交易开多 207: 大宗交易开空 208: 大宗交易平多 209: 大宗交易平空 224: 还债转入 225: 还债转出
     * <br>
     * after --	String	否	请求此id之前（更旧的数据）的分页内容，传的值为对应接口的billId <br>
     * before -- String	否	请求此id之后（更新的数据）的分页内容，传的值为对应接口的billId <br>
     * begin --	String	否	筛选的开始时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     * end -- String	否	筛选的结束时间戳，Unix 时间戳为毫秒数格式，如 1597027383085 <br>
     * limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-bills-details-last-3-months">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-bills-details-last-3-months</a>
     */
    public String bills3Months(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, BILLS_3MONTHS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String CONFIG = "/api/v5/account/config";
    /**
     * 查看账户配置
     * 查看当前账户的配置信息。
     * <br><br>
     * GET /api/v5/account/config
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * uid -- String	账户ID，账户uid和app上的一致 <br>
     * acctLv  -- String	账户层级 <br>
     * 1：简单交易模式，2：单币种保证金模式 ，3：跨币种保证金模式 ，4：组合保证金模式
     * posMode -- String	持仓方式
     * long_short_mode：双向持仓 net_mode：单向持仓
     * 仅适用交割/永续
     * autoLoan	Boolean	是否自动借币
     * true：自动借币 false：非自动借币 <br>
     * greeksType -- String	当前希腊字母展示方式
     * PA：币本位 BS：美元本位 <br>
     * level --	String	当前在平台上真实交易量的用户等级，例如 lv1 <br>
     * levelTmp -- String	特约用户的临时体验用户等级，例如 lv3 <br>
     * ctIsoMode --	String	衍生品的逐仓保证金划转模式
     * automatic：开仓划转 autonomy：自主划转 <br>
     * mgnIsoMode -- String	币币杠杆的逐仓保证金划转模式
     * automatic：开仓划转 autonomy：自主划转 <br>
     * spotOffsetType -- String	现货对冲类型
     * 1：现货对冲模式U模式 2：现货对冲模式币模式 3：非现货对冲模式
     * 适用于组合保证金模式 <br>
     * label --	String	当前请求API Key的备注名，不超过50位字母（区分大小写）或数字，可以是纯字母或纯数字。 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-account-configuration">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-account-configuration</a>
     */
    public String config(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, CONFIG, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String POSITION_MODE = "/api/v5/account/set-position-mode";
    /**
     * 设置持仓模式
     * 单币种账户和跨币种账户模式：交割和永续合约支持双向持仓模式和单向持仓模式。单向持仓只会有一个方向的仓位；双向持仓可以分别持有多、空2个方向的仓位。
     * 组合保证金模式：交割和永续仅支持单向持仓模式 .
     *
     * <br><br>
     * GET /api/v5/account/set-position-mode
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * posMode	String	是	持仓方式 long_short_mode：双向持仓 net_mode：单向持仓 仅适用交割/永续 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-set-position-mode">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-set-position-mode</a>
     */
    public String setPositionMode(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "posMode", String.class);
        return requestHandler.sendSignedRequest(baseUrl, POSITION_MODE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String SET_LEVERAGE = "/api/v5/account/set-leverage";
    /**
     * 设置杠杆倍数
     * 一个产品可以有如下9种杠杆倍数的设置场景：
     * 1. 在逐仓交易模式下，设置币币杠杆的杠杆倍数（币对层面）；
     * 2. 单币种保证金账户在全仓交易模式下，设置币币杠杆的杠杆倍数（币对层面）；
     * 3. 跨币种保证金账户在全仓交易模式下，设置币币杠杆的杠杆倍数（币种层面）；
     * 4. 在全仓交易模式下，设置交割的杠杆倍数（指数层面）；
     * 5. 在逐仓交易模式、买卖持仓模式下，设置交割的杠杆倍数（合约层面）；
     * 6. 在逐仓交易模式、开平仓持仓模式下，设置交割的杠杆倍数（合约与持仓方向层面）；
     * 7. 在全仓交易模式下，设置永续的杠杆倍数（合约层面）；
     * 8. 在逐仓交易模式、买卖持仓模式下，设置永续的杠杆倍数（合约层面）；
     * 9. 在逐仓交易模式、开平仓持仓模式下，设置永续的杠杆倍数（合约与持仓方向层面）；
     * 注意请求参数 posSide 仅在交割/永续的开平仓持仓模式下才需要填写（参见场景6和9）。
     * 请参阅右侧对应的每个案例的请求示例。
     *
     * <br><br>
     * GET /api/v5/account/set-leverage
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- String	可选	产品ID：币对、合约
     * instId和ccy至少要传一个；如果两个都传，默认使用instId <br>
     * ccy -- String	可选	保证金币种
     * 仅适用于跨币种保证金模式的全仓 币币杠杆。设置自动借币的杠杆倍数时必填 <br>
     * lever --	String	是	杠杆倍数 <br>
     * mgnMode -- String	是	保证金模式
     * isolated：逐仓 cross：全仓
     * 如果ccy有效传值，该参数值只能为cross。 <br>
     * posSide -- String	可选	持仓方向
     * long：双向持仓多头
     * short：双向持仓空头
     * 仅适用于逐仓交割/永续
     * 在双向持仓且保证金模式为逐仓条件下必填 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-set-leverage">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-set-leverage</a>
     */
    public String setLeverage(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "lever", String.class);
        ParameterChecker.checkParameter(parameters, "mgnMode", String.class);
        return requestHandler.sendSignedRequest(baseUrl, SET_LEVERAGE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MAX_SIZE = "/api/v5/account/max-size";
    /**
     * 获取最大可买卖/开仓数量
     *  Portfolio Margin 账户下，衍生品的全仓模式不支持最大可买卖/开仓数量的计算。
     *
     * <br><br>
     * GET /api/v5/account/max-size
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- String	是	产品ID，如 BTC-USDT
     * 支持多产品ID查询（不超过5个），半角逗号分隔 <br>
     * tdMode -- String	是	交易模式
     * cross：全仓 isolated：逐仓 cash：非保证金 <br>
     * ccy -- String	可选	保证金币种，仅适用于单币种保证金模式下的全仓杠杆订单 <br>
     * px -- String	否	委托价格
     * 当不填委托价时会按当前最新成交价计算
     * 当指定多个产品ID查询时，忽略该参数，按当前最新成交价计算 <br>
     * leverage -- String	否	开仓杠杆倍数
     * 默认为当前杠杆倍数
     * 仅适用于币币杠杆/交割/永续 <br>
     * unSpotOffset --	Boolean	否	true：禁止现货对冲，false：允许现货对冲
     * 默认为false
     * 仅适用于组合保证金模式
     * 开启现货对冲模式下有效，否则忽略此参数。 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-maximum-buy-sell-amount-or-open-amount">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-maximum-buy-sell-amount-or-open-amount</a>
     */
    public String maxSize(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "tdMode", String.class);
        return requestHandler.sendSignedRequest(baseUrl, MAX_SIZE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MAX__AVAIL_SIZE = "/api/v5/account/max-avail-size";
    /**
     * 获取最大可用数量
     *
     * <br><br>
     * GET /api/v5/account/max-avail-size
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- String	是	产品ID，如 BTC-USDT
     * 支持多产品ID查询（不超过5个），半角逗号分隔 <br>
     * tdMode -- String	是	交易模式
     * cross：全仓 isolated：逐仓 cash：非保证金 <br>
     * ccy -- String	可选	保证金币种，仅适用于单币种保证金模式下的全仓杠杆订单 <br>
     * reduceOnly -- Boolean	否	是否为只减仓模式，仅适用于币币杠杆 <br>
     * unSpotOffset -- Boolean	否	true：禁止现货对冲，false：允许现货对冲
     * 默认为false
     * 仅适用于组合保证金模式
     * 开启现货对冲模式下有效，否则忽略此参数。 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-maximum-available-tradable-amount">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-maximum-available-tradable-amount</a>
     */
    public String maxAvailSize(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "tdMode", String.class);
        return requestHandler.sendSignedRequest(baseUrl, MAX__AVAIL_SIZE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MARGIN_BALANCE = "/api/v5/account/position/margin-balance";
    /**
     * 调整保证金
     * 增加或者减少逐仓保证金。减少保证金可能会导致实际杠杆倍数发生变化。
     *
     * <br><br>
     * GET /api/v5/account/position/margin-balance
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- String	是	产品ID <br>
     * posSide -- String	是	持仓方向，默认值是net
     * long：双向持仓多头
     * short：双向持仓空头
     * net：单向持仓 <br>
     * type -- String	是	增加/减少保证金
     * add：增加
     * reduce：减少 <br>
     * amt -- String	是	增加或减少的保证金数量 <br>
     * ccy -- String	否	增加或减少的保证金的币种，
     * 仅适用于逐仓自主划转保证金模式下的币币杠杆 <br>
     * auto -- Boolean	否	是否自动借币转 true 或 false，默认false
     * 仅适用于逐仓自主划转保证金模式下的币币杠杆 <br>
     * loanTrans --	Boolean	否	是否支持跨币种保证金模式或组合保证金模式下的借币转入/转出
     * true 或 false，默认false <br>
     * @return String
     * note :
     * 自主划转模式
     * 初始划入逐仓仓位的保证金价值必须大于等于1万USDT,账户上会产生一个仓位。
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-increase-decrease-margin">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-increase-decrease-margin</a>
     */
    public String marginBalance (LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "posSide", String.class);
        ParameterChecker.checkParameter(parameters, "type", String.class);
        ParameterChecker.checkParameter(parameters, "amt", String.class);
        return requestHandler.sendSignedRequest(baseUrl, MARGIN_BALANCE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String LEVERAGE_INFO = "/api/v5/account/leverage-info";
    /**
     * 获取杠杆倍数
     *
     * <br><br>
     * GET /api/v5/account/leverage-info
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- String	是	产品ID
     * 支持多个instId查询，半角逗号分隔。instId个数不超过20个 <br>
     * mgnMode -- String	是	保证金模式
     * isolated：逐仓 cross：全仓 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-leverage">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-leverage</a>
     */
    public String leverageInfo(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "mgnMode", String.class);
        return requestHandler.sendSignedRequest(baseUrl, LEVERAGE_INFO, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MAX_LOAN = "/api/v5/account/max-loan";
    /**
     * 获取交易产品最大可借
     *
     * <br><br>
     * GET /api/v5/account/max-loan
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- String	是	产品 ID，如 BTC-USDT
     * 支持多产品ID查询（不超过5个），半角逗号分隔 <br>
     * mgnMode -- String	是	仓位类型
     * isolated：逐仓
     * cross：全仓 <br>
     * mgnCcy -- String	可选	保证金币种，如 BTC
     * 币币杠杆单币种全仓情况下必须指定保证金币种 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-the-maximum-loan-of-instrument">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-the-maximum-loan-of-instrument</a>
     */
    public String maxLoan(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "mgnMode", String.class);
        return requestHandler.sendSignedRequest(baseUrl, MAX_LOAN, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String TRADE_FEE = "/api/v5/account/trade-fee";
    /**
     * 获取当前账户交易手续费费率
     *
     * <br><br>
     * GET /api/v5/account/trade-fee
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- String	是	产品类型
     * SPOT：币币
     * MARGIN：币币杠杆
     * SWAP：永续合约
     * FUTURES：交割合约
     * OPTION：期权 <br>
     * instId -- String	否	产品ID，如 BTC-USDT
     * 仅适用于instType为币币/币币杠杆 <br>
     * uly -- String	否	标的指数
     * 适用于交割/永续/期权，如 BTC-USD <br>
     * instFamily -- String	否	交易品种
     * 适用于交割/永续/期权，如 BTC-USD <br>
     * @return String
     *  备注：
     * maker/taker的值：正数，代表是返佣的费率；负数，代表平台扣除的费率。
     *  USDⓈ 代表除 USDT 和 USDC 之外的稳定币。
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-fee-rates">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-fee-rates</a>
     */
    public String tradeFee(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, TRADE_FEE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String INTEREST_ACCRUED = "/api/v5/account/interest-accrued";
    /**
     * 获取计息记录
     *
     * <br><br>
     * GET /api/v5/account/interest-accrued
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * type -- String	否	借币类型
     * 1：尊享借币
     * 2：市场借币
     * 默认为市场借币 <br>
     * ccy -- String	否	借贷币种，如 BTC
     * 仅适用于市场借币
     * 仅适用于币币杠杆 <br>
     * instId -- String	否	产品ID，如 BTC-USDT
     * 仅适用于市场借币 <br>
     * mgnMode -- String	否	保证金模式
     * cross：全仓
     * isolated：逐仓
     * 仅适用于市场借币  <br>
     * after -- String	否	请求此时间戳之前（更旧的数据）的分页内容，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * before -- String	否	请求此时间戳之后（更新的数据）的分页内容，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * limit -- String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     *  备注：
     * maker/taker的值：正数，代表是返佣的费率；负数，代表平台扣除的费率。
     *  USDⓈ 代表除 USDT 和 USDC 之外的稳定币。
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-interest-accrued-data">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-interest-accrued-data</a>
     */
    public String interestAccrued(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, INTEREST_ACCRUED, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String INTEREST_RATE = "/api/v5/account/interest-rate";
    /**
     * 获取用户当前杠杆借币利率
     *
     * <br><br>
     * GET /api/v5/account/interest-rate
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ccy -- String	否	币种 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-interest-rate">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-interest-rate</a>
     */
    public String interestRate(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, INTEREST_RATE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String SET_GREEKS = "/api/v5/account/set-greeks";
    /**
     * 期权greeks的PA/BS切换
     * 设置greeks的展示方式。
     *
     * <br><br>
     * GET /api/v5/account/set-greeks
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * greeksType -- String	是	希腊字母展示方式
     * PA：币本位，BS：美元本位 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-set-greeks-pa-bs">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-set-greeks-pa-bs</a>
     */
    public String setGreeks(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "greeksType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, SET_GREEKS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String SET_ISOLATED_MODE = "/api/v5/account/set-isolated-mode";
    /**
     * 逐仓交易设置
     * 可以通过该接口设置币币杠杆和交割、永续的逐仓仓位保证金的划转模式
     *
     * <br><br>
     * GET /api/v5/account/set-isolated-mode
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * isoMode -- String	是	逐仓保证金划转模式
     * automatic:开仓自动划转
     * autonomy:自主划转 <br>
     * type -- String	是	业务线类型
     * MARGIN:币币杠杆
     * CONTRACTS:合约 <br>
     * @return String
     * note:当前账户内有持仓和挂单时，不能调整逐仓保证金划转模式。
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-isolated-margin-trading-settings">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-isolated-margin-trading-settings</a>
     */
    public String setIsolatedMode(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "isoMode", String.class);
        ParameterChecker.checkParameter(parameters, "type", String.class);
        return requestHandler.sendSignedRequest(baseUrl, SET_ISOLATED_MODE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MAX_WITHDRAWAL = "/api/v5/account/max-withdrawal";
    /**
     * 查看账户最大可转余额
     * 当指定币种时会返回该币种的交易账户到资金账户的最大可划转数量，不指定币种会返回所有拥有的币种资产可划转数量。
     *
     * <br><br>
     * GET /api/v5/account/max-withdrawal
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ccy -- String	否	币种，如 BTC
     * 支持多币种查询（不超过20个），币种之间半角逗号分隔 <br>
     * @return String
     * note:当前账户内有持仓和挂单时，不能调整逐仓保证金划转模式。
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-maximum-withdrawals">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-maximum-withdrawals</a>
     */
    public String maxWithdrawal(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, MAX_WITHDRAWAL, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String RISK_STATE = "/api/v5/account/risk-state";
    /**
     * 查看账户特定风险状态
     * 仅适用于PM账户
     *
     * <br><br>
     * GET /api/v5/account/risk-state
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * atRisk -- String	自动借币模式下的账户风险状态
     * true： 当前账户为特定风险状态
     * false： 当前不是特定风险状态 <br>
     * atRiskIdx --	Array	衍生品的risk unit列表 <br>
     * atRiskMgn --	Array	杠杆的risk unit列表 <br>
     * ts -- String	接口数据返回时间 ，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * @return String
     * note: 当账户进入特定风险状态后，仅可以委托降低账户风险方向的IOC类型订单.
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-account-risk-state">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-account-risk-state</a>
     */
    public String riskState(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, RISK_STATE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  BORROW_REPAY = "/api/v5/account/quick-margin-borrow-repay";
    /**
     * 一键借币模式手动借币还币
     *
     * <br><br>
     * GET /api/v5/account/quick-margin-borrow-repay
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- String	是	产品ID，如BTC-USDT <br>
     * ccy -- String	是	借贷币种，如 BTC <br>
     * side -- String	是	borrow：借币，repay：还币 <br>
     * amt -- String	是	借/还币的数量 <br>
     * @return String
     * note: 当账户进入特定风险状态后，仅可以委托降低账户风险方向的IOC类型订单.
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-manual-borrow-and-repay-in-quick-margin-mode">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-manual-borrow-and-repay-in-quick-margin-mode</a>
     */
    public String borrowRepay(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instId", String.class);
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "amt", String.class);
        return requestHandler.sendSignedRequest(baseUrl, BORROW_REPAY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  BORROW_REPAY_HISTORY = "/api/v5/account/borrow-repay-history";
    /**
     * 获取一键借币还币历史
     *
     * <br><br>
     * GET /api/v5/account/borrow-repay-history
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instId -- String	否	产品ID，如 BTC-USDT <br>
     * ccy -- String	否	借贷币种，如 BTC <br>
     * side -- String	否	borrow：借币，repay：还币 <br>
     * after --	String	否	请求此 ID 之前（更旧的数据）的分页内容，传的值为对应接口的refId <br>
     * before -- String	否	请求此 ID 之后（更新的数据）的分页内容，传的值为对应接口的refId <br>
     * begin --	String	否	筛选的开始时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     * end -- String	否	筛选的结束时间戳，Unix 时间戳为毫秒数格式，如 1597027383085 <br>
     * limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-manual-borrow-and-repay-history-in-quick-margin-mode">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-manual-borrow-and-repay-history-in-quick-margin-mode</a>
     */
    public String borrowRepayHistory(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, BORROW_REPAY_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  BORROW_REPAY_VIP = "/api/v5/account/borrow-repay";
    /**
     * 尊享借币还币
     *
     * <br><br>
     * GET /api/v5/account/borrow-repay
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ccy -- String	是	借贷币种，如 BTC <br>
     * side -- String	是	borrow：借币，repay：还币 <br>
     * amt -- String	是	借/还币的数量 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-vip-loans-borrow-and-repay">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-vip-loans-borrow-and-repay</a>
     */
    public String borrowRepayVip(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "amt", String.class);
        return requestHandler.sendSignedRequest(baseUrl, BORROW_REPAY_VIP, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  BORROW_REPAY_HISTORY_VIP = "/api/v5/account/borrow-repay-history";
    /**
     * 获取尊享借币还币历史
     *
     * <br><br>
     * GET /api/v5/account/borrow-repay-history
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ccy -- String	否	借贷币种，如 BTC <br>
     * after --	String	否	请求此时间戳之前（更旧的数据）的分页内容，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * before -- String	否	请求此时间戳之后（更新的数据）的分页内容，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-borrow-and-repay-history-for-vip-loans">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-borrow-and-repay-history-for-vip-loans</a>
     */
    public String borrowRepayHistoryVip(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, BORROW_REPAY_HISTORY_VIP, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  INTEREST_ACCRUED_VIP = "/api/v5/account/vip-interest-accrued";
    /**
     * 获取尊享借币计息记录
     *
     * <br><br>
     * GET /api/v5/account/vip-interest-accrued
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ccy -- String	否	借贷币种，如 BTC，仅适用于币币杠杆 <br>
     * ordId --	String	否	借币订单ID <br>
     * after --	String	否	请求此时间戳之前（更旧的数据）的分页内容，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * before -- String	否	请求此时间戳之后（更新的数据）的分页内容，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-vip-interest-accrued-data">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-vip-interest-accrued-data</a>
     */
    public String interestAccruedVip(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, INTEREST_ACCRUED_VIP, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  ORDERS_HISTORY_VIP = "/api/v5/trade/orders-history";
    /**
     * 尊享借币订单列表
     *
     * <br><br>
     * GET /api/v5/trade/orders-history
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ordId --	String	否	借币订单ID <br>
     * state --	String	否	订单状态
     * 1:借币申请中
     * 2:借币中
     * 3:还币申请中
     * 4:已还币
     * 5:借币失败 <br>
     * ccy -- String	否	借贷币种，如 BTC <br>
     * after --	String	否	请求此ID之前（更旧的数据）的分页内容，传的值为对应接口的ordId <br>
     * before -- String	否	请求此ID之后（更新的数据）的分页内容，传的值为对应接口的ordId <br>
     * limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-vip-loan-order-list">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-vip-loan-order-list</a>
     */
    public String ordersHistoryVip(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, ORDERS_HISTORY_VIP, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  ORDERS_DETAIL_VIP = "/api/v5/account/vip-loan-order-detail";
    /**
     * 尊享借币订单详情
     *
     * <br><br>
     * GET /api/v5/account/vip-loan-order-detail
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ordId --	String	是	借币订单ID <br>
     * ccy -- String	否	借贷币种，如 BTC <br>
     * after --	String	否	请求此时间戳之后（更新的数据）的分页内容，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * before -- String	否	请求此时间戳之前（更旧的数据）的分页内容，Unix时间戳的毫秒数格式，如 1597026383085 <br>
     * limit --	String	否	返回结果的数量，最大为100，默认100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-vip-loan-order-detail">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-vip-loan-order-detail</a>
     */
    public String ordersDetailVip(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ordId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDERS_DETAIL_VIP, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  INTEREST_LIMITS = "/api/v5/account/interest-limits";
    /**
     * 获取借币利率与限额
     *
     * <br><br>
     * GET /api/v5/account/interest-limits
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * type -- String	否	借币类型
     * 1：尊享借币
     * 2：市场借币
     * 默认为市场借币 <br>
     * ccy -- String	否	借贷币种，如 BTC <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-borrow-interest-and-limit">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-borrow-interest-and-limit</a>
     */
    public String interestLimits(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, INTEREST_LIMITS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  SIMULATED_MARGIN = "/api/v5/account/simulated_margin";
    /**
     * 组合保证金的虚拟持仓保证金计算
     * 计算用户的模拟头寸或当前头寸的投资组合保证金信息，一次请求最多添加200个虚拟仓位
     *
     * <br><br>
     * GET /api/v5/account/simulated_margin
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- String	否	产品类型
     * SWAP：永续合约
     * FUTURES：交割合约
     * OPTION：期权 <br>
     * inclRealPos -- Boolean	否	是否代入已有仓位
     * true：调整被代入的已有仓位信息
     * false：不代入已有仓位，仅使用simPos里新增的模拟仓位进行计算,默认为True <br>
     * spotOffsetType -- String	否	现货对冲模式
     * 1：现货对冲模式U模式 2：现货对冲模式币模式 3：衍生品模式
     * 默认是 3 <br>
     * simPos -- Array	否	调整持仓列表
     * > instId -- String	否	交易产品ID
     * > pos --	String	否	持仓量 <br>
     * @return String
     * note:  代入的仅是单币种账户、跨币种账户、PM账户里的衍生品买卖模式下的全仓仓位，不包含杠杆仓位。
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-position-builder">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-position-builder</a>
     */
    public String simulatedMargin(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, SIMULATED_MARGIN, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  GREEKS = "/api/v5/account/greeks";
    /**
     * 查看账户Greeks
     * 获取账户资产的greeks信息。
     *
     * <br><br>
     * GET /api/v5/account/greeks
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * ccy -- String	否	币种，如 BTC <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-greeks">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-greeks</a>
     */
    public String greeks(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, GREEKS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  POSITION_TIERS = "/api/v5/account/position-tiers";
    /**
     * 获取组合保证金模式全仓限制
     * 仅支持获取组合保证金模式下，交割、永续和期权的全仓限制。
     *
     * <br><br>
     * GET /api/v5/account/position-tiers
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * instType -- String	是	产品类型
     * SWAP：永续合约
     * FUTURES：交割合约
     * OPTION：期权 <br>
     * uly -- String	可选	标的指数，如 BTC-USDT，支持多个查询（不超过3个），uly之间半角逗号分隔
     * 适用于交割/永续/期权
     * uly与instFamily必须传一个,若传两个，以instFamily为主 <br>
     * instFamily -- String	可选	交易品种，如 BTC-USDT，支持多个查询（不超过5个），instFamily之间半角逗号分隔
     * 适用于交割/永续/期权
     * uly与instFamily必须传一个,若传两个，以instFamily为主 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-get-pm-limitation">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-get-pm-limitation</a>
     */
    public String positionTiers(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "instType", String.class);
        return requestHandler.sendSignedRequest(baseUrl, POSITION_TIERS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String  RISKOFFSET_TYPE = "/api/v5/account/set-riskOffset-type";
    /**
     * 设置组合保证金账户风险对冲模式
     * 设置 Portfolio Margin 账户风险对冲模式
     *
     * <br><br>
     * GET /api/v5/account/set-riskOffset-type
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * type	String	是	风险对冲模式
     * 1：现货对冲(USDT)
     * 2:现货对冲(币)
     * 3:衍生品对冲 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-account-set-risk-offset-type">
     *     https://www.okx.com/docs-v5/zh/#rest-api-account-set-risk-offset-type</a>
     */
    public String setRiskOffsetType(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "type", String.class);
        return requestHandler.sendSignedRequest(baseUrl, RISKOFFSET_TYPE, parameters, HttpMethod.GET, showLimitUsage);
    }


}
