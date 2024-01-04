/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinghuolin
 * @date 2023/12/11 16:14
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

  @GetMapping("/world")
  public String hello() {
    return "Hello World!";
  }
}