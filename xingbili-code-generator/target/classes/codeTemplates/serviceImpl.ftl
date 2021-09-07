<#include "copyright.ftl"/>
package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.fusionfintrade.core.enums.ErrorCodeEnum;
import com.fusionfintrade.core.exceptions.BusinessException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByIdSelectiveWithOptimistiLock(${entity} entity) {
        log.info("准备更新操作,入参:{}", JSONObject.toJSONString(entity));
        boolean result = this.updateById(entity);
        if (!result) {
            throw new BusinessException(ErrorCodeEnum.E00000009.getErrorCode(),
                ErrorCodeEnum.E00000009.getErrorMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateByIdWithOptimistiLock(${entity} entity) {
        log.info("准备更新操作,入参:{}", JSONObject.toJSONString(entity));
        int result = getBaseMapper().updateByIdWithOptimistiLock(entity);
        if (result == 0) {
            throw new BusinessException(ErrorCodeEnum.E00000009.getErrorCode(),
                ErrorCodeEnum.E00000009.getErrorMessage());
        }
    }    
}
</#if>
