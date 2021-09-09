/**
*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;


import lombok.Getter;


/**
 * 编号类型
 *
 * @author gaopeng
 * @date 2021/05/24
 */
@Getter
public enum NumTypeEnum {

	PICKUP_APPLY_NO(1,"MHTH" ,"yyyyMMdd",5,"提货申请编号"),
	LOAN_APPLY_NO(2,"MHRZ" ,"yyyyMMdd",5,"融资申请编号"),
	CONTRACT_TEMPLATE_NO(3,"CONTRACT" ,"yyyyMMdd",6,"合同模板编号"),
	VOUCHER_NO(4,"PZ" ,"yyyyMMdd",3,"凭证编号"),
	CHAIN_WHITE_APPLY_NO(5,"MHBMD" ,"yyyyMMdd",5,"白名单申请编码"),
	CHAIN_CORP_APPLY_NO(6,"CCAN" ,"yyyyMMdd",5,"链属企业申请编号"),
	PARTNERSHIP_APPLY_NO(7,"MHHTBH" ,"yyyyMMdd",5,"合作申请编号"),
	VOUCHER_PAY_NO(8, "PZPAY", "yyyyMMdd", 5, "凭证兑付流水号"),
	BATCH_NO(9,"TX" ,"yyyyMMdd",6,"交易流水号"),
	TRANS_CORP_APPLY_NO(10,"TCAN" ,"yyyyMMdd",5,"转让白名单申请编号"),
	CREAT_ACCT_APPLY_NO(11,"MHJH" ,"yyyyMMdd",5,"建户申请编号"),
	COOP_TREATY_NO(12,"CX" ,"yyyyMMdd",3,"橙信协议编号编号"),
	SCS_PAY_NO(13, "MHDF", "yyyyMMdd", 5, "供应链兑付编号"),
	ARRIVAL_APPLY_NO(14,"MHYK" ,"yyyyMMdd",5,"到货申请编号"),
	PURCHASE_ORDER_NO(15,"ORDER" ,"yyyyMMdd",5,"采购订单编号编号"),
	LOAN_REPAY_APPLY_NO(16, "MHHK", "yyyyMMdd", 5, "还款申请编号"),
	TREATY_SIGN_NO(17, "MHJH", "yyyyMMdd", 5, "协议编号"),
	IDENTITY_AUTHENTICATION_NO(18, "SFRZ", "yyyyMMdd", 5, "身份认证编号"),
	PAYMENT_UNDERTAKING_NO(19, "FK", "yyyyMMdd", 3, "付款承诺函编号"),
	EXPENSE_SERIAL_NO(20, "MHSXF", "yyyyMMdd", 5, "费用流水编号"),
	ENTERPRISE_CREDIT_NO(21, "ENTE", "yyyyMMdd", 5, "企业征信查询授权书合同编号"),
	SPRING_BATCH_NO(21, "SBN", "yyyyMMdd", 8, "批量任务流水号"),
	CREDIT_AUTH_NO(22, "ZX", "yyyyMMdd", 3, "征信授权协议编号"),
	TRANSFER_NOTICE_NO(23, "TZ", "yyyyMMdd", 3, "应收账款转让通知书(开立方/支付方)协议编号"),
	ARRIVAL_LIST_NO(24, "SDWP", "yyyyMMdd", 5, "收到监管物品确认书协议编号"),
	WITH_LOAN_NO(25, "TK", "yyyyMMdd", 3, "提款申请书协议编号"),
	DETENTION_CREDENTIAL_NO(26, "SY", "yyyyMMdd", 3, "收押凭证协议编号"),
	ATTORNEY_PAYMENT_NO(27, "ZFWT", "yyyyMMdd", 3, "支付委托书"),
	TREATY_SIGN_APPLY_NO(28, "XYQY", "yyyyMMdd", 5, "协议签约申请编号"),
	;

	private Integer code;
	private String prefix ;
	private String  dateFormat;

	private Integer  seqnoLen;
	private String info;

	NumTypeEnum(Integer code, String prefix, String dateFormat, Integer seqnoLen, String info) {
		this.code = code;
		this.prefix = prefix;
		this.dateFormat = dateFormat;
		this.seqnoLen = seqnoLen;
		this.info = info;
	}

	public static NumTypeEnum getNumTypeEnumByCode(Integer code){
		for(NumTypeEnum item : values()){
			if(item.getCode().equals(code)){
				return item;
			}
		}
		return null;
	}

}
