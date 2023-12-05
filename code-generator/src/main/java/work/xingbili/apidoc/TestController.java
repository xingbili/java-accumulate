/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.apidoc;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinghuolin
 * @date 2023/2/8 13:58
 */
@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping(value = "/object")
  public Map<String, String> testMapUser() {
    return null;
  }

}