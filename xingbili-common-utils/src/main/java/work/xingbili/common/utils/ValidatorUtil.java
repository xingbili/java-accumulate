/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段验证工具
 *
 * @author yanping.shi
 * @date 2021/04/13
 */
@Slf4j
public class ValidatorUtil {


    /**
     * 判断字符串是否符合正则表达式
     *
     * @param str
     * @param regex
     * @return
     * @date : 2016年6月1日 下午12:43:05
     */
    public static boolean find(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean b = m.find();
        return b;
    }

    /**
     * 判断字符串是否为空(包含null与"")
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 验证空白字符
     *
     * @param str 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isBlankSpace(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("\\s+");
    }

    /**
     * @param object
     * @return
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判断集合类型是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {

        return collection == null ? true : collection.isEmpty();
    }

    /**
     * 判断字符串是否为数字
     *
     * @param inputStr
     * @return
     */
    public static boolean isNumber(String inputStr) {
        return StringUtils.isNumeric(inputStr);
    }

    /**
     * 判断字符串是否为Long类型
     *
     * @param inputStr
     * @return
     */
    public static boolean isLong(String inputStr) {
        boolean isLong = false;
        try {
            if (StringUtils.isEmpty(inputStr)) {
                return false;
            }
            Long.parseLong(inputStr);
            isLong = true;
        } catch (Exception e) {
            log.error("判断是否未Long类型失败：{}", e);
            Exceptions.throwBusinessException("不是Long类型！");
        }
        return isLong;
    }

    /**
     * 判断字符串是否为BigDecimal类型
     *
     * @param inputStr
     * @return
     */
    public static boolean isBigDecimal(String inputStr) {
        boolean isDecimal = false;
        try {
            if (StringUtils.isEmpty(inputStr)) {
                return false;
            }
            new BigDecimal(inputStr);
            isDecimal = true;
        } catch (Exception e) {
            log.error("判断字符串是否为BigDecimal类型失败：{}", e);
            Exceptions.throwBusinessException("不是BigDecimal类型！");
        }
        return isDecimal;
    }

    /**
     * 判别字符串是否为正BigDecimal类型
     *
     * @param inputStr
     * @return
     */
    public static boolean isPositiveBigDecimal(String inputStr) {
        boolean isPositive = false;
        if (isBigDecimal(inputStr)) {
            if (new BigDecimal(inputStr).compareTo(BigDecimal.ZERO) > 0) {
                isPositive = true;
            }
        }
        return isPositive;
    }

    /**
     * 判断字符串是否为Integer类型
     *
     * @param inputStr
     * @return
     */
    public static boolean isInt(String inputStr) {
        boolean isInt = false;
        try {
            if (StringUtils.isEmpty(inputStr)) {
                return false;
            }
            Integer.parseInt(inputStr);
            isInt = true;
        } catch (Exception e) {
            log.error("判断字符串是否为Integer类型失败：{}", e);
            Exceptions.throwBusinessException("不是Integer类型！");
        }
        return isInt;
    }

    /**
     * 判断字符串是否为短整型
     *
     * @param inputStr
     * @return
     */
    public static boolean isShort(String inputStr) {
        boolean bShort = false;
        if (StringUtils.isEmpty(inputStr)) {
            return false;
        }
        try {
            Short.parseShort(inputStr);
            bShort = true;
        } catch (Exception e) {
            log.error("判断字符串是否为短整型失败：{}", e);
            Exceptions.throwBusinessException("不是短整型！");
        }
        return bShort;
    }

    /**
     * 判断字符串是否为字节
     *
     * @param inputStr
     * @return
     */
    public static boolean isByte(String inputStr) {
        boolean bByte = false;
        if (StringUtils.isEmpty(inputStr)) {
            return false;
        }
        try {
            Byte.parseByte(inputStr);
            bByte = true;
        } catch (Exception e) {
            log.error("判断字符串是否为字节失败：{}", e);
            Exceptions.throwBusinessException("不是字节类型！");
        }
        return bByte;
    }

    /**
     * 验证Email
     *
     * @param str email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isEmail(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("^([a-zA-Z0-9_\\.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
    }

    /**
     * 验证身份证号码
     *
     * @param str 居民身份证号码18位，第一位不能为0，最后一位可能是数字或字母，中间16位为数字 \d同[0-9]
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isIdCard(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[1-9]\\d{16}[a-zA-Z0-9]{1}");
    }

    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @param str 移动、联通、电信运营商的号码段
     *            <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
     *            、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
     *            <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
     *            <p>电信的号段：133、153、180（未启用）、189</p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isMobile(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches(
                "^(\\+\\d+)?(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}|(17[0-9]{1})))+\\d{8})$");
    }

    /**
     * 验证固定电话号码
     *
     * @param str 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
     *            <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
     *            数字之后是空格分隔的国家（地区）代码。</p>
     *            <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
     *            对不使用地区或城市代码的国家（地区），则省略该组件。</p>
     *            <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isPhone(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$");
    }

    /**
     * 验证中文
     *
     * @param str 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isChinese(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("^[\u4E00-\u9FA5]+$");
    }

    /**
     * 验证日期（年月日）
     *
     * @param str 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isBirthday(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}");
    }

    /**
     * 验证URL地址
     *
     * @param str 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isURL(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches(
                "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?");
    }

    /**
     * 匹配中国邮政编码
     *
     * @param str 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isPostcode(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[1-9]\\d{5}");
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param str IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean isIpAddress(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches(
                "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))");
    }

    /**
     * 判断是否是金额
     *
     * @param amt
     * @return
     */
    public static boolean isAmount(String amt) {
        if (StringUtils.isBlank(amt)) {
            return false;
        }
        return amt.matches("([1-9][0-9]*(\\.\\d{1,2})?)|(0\\.\\d{1,2})");
    }
}
