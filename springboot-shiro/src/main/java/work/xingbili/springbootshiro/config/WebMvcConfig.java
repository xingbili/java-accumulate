/**
 * xingbili
 */

package work.xingbili.springbootshiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: xinghuolin
 * @create: 2021/12/6 20:05
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired AuthorityInterceptor authorityInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 通过registry来注册拦截器，通过addPathPatterns来添加拦截路径
        registry.addInterceptor(authorityInterceptor).addPathPatterns("/**");
    }
}
