/*
 *  版权信息: © 聚均科技
 */

package work.xingbili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xinghuolin
 * @date 2023/12/11 16:12
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    //java10： 局部变量类型的自动推断
ConfigurableApplicationContext ioc= SpringApplication.run(Application.class, args);
    System.out.println("启动成功");

    //1、获取容器中所有组件的名字
    String[] names = ioc.getBeanDefinitionNames();
    //2、挨个遍历：
    // dispatcherServlet、beanNameViewResolver、characterEncodingFilter、multipartResolver
    // SpringBoot把以前配置的核心组件现在都给我们自动配置好了。
    for (String name : names) {
      System.out.println(name);
    }

  }

}