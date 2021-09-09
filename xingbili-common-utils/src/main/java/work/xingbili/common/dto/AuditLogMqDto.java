/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Map;

/**
 * 审计日志MQ对象
 *
 * @author Aning
 * @date 2021/05/19 16:18
 **/
@ApiModel(description = "审计日志MQ对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class AuditLogMqDto {


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
     * 操作平台发布系统 : 发布系统[BANK_UMS-银行统一管理系统/PROTAL_OPERATION-门户运营系统/BANK_VOUCHER-电子债权业务系统/BANK_WORKFLOW-工作流引擎系统/PROTAL_CORP-门户企业系统]
     */
    @ApiModelProperty(value = "操作平台")
    private String platformType;

    /**
     * 当前登陆机构
     */
    @ApiModelProperty(value = "当前登陆机构")
    private Long orgId;

    /**
     * 机构代码
     */
    @ApiModelProperty(value = "机构代码")
    private String orgCode;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String orgName;


    /**
     * 操作人ID
     */
    @ApiModelProperty(value = "操作人ID")
    private Long createUser;


    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名")
    private String createUserName;


    /**
     * 业务操作日期
     */
    @ApiModelProperty(value = "业务操作日期")
    private Date createDate;

}
