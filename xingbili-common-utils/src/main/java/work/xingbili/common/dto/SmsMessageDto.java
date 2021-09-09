/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Mq短信消息服务类
 * 
 * @author user
 * @date 2021/05/18
 */
@ApiModel(description = "Mq短信消息服务类")
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsMessageDto implements Serializable {

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNumbers;

    /**
     * 短信模板
     */
    @ApiModelProperty(value = "短信模板")
    private String templateCode;

    /**
     * 模板中的变量
     */
    @ApiModelProperty(value = "模板中的变量")
    private Map<String,String> templateParamMap;

    /**
     * 短信模板描述
     */
    @ApiModelProperty(value = "短信模板描述")
    private String templateInfo;


    /**
     * Token
     */
    @ApiModelProperty(value = "Token")
    private String token;

}
