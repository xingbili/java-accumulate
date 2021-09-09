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

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 分页查询对象传输Bean
 * </p>
 *
 * @author yangfan
 * @date 2021/04/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@ApiModel(description = "分页查询对象")
public class PageBaseQuery implements Serializable {

  /**
   * 当前所在位置
   */
  @NotNull(message = "index不能为空")
  @ApiModelProperty(value = "当前所在位置", required = true)
  private Integer index;

  /**
   * 每页的数量
   */
  @NotNull(message = "pageSize不能为空")
  @ApiModelProperty(value = "每页的数量", required = true)
  private Integer pageSize;

  /**
   * 当前页码
   */
  @NotNull(message = "current不能为空")
  @ApiModelProperty(value = "当前页码", required = true)
  private Integer current;
}
