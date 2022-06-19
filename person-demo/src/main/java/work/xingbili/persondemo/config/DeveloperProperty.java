/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/2/19 14:10
 */
@Data
@ConfigurationProperties(prefix = "developer")
@Component
public class DeveloperProperty {
    private String name ;
    private String website;
    private String qq;
    private String sex;
}
