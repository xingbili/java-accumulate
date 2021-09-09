/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * <p>
 * 错误码定义
 * </p>
 *
 * @author yangfan
 * @date 2021/04/13
 */
public enum ErrorCodeEnum {

    /**
     * 交易成功
     */
    E00000000("00000000", "交易成功"),

    /**
     * 交易失败
     */
    E00000001("00000001", "交易失败"),

    /**
     * 请求参数格式不正确
     */
    E00000002("00000002", "请求参数格式不正确"),

    /**
     * 非法访问
     */
    E00000003("00000003", "非法访问"),

    /**
     * 验签失败
     */
    E00000004("00000004", "验签失败"),

    /**
     * 渠道不存在
     */
    E00000005("00000005", "渠道不存在"),

    /**
     * 渠道公钥不存在
     */
    E00000006("00000006", "渠道公钥不存在"),

    /**
     * 服务调用超时
     */
    E00000007("00000007", "服务调用超时"),

    /**
     * 记录重复
     */
    E00000008("00000008", "记录重复"),

    /**
     * 乐观锁异常
     */
    E00000009("00000009", "乐观锁异常"),


    /**
     * 供应链合同已存在
     */
    E00000010("00000010", "供应链合同已存在，不可重复创建！"),
    /**
     * 服务处理异常
     */
    E00999999("00999999", "服务处理异常");

    private String errorCode;

    private String errorMessage;

    private ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
