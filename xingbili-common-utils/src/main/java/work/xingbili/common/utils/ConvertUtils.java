/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换工具类
 *
 * @author yanping.shi
 * @date 2021/04/13
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Slf4j
public final class ConvertUtils {


    /**
     * 将值转换为对应类型的数据
     *
     * @param type
     * @param value
     * @return
     */
    public static Object convert(Class type, Object value) {
        if (null == value) {
            return null;
        }
        String s = String.valueOf(value);
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        String tname = type.getName();
        if ("java.util.Date".equals(tname)) {
            value = DateUtil.format(s);
        } else if (tname.startsWith("java.lang") || type.isPrimitive()) {
            if (tname.equals("java.lang.Integer") || tname.equals("int")) {
                value = Integer.valueOf(Integer.parseInt(s));
            } else if (tname.equals("java.lang.Character") || tname.equals("char")) {
                value = Character.valueOf(s.charAt(0));
            } else if (tname.equals("java.lang.Double") || tname.equals("double")) {
                value = Double.valueOf(s);
            } else if (tname.equals("java.lang.Long") || tname.equals("long")) {
                value = Long.valueOf(s);
            } else if (tname.equals("java.lang.String") || tname.equals("String")) {
                value = String.valueOf(s);
            } else {
                Class packClass = type;
                if (!tname.startsWith("java.lang")) {
                    try {
                        packClass = Class.forName(
                                (new StringBuilder("java.lang."))
                                        .append(work.xingbili.common.utils.StringUtils.fstCharUpperCase(tname)).toString());
                        Method parseMethod = packClass.getMethod(
                                (new StringBuilder("parse")).append(work.xingbili.common.utils.StringUtils.fstCharUpperCase(tname)).toString(),
                                new Class[]{String.class});
                        value = parseMethod.invoke(type, new Object[]{s});

                    } catch (Exception e) {
                        // 记录底层异常信息情况
                        log.error("Bean转换失败：{}", e);
                        return null;
                    }
                }
            }
        } else if (tname.equals("java.math.BigDecimal") || tname.equals("BigDecimal")) {
            value = new BigDecimal(s);
        } else if (!tname.equals("java.lang.String")) {
            return null;
        }
        return value;
    }

    /**
     * 将List转换为SQL In条件所需的逗号分隔字符串列表（每组最多800个value)
     *
     * @param numberList
     * @return
     */
    public static List<String> convertNumberListToSQLIN(List<String> numberList) {
        StringBuilder builder = new StringBuilder();
        List<String> sqlinlist = new ArrayList<String>();
        int insize = 800;// 每组in的最大个数
        for (int numberIndex = 0; numberIndex < numberList.size(); numberIndex++) {
            if (numberIndex % insize != 0) {
                builder.append(",");
            }
            builder.append(numberList.get(numberIndex));
            if ((numberIndex + 1) % insize == 0) {
                sqlinlist.add(builder.toString());
                builder = new StringBuilder();
            }
        }
        if (builder.length() > 0) {
            sqlinlist.add(builder.toString());
        }
        return sqlinlist;
    }

}
