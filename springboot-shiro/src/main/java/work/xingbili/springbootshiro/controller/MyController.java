/**
 * xingbili
 */

package work.xingbili.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import work.xingbili.springbootshiro.myannotations.AllowRequest;

/**
 * @description:
 * @author: xinghuolin
 * @create: 2021/12/4 16:06
 */
@Controller
public class MyController {
    @RequestMapping("/test")
    @ResponseBody
    @AllowRequest("1111")
    public String test(@RequestParam String id){
        return "login";
    }


    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

//    @RequestMapping("/login")
//    public String login(String username ,String password,Model model){
//        //获取当前用户
//        Subject subject= SecurityUtils.getSubject();
//        //封装用户登录数据
//      UsernamePasswordToken token=  new UsernamePasswordToken(username,password);
//      try {
//          //执行登录的方法，如果没有异常就说明成功了
//          subject.login(token);
//          return "index";
//      }catch (UnknownAccountException e){
//          model.addAttribute("msg","用户名不对");
//          return "login";
//      } catch (IncorrectCredentialsException e){
//          model.addAttribute("msg","密码不对");
//          return "login";
//      }
//
//    }
}
