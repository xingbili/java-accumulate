/**
 * xingbili
 */

package work.xingbili.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: test devTools
 * @author: xinghuolin
 * @create: 2021/11/27 19:46
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @PostMapping("test01")
    public String test01(){
        log.info("test0012244");
        return "first";
    }

    @GetMapping("test02")
    public String test02(){

        List<Person> list = new ArrayList<>();
        Person p = new Person();
        p.setName("张三");
        p.setInfo("hha");
        list.add(p);
        Person p2 = new Person();
        p2.setName("李四");
        p2.setInfo("hhh");
        list.add(p2);
        World world = new World();
        world.setName("shijian");
        world.setPersonList(list);

        List<Person> personList = world.getPersonList();
         for (Person person:personList){
             person.setDesc("llll");
         }

         log.info(world.getPersonList().toString());
        log.info("test02031111");
        return "second";
    }
}
