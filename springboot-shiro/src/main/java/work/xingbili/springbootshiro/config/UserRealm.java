/**
 * xingbili
 */

package work.xingbili.springbootshiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @description:
 * @author: xinghuolin
 * @create: 2021/12/4 16:21
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行了授权");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行了认证");
        // 获取当前用户
         String name ="root";
         String password ="123456";
        UsernamePasswordToken userToken= (UsernamePasswordToken)authenticationToken;
        if(!userToken.getUsername().equals(name)){
            return null;
        }
        //密码认证
        return new SimpleAuthenticationInfo("",password,"");
    }
}
