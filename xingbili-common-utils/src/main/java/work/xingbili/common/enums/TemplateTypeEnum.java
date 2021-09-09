/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模板类型枚举
 *
 * @author liuming1
 * @date 2021/07/16 11:21
 **/
@Getter
@AllArgsConstructor
public enum TemplateTypeEnum {

    /**
     * 日照银行橙信业务合作协议
     */
    COOP_AGREEMENT_TEMPLATE(1, "日照银行橙信业务合作协议", "COOP_AGREEMENT"),

    /**
     * 付款承诺函
     */
    PAYMENT_COMMIT_TEMPLATE(2, "付款承诺函", "PAYMENT_COMMIT"),

    /**
     * 橙信开立单
     */
    ISSUE_TEMPLATE(3, "橙信开立单", "ISSUE"),

    /**
     * 转让协议
     */
    TRANS_AGREEMENT_TEMPLATE(4, "转让协议", "TRANS_AGREEMENT"),

    /**
     * 橙信流转单
     */
    TRANSFER_ORDER_TEMPLATE(5, "橙信流转单", "TRANSFER_ORDER"),

    /**
     * 业务申请书
     */
    BUSINESS_APPLICATION_TEMPLATE(6, "业务申请书", "BUSINESS_APPLICATION"),

    /**
     * 保理合同
     */
    FACTOR_CONTRACT_TEMPLATE(7, "保理合同", "FACTOR_CONTRACT"),
    /**
     * 日照银行橙信业务服务协议
     */
    PLATFORM_SERVICE_TEMPLATE(8, "日照银行橙信业务服务协议", "PLATFORM_SERVICE"),

    /**
     * 平台授权委托书
     */
    PLATFORM_POWER_TEMPLATE(9, "平台授权委托书", "PLATFORM_POWER"),
    /**
     * 担保协议
     */
    SECURITY_AGREEMENT_TEMPLATE(10, "担保协议", "SECURITY_AGREEMENT"),
    /**
     * 企业信用报告查询授权书
     */
    ENTERPRISE_CREDIT_TEMPLATE(11, "企业信用报告查询授权书", "ENTERPRISE_CREDIT"),
    /**
     * 法人征信查询授权书
     */
    CORPORATE_CREDIT_TEMPLATE(12, "法人征信查询授权书", "CORPORATE_CREDIT"),

    /**
     * 实际控制人征信查询授权书
     */
    ACTUAL_CREDIT_TEMPLATE(13, "实际控制人征信查询授权书", "ACTUAL_CREDIT"),
    /**
     * 三方数据查询授权书
     */
    THREE_DATA_TEMPLATE(14, "三方数据查询授权书", "THREE_DATA"),
    /**
     * 流动资金借款合同
     */
    LOAN_CONTRACT_TEMPLATE(15, "流动资金借款合同", "LOAN_CONTRACT"),
    /**
     * 动产质押合同
     */
    CHATTEL_PLEDGE_TEMPLATE(16, "动产质押合同", "CHATTEL_PLEDGE"),
    /**
     * 质物移交清单
     */
    PLEDGE_HANDOVER_TEMPLATE(17, "质物移交清单", "PLEDGE_HANDOVER"),
    /**
     * 动产监管协议
     */
    CHATTEL_SUPERVISION_TEMPLATE(18, "动产监管协议", "CHATTEL_SUPERVISION"),
    /**
     * 质物最低价值/最低数量通知书
     */
    MINIMUM_PLEDGE_TEMPLATE(19, "质物最低价值/最低数量通知书", "MINIMUM_PLEDGE"),
    /**
     * 质物最低价值/最低数量通知书回执
     */
    MINIMUM_NOTICE_TEMPLATE(20, "质物最低价值/最低数量通知书回执", "MINIMUM_NOTICE"),

    /**
     * 受托支付委托书
     */
    ATTORNEY_PAYMENT_TEMPLATE(21, "支付委托书", "ATTORNEY_PAYMENT"),

    /**
     * 电子借据凭证
     */
    ELECTRONIC_VOUCHER_TEMPLATE(22, "电子借据凭证", "ELECTRONIC_VOUCHER"),

    /**
     * 提款申请书
     */
    WITHDRAWAL_APPLICATION_TEMPLATE(23, "提款申请书", "WITHDRAWAL_APPLICATION"),
    /**
     * 货物运输明细单
     */
    CARGO_TRANSPORTATION_TEMPLATE(24, "货物运输明细单", "ARGO_TRANSPORTATION"),

    /**
     * 提货通知书
     */
    DELIVERY_NOTICE_TEMPLATE(25, "提货通知书", "DELIVERY_NOTICE"),

    /**
     * 提货通知书回执
     */
    DELIVERY_RECEIPT_TEMPLATE(26, "提货通知书回执", "DELIVERY_RECEIPT"),

    /**
     * 最终付款明细表
     */
    FINAL_PAYMENT_DETAILS(27, "最终付款明细单表", "FINAL_PAYMENT"),

    /**
     * 价格协议
     */
    PAYMENT_AGREEMENT_TEMPLATE(28, "日照银行“橙信贷”价格协议", "PAYMENT_AGREEMENT"),

    /**
     * 企业受益人凭证
     */
    BENEFICIARY_CERTIFICATE(29, "企业受益人凭证", "BENEFICIARY"),

    /**
     * 签收回执
     */
    SIGN_RECEIPT_TEMPLATE(30, "签收回执", "SIGN_RECEIPT"),

    /**
     * 应收账款转让通知书-支付方
     */
    RECEIPT_TRANSFER_NOTICE(31, "应收账款转让通知书-支付方", "RECEIPT_TRANSFER"),

    /**
     * 应收账款转让通知书-开立方
     */
    RECEIPT_OPEN_NOTICE(32, "应收账款转让通知书-开立方", "RECEIPT_OPEN"),

    /**
     * 日照银行“橙信贷”保理业务合同（无追索权）
     */
    FACTORING_BIZ_NO_RECOURSE(33, "日照银行“橙信贷”保理业务合同（无追索权）", "FACTORING_BIZ_NO_RECOURSE"),

    /**
     * 商链通三方协议动产质押
     */
    THREE_COOPERATION_AGREEMENT(34, "商链通三方协议动产质押", "THREE_COOPERATION_AGREEMENT"),

    /**
     * 本金最高额保证合同
     */
    MAXIMUM_PRINCIPAL_CONTRACT(35, "本金最高额保证合同", "MAXIMUM_PRINCIPAL_CONTRACT"),

    /**
     * 购买通知书
     */
    REPURCHASE_NOTICE_TEMPLATE(36, "购买通知书", "REPURCHASE_NOTICE"),

    /**
     * 收到监管物品确认书
     */
    CONFIRMATION_REGULATED_ARTICLE(37, "收到监管物品确认书", "CONFIRMATION_REGULATED_ARTICLE"),

    /**
     * 收押凭据
     */
    PROOF_CUSTODY_TEMPLATE(38, "收押凭据", "PROOF_CUSTODY"),;

    /**
     * 编码
     */
    private Integer type;

    /**
     * 描述
     */
    private String info;

    /**
     * 描述
     */
    private String esName;

    /**
     * 通过类型获取描述
     *
     * @param type
     * @return
     */
    public static String getInfoByType(Integer type) {
        for (TemplateTypeEnum item : TemplateTypeEnum.values()) {
            if (item.getType().equals(type)) {
                return item.getInfo();
            }
        }
        return null;
    }

}
