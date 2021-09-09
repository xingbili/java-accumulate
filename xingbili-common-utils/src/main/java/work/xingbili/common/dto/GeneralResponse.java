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
 * <p>
 * 通用返回对象
 * </p>
 *
 * @author yangfan
 * @date 2021/04/11
 */
@ApiModel(description = "通用返回对象")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class GeneralResponse<T> implements Serializable {

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

    /**
     * 报文体
     */
    @ApiModelProperty(value = "报文体")
    private T data;
}
