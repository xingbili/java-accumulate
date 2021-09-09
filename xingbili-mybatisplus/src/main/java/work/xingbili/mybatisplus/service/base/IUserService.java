/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mybatisplus.service.base;

import work.xingbili.mybatisplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2021-09-07
 */
public interface IUserService extends IService<User> {

    /**
     * 乐观锁更新操作,可选择的更新，entity字段值为null不做更新
     * 
     * @param entity
     */
    void updateByIdSelectiveWithOptimistiLock(User entity);

    /**
     * 乐观锁更新操作, 表全字段更新,entity字段值为null则对应表字段更新为null
     * 
     * @param entity
     */
    void updateByIdWithOptimistiLock(User entity);

}
