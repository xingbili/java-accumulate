/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * 启用禁用标识
 * 
 * @author yangfan
 * @date 2021/04/13
 */
public enum UserFlagEnum {

    /**
     * 启用
     */
    ENABLE("Y", "启用"),

    /**
     * 机构平台角色
     */
    DISABLE("N", "禁用"),;

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private UserFlagEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
