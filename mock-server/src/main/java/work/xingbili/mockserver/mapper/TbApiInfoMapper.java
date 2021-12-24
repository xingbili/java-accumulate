/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.xingbili.mockserver.entity.TbApiInfo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-12-08
 */
public interface TbApiInfoMapper extends BaseMapper<TbApiInfo> {

    /**
     * 表全字段更新
     * 
     * @param entity
     * @return
     */
    int updateByIdWithOptimistiLock(TbApiInfo entity);
   
}
