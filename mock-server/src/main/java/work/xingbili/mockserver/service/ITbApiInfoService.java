/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.xingbili.mockserver.entity.TbApiInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2021-12-08
 */
public interface ITbApiInfoService extends IService<TbApiInfo> {

    /**
     * 乐观锁更新操作,可选择的更新，entity字段值为null不做更新
     * 
     * @param entity
     */
    void updateByIdSelectiveWithOptimistiLock(TbApiInfo entity);

    /**
     * 乐观锁更新操作, 表全字段更新,entity字段值为null则对应表字段更新为null
     * 
     * @param entity
     */
    void updateByIdWithOptimistiLock(TbApiInfo entity);

     void insert(TbApiInfo tbApiInfo);

     void deleteIt(Integer id);

    TbApiInfo update(TbApiInfo tbApiInfo);

    TbApiInfo getById(Integer id);

     void testException() throws  RuntimeException;

}
