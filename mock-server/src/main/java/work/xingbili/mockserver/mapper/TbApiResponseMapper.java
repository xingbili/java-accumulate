/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import work.xingbili.mockserver.entity.TbApiResponse;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author code generator
 * @since 2021-12-08
 */
@Mapper
public interface TbApiResponseMapper extends BaseMapper<TbApiResponse> {

    /**
     * 表全字段更新
     * 
     * @param entity
     * @return
     */
    int updateByIdWithOptimistiLock(TbApiResponse entity);
   
}
