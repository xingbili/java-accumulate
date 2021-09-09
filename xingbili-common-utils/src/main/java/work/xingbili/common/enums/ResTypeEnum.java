/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 资源类型枚举
 *
 * @author zhiqing.zhou
 * @date 2021/05/18 13:58
 **/
@Getter
@AllArgsConstructor
public enum ResTypeEnum {

    /**
     * 系统
     */
    SYSTEM("S", "系统"),

    /**
     * 菜单
     */
    MENU("M", "菜单"),

    /**
     * URI地址
     */
    URI("U", "URI地址"),

    /**
     * 按钮
     */
    BUTTON("B", "按钮");

    String code;

    String info;
}
