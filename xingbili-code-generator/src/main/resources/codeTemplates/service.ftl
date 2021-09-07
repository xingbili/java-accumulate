<#include "copyright.ftl"/>
package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 乐观锁更新操作,可选择的更新，entity字段值为null不做更新
     * 
     * @param entity
     */
    void updateByIdSelectiveWithOptimistiLock(${entity} entity);

    /**
     * 乐观锁更新操作, 表全字段更新,entity字段值为null则对应表字段更新为null
     * 
     * @param entity
     */
    void updateByIdWithOptimistiLock(${entity} entity);

}
</#if>
