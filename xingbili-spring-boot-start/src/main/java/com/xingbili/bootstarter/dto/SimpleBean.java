/**
 * 版权信息: © 聚均科技
 */

package com.xingbili.bootstarter.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/5/30 10:54
 */
@EnableConfigurationProperties(SimpleBean.class)
@ConfigurationProperties(prefix = "simplebean")
@Data
public class SimpleBean {
    private int id;
    private String name;
}
