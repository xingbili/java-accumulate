/**
 * xingbili
 */

package work.xingbili.springbootshiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: xinghuolin
 * @create: 2021/12/4 16:18
 */
@Configuration
public class ShiroConfig {
    // ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /*
        * anon 无需认证就可以访问
        * authc 必须认证了才能文
        * user 必须拥有记住我才能访问
        * perms 拥有对某个资源的权限才能访问
        * role 拥有某个角色才能访问
        * */

        Map<String ,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        // 设置登录请求
        bean.setLoginUrl("/toLogin");
        return bean;
    }

    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建 realm 对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


}
