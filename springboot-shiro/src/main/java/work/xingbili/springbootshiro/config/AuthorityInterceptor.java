/**
 * xingbili
 */

package work.xingbili.springbootshiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import work.xingbili.springbootshiro.myannotations.AllowRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 请求拦截器
 * @author: xinghuolin
 * @create: 2021/12/5 12:05
 */
@Component
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器处理 request：{}",request);
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            AllowRequest allowRequest = handlerMethod.getMethod().getAnnotation(AllowRequest.class);
            // 如果方法上的注解为空 则获取类的注解
            if (allowRequest == null) {
                allowRequest = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AllowRequest.class);
            }
            // 如果标记了注解，则判断权限
            if (allowRequest != null && StringUtils.isNotBlank(allowRequest.value())) {
                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
                Set<String> permissionSet = new HashSet<>();
                permissionSet.add("1111");
                //没有包含 则
                if (CollectionUtils.isEmpty(permissionSet) ){
                    return false;
                }
                return permissionSet.contains(allowRequest.value());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
