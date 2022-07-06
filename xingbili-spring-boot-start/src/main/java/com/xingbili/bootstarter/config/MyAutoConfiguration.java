/**
 * 版权信息: © 聚均科技
 */

package com.xingbili.bootstarter.config;

import com.xingbili.bootstarter.dto.SimpleBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/5/30 10:57
 */
@Configuration
@ConditionalOnBean(ConfigMarker.class)
public class MyAutoConfiguration {
    @Bean
    public SimpleBean simpleBean(){
        return new SimpleBean();
    }
}
