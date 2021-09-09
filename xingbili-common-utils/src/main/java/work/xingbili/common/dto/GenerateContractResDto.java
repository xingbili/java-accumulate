/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 生成合同返回dto
 *
 * @author Aning
 * @date 2021/07/21 09:07
 **/

@SuppressWarnings("serial")
@Data
@ApiModel(description = "生成合同返回dto")
public class GenerateContractResDto implements Serializable {

    /**
     * 内部文件平台文件id
     */
    @ApiModelProperty(value = "内部文件平台文件id")
    private  Long  fileId;

    /**
     * 文件英文名称
     */
    @ApiModelProperty(value = "文件英文名称")
    private  String  fileName;


    /**
     * 文件编号
     */
    @ApiModelProperty(value = "文件编号(协议合同编号)")
    private  String  fileNo;
    
    /**
     * 关联业务编号（批量生成时需要）
     */
    @ApiModelProperty(value = "关联业务编号")
    private  String  bizRelationNo;
    
    /**
     * （批量生成时需要）
     * 模板类型 :
     * 模板类型[1=合作协议/2=付款承诺函/3=开立单/4=转让协议/5=流转单/6=业务申请书/7=保理合同/8=转让协议/9=平台服务协议/
     * 10=平台授权委托书/11=担保协议/12=企业征信查询授权书/13=法人征信查询授权书/14=实际控制人征信查询授权书/
     * 15=三方数据查询授权书/16=流动资金借款合同/17=动产质押合同/18=质物移交清单/19=动产监管协议/
     * 20=质物最低价值/最低数量通知书/21=质物最低价值/最低数量通知书回执/22=受托支付委托书/23=电子借据凭证/24=
     */
    @ApiModelProperty(value = "协议合同类型")
    private Integer templateType;
}
