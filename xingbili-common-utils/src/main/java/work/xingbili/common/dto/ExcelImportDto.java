/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.dto;

/**
 * ***************************************************************************
 * 创建时间 : 2019/7/24
 * 实现功能 :
 * 作者 : bill
 * 版本 : v0.0.1
 * -----------------------------------------------------------------------------
 * 修改记录:
 * 日 期 版本 修改人 修改内容
 * 2019/7/24 v0.0.1 bill 创建
 * ****************************************************************************
 */
public class ExcelImportDto {

	// 属性名
	private String propertyName;
	// 类型
	private Class<?> classType;
	// 长度
	private Integer length;

	// 是否必输
	private boolean required;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Class<?> getClassType() {
		return classType;
	}

	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

}
