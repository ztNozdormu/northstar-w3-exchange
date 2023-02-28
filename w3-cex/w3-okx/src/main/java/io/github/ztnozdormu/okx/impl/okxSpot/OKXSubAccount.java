package io.github.ztnozdormu.okx.impl.okxSpot;

import io.github.ztnozdormu.common.domain.SubAccount;
import io.github.ztnozdormu.common.enums.HttpMethod;
import io.github.ztnozdormu.common.utils.ParameterChecker;
import io.github.ztnozdormu.common.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>Sub-Account Endpoints</h2>
 * <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount">Sub-Account Endpoint</a>
 * 子账户
 * 子账户功能模块下的API接口需要身份验证
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class OKXSubAccount extends SubAccount {

    String passPhrase;

    boolean isSimluate;

    public OKXSubAccount(String baseUrl, String apiKey, String secretKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, secretKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String LIST = "/api/v5/users/subaccount/list";

    /**
     * 查看子账户列表
     * <b>仅适用于母账户</b>
     * <br><br>
     * GET /api/v5/users/subaccount/list
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   enable -- String	否	子账户状态，true：正常使用 false：冻结 <br>
     *                   subAcct -- String	否	子账户名称 <br>
     *                   after -- String	否	查询在此之前的内容，值为时间戳，Unix时间戳为毫秒数格式 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix时间戳为毫秒数格式 <br>
     *                   limit -- String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount-view-sub-account-list">
     * https://www.okx.com/docs-v5/zh/#rest-api-subaccount-view-sub-account-list</a>
     */
    public String list(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, LIST, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String MODIFY_APIKEY = "/api/v5/users/subaccount/modify-apikey";

    /**
     * 重置子账户的APIKey
     * <b>仅适用于母账户,且母账户APIKey必须绑定IP</b>
     * <br><br>
     * GET /api/v5/users/subaccount/modify-apikey
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   subAcct -- String	是	子账户名称 <br>
     *                   apiKey -- String	是	子账户API的公钥 <br>
     *                   label --	String	否	子账户APIKey的备注，如果填写该字段，则该字段会被重置 <br>
     *                   perm -- String	否	子账户APIKey权限 read_only：只读 ；trade ：交易
     *                   多个权限用半角逗号隔开。
     *                   如果填写该字段，则该字段会被重置 <br>
     *                   ip -- String	否	子账户APIKey绑定ip地址，多个ip用半角逗号隔开，最多支持20个ip。
     *                   如果填写该字段，那该字段会被重置
     *                   如果ip传""，则表示解除IP绑定 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount-reset-the-apikey-of-a-sub-account">
     * https://www.okx.com/docs-v5/zh/#rest-api-subaccount-reset-the-apikey-of-a-sub-account</a>
     */
    public String modifyApikey(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "subAcct", String.class);
        ParameterChecker.checkParameter(parameters, "apiKey", String.class);
        return requestHandler.sendSignedRequest(baseUrl, MODIFY_APIKEY, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String BALANCES = "/api/v5/account/subaccount/balances";

    /**
     * 获取子账户交易账户余额
     * 获取子账户交易账户余额（适用于母账户）
     * <br><br>
     * GET /api/v5/account/subaccount/balances
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   subAcct	String	是	子账户名称 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount-get-sub-account-trading-balance">
     * https://www.okx.com/docs-v5/zh/#rest-api-subaccount-get-sub-account-trading-balance</a>
     */
    public String balances(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "subAcct", String.class);
        return requestHandler.sendSignedRequest(baseUrl, BALANCES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ASSET_BALANCES = "/api/v5/asset/subaccount/balances";

    /**
     * 获取子账户资金账户余额
     * 获取子账户资金账户余额（适用于母账户）
     * <br><br>
     * GET /api/v5/asset/subaccount/balances
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   subAcct -- String	是	子账户名称 <br>
     *                   ccy -- String	否	币种，如 BTC
     *                   支持多币种查询（不超过20个），币种之间半角逗号分隔 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount-get-sub-account-funding-balance">
     * https://www.okx.com/docs-v5/zh/#rest-api-subaccount-get-sub-account-funding-balance</a>
     */
    public String assetBalances(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "subAcct", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ASSET_BALANCES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ASSET_BILLS = "/api/v5/asset/subaccount/bills";

    /**
     * 查询子账户转账记录
     * 仅适用于母账户，可以获取最近3个月的转账记录
     * <br><br>
     * GET /api/v5/asset/subaccount/bills
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	否	币种，如 BTC <br>
     *                   type -- String	否	0: 母账户转子账户 ；1 : 子账户转母账户  <br>
     *                   subAcct -- String	否	子账户名称  <br>
     *                   after --	String	否	查询在此之前的内容，值为时间戳，Unix时间戳为毫秒数格式 <br>
     *                   before -- String	否	查询在此之后的内容，值为时间戳，Unix时间戳为毫秒数格式 <br>
     *                   limit --	String	否	分页返回的结果集数量，最大为100，不填默认返回100条 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount-history-of-sub-account-transfer">
     * https://www.okx.com/docs-v5/zh/#rest-api-subaccount-history-of-sub-account-transfer</a>
     */
    public String assetBills(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, ASSET_BILLS, parameters, HttpMethod.GET, showLimitUsage);
    }

    private final String ASSET_TRANSFER = "/api/v5/asset/subaccount/transfer";
    /**
     * 子账户间资金划转
     * 母账户控制子账户与子账户之间划转（仅适用于母账户）
     * <br><br>
     * GET /api/v5/asset/subaccount/transfer
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     *                   ccy -- String	是	币种 <br>
     *                   amt -- String	是	划转数量 <br>
     *                   from -- String	是	6：资金账户 18：交易账户 <br>
     *                   to -- String	是	6：资金账户 18：交易账户 <br>
     *                   fromSubAccount -- String	是	转出子账户的子账户名称 <br>
     *                   toSubAccount -- String	是	转入子账户的子账户名称 <br>
     *                   loanTrans --	Boolean	否	是否支持跨币种保证金模式或组合保证金模式下的借币转入/转出
     *                   true 或 false，默认false <br>
     *                   omitPosRisk -- String	否	是否忽略仓位风险
     *                   默认为false
     *                   仅适用于组合保证金模式 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount-master-accounts-manage-the-transfers-between-sub-accounts">
     * https://www.okx.com/docs-v5/zh/#rest-api-subaccount-master-accounts-manage-the-transfers-between-sub-accounts</a>
     */
    public String assetTransfer(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "ccy", String.class);
        ParameterChecker.checkParameter(parameters, "amt", String.class);
        ParameterChecker.checkParameter(parameters, "from", String.class);
        ParameterChecker.checkParameter(parameters, "to", String.class);
        ParameterChecker.checkParameter(parameters, "fromSubAccount", String.class);
        ParameterChecker.checkParameter(parameters, "toSubAccount", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ASSET_TRANSFER, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String SET_TRANSFER_OUT = "/api/v5/users/subaccount/set-transfer-out";
    /**
     * 设置子账户主动转出权限
     * 设置子账户转出权限（仅适用于母账户），默认可转出至母账户。
     * <br><br>
     * GET /api/v5/users/subaccount/set-transfer-out
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     * subAcct -- String	是	子账户名称，支持设置多个（不超过20个），子账户名称之间半角逗号分隔 <br>
     * canTransOut -- Boolean	否	是否可以主动转出，默认为true
     * false：不可转出
     * true：可以转出 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount-set-permission-of-transfer-out">
     * https://www.okx.com/docs-v5/zh/#rest-api-subaccount-set-permission-of-transfer-out</a>
     */
    public String setTransferOut(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "subAcct", String.class);
        return requestHandler.sendSignedRequest(baseUrl, SET_TRANSFER_OUT, parameters, HttpMethod.POST, showLimitUsage);
    }

    private final String ENTRUST_SUBACCOUNT_LIST = "/api/v5/users/entrust-subaccount-list";
    /**
     * 查看被托管的子账户列表
     * 交易团队使用该接口查看当前托管中的子账户列表
     * <br><br>
     * GET /api/v5/users/entrust-subaccount-list
     * <br>
     *
     * @param parameters LinkedHashedMap of String,Object pair
     *                   where String is the name of the parameter and Object is the value of the parameter
     *                   <br><br>
     * subAcct	String	否	子账户名称 <br>
     * @return String
     * @see <a href="https://www.okx.com/docs-v5/zh/#rest-api-subaccount-get-custody-trading-sub-account-list">
     * https://www.okx.com/docs-v5/zh/#rest-api-subaccount-get-custody-trading-sub-account-list</a>
     */
    public String entrustSubaccountList(LinkedHashMap<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, ENTRUST_SUBACCOUNT_LIST, parameters, HttpMethod.GET, showLimitUsage);
    }
}
