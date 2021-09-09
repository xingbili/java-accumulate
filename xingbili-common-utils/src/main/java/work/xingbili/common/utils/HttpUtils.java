/*
 * 版权信息: © 聚均科技
 */ 
package work.xingbili.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * http工具类
 * 
 * @author gaopeng
 * @date 2021/05/31
 */
public class HttpUtils {
    
    private static String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
    
    public static boolean isMSBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal)) {
                return true;
            }
        }
        return false;
    }
}
