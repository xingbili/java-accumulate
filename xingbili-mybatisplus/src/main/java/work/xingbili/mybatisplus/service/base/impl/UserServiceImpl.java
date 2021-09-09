/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mybatisplus.service.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.xingbili.mybatisplus.entity.User;
import work.xingbili.mybatisplus.mapper.UserMapper;
import work.xingbili.mybatisplus.service.base.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code generator
 * @since 2021-09-07
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByIdSelectiveWithOptimistiLock(User entity) {
//        log.info("准备更新操作,入参:{}", JSONObject.toJSONString(entity));
//        boolean result = this.updateById(entity);
//        if (!result) {
//            throw new BusinessException(ErrorCodeEnum.E00000009.getErrorCode(),
//                ErrorCodeEnum.E00000009.getErrorMessage());
//        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByIdWithOptimistiLock(User entity) {
//        log.info("准备更新操作,入参:{}", JSONObject.toJSONString(entity));
//        int result = getBaseMapper().updateByIdWithOptimistiLock(entity);
//        if (result == 0) {
//            throw new BusinessException(ErrorCodeEnum.E00000009.getErrorCode(),
//                ErrorCodeEnum.E00000009.getErrorMessage());
//        }
    }    
}
