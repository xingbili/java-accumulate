/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import work.xingbili.common.enums.SignNodeEnum;

import java.io.Serializable;

/**
 * 签章基础dto
 * @author yangfan
 * @date 2021/07/20
 */
@SuppressWarnings("serial")
@Data
public class CorpAutoSealBaseDto implements Serializable{

    /**
     * 签章方式 1-uk,2-场景证书
     */
    @ApiModelProperty(name = "signType",value = "签章方式 1-uk,2-场景证书")
    private Integer signType;

    /**
     * pdf文件id
     */
    private Long pdfId;
    
    /**
     * 协议编号
     */
    private String contractNo;
    
    /**
     * 手机号码
     */
    private String phoneNo;
    
    /**
     * 短信验证码
     */
    private String msgCode;
    
    /**
     * 当前用户id
     */
    private Long userId;
    
    /**
     * 企业法人签章, Y-是，N-否
     */
    private String corpLegalSeal;
    
    /**
     * 企业统一社会信用代码
     */
    private String orgCode;
    
    /**
     * 企业名称
     */
    private String orgName;
    
    /**
     * 签章关键字
     */
    private String signLocationKey;

    /**
     * 签章节点
     */
    private SignNodeEnum signNode;

    /**
     * 是否上传影像  Y-是，N-否
     */
    private String uploadImageFlag;
    
    /**
     * 影像节点编号，上传影像必传
     */
    private String busiFileType;

    /**
     * 加密串
     */
    private String pkcs;

    /**
     * 签章算法, 从客户端传入，表示UKEY或软UKEY支持的MD5 SHA1 SHA256
     */
//    private String hashAlogrithmArg;

    /**
     * reservedDataKey
     */
    private String reservedDataKey;

    /**
     * reservedPdfKey
     */
    private String reservedPdfKey;
    
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
