/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.xingbili.mockserver.common.BusinessException;
import work.xingbili.mockserver.entity.TbApiInfo;
import work.xingbili.mockserver.entity.TbApiResponse;
import work.xingbili.mockserver.enums.ErrorCodeEnum;
import work.xingbili.mockserver.mapper.TbApiResponseMapper;
import work.xingbili.mockserver.service.ITbApiInfoService;
import work.xingbili.mockserver.service.ITbApiResponseService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code generator
 * @since 2021-12-08
 */
@Slf4j
@Service
public class TbApiResponseServiceImpl extends ServiceImpl<TbApiResponseMapper, TbApiResponse> implements ITbApiResponseService {

    @Autowired
    ITbApiInfoService iTbApiInfoService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByIdSelectiveWithOptimistiLock(TbApiResponse entity) {
        log.info("准备更新操作,入参:{}", JSONObject.toJSONString(entity));
        boolean result = this.updateById(entity);
        if (!result) {
            throw new BusinessException(ErrorCodeEnum.E009.getErrorCode(),
                ErrorCodeEnum.E009.getErrorMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByIdWithOptimistiLock(TbApiResponse entity) {
        log.info("准备更新操作,入参:{}", JSONObject.toJSONString(entity));
        int result = getBaseMapper().updateByIdWithOptimistiLock(entity);
        if (result == 0) {
            throw new BusinessException(ErrorCodeEnum.E009.getErrorCode(),
                ErrorCodeEnum.E009.getErrorMessage());
        }
    }

    @Override
    public JSONObject getResponse(String path, String strReqJson){
        QueryWrapper<TbApiInfo> queryWrapper = new QueryWrapper<TbApiInfo>();
        queryWrapper.eq("api_path", path);
        TbApiInfo tbApiInfo = iTbApiInfoService.getOne(queryWrapper);
        // 没有对应api配置信息
        if(tbApiInfo==null){
          throw new BusinessException(ErrorCodeEnum.E002.getErrorCode(),ErrorCodeEnum.E002.getErrorMessage());
        }
        JSONObject reqObj = JSON.parseObject(strReqJson);
        //请求参数中获取参数信息
        String keyValue = reqObj.getString(tbApiInfo.getApiKeyword());
        QueryWrapper<TbApiResponse> queryResponseWrapper = new QueryWrapper<TbApiResponse>();
        queryResponseWrapper.eq("api_path",path)
                .eq("api_keyword_value",keyValue);
        TbApiResponse tbApiResponse = this.getOne(queryResponseWrapper);
        // 没有对应接口请求关键字参数的配置信息
        if(tbApiResponse==null){
            throw new BusinessException(ErrorCodeEnum.E003.getErrorCode(),ErrorCodeEnum.E003.getErrorMessage());
        }
        // 封装成JSONObject对象返回
        JSONObject obj = null;
        try {
            obj= JSONObject.parseObject(tbApiResponse.getApiResponseBody());
        }catch (Exception e){
            throw new BusinessException(ErrorCodeEnum.E001.getErrorCode(),ErrorCodeEnum.E001.getErrorMessage());
        }
        return obj;
    }


}
