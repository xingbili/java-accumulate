/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * auth模块token prefix定义
 * 
 * @author yangfan
 * @date 2021/04/11
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AuthTokenPrefixRedisEnum {

    /**
     * TOKEN超时时间
     */
    BANK_AUTH_TOKEN("BANK:AUTH:TOKEN", 7200),

    /**
     * TOKEN超时时间
     */
    CORP_AUTH_TOKEN("CORP:AUTH:TOKEN", 7200),
    
    ;

    /**
     * redis存储的前缀
     */
    private String prefix;

    /**
     * reids超时时间,单位为s
     */
    private Integer timeout;

}
