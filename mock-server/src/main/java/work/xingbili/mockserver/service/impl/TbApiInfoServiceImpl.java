/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.xingbili.mockserver.common.BusinessException;
import work.xingbili.mockserver.entity.TbApiInfo;
import work.xingbili.mockserver.enums.ErrorCodeEnum;
import work.xingbili.mockserver.mapper.TbApiInfoMapper;
import work.xingbili.mockserver.service.ITbApiInfoService;

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
@CacheConfig(cacheNames = {"apiinfo"})
public class TbApiInfoServiceImpl extends ServiceImpl<TbApiInfoMapper, TbApiInfo> implements ITbApiInfoService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByIdSelectiveWithOptimistiLock(TbApiInfo entity) {
        log.info("准备更新操作,入参:{}", JSONObject.toJSONString(entity));
        boolean result = this.updateById(entity);
        if (!result) {
            throw new BusinessException(ErrorCodeEnum.E009.getErrorCode(),
                ErrorCodeEnum.E009.getErrorMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByIdWithOptimistiLock(TbApiInfo entity) {
        log.info("准备更新操作,入参:{}", JSONObject.toJSONString(entity));
        int result = getBaseMapper().updateByIdWithOptimistiLock(entity);
        if (result == 0) {
            throw new BusinessException(ErrorCodeEnum.E009.getErrorCode(),
                ErrorCodeEnum.E009.getErrorMessage());
        }
    }

    @Cacheable(key = "#a0.id")
    @Override
    public void insert(TbApiInfo tbApiInfo) {
        getBaseMapper().insert(tbApiInfo);
    }
    @CacheEvict(key = "#p0")
    @Override
    public void deleteIt(Integer id) {
        getBaseMapper().deleteById(id);
    }
    @CachePut(key = "#a0.id")
    @Override
    public TbApiInfo update(TbApiInfo tbApiInfo) {
        getBaseMapper().updateByIdWithOptimistiLock(tbApiInfo);
        return tbApiInfo;
    }
    @Cacheable(key = "#id", condition = "#p0>=1")
    @Override
    public TbApiInfo getById(Integer id) {
        return getBaseMapper().selectById(id);
    }
}
