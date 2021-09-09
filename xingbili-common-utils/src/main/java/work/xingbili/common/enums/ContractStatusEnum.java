/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 协议合同状态枚举
 *
 * @author zhiqing.zhou
 * @date 2021/06/28 16:14
 **/
@Getter
@AllArgsConstructor
public enum ContractStatusEnum {

    /**
     * 待签约
     */
    UNSIGNED(1, "待签约"),

    /**
     * 签约中
     */
    SIGNING(2, "签约中"),

    /**
     * 已签约
     */
    SIGNED(3, "已签约"),

    /**
     * 不签约
     */
    NOT_REQUIRED(4, "不签约"),
    
    /**
     * 已拒绝
     */
    REFUSED(5, "已拒绝"),
    
    ;

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String info;

    /**
     * 获取审批意见描述
     *
     * @author zhiqing.zhou
     * @date 2021/06/23 19:38
     * @param code
     *            审批状态
     * @return java.lang.String
     */
    public static String getDesc(Integer code) {
        for (ContractStatusEnum statusEnum : ContractStatusEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getInfo();
            }
        }
        return null;
    }
}
