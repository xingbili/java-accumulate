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
import work.xingbili.common.enums.TokenSatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * Corp端token对象
 * 
 * @author yangfan
 * @date 2021/04/11
 */
@SuppressWarnings("serial")
@ApiModel(description = " Corp端token对象")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CorpUserToken implements Serializable {

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;

    /**
     * 渠道码
     */
    @ApiModelProperty(value = "渠道码")
    private String channelCode;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 企业Id
     */
    @ApiModelProperty(value = "企业Id")
    private Long orgId;

    /**
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    private String orgCode;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String mobilePhone;

    /**
     * token状态
     */
    @ApiModelProperty(value = "token状态")
    private TokenSatusEnum tokenStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    /**
     * 登录ip
     */
    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    /**
     * 登录地区
     */
    @ApiModelProperty(value = "登录地区")
    private String loginArea;
}
