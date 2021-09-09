/**
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 机构类型
 *
 * @author caolh
 * @date 2021/07/21 19:42
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OrgTypeEnum {

    /**
     * 银行
     */
    BANK("BANK", "银行"),

    /**
     * 企业
     */
    CORP("CORP", "企业"),
    ;

    private String orgType;

    private String orgTypeDesc;
}
