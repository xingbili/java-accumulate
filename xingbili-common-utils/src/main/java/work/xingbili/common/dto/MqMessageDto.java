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
 * mq消息dto对象
 * 
 * @author yangfan
 * @date 2021/05/18
 */
@ApiModel(description = "mq消息dto对象")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class MqMessageDto <T> implements Serializable{

    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型")
    private String messageType;

    /**
     * 消息体
     */
    @ApiModelProperty(value = "消息体")
    private T data;
}
