/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取请求的IP地址
 * 
 * @author yangfan
 * @date 2021/04/13
 */
public class IpUtils {

    /**
     * unknown类型
     */
    private final static String UNKNOWN = "unknown";

    /**
     * 逗号
     */
    private final static String COMMA = ",";

    /**
     * 长度
     */
    private final static int IP_LENGTH = 15;

    private IpUtils() {}

    /**
     * 获取请求的IP地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return UNKNOWN;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > IP_LENGTH) {
            if (ip.indexOf(COMMA) > 0) {
                ip = ip.substring(0, ip.indexOf(COMMA));
            }
        }

        return ip;
    }

    /**
     * 判断ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
            && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
