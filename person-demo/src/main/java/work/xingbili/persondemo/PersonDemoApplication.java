package work.xingbili.persondemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author xinghuolin
 */
@SpringBootApplication
@Slf4j
public class PersonDemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(PersonDemoApplication.class, args);
//        log.info("hahah");
//        log.debug("debug");
//        log.error("error");
//        log.trace("trace");
    }


    @Bean
    CommandLineRunner lookupTestService( ) {
        System.out.println("i am back");
        return null;
    }

}