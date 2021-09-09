/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @des: 签章节点
 * @date: 2021/8/20 16:41
 * @author: zxx
 */
@Getter
@AllArgsConstructor
public enum SignNodeEnum {

    /**
     *
     */
    S001("S001", ""),

    /**
     *
     */
    S002("S002", ""),
    /**
     * 到货确认-收到监管物品确认书-签公章
     */
    S003("S003", "公章"),
    /**
     * 用款申请-提款申请书-签公章
     */
    S004("S004", "企业公章"),
    /**
     * 用款申请-提款申请书-授权人签章
     */
    S005("S005", "授权人签章"),

    /**
     * 用款申请-收押凭据-公章或信贷专用章
     */
    S006("S006", "公章或信贷专用章"),
    /**
     * 提货确认-提货通知书（回执）-公章
     */
    S007("S007", "公章"),
    ;

    /**
     * 签章节点编号
     */
    private String signCode;

    /**
     * 签章节点描述
     */
    private String signDesc;
}
