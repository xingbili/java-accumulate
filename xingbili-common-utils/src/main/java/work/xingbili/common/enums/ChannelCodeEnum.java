/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * <p>
 * 渠道码常量
 * </p>
 *
 * @author yangfan
 * @date 2021/05/15
 */
public enum ChannelCodeEnum {

    /**
     * 银行统一管理系统
     */
    BANK_UMS("BANK_UMS", "银行统一管理系统"),

    /**
     * 门户运营系统
     */
    PROTAL_OPERATION("PROTAL_OPERATION", "门户运营系统"),

    /**
     * 电子债权业务系统
     */
    BANK_VOUCHER("BANK_VOUCHER", "电子债权业务系统"),

    /**
     * 工作流引擎系统
     */
    BANK_WORKFLOW("BANK_WORKFLOW", "工作流引擎系统"),

    /**
     * 门户企业系统
     */
    PROTAL_CORP("PROTAL_CORP", "门户企业系统"),
    
    /**
     * 外部服务渠道
     */
    FIN_EXTERNAL("FIN_EXTERNAL", "外部渠道服务"),
    
    /**
     * 内部渠道,用于内部调用自动生成内部token,方便内部鉴权,内部调用的的服务权限码均为default
     */
    FIN_INNER("FIN_INNER", "内部渠道"),
    ;

    private String code;
    private String info;

    private ChannelCodeEnum(String code, String info) {
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

    /**
     * 获取描述
     *
     * @param code
     * @return
     */
    public static String getInfo(String code) {
        for (ChannelCodeEnum channelCode : ChannelCodeEnum.values()) {
            if (channelCode.getCode().equals(code)) {
                return channelCode.getInfo();
            }
        }
        return null;
    }
}
