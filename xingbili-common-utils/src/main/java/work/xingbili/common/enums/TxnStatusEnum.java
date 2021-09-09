/*
 *
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * <p>
 * 交易状态
 * </p>
 *
 * @author yangfan
 * @date 2021/04/13
 */
public enum TxnStatusEnum {
    /**
     * 处理中
     */
    PROCESSING("P", "处理中"),

    /**
     * 成功
     */
    SUCCESS("S", "成功"),

    /**
     * 失败
     */
    FAIL("F", "失败");

    private String code;

    private String info;

    private TxnStatusEnum(String code, String info) {
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
