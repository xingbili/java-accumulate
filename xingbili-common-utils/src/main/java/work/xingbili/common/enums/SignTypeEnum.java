/**
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 签章方式
 * 
 * @author gaopeng
 * @date 2021/07/29
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SignTypeEnum {

    /**
     * ukey
     */
    U_KEY_SIGN(1, "ukey"),

    /**
     * 场景证书
     */
    COMMON_SIGN(2, "场景证书")
    ;

    private Integer code;

    private String info;
}
