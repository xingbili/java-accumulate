/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author yanping.shi
 * @date 2021/04/13
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    private static final Pattern P_HTML = Pattern.compile("<[a-zA-z]{1,9}((?!>).)*>", Pattern.CASE_INSENSITIVE);
    private static final Pattern T_HTML = Pattern.compile("</[a-zA-z]{1,9}>", Pattern.CASE_INSENSITIVE);
    /**
     * 左书名号
     */
    public static final String OPENING_CHEVRON = "《";

    /**
     * 右书名号
     */
    public static final String CLOSING_CHEVRON = "》";

    /**
     * 左中文括号
     */
    public static final String OPENING_CHINESE_BRACKETS = "（";

    /**
     * 右中文括号
     */
    public static final String CLOSING_CHINESE_BRACKETS = "）";

    /**
     * 左英文括号
     */
    public static final String OPENING_ENGLISH_BRACKETS = "\\(";

    /**
     * 右英文括号
     */
    public static final String CLOSING_ENGLISH_BRACKETS = "\\)";

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为字节数组
     *
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 如果不为空，则设置值
     *
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
        if (isNotBlank(source)) {
            target = source;
        }
    }

    /**
     * 按规定长度，拼装字符串，不足位补0。
     *
     * @param s
     * @param len
     * @return
     */
    public static String dealZeroStr(String s, int len) {
        int bt_len = s.length();
        if (bt_len >= len) {
            s = s.substring(0, len); // 应该不会发生的情况
        } else {
            String blank = "";
            for (int j = 0; j < len - bt_len; j++) {
                blank = blank + "0";
            }
            s = blank + s;
        }
        return s;
    }

    /**
     * 处理属性名,将属性名的首字母小写
     *
     * @param str
     * @return
     */
    public static String fstCharLowerCase(String str) {
        if (str == null || "".equals(str)) {
            return "";
        } else {
            String firstChar = String.valueOf(Character.toLowerCase(str.charAt(0)));
            StringBuilder sBuilder = new StringBuilder(firstChar).append(str.substring(1, str.length()));
            return sBuilder.toString();
        }
    }

    /**
     * 处理属性名,将属性名的首字母大写
     *
     * @param str
     * @return
     */
    public static String fstCharUpperCase(String str) {
        if (str == null || "".equals(str)) {
            return "";
        } else {
            String firstChar = String.valueOf(Character.toUpperCase(str.charAt(0)));
            StringBuilder sBuilder = new StringBuilder(firstChar).append(str.substring(1, str.length()));
            return sBuilder.toString();
        }
    }

    /**
     * 转换html标签
     *
     * @param html
     * @return
     */
    public static String getTextByHtml(String html) {

        Matcher mScript = P_HTML.matcher(html);

        html = mScript.replaceAll("");

        Matcher lScript = T_HTML.matcher(html);

        return lScript.replaceAll("").trim();

    }

    /**
     * 返回带书名号的字符串
     *
     * @author TGB
     * @date 2021/08/14
     * @return
     */
    public static String getChevronName(String name) {
        return OPENING_CHEVRON + name + CLOSING_CHEVRON;
    }

    /**
     * 英文括号转中文括号
     * @param name
     * @return
     */
    public static String convertBracketsEnglishToChinese(String name){
        if(isBlank(name)){
            return name;
        }
        name = name.replaceAll(OPENING_ENGLISH_BRACKETS, OPENING_CHINESE_BRACKETS);
        name = name.replaceAll(CLOSING_ENGLISH_BRACKETS, CLOSING_CHINESE_BRACKETS);
        return name;
    }

}
