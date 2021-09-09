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
 * 工作流业务对象(提取流程节点业务object的公共字段)
 * 
 * @author gaopeng
 * @date 2021/07/14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@ApiModel(description = "工作流业务对象(提取流程节点业务object的公共字段)")
public class WorkflowBusinessDto implements Serializable {

    /**
     * 工作流bizkey
     */
    @ApiModelProperty(value = "工作流bizkey")
    private String workflowBizKey;
    
    /**
     * 工作流业务信息名称
     */
    @ApiModelProperty(value = "工作流业务信息名称")
    private String workflowBizName;

    /**
     * 工作流任务id
     */
    @ApiModelProperty(value = "工作流任务id")
    private String taskId;
    
    /**
     * 审批状态
     */
    @ApiModelProperty(value = "审批状态")
    private Integer approvalStatus;
    
    /**
     * 审批意见
     */
    @ApiModelProperty(value = "审批意见")
    private String approvalDesc;
    
    /**
     * 当前企业统一社会信用代码
     */
    @ApiModelProperty(value = "当前企业统一社会信用代码")
    private String currentCorpCode;
    
    /**
     * 当前银行编码
     */
    @ApiModelProperty(value = "当前银行编码")
    private String currentBankCode;
    
}
