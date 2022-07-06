/**
 * xingbili
 */

package work.xingbili.mockserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 启动类
 * @author: xinghuolin
 * @create: 2021/12/7 20:27
 */
@SpringBootApplication
@MapperScan("work.xingbili.mockserver.mapper")
public class Application {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List A = list.stream().filter((item)->item>2).collect(Collectors.toList());
        List B = list.stream().filter((item)->item<3).collect(Collectors.toList());
        System.out.println(A);

        ConfigurableApplicationContext run =   SpringApplication.run(Application.class,args);
    }
}
