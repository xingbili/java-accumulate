/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 签章请求dto
 *
 * @author Aning
 * @date 2021/07/27 09:35
 **/
@Data
@ApiModel(description = "签章请求dto")
public class SealCommReqDto<T> implements Serializable {


    /**
     * 企业签章数据 sealType为Y时必填
     */
    private T corpSealDto;

}
