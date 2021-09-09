/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * BigDecimal处理类
 * 
 * @author yanping.shi
 * @date 2021/04/13
 */
@Slf4j
public class BigDecimalUtil {

    public static final String DEFAULT_FORMAT_PATTERN = "##,##0.00";

    /**
     * 小数点精确度
     * 
     * @param bigDecimal
     * @param point
     * @param roundType
     * @return
     */
    public static BigDecimal round(BigDecimal bigDecimal, int point, int roundType) {
        return bigDecimal.setScale(point, roundType);
    }

    /**
     * 小数点精确，并四舍五入
     * 
     * @param bigDecimal
     * @param point
     * @return
     */
    public static BigDecimal roundHalfUp(BigDecimal bigDecimal, int point) {
        return round(bigDecimal, point, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 金额格式化
     * 
     * @param bigDecimal
     * @param currency
     * @return
     */
    public static String formatAmt(BigDecimal bigDecimal, String currency) {
        DecimalFormat format = new DecimalFormat();
        String format_pattern = null;
        if (StringUtils.isEmpty(currency)) {
            format_pattern = DEFAULT_FORMAT_PATTERN;
        } else {
            format_pattern = DEFAULT_FORMAT_PATTERN;
        }
        format.applyPattern(format_pattern);
        return format.format(bigDecimal);
    }

    /**
     * 整形格式化
     * 
     * @param value
     * @return
     */
    public static String formatInt(int value) {
        DecimalFormat format = new DecimalFormat();
        String format_pattern = "##,##0";
        format.applyPattern(format_pattern);
        return format.format(value);
    }

    /**
     * 按默认格式化金额
     * 
     * @param bigDecimal
     * @return
     */
    public static String formatAmt(BigDecimal bigDecimal) {
        return formatAmt(bigDecimal, null);
    }

    /**
     * 格式化为百分数
     * 
     * @param bigDecimal
     * @param point
     * @return
     */
    public static String formatPercent(BigDecimal bigDecimal, int point) {
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(point);
        return format.format(bigDecimal);
    }

    /**
     * 按默认格式百分数
     * 
     * @param bigDecimal
     * @return
     */
    public static String formatPercent(BigDecimal bigDecimal) {
        return formatPercent(bigDecimal, 2);
    }

    /**
     * 传入数据转换为BigDecimal对象
     * 
     * @param value
     * @return
     */
    public static BigDecimal getBigDecimal(Object value) {
        log.info("准备转换的值为:{}", value);
        BigDecimal val = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                val = (BigDecimal)value;
            } else if (value instanceof String) {
                if (StringUtils.equals(value.toString(), "null")) {
                    val = BigDecimal.ZERO;
                } else {
                    val = new BigDecimal(StringUtils.trim((String)value));
                }
            } else if (value instanceof BigInteger) {
                val = new BigDecimal((BigInteger)value);
            } else if (value instanceof Number) {
                val = new BigDecimal(((Number)value).doubleValue());
            } else {
                throw new ClassCastException(
                    "Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return val;
    }

    /**
     *
     * 字符串转BigDecimal
     * 
     * @author Aning
     * @param amtStr:
     *            字符串
     * @return null
     * @date 2021/5/18 15:26
     * 
     **/
    public static BigDecimal formatAmtToBigDecimal(String amtStr) {
        return convertAmtStringtoDecimal(amtStr, DEFAULT_FORMAT_PATTERN);
    }

    /**
     *
     * 字符串转BigDecimal author Aning
     * 
     * @param amtString:
     *            字符串
     * @param pattern:
     *            样式
     * @return null
     * @date 2021/5/18 15:26
     *
     **/
    public static BigDecimal convertAmtStringtoDecimal(String amtString, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setParseBigDecimal(true);
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = (BigDecimal)decimalFormat.parse(amtString);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return bigDecimal;
    }

    /**
     * 小数点精确，并四舍五入
     * 
     * @param bigDecimal
     * @return
     */
    public static String formatScsDecimal(BigDecimal bigDecimal) {
        int point = 4;
        if (bigDecimal == null) {
            return null;
        }
        BigDecimal val = bigDecimal.setScale(point, BigDecimal.ROUND_HALF_UP);
        return val.toPlainString();
    }

    /**
     * 小数点精确，并四舍五入
     *
     * @param bigDecimal
     * @return
     */
    public static String formatScsPrice(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        BigDecimal val = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return val.toPlainString();
    }

}
