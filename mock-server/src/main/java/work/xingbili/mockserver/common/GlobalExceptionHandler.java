/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import work.xingbili.mockserver.enums.ErrorCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * 
 * @author xinghuolin
 * @date 2021/04/11
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public JSONObject exceptionHandler(Exception e) {
        log.info("全局异常捕获，异常信息为:",e);
        Map<String, Object> respMap = this.createExceptionRes(ErrorCodeEnum.E009.getErrorCode(),ErrorCodeEnum.E009.getErrorMessage());
       if(e instanceof BusinessException) {
           respMap = this.createExceptionRes(((BusinessException)e).getErrorCode(), ((BusinessException)e).getErrorMessage());
        }
        JSONObject jsonObj = new JSONObject(respMap);
        return jsonObj;
    }

    public  Map<String, Object> createExceptionRes(String errorCode ,String errorMsg ) {
        Map<String,Object> map = new HashMap<>(2);
        map.put("rspCode", errorCode);
        map.put("rspDesc", errorMsg);
        return map;
    }

}
