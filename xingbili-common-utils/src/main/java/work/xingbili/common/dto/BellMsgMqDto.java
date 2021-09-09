/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 小铃铛Mq消息
 * @author Aning
 * @date 2021/05/28 09:58
 **/
@ApiModel(description = "小铃铛Mq消息DTO")
@Data
@EqualsAndHashCode(callSuper = false)
public class BellMsgMqDto {

  /**
   * 模板代码 : 模板代码
   */
  @ApiModelProperty(value = "模板代码")
  private String templateCode;

  /**
   * 模板数据 : 模板数据
   */
  @ApiModelProperty(value = "模板数据")
  private Map<String, String> templateData;

  /**
   * 创建人id
   */
  @ApiModelProperty(value = "创建人id")
  private Long createUser;

  /**
   * 创建人姓名
   */
  @ApiModelProperty(value = "创建人姓名")
  private String createUserName;

  /**
   * 机构id
   */
  @ApiModelProperty(value = "机构id")
  private Long orgId;

  /**
   * 机构编码
   */
  @ApiModelProperty(value = "机构编码")
  private String orgCode;
  
  /**
   * 渠道码
   */
  @ApiModelProperty(value = "渠道码")
  private String channelCode;

}
