/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.xingbili.persondemo.config.DeveloperProperty;

/**
 * bean 注入的三种方式：set注入，构造器注入，注解注入
 *
 *
 * @author xinghuolin
 * @date 2022/2/19 14:19
 */
@RestController
@RequiredArgsConstructor
public class PropertyController {
    final DeveloperProperty developerProperty;

    @GetMapping("/property")
    public Object index(){
        return developerProperty.getName();
    }
}
