/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * 发布状态
 * 
 * @author TGB
 * @date 2021/05/21
 */
public enum PublishStatusEnum {

    /**
     * 未发布
     */
    UNPUBLISH("N", "未发布"),

    /**
     * 已发布
     */
    PUBLISHED("Y", "已发布");

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

    private PublishStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
