/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.mockserver.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import work.xingbili.mockserver.entity.TbApiResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code generator
 * @since 2021-12-08
 */
public interface ITbApiResponseService extends IService<TbApiResponse> {

    /**
     * 乐观锁更新操作,可选择的更新，entity字段值为null不做更新
     * 
     * @param entity 实体信息
     */
    void updateByIdSelectiveWithOptimistiLock(TbApiResponse entity);

    /**
     * 乐观锁更新操作, 表全字段更新,entity字段值为null则对应表字段更新为null
     * 
     * @param entity 实体信息
     */
    void updateByIdWithOptimistiLock(TbApiResponse entity);

    /**
     * 查询返回
     *
     * @param path
     * @param strReqJson
     */
    JSONObject getResponse(String path, String strReqJson);

}
