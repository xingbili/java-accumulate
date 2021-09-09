/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.api;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import work.xingbili.common.dto.GeneralResponse;
import work.xingbili.common.dto.GeneralVoidResponse;
import work.xingbili.common.enums.ErrorCodeEnum;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * 公用返回处理工具类
 *
 * @author yangfan
 * @date 2021/04/13
 */
@Slf4j
public class CommHelper {

    /**
     * 判断响应报文是否成功
     *
     * @param response
     * @return
     */
    public static boolean isSuccess(Map<String, Object> response) {
        String respCode = getValue(response, "rspCode", String.class);
        return StringUtils.equals(ErrorCodeEnum.E00000000.getErrorCode(), respCode);
    }

    /**
     * 判断响应报文是否成功
     *
     * @param <T>
     * @param response
     * @return
     */
    public static <T> boolean isGeneralSuccess(GeneralResponse<T> response) {
        String respCode = response.getRspCode();
        return StringUtils.equals(ErrorCodeEnum.E00000000.getErrorCode(), respCode);
    }
    
    
    public static boolean isSuccess(GeneralVoidResponse response) {
        String respCode = response.getRspCode();
        return StringUtils.equals(ErrorCodeEnum.E00000000.getErrorCode(), respCode);
    }

    /**
     * 构建响应报文
     *
     * @param apiErrorEnum
     * @return
     */
    public static Map<String, Object> createResponse(ErrorCodeEnum apiErrorEnum) {
        Map<String, Object> response = Maps.newHashMap();
        response.put("rspCode", apiErrorEnum.getErrorCode());
        response.put("rspDesc", apiErrorEnum.getErrorMessage());
        return response;
    }

    /**
     * 构建data为空响应报文
     * 
     * @param apiErrorEnum
     * @return
     */
    public static GeneralVoidResponse createVoidResponse(ErrorCodeEnum apiErrorEnum) {
        GeneralVoidResponse response = new GeneralVoidResponse();
        response.setRspCode(apiErrorEnum.getErrorCode());
        response.setRspDesc(apiErrorEnum.getErrorMessage());
        return response;
    }

    /**
     * 构建成功响应报文
     *
     * @return
     */
    public static Map<String, Object> createSuccssResponse() {

        return createResponse(ErrorCodeEnum.E00000000);
    }

    /**
     * 构建data为空成功响应报文
     * 
     * @return
     */
    public static GeneralVoidResponse createVoidSuccssResponse() {

        return createVoidResponse(ErrorCodeEnum.E00000000);
    }

    /**
     * 构建成功且带返回体的响应报文
     *
     * @param data
     * @return
     */
    public static Map<String, Object> createSuccssResponse(Object data) {

        return createResponse(ErrorCodeEnum.E00000000.getErrorCode(), ErrorCodeEnum.E00000000.getErrorMessage(), data);
    }

    /**
     * 返回指定拒绝码的应答
     *
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static Map<String, Object> createResponse(String errorCode, String errorMessage) {
        Map<String, Object> response = Maps.newHashMap();
        response.put("rspCode", errorCode);
        response.put("rspDesc", errorMessage);
        return response;
    }

    /**
     * 构建响应报文
     *
     * @param errorCode
     * @param errorMessage
     * @param data
     * @return
     */
    public static Map<String, Object> createResponse(String errorCode, String errorMessage, Object data) {
        Map<String, Object> response = Maps.newHashMap();
        response.put("rspCode", errorCode);
        response.put("rspDesc", errorMessage);
        response.put("data", data);
        return response;
    }

    /**
     * 构建响应报文
     *
     * @param <T>
     * @param data
     * @return
     */
    public static <T> GeneralResponse<T> createSuccessGeneralResponse(T data) {

        return createGeneralResponse(ErrorCodeEnum.E00000000.getErrorCode(), ErrorCodeEnum.E00000000.getErrorMessage(),
            data);
    }

    /**
     * 构建响应报文
     *
     * @param <T>
     * @param errorCodeEnum
     * @return
     */
    public static <T> GeneralResponse<T> createGeneralResponse(ErrorCodeEnum errorCodeEnum) {
        return new GeneralResponse<T>(errorCodeEnum.getErrorCode(), errorCodeEnum.getErrorMessage(), null);
    }

    public static <T> GeneralResponse<T> createGeneralResponse(String errorCode, String errorMessage) {
        return new GeneralResponse<T>(errorCode, errorMessage, null);
    }

    public static <T> GeneralResponse<T> createGeneralResponse(String errorCode, String errorMessage, T data) {
        return new GeneralResponse<T>(errorCode, errorMessage, data);
    }

    /**
     * 构建data为空响应报文
     * 
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static GeneralVoidResponse createVoidResponse(String errorCode, String errorMessage) {
        GeneralVoidResponse response = new GeneralVoidResponse();
        response.setRspCode(errorCode);
        response.setRspDesc(errorMessage);
        return response;
    }

    @SuppressWarnings("unchecked")
    public static <R> R getValue(Map<String, Object> map, String key, final Class<R> clazz) {
        if (map == null) {
            return null;
        }

        Object value = map.get(key);
        if (value == null || StringUtils.isBlank(value.toString())) {
            return null;
        }
        try {
            if (clazz == String.class && value.getClass() != String.class) {
                return (R)value.toString();
            }

            if (clazz == BigDecimal.class && value.getClass() != BigDecimal.class) {
                return (R)new BigDecimal(value.toString());
            }

            if (clazz == Integer.class && value.getClass() != Integer.class) {
                return (R)new Integer(value.toString());
            }

            if (clazz == Long.class && value.getClass() != Long.class) {
                return (R)new Long(value.toString());
            }

            if (clazz == Short.class && value.getClass() != Short.class) {
                return (R)new Short(value.toString());
            }

            if (clazz == Byte.class && value.getClass() != Byte.class) {
                return (R)new Byte(value.toString());
            }

            if (clazz == Float.class && value.getClass() != Float.class) {
                return (R)new Float(value.toString());
            }

            if (clazz == Double.class && value.getClass() != Double.class) {
                return (R)new Double(value.toString());
            }

            if (clazz == Boolean.class && value.getClass() != Boolean.class) {
                return (R)new Boolean(value.toString());
            }

            if (clazz == Character.class && value.getClass() != Character.class) {
                return (R)new Character(value.toString().charAt(0));
            }

        } catch (Exception e) {
            log.error("处理异常c", e);
            return null;
        }
        return (R)value;
    }

    /**
     * 数据复制
     *
     * @param <T>
     * @param updateProperties
     * @param bean
     */
    public static <T> void copyPropertiesToObject(Map<String, Object> updateProperties, T bean) {
        Set<Map.Entry<String, Object>> revisabilityFiledSet = updateProperties.entrySet();
        for (Map.Entry<String, Object> entry : revisabilityFiledSet) {
            Object value = entry.getValue();
            if (value != null) {
                try {
                    BeanUtils.setProperty(bean, entry.getKey(), value);
                } catch (Exception e) {
                    log.error("从Map{} 复制 到对象 {} 属性{} 错误key{} 异常", updateProperties.getClass(), bean.getClass(),
                        entry.getKey(), e);
                }
            }
        }
    }
}
