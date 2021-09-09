/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES加解密公函
 *
 * @author yangfan
 * @date 2021/04/13
 */
@Slf4j
public class AesUtils {

    private final static String ENCODING = "UTF-8";

    /**
     * AES加密
     *
     * @param content
     * @param aesKey
     * @return String
     */
    public static String aesEncode(String content, String aesKey) throws Exception {

        byte[] encryptResult = encrypt(content, aesKey);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        // BASE64位加密
        encryptResultStr = ebotongEncrypto(encryptResultStr);
        log.info("加密前数据{}, key{}, 加密后数据:{}", content, aesKey, encryptResultStr);
        return encryptResultStr;
    }

    /**
     * AES解密
     *
     * @param encryptResultStr
     * @param aesKey
     * @return String
     */
    public static String aesDecode(String encryptResultStr, String aesKey) throws Exception {
        // BASE64位解密
        String decrpt = ebotongDecrypto(encryptResultStr);
        byte[] decryptFrom = parseHexStr2Byte(decrpt);
        byte[] decryptResult = decrypt(decryptFrom, aesKey);
        String res = new String(decryptResult);
        log.info("解密前数据{}, key{}, 解密后数据:{}", encryptResultStr, aesKey, res);
        return res;
    }

    /**
     * 加密字符串
     *
     * @param str
     * @return String
     */
    private static String ebotongEncrypto(String str) throws Exception {
        String result = str;
        if (str != null && str.length() > 0) {
            byte[] encodeByte = str.getBytes(ENCODING);
            byte[] debytes = Base64.encodeBase64Chunked(encodeByte);
            return new String(debytes);
        }
        // base64加密超过一定长度会自动换行 需要去除换行符
        return result.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 解密字符串
     *
     * @param str
     * @return String
     */
    private static String ebotongDecrypto(String str) throws Exception {
        byte[] encodeByte = Base64.decodeBase64(str);
        return new String(encodeByte);
    }

    /**
     * 加密
     *
     * @param content
     * @param aesKey
     * @return byte[]
     */
    private static byte[] encrypt(String content, String aesKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(aesKey.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        byte[] byteContent = content.getBytes("utf-8");
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(byteContent);
        return result;
    }

    /**
     * 解密
     *
     * @param content
     * @param aesKey
     * @return byte[]
     */
    private static byte[] decrypt(byte[] content, String aesKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        // 防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(aesKey.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES");
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(content);
        // 加密
        return result;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return String
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return byte[]
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        // 除数
        int val = 2;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / val; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    // public static void main(String[] args) throws Exception {
    // //加密内容
    // String content = "abc测试";
    // //加密key
    // String aesKey = "111222333";
    // //AES密文
    // String enCodeStr = aesEncode(content, aesKey);
    // System.out.println(content + " 加密后 " + enCodeStr);
    // System.out.println(enCodeStr + " 解密后 " + aesDecode(enCodeStr, aesKey));
    // }
}
