/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.mockserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import work.xingbili.mockserver.entity.Second;
import work.xingbili.mockserver.entity.SubItem;
import work.xingbili.mockserver.service.ITbApiInfoService;

import jakarta.validation.groups.Default;

/**
 * @author xinghuolin
 * @date 2022/5/27 10:07
 */
@RestController
@RequestMapping("/test")
public class TestController {

    //@Autowired
    //private SimpleBean simpleBean;

    @Autowired
    private ITbApiInfoService tbApiInfoService;


    @RequestMapping(value = "/hhh", method = RequestMethod.POST)
    public String addItem(@RequestBody @Validated({Second.class, Default.class}) SubItem subItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(subItem.toString());
            return bindingResult.getFieldError().getDefaultMessage();
        }
        System.out.println(subItem.getProps());
        System.out.println(subItem.toString());
        return "Ok";
    }

    @RequestMapping(value = "/mm", method = RequestMethod.GET)
    public String test() {
        //System.out.println(simpleBean);
        return "Ok";
    }
}