/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.enums;

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
     * 返回结果不时JSON格式
     */
    E001("001", "表：tb_api_response 字段：api_respons_body 配置的接口返回信息不是JSON格式"),

    /**
     *
     */
    E002("002", "表：tb_api_info 没有对应的接口信息，请检查接口地址是否正确"),

    /**
     *
     */
    E003("003", "表：tb_api_response 没有对应的返回信息"),


    /**
     * 服务异常
     */
    E009("009", "服务异常");





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
