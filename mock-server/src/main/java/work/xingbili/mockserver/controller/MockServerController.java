/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import work.xingbili.mockserver.service.ITbApiResponseService;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ITbApiResponseService iTbApiResponseService;

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
