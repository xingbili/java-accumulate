///*
// *  版权信息: © 聚均科技
// */
//
//package com.fusionfintrade.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @des: 支票金额数字转汉字金额
// * @date: 2021/8/12 15:51
// * @author: zxx
// */
//public class ChequeCNYUtil {
//
//    private ChequeCNYUtil() {
//    }
//
//    /**
//     * 数字汉字映射
//     */
//    private static final Map<Character, Character> CONVERSION_MAP = new HashMap<>(10);
//
//    /**
//     * 单位
//     */
//    private static final char[] MONEY_UNIT = new char[]{'拾', '佰', '仟'};
//
//    static {
//        CONVERSION_MAP.put('1', '壹');
//        CONVERSION_MAP.put('2', '贰');
//        CONVERSION_MAP.put('3', '叁');
//        CONVERSION_MAP.put('4', '肆');
//        CONVERSION_MAP.put('5', '伍');
//        CONVERSION_MAP.put('6', '陆');
//        CONVERSION_MAP.put('7', '柒');
//        CONVERSION_MAP.put('8', '捌');
//        CONVERSION_MAP.put('9', '玖');
//        CONVERSION_MAP.put('0', '零');
//    }
//
//    /**
//     * 最大仅支持：999999999999.99 ~ 999999999999.99
//     *
//     * @param oriMoneyStr 金额字符串
//     * @return String
//     */
//    public static String money2ChineseStr(String oriMoneyStr) {
//        if (Objects.isNull(oriMoneyStr)) {
//            return "转换金额不能为空!";
//        }
//        boolean isnNegative = false;
//        if (oriMoneyStr.startsWith("-")) {
//            isnNegative = true;
//            oriMoneyStr = oriMoneyStr.substring(1);
//        }
//        String[] sp = oriMoneyStr.split("\\.");
//        if (sp[0].length() > 12) {
//            return "转换金额数值位数过大!";
//        }
//        StringBuffer chineseBuffer = new StringBuffer();
//        if (sp.length == 2) {
//            integerMoneyStr(sp[0], 0, chineseBuffer);
//            StringBuffer decimalMoneySb = decimalMoneyStr(sp[1]);
//            //仅有小数位，小数位前面去零
//            if (chineseBuffer.length() < 1 && decimalMoneySb.charAt(0) == '零') {
//                decimalMoneySb.deleteCharAt(0);
//            } else if (chineseBuffer.length() >= 1 && decimalMoneySb.length() < 1) {
//                //金额为整数，末尾补整
//                chineseBuffer.append('整');
//            } else if (chineseBuffer.length() >= 1 && decimalMoneySb.length() >= 1 && decimalMoneySb.charAt(0) != '零') {
//                //有整数，有小数，小数位不以零开头中间需补充零
//                decimalMoneySb.insert(0, '零');
//            }
//            chineseBuffer.append(decimalMoneySb);
//        } else {
//            integerMoneyStr(oriMoneyStr, 0, chineseBuffer);
//            //金额为整数，末尾补整
//            chineseBuffer.append('整');
//        }
//        if (isnNegative) {
//            chineseBuffer.insert(0, '负');
//        }
//        chineseBuffer.insert(0, "人民币");
//        return chineseBuffer.toString();
//    }
//
//    /**
//     * 最大仅支持：-999999999999.99 ~ 999999999999.99
//     *
//     * @param oriMoney 金额
//     * @return String
//     */
//    public static String money2ChineseStr(double oriMoney) {
//        return money2ChineseStr(String.valueOf(oriMoney));
//    }
//
//    /***
//     * 切割字符串(每四位单独处理)
//     *
//     * @param chineseBuffer
//     */
//    private static void integerMoneyStr(String integerMoney, int type, StringBuffer chineseBuffer) {
//        if ("0".equals(integerMoney)) {
//            return;
//        }
//        int len = integerMoney.length();
//        int b;
//        type++;
//        if (len >= 4) {
//            b = len - 4;
//            chineseBuffer.insert(0, moneyUnit(integerMoney.substring(b), type));
//            integerMoneyStr(integerMoney.substring(0, b), type, chineseBuffer);
//        } else if (len > 0) {
//            chineseBuffer.insert(0, moneyUnit(integerMoney, type));
//        }
//        if ('零' == chineseBuffer.charAt(0)) {
//            chineseBuffer.deleteCharAt(0);
//        }
//    }
//
//    /***
//     * 单位转换及重复零处理
//     *
//     * @param chineseBuffer
//     */
//    private static StringBuffer moneyUnit(String chineseBuffer, int type) {
//        StringBuffer newChineseBuffer = new StringBuffer(chineseBuffer);
//        switch (type) {
//            case 1:
//                newChineseBuffer.append("元");
//                break;
//            case 2:
//                newChineseBuffer.append("万");
//                break;
//            case 3:
//                newChineseBuffer.append("亿");
//                break;
//            default:
//                break;
//        }
//        // 开始赋值
//        int b = 0;
//        char t;
//        for (int i = newChineseBuffer.length() - 2; i >= 0; i--) {
//            t = newChineseBuffer.charAt(i);
//            newChineseBuffer.setCharAt(i, CONVERSION_MAP.get(t));
//            if (i != 0) {
//                newChineseBuffer.insert(i, MONEY_UNIT[b]);
//            }
//            b++;
//        }
//        for (int i = 0; i < newChineseBuffer.length(); i++) {
//            t = newChineseBuffer.charAt(i);
//            if (t == '零') {
//                t = newChineseBuffer.charAt(i + 1);
//                if ('元' != t && '万' != t && '亿' != t) {
//                    newChineseBuffer.deleteCharAt(i + 1);
//                } else {
//                    newChineseBuffer.deleteCharAt(i);
//                }
//                if (i != 0) {
//                    if (newChineseBuffer.charAt(i - 1) == '零') {
//                        newChineseBuffer.deleteCharAt(i - 1);
//                        i--;
//                    }
//                }
//            }
//        }
//        if (newChineseBuffer.length() == 1) {
//            if ('元' != newChineseBuffer.charAt(0)) {
//                newChineseBuffer.setLength(0);
//            }
//        }
//        return newChineseBuffer;
//    }
//
//    /**
//     * 小数金额转换
//     *
//     * @param decimalMoney 小数部分金额
//     * @return StringBuffer
//     */
//    private static StringBuffer decimalMoneyStr(String decimalMoney) {
//        if (decimalMoney.length() > 2) {
//            decimalMoney = decimalMoney.substring(0, 2);
//        }
//        decimalMoney = decimalMoney.replaceFirst("00", "");
//        StringBuffer decimalMoneySb = new StringBuffer(decimalMoney);
//        if (decimalMoneySb.length() > 0) {
//            if (decimalMoneySb.charAt(decimalMoneySb.length() - 1) == '0') {
//                decimalMoneySb.deleteCharAt(decimalMoneySb.length() - 1);
//            }
//            if (decimalMoneySb.length() <= 0) {
//                return decimalMoneySb;
//            }
//            decimalMoneySb.setCharAt(0, CONVERSION_MAP.get(decimalMoneySb.charAt(0)));
//            switch (decimalMoneySb.length()) {
//                case 1:
//                    decimalMoneySb.append("角");
//                    break;
//                case 2:
//                    decimalMoneySb.setCharAt(1, CONVERSION_MAP.get(decimalMoneySb.charAt(1)));
//                    if (decimalMoneySb.charAt(0) != '零') {
//                        decimalMoneySb.insert(1, '角');
//                    }
//                    decimalMoneySb.append("分");
//                    break;
//                default:
//                    break;
//            }
//        }
//        return decimalMoneySb;
//    }
//
//    public static void main(String[] agrs){
//
//        System.out.println(money2ChineseStr("1233.22"));
//    }
//}
