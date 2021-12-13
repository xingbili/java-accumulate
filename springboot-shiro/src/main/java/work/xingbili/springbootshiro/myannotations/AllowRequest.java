package work.xingbili.springbootshiro.myannotations;

import java.lang.annotation.*;

/**
 * @Description: 注解拦截
 * @author: xinghuolin
 * @create: 2021-12-05
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AllowRequest {
    String value();
}
