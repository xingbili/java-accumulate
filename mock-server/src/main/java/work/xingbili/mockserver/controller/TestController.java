/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.mockserver.controller;

import com.xingbili.bootstarter.dto.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import work.xingbili.mockserver.entity.Item;
import work.xingbili.mockserver.service.ITbApiInfoService;

import javax.validation.Valid;

/**
 * @author xinghuolin
 * @date 2022/5/27 10:07
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SimpleBean simpleBean;

    @Autowired
    private ITbApiInfoService tbApiInfoService;


    @RequestMapping(value = "/hhh", method = RequestMethod.POST)
    public String addItem(@RequestBody @Valid Item item , BindingResult bindingResult)  {
        if(bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        tbApiInfoService.testException();
        return "Ok";
    }

    @RequestMapping(value = "/mm", method = RequestMethod.GET)
    public String test() {
        System.out.println(simpleBean);
        return "Ok";
    }
}
