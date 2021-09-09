/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

/**
 * 敏感信息处理
 * @author yanping.shi
 * @date 2021/04/13
 */
public class SensitiveInfoUtils {

    /**
     * 身份证号显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
     * @param idNo
     * @return
     */
    public static String maskIdNo(String idNo) {
    	if (StringUtils.isEmpty(idNo)) {
    		return "";
    	}
    	if(idNo.length() == 15) {
    		return StringUtils.overlay(idNo, "***********", 0, 11);
    	}
        return StringUtils.overlay(idNo, "***********", 0, 14);
    }

    /**
     * 手机号码前三位，后四位，其他隐藏<例子:138*****1234>
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return "";
        }
        return StringUtils.overlay(mobile, "****", 3, 7);
    }

    /**
     * 固定号码，后四位，其他隐藏<例子：****1234>
     * @param phoneNo
     * @return
     */
    public static String maskTelPhone(String phoneNo) {
        if (StringUtils.isEmpty(phoneNo)) {
            return phoneNo;
        }
        return StringUtils.overlay(phoneNo, "****", 0, phoneNo.length() - 4);
    }

    /**
     * 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if(index <= 1) {
        	return email;
        }
        return StringUtils.overlay(email, "**", 1, index);
    }
    
}
