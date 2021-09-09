/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * 公共枚举
 * 
 * @author yangfan
 * @date 2021/04/13
 */
public enum YesOrNoEnum {

    /**
     * 否
     */
    NO("N", "否"),
    /**
     * 是
     */
    YES("Y", "是");

    private String code;
    private String info;

    private YesOrNoEnum(String code, String info) {
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
