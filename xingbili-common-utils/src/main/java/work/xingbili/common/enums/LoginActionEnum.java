package work.xingbili.common.enums;

/**
 * 登录动作
 * 
 * @author TGB
 * @date 2021/05/21
 */
public enum LoginActionEnum {

    /**
     * 登录
     */
	LOGIN("login", "登录"),
	
	/**
	 * 登出
	 */
	LOGOUT("logout", "登出");
	
	private String code;
    private String info;

    private LoginActionEnum(String code, String info) {
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
