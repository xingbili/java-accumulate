/**
*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 证件类型枚举
 * </p>
 *
 * @author yangfan
 * @date 2021/04/13
 */
@Getter
@AllArgsConstructor
public enum IDTypeEnum {

	/**
	 * 社会统一信用代码
	 */

    IND01("IND01", "身份证代码"),

	/**
	 * 社会统一信用代码
	 */

	ENT05("ENT05", "社会统一信用代码"),


	;


	private String code;
	private String info;

}
