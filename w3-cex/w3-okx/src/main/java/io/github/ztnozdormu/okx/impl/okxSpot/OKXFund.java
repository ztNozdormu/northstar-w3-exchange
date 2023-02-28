package io.github.ztnozdormu.okx.impl.okxSpot;

import io.github.ztnozdormu.common.domain.SubAccount;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.utils.ParameterChecker;
import io.github.ztnozdormu.common.utils.RequestHandler;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * <h2>Fund Endpoints</h2>
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding">Fund Endpoint</a>
 * 资金
 * 资金功能模块下的API接口需要身份验证。
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXFund extends SubAccount {

    String passPhrase;

    boolean isSimluate;

    public OKXFund(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String CURRENCIES = "/api/v5/asset/currencies";

    /**
     * 获取币种列表
     * 获取平台所有币种列表。
     * <br><br>
     * GET /api/v5/asset/currencies
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	否	币种，如 BTC
     *                   支持多币种查询（不超过20个），币种之间半角逗号分隔 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-currencies">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-currencies</a>
     */
    public String currencies(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, CURRENCIES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String BALANCES = "/api/v5/asset/balances";

    /**
     * 获取资金账户余额
     * 获取资金账户所有资产列表，查询各币种的余额、冻结和可用等信息。
     * 只返回余额大于0的币资产信息。
     * <br><br>
     * GET /api/v5/asset/balances
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	否	币种，如 BTC
     *                   支持多币种查询（不超过20个），币种之间半角逗号分隔<br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-balance">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-balance</a>
     */
    public String balances(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, BALANCES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String NON_TRADE_ASSETS = "/api/v5/asset/non-tradable-assets";

    /**
     * 获取不可交易资产
     * <br><br>
     * GET /api/v5/asset/non-tradable-assets
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	否	币种，如 BTC
     *                   支持多币种查询（不超过20个），币种之间半角逗号分隔 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-non-tradable-assets">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-non-tradable-assets</a>
     */
    public String nonTradableAssets(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, NON_TRADE_ASSETS, parameters, HttpMethod.GET, showLimitUsage);
    }


    private final String ASSETS_VALUATION = "/api/v5/asset/asset-valuation";

    /**
     * 获取账户资产估值
     * 查看账户资产估值
     * <br><br>
     * GET /api/v5/asset/asset-valuation
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	否	资产估值对应的单位
     *                   BTC 、USDT
     *                   USD 、CNY 、JPY、KRW、RUB、EUR
     *                   VND 、IDR 、INR、PHP、THB、TRY
     *                   AUD 、SGD 、ARS、SAR、AED、IQD
     *                   默认为 BTC 为单位的估值 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-account-asset-valuation">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-account-asset-valuation</a>
     */
    public String assetValuation(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, ASSETS_VALUATION, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ASSETS_TRANSFER = "/api/v5/asset/transfer";

    /**
     * 资金划转
     * 调用时，APIKey 需要有交易权限
     * 支持母账户的资金账户划转到交易账户，母账户到子账户的资金账户和交易账户划转；
     * 子账户默认可转出至母账户，划转到同一母账户下的其他子账户，需要先调用“设置子账户转出权限”接口进行授权。
     * note: 请求失败不代表划转失败，建议以获取资金划转状态接口返回的状态为准。
     * <br><br>
     * GET /api/v5/asset/transfer
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	是	币种，如 USDT <br>
     *                   amt -- String	是	划转数量 <br>
     *                   from -- String	是	转出账户
     *                   6：资金账户 18：交易账户 <br>
     *                   to -- String	是	转入账户
     *                   6：资金账户 18：交易账户 <br>
     *                   subAcct -- String	可选	子账户名称，type 为1，2 或 4：subAcct 为必填项 <br>
     *                   type -- String	否	划转类型
     *                   0：账户内划转
     *                   1：母账户转子账户(仅适用于母账户APIKey)
     *                   2：子账户转母账户(仅适用于母账户APIKey)
     *                   3：子账户转母账户(仅适用于子账户APIKey)
     *                   4：子账户转子账户(仅适用于子账户APIKey，且目标账户需要是同一母账户下的其他子账户)
     *                   默认是0 <br>
     *                   loanTrans --	Boolean	否	是否支持跨币种保证金模式或组合保证金模式下的借币转入/转出
     *                   true 或 false，默认false <br>
     *                   clientId -- String	否	客户自定义ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。 <br>
     *                   omitPosRisk -- String	否	是否忽略仓位风险
     *                   默认为false
     *                   仅适用于组合保证金模式 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-funds-transfer">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-funds-transfer</a>
     */
    public String transfer(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "amt", String.class);
        ParameterChecker.checkParameter(parameters, "from", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ASSETS_TRANSFER, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String TRANSFER_STATE = "/api/v5/asset/transfer-state";

    /**
     * 获取资金划转状态
     * 获取最近2个星期内的资金划转状态数据
     * <br><br>
     * GET /api/v5/asset/transfer-state
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   transId -- String	可选	划转ID
     *                   transId和clientId必须传一个，若传两个，以transId为主 <br>
     *                   clientId -- String	可选	客户自定义ID <br>
     *                   type -- String	否	划转类型
     *                   0：账户内划转
     *                   1：母账户转子账户(仅适用于母账户APIKey)
     *                   2：子账户转母账户(仅适用于母账户APIKey)
     *                   3：子账户转母账户(仅适用于子账户APIKey)
     *                   4：子账户转子账户(仅适用于子账户APIKey，且目标账户需要是同一母账户下的其他子账户)
     *                   默认是0 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-funds-transfer-state">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-funds-transfer-state</a>
     */
    public String transferState(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "transId", String.class);
        ParameterChecker.checkParameter(parameters, "clientId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, TRANSFER_STATE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ASSET_BILLS = "/api/v5/asset/bills";

    /**
     * 获取资金流水
     * 查询资金账户账单流水，可查询最近一个月的数据。
     *
     * <br><br>
     * GET /api/v5/asset/bills
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	否	币种 <br>
     *                   type -- String	否	账单类型
     *                   1：充值
     *                   2：提现
     *                   13：撤销提现
     *                   20：转出至子账户（主体是母账户）
     *                   21：从子账户转入（主体是母账户）
     *                   22：转出到母账户（主体是子账户）
     *                   23：母账户转入（主体是子账户）
     *                   28：领取
     *                   47：系统冲正
     *                   48：活动得到
     *                   49：活动送出
     *                   50：预约得到
     *                   51：预约扣除
     *                   52：发红包
     *                   53：抢红包
     *                   54：红包退还
     *                   61：兑换
     *                   68：领取卡券权益
     *                   69：发送卡券权益
     *                   72：收币
     *                   73：送币
     *                   74：送币退还
     *                   75：申购余币宝
     *                   76：赎回余币宝
     *                   77：派发
     *                   78：锁定
     *                   79：节点投票
     *                   80：锁仓挖矿
     *                   81：投票赎回
     *                   82：锁仓赎回
     *                   83：锁仓挖矿收益
     *                   84：违约金
     *                   85：算力挖矿收益
     *                   86：云算力支付
     *                   87：云算力收益
     *                   88：补贴收益
     *                   89：存币收益
     *                   90：挖矿申购
     *                   91：挖矿赎回
     *                   92：补充质押物
     *                   93：赎回质押物
     *                   94：投资
     *                   95：借款人借款
     *                   96：投资本金转入
     *                   97：借款人借款转出
     *                   98：借款人借款利息转出
     *                   99：投资人投资利息转入
     *                   102：提前还款违约金转入
     *                   103：提前还款违约金转出
     *                   104：抵押借贷手续费转入
     *                   105：抵押借贷手续费转出
     *                   106：逾期手续费转入
     *                   107：逾期手续费转出
     *                   108：逾期利息转出
     *                   109：借款还款逾期利息转入
     *                   110：平仓质押物转入到系统账号
     *                   111：平仓质押物转出到系统账号
     *                   112：爆仓质押物转入到系统账号
     *                   113：爆仓质押物转出到系统账号
     *                   114：风险准备金转入
     *                   115：风险准备金转出
     *                   116：创建订单
     *                   117：完成订单
     *                   118：取消订单
     *                   119：商家解冻保证金
     *                   120：商家添加保证金
     *                   121：FiatGateway 创建订单
     *                   122：FiatGateway 取消订单
     *                   123：FiatGateway 完成订单
     *                   124：Jumpstart 解锁
     *                   125：手动注入
     *                   126：利息注入
     *                   127：投资手续费转入
     *                   128：投资手续费转出
     *                   129：奖励转入
     *                   130：从交易账户转入
     *                   131：转出至交易账户132：客服冻结
     *                   133：客服解冻
     *                   134：客服转交
     *                   135：跨链兑换
     *                   136：兑换
     *                   137：ETH2.0申购
     *                   138：ETH2.0兑换
     *                   139：ETH2.0收益
     *                   143：系统退款
     *                   145：系统回收
     *                   146：客户回馈
     *                   147：sushi 增发收益
     *                   150：节点返佣
     *                   151：邀请奖励
     *                   152：经纪商返佣
     *                   153：新手奖励
     *                   154：拆盲盒奖励
     *                   155：福利中心提现
     *                   156：返佣卡返佣
     *                   157：红包
     *                   160：双币赢申购
     *                   161：双币赢回款
     *                   162：双币赢收益
     *                   163：双币赢退款
     *                   169：2022春节红包
     *                   172：助力人返佣
     *                   173：手续费返现
     *                   174：支付
     *                   175：锁定质押物
     *                   176：借款转入
     *                   177：添加质押物
     *                   178：减少质押物
     *                   179：还款
     *                   180：释放质押物
     *                   181：偿还空投糖果
     *                   182：反馈奖励
     *                   183：邀请好友奖励
     *                   184：瓜分奖池奖励
     *                   185：经纪商闪兑返佣
     *                   186：0元领ETH
     *                   187：闪兑划转
     *                   188：插槽竞拍兑换
     *                   189：盲盒奖励
     *                   193：卡支付购买
     *                   195：不可交易资产提币
     *                   196：不可交易资产提币撤销
     *                   197：不可交易资产充值
     *                   198：无效资产减少
     *                   199：有效资产增加
     *                   200：买入
     *                   211：投聪夺币中奖 <br>
     *                   clientId --	String	否	转账或提币的客户自定义ID  <br>
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。<br>
     *                   after --	String	否	查询在此之前的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   limit --	String	否	分页返回的结果集数量，最大为 100，不填默认返回 100 条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-funds-transfer-state">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-funds-transfer-state</a>
     */
    public String assetBills(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, ASSET_BILLS, parameters, HttpMethod.GET, showLimitUsage);
    }


    private final String DEPOSIT_LIGHTNING = "/api/v5/asset/deposit-lightning";

    /**
     * 闪电网络充币
     * 一个用户在最近24小时内可以生成不超过1万个不同的invoice。
     * <br><br>
     * GET /api/v5/asset/deposit-lightning
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	是	币种，仅支持BTC
     *                   amt -- String	是	充值数量，推荐在0.000001〜0.1之间 <br>
     *                   to -- String	否	资金充值到账账户
     *                   6: 资金账户
     *                   18: 交易账户
     *                   不填写此参数，默认到账资金账户 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-lightning-deposits">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-lightning-deposits</a>
     */
    public String depositLightning(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "amt", String.class);
        return requestHandler.sendSignedRequest(baseUrl, DEPOSIT_LIGHTNING, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String DEPOSIT_ADDRESS = "/api/v5/asset/deposit-address";

    /**
     * 获取充值地址信息
     * 获取各个币种的充值地址，包括曾使用过的老地址。
     * <br><br>
     * GET /api/v5/asset/deposit-address
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	是	币种，如BTC <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-deposit-address">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-deposit-address</a>
     */
    public String depositAddress(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        return requestHandler.sendSignedRequest(baseUrl, DEPOSIT_ADDRESS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String DEPOSIT_HISTORY = "/api/v5/asset/deposit-history";

    /**
     * 获取充值记录
     * 根据币种，充值状态，时间范围获取充值记录，按照时间倒序排列，默认返回 100 条数据。
     * <br><br>
     * GET /api/v5/asset/deposit-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	否	币种名称，如 BTC <br>
     *                   depId -- String	否	充值记录 ID <br>
     *                   txId -- String	否	区块转账哈希记录 <br>
     *                   type -- String	否	充值方式
     *                   3：内部转账
     *                   4：链上充值 <br>
     *                   state --	String	否	充值状态
     *                   0：等待确认
     *                   1：确认到账
     *                   2：充值成功
     *                   8：因该币种暂停充值而未到账，恢复充值后自动到账
     *                   12：账户或充值被冻结
     *                   13：子账户充值拦截 <br>
     *                   after --	String	否	查询在此之前的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1654041600000 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1656633600000 <br>
     *                   limit --	string	否	返回的结果集数量，默认为100，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-deposit-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-deposit-history</a>
     */
    public String depositHistory(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, DEPOSIT_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String WITHDRAWAL = "/api/v5/asset/withdrawal";

    /**
     * 提币
     * 用户提币。普通子账户不支持提币。
     * <br><br>
     * GET /api/v5/asset/withdrawal
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	是	币种，如 USDT <br>
     *                   amt -- String	是	数量 <br>
     *                   dest -- String	是	提币方式
     *                   3：内部转账
     *                   4：链上提币 <br>
     *                   toAddr -- String	是	如果选择链上提币，toAddr必须是认证过的数字货币地址。某些数字货币地址格式为地址:标签，如 ARDOR-7JF3-8F2E-QUWZ-CAN7F:123456
     *                   如果选择内部转账，toAddr必须是接收方地址，可以是邮箱、手机或者账户名。 <br>
     *                   fee -- String	是	网络手续费≥0，提币到数字货币地址所需网络手续费可通过获取币种列表接口查询 <br>
     *                   chain --	String	可选	币种链信息
     *                   如USDT下有USDT-ERC20，USDT-TRC20，USDT-Omni多个链
     *                   如果没有不填此参数，则默认为主链 <br>
     *                   areaCode -- String	可选	手机区号
     *                   当toAddr为手机号时，该参数必填 <br>
     *                   clientId -- String	否	客户自定义ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。 <br>
     * @return String <br>
     * note: 关于标签：某些币种如XRP充币时同时需要一个充值地址和标签（又名memo/payment_id），标签是一种保证您的充币地址唯一性的数字串，与充币地址成对出现并一一对应。请您务必遵守正确的充值步骤，在提币时输入完整信息，否则将面临丢失币的风险！
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-withdrawal">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-withdrawal</a>
     */
    public String withdrawal(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "amt", String.class);
        ParameterChecker.checkParameter(parameters, "dest", String.class);
        ParameterChecker.checkParameter(parameters, "toAddr", String.class);
        ParameterChecker.checkParameter(parameters, "fee", String.class);
        return requestHandler.sendSignedRequest(baseUrl, WITHDRAWAL, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String WITHDRAWAL_LIGHTNING = "/api/v5/asset/withdrawal-lightning";

    /**
     * 闪电网络提币
     * 单笔提币金额最大值为"0.1BTC"，最小值暂定为"0.000001BTC"，最近24小时内最大提币数量为"1BTC"。子账户不支持提币。
     *
     * <br><br>
     * GET /api/v5/asset/withdrawal-lightning
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	是	币种，如 BTC <br>
     *                   invoice -- String	是	invoice 号码 <br>
     *                   memo -- String	否	闪电网络提币的备注 <br>
     * @return String <br>
     * note: 仅针对部分用户开放此API功能服务，如果需要此功能服务请发邮件至`pablo.magro@okx.com`申请
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-lightning-withdrawals">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-lightning-withdrawals</a>
     */
    public String withdrawalLightning(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "invoice", String.class);
        return requestHandler.sendSignedRequest(baseUrl, WITHDRAWAL_LIGHTNING, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String CANCEL_WITHDRAWAL = "/api/v5/asset/cancel-withdrawal";

    /**
     * 撤销提币
     * 可以撤销普通提币，但不支持撤销闪电网络上的提币。
     *
     * <br><br>
     * GET /api/v5/asset/cancel-withdrawal
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   wdId -- String	是	提币申请ID <br>
     * @return String <br>
     * note:  接口返回code等于0不能严格认为提币已经被撤销，只表示您的请求被系统服务器所接受，实际结果以获取提币记录中的状态为准。
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-cancel-withdrawal">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-cancel-withdrawal</a>
     */
    public String cancelWithdrawal(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "wdId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, CANCEL_WITHDRAWAL, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String WITHDRAWAL_HISTORY = "/api/v5/asset/withdrawal-history";

    /**
     * 获取提币记录
     * 根据币种，提币状态，时间范围获取提币记录，按照时间倒序排列，默认返回100条数据。
     *
     * <br><br>
     * GET /api/v5/asset/withdrawal-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	否	币种名称，如 BTC <br>
     *                   wdId -- String	否	提币申请ID <br>
     *                   clientId -- String	否	客户自定义ID
     *                   字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。 <br>
     *                   txId -- String	否	区块转账哈希记录 <br>
     *                   type -- String	否	提币方式
     *                   3：内部转账
     *                   4：链上提币 <br>
     *                   state --	String	否	提币状态
     *                   -3：撤销中
     *                   -2：已撤销
     *                   -1：失败
     *                   0：等待提币
     *                   1：提币中
     *                   2：提币成功
     *                   7: 审核通过
     *                   10: 等待划转
     *                   4, 5, 6, 8, 9, 12: 等待客服审核 <br>
     *                   after --	String	否	查询在此之前的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1654041600000 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1656633600000 <br>
     *                   limit --	string	否	返回的结果集数量，默认为100，最大为100，不填默认返回100条 <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-withdrawal-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-withdrawal-history</a>
     */
    public String withdrawalHistory(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, WITHDRAWAL_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String CONVERT_DUST_ASSETS = "/api/v5/asset/convert-dust-assets";

    /**
     * 小额资产兑换
     * 将资金账户中的小额资产转化为OKB。24小时之内只能兑换一次。
     *
     * <br><br>
     * GET /api/v5/asset/convert-dust-assets
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- Array	是	需要转换的币种资产 <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-small-assets-convert">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-small-assets-convert</a>
     */
    public String convertDustAssets(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", Arrays.class);
        return requestHandler.sendSignedRequest(baseUrl, CONVERT_DUST_ASSETS, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String SAVING_BALANCE = "/api/v5/asset/saving-balance";

    /**
     * 获取余币宝余额
     *
     * <br><br>
     * GET /api/v5/asset/saving-balance
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	否	币种，如 BTC <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-saving-balance">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-saving-balance</a>
     */
    public String savingBalance(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, SAVING_BALANCE, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String PURCHASE_REDEMPT = "/api/v5/asset/purchase_redempt";

    /**
     * 余币宝申购/赎回
     * 仅资金账户中的资产支持余币宝申购。
     *
     * <br><br>
     * GET /api/v5/asset/purchase_redempt
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	是	币种名称，如 BTC <br>
     *                   amt -- String	是	申购（赎回）数量 <br>
     *                   side -- String	是	操作类型
     *                   purchase：申购 redempt：赎回 <br>
     *                   rate -- String	是	申购利率
     *                   仅适用于申购，新申购的利率会覆盖上次申购的利率
     *                   参数取值范围在1%到365%之间 <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-savings-purchase-redemption">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-savings-purchase-redemption</a>
     */
    public String purchaseRedempt(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "amt", String.class);
        ParameterChecker.checkParameter(parameters, "side", String.class);
        ParameterChecker.checkParameter(parameters, "rate", String.class);
        return requestHandler.sendSignedRequest(baseUrl, PURCHASE_REDEMPT, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String SET_LENDING_RATE = "/api/v5/asset/set-lending-rate";

    /**
     * 设置余币宝借贷利率
     *
     * <br><br>
     * GET /api/v5/asset/set-lending-rate
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	是	币种名称，如 BTC <br>
     *                   rate -- String	是	贷出利率
     *                   参数取值范围在1%到365%之间 <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-set-lending-rate">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-set-lending-rate</a>
     */
    public String setLendingRate(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "rate", String.class);
        return requestHandler.sendSignedRequest(baseUrl, SET_LENDING_RATE, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String LENDING_HISTORY = "/api/v5/asset/lending-history";

    /**
     * 获取余币宝出借明细
     *
     * <br><br>
     * GET /api/v5/asset/lending-history
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy --	String	否	币种，如 BTC  <br>
     *                   after --	String	否	查询在此之前的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix 时间戳为毫秒数格式，如 1597026383085 <br>
     *                   limit --	String	否	分页返回的结果集数量，最大为 100，不填默认返回 100 条 <br>
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-funding-get-lending-history">
     * https://www.okx.com/docs-v5/zh/#rest-api-funding-get-lending-history</a>
     */
    public String lendingHistory(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, LENDING_HISTORY, parameters, HttpMethod.GET, showLimitUsage);
    }

}
