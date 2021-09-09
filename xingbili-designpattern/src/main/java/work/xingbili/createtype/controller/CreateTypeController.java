/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.createtype.controller;

import org.springframework.web.bind.annotation.*;
import work.xingbili.createtype.SimpleFactory;

/**
 * @des: TODO
 * @author xinghuolin
 * @date 2021/9/8 9:15
 */
@RestController
@RequestMapping("/create")
public class CreateTypeController {

    @GetMapping("/getProduct")
    public String getProduct(@RequestParam("type") String type ){

        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.getProduct(type);
        return "";

    }
}
