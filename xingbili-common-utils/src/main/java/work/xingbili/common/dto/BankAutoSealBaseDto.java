/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

import lombok.Data;
import work.xingbili.common.enums.SignNodeEnum;

import java.io.Serializable;

/**
 * 银行签章基础dto
 * 
 * @author yangfan
 * @date 2021/07/20
 */
@SuppressWarnings("serial")
@Data
public class BankAutoSealBaseDto implements Serializable {

    /**
     * pdf文件id
     */
    private Long pdfId;

    /**
     * 当前用户id
     */
    private Long userId;

    /**
     * 签章关键字
     */
    private String signLocationKey;

    /**
     * 签章节点
     */
    private SignNodeEnum signNode;

    /**
     * 是否上传影像 Y-是，N-否
     */
    private String uploadImageFlag;

    /**
     * 影像节点编号，上传影像必传
     */
    private String busiFileType;
}
