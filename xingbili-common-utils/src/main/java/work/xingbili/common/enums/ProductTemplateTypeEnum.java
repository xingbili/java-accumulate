/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 产品类型下模板类型枚举
 *
 * @author liuming1
 * @date 2021/08/05 11:02
 **/
@Getter
@AllArgsConstructor
public enum ProductTemplateTypeEnum {

    /**
     * 1-橙信
     */
    VOUCHER("060", new work.xingbili.common.enums.TemplateTypeEnum[]{work.xingbili.common.enums.TemplateTypeEnum.COOP_AGREEMENT_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.PAYMENT_COMMIT_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.ISSUE_TEMPLATE,
            work.xingbili.common.enums.TemplateTypeEnum.PLATFORM_SERVICE_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.FINAL_PAYMENT_DETAILS, work.xingbili.common.enums.TemplateTypeEnum.TRANSFER_ORDER_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.ENTERPRISE_CREDIT_TEMPLATE,
            work.xingbili.common.enums.TemplateTypeEnum.FACTORING_BIZ_NO_RECOURSE, work.xingbili.common.enums.TemplateTypeEnum.RECEIPT_TRANSFER_NOTICE, work.xingbili.common.enums.TemplateTypeEnum.RECEIPT_OPEN_NOTICE, work.xingbili.common.enums.TemplateTypeEnum.PAYMENT_AGREEMENT_TEMPLATE},
            "橙信"),

    /**
     * 2-商链通
     */
    POS("061", new work.xingbili.common.enums.TemplateTypeEnum[]{work.xingbili.common.enums.TemplateTypeEnum.THREE_COOPERATION_AGREEMENT, work.xingbili.common.enums.TemplateTypeEnum.MAXIMUM_PRINCIPAL_CONTRACT, work.xingbili.common.enums.TemplateTypeEnum.REPURCHASE_NOTICE_TEMPLATE,
            work.xingbili.common.enums.TemplateTypeEnum.ENTERPRISE_CREDIT_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.LOAN_CONTRACT_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.ELECTRONIC_VOUCHER_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.ATTORNEY_PAYMENT_TEMPLATE,
            work.xingbili.common.enums.TemplateTypeEnum.CHATTEL_PLEDGE_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.PLEDGE_HANDOVER_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.CHATTEL_SUPERVISION_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.MINIMUM_PLEDGE_TEMPLATE,
            work.xingbili.common.enums.TemplateTypeEnum.MINIMUM_NOTICE_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.CONFIRMATION_REGULATED_ARTICLE, work.xingbili.common.enums.TemplateTypeEnum.WITHDRAWAL_APPLICATION_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.PROOF_CUSTODY_TEMPLATE,
            work.xingbili.common.enums.TemplateTypeEnum.DELIVERY_NOTICE_TEMPLATE, work.xingbili.common.enums.TemplateTypeEnum.DELIVERY_RECEIPT_TEMPLATE},
            "商链通"),
    ;



    private String code;

    private work.xingbili.common.enums.TemplateTypeEnum[] templateTypeList;

    private String info;


    /**
     *  通过编码获取描述
     *
     * @param code
     * @return
     */
    public static String getInfoByCode(String code) {
        for (ProductTemplateTypeEnum item : ProductTemplateTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item.getInfo();
            }
        }
        return null;
    }

    /**
     * 通过描述获取编码
     *
     * @author liuming1
     * @date 2021/07/26 16:57
     * @param info
     * @return String
     */
    public static String getCodeByName(String info) {
        for (ProductTemplateTypeEnum item : ProductTemplateTypeEnum.values()) {
            if (item.getInfo().equals(info)) {
                return item.getCode();
            }
        }
        return null;
    }

    /**
     *  通过编码获取描述
     *
     * @param code
     * @return
     */
    public static work.xingbili.common.enums.TemplateTypeEnum[] getTemplateTypesByCode(String code) {
        for (ProductTemplateTypeEnum item : ProductTemplateTypeEnum.values()) {
            if (item.getCode().equals(code)) {
                return item.getTemplateTypeList();
            }
        }
        return null;
    }


}
