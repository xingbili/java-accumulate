/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Id通用Dto
 *
 * @author zhiqing.zhou
 * @date 2021/05/21 09:13
 **/
@Data
@ApiModel(description = "Id通用Dto")
public class IdReqDto {

    /**
     * id
     */
    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "id")
    private Long id;
}
