/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mybatisplus.mapper;

import org.apache.ibatis.annotations.Mapper;
import work.xingbili.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-09-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 表全字段更新
     * 
     * @param entity
     * @return
     */
    int updateByIdWithOptimistiLock(User entity);
   
}
