/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/2/20 10:46
 */
@Data
@ConfigurationProperties(prefix = "developer")
@Component
public class DBConfig {

}
