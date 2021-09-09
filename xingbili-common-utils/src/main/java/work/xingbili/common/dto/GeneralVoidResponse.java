/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用无结构体返回对象
 * 
 * @author zhangxinyi
 * @date 2021/05/17
 */
@ApiModel(description = "通用无结构体返回对象")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class GeneralVoidResponse implements Serializable {

    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码")
    private String rspCode;

    /**
     * 响应描述
     */
    @ApiModelProperty(value = "响应描述")
    private String rspDesc;

}
