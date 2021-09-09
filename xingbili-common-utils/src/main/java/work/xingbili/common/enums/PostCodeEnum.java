/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * 岗位编码
 * 
 * @author gaopeng
 * @date 2021/08/05
 */
public enum PostCodeEnum {

    /**
     * 门户企业经办岗
     */
    PROTAL_CORP_HANDLE("QYJBG", "门户企业经办岗"),

    ;

    private String code;
    private String info;

    private PostCodeEnum(String code, String info) {
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
        for (PostCodeEnum channelCode : PostCodeEnum.values()) {
            if (channelCode.getCode().equals(code)) {
                return channelCode.getInfo();
            }
        }
        return null;
    }
}
