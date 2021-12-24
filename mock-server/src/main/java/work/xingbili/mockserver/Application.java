/**
 * xingbili
 */

package work.xingbili.mockserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 启动类
 * @author: xinghuolin
 * @create: 2021/12/7 20:27
 */
@SpringBootApplication
@MapperScan("work.xingbili.mockserver.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
