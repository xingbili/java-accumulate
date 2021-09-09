/**
*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 字体枚举
 * </p>
 *
 * @author yangfan
 * @date 2021/04/13
 */
@Getter
@AllArgsConstructor
public enum FontEnum {
	/**
	 * 仿宋体
	 */
	STFANGSO("STFANGSO.TTF", "仿宋体"),
	/**
	 * 微软雅黑
	 */
	MSYH("MSYH.TTF", "微软雅黑");

	private String code;
	private String info;

}
