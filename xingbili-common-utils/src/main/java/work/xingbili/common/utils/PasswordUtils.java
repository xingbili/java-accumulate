/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 密码加密和校验工具
 * 
 * @author yangfan
 * @date 2021/05/15
 */
public class PasswordUtils {

    /**
     * hash次数
     */
    public static final int HASH_INTERATIONS = 1024;
    
    /**
     * 验证密码是否匹配
     * 
     * @param pwd
     * @param salt
     * @param pwdFromdb
     * @return
     */
	public static boolean checkUserPwd(String pwd, String salt, String pwdFromdb) {

		return StringUtils.equals(entryptPassword(pwd, salt), pwdFromdb);
	}

	public static String entryptPassword(String password, String saltstr) {
		byte[] salt = Encodes.decodeHex(saltstr);
		byte[] hashPassword = Digests.sha512(password.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}
}
