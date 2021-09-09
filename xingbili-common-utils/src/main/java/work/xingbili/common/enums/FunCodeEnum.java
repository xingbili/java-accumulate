/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * funcode枚举
 * 
 * @author gaopeng
 * @date 2021/06/29
 */
public enum FunCodeEnum {

    /**
     * 债权凭证合作协议申请
     */
    COOP_TREATY_APPLY("T001", "债权凭证合作协议申请"),
    /**
     * 债权凭证合作协议变更
     */
    COOP_TREATY_CHANGE("T002", "债权凭证合作协议变更"),
    /**
     * 债权凭证合作协议冻结
     */
    COOP_TREATY_FROZEN("T003", "债权凭证合作协议冻结"),
    /**
     * 债权凭证合作协议解冻
     */
    COOP_TREATY_THAW("T004", "债权凭证合作协议解冻"),
    /**
     * 凭证开立
     */
    VOUCHER_OPEN("T005", "凭证开立"),
    /**
     * 凭证申请开立
     */
    VOUCHER_APPLY_OPEN("T006", "凭证申请开立"),
    /**
     * 凭证拆分
     */
    VOUCHER_SPLIT("T007", "凭证拆分"),
    /**
     * 凭证转让
     */
    VOUCHER_TRANSFER("T008", "凭证转让"),
    /**
     * 凭证兑付
     */
    VOUCHER_PAY("T009", "凭证兑付"),
    /**
     * 账户提现
     */
    CASH_OUT("T010", "账户提现"),
    /**
     * 手续费充值
     */
    RECHARGE_FEE("T011", "手续费充值"),
    /**
     * 链属白名单维护
     */
    CHAIN_WHITE_MAINTAIN("T012", "链属白名单维护"),
    /**
     * 企业账户新增
     */
    CORP_ACCOUNT_ADD("T013", "企业账户新增"),
    /**
     * 企业账户修改
     */
    CORP_ACCOUNT_UPDATE("T014", "企业账户修改"),
    /**
     * 虚户绑定
     */
    VIRTUAL_ACCOUNT_BIND("T015", "虚户绑定"),
    /**
     * 转让白名单维护
     */
    TRANS_WHITE_MAINTAIN("T016", "转让白名单维护"),
    /**
     * 合作关系申请
     */
    PARTNERSHIP_APPLY("T017", "金融产品申请"),
    /**
     * 采购订单登记
     */
    PURCHASE_ORDER_APPLY("T018", "采购订单登记"),
    /**
     * 融资申请
     */
    FINANCING_APPLY("T019", "融资申请"),
    /**
     * 放款申请
     */
    LOAN_APPLY("T020", "放款申请"),
    /**
     * 到货申请
     */
    ARRIVAL_APPLY("T021", "到货申请"),
    /**
     * 还款申请
     */
    REPAYMENT_APPLY("T022", "还款申请"),
    /**
     * 提货申请
     */
    PICKUP_APPLY("T023", "提货申请"),
    /**
     * 提款申请
     */
    DRAWING_APPLY("T024", "提款申请"),
    /**
     * 资产提供方配置申请
     */
    ASSETPROVIDER_APPLY("T025", "资产提供方配置申请"),
    /**
     * 资产提供方配置变更
     */
    ASSETPROVIDER_CHANGE("T026", "资产提供方配置变更"),

    /**
     * 受益人签约
     */
    BENEFICIARY_SIGN("T027", "受益人签约"),
    ;
    
    private String code;
    private String info;
    
    private FunCodeEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
}
