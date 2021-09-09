/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * 用户状态
 * 
 * @author user
 * @date 2021/05/18
 */
public enum UserStatusEnum {

    /**
     * 新增用户
     */
    NEW("N", "新建"),

    /**
     * 用户激活
     */
    ACTIVE("A", "激活"),

    /**
     * 用户注册状态
     */
    REGISTER("R", "注册"),

    /**
     * 锁定
     */
    LOCKED("L", "锁定"),

    /**
     * 新用户锁定
     */
    NEW_LOCKED("M", "新用户锁定"),

    ;

    private String userStatusType;

    private String userStatusDesc;

    public String getUserStatusType() {
        return userStatusType;
    }

    public void setUserStatusType(String userStatusType) {
        this.userStatusType = userStatusType;
    }

    public String getUserStatusDesc() {
        return userStatusDesc;
    }

    public void setUserStatusDesc(String userStatusDesc) {
        this.userStatusDesc = userStatusDesc;
    }

    private UserStatusEnum(String userStatusType, String userStatusDesc) {
        this.userStatusType = userStatusType;
        this.userStatusDesc = userStatusDesc;
    }

}
