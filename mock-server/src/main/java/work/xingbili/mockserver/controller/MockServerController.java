/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import work.xingbili.mockserver.entity.TbApiInfo;
import work.xingbili.mockserver.service.ITbApiInfoService;
import work.xingbili.mockserver.service.ITbApiResponseService;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * <p>
 * mock接口测试表 前端控制器
 * </p>
 *
 * @author code generator
 * @since 2021-12-07
 */
@Slf4j
@RestController
@RequestMapping("/mockserver")
public class MockServerController {

    //@Autowired
    private ITbApiResponseService iTbApiResponseService;
    @Autowired
    private ITbApiInfoService iTbApiInfoService;

    /**
     * 通过@PathVariable获取路径中的参数
     * @param apiPath api路径
     * @param request 请求
     * @return JSONObject
     */
    @RequestMapping(value="/{apiPath}/**",method= RequestMethod.POST)
    public JSONObject getApiResponse(@PathVariable String apiPath, HttpServletRequest request) {
        final String path =
                request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern =
                request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
        if (!arguments.isEmpty()) {
            apiPath = apiPath + '/' + arguments;
        }
        String req= this.readAsChars(request);
        log.info("mock 接口调用入参apiPath:{},request:{}",apiPath,JSONObject.toJSONString(JSONObject.toJSONString(req)));
        JSONObject returnObject = iTbApiResponseService.getResponse("/"+apiPath,req);
        return returnObject;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/deleteTblApiInfo/{id}")
    public String deleteDepart(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("condition", "delete");
        TbApiInfo delete = iTbApiInfoService.getById(id);
        model.addAttribute("department", delete);
        iTbApiInfoService.deleteIt(id);
        return "success";
    }
    @PostMapping("/updateTblApiInfo")
    public String updateDepart(TbApiInfo tbApiInfo, Model model) {
        model.addAttribute("condition", "update");
        TbApiInfo update = iTbApiInfoService.update(tbApiInfo);
        model.addAttribute("department", update);
        return "success";
    }
    @GetMapping("/getTblApiInfo/{id}")
    public String getDepartmentById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("condition", "delete");
        TbApiInfo get = iTbApiInfoService.getById(id);
        model.addAttribute("department", get);
        return "success";
    }

    /**
     * 读取request信息
     * @param request 请求信息
     * @return 返回请求对象中的内容
     */
    private  String readAsChars(HttpServletRequest request)
    {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


}