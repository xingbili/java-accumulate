/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.enums;

/**
 * <p>
 * 数据库操作类型
 * </p>
 *
 * @author yangfan
 * @date 2021/04/13
 */
public enum AuditLogOperationTypeEnum {

  /**
   * 新增
   */
  NEW("NEW", "新增"),
  /**
   * 修改
   */
  UPDATE("UPD", "修改"),
  /**
   * 删除
   */
  DELETE("DEL", "删除"),

  /**
   * 授权
   */
  AUTH("AUTH", "授权"),
  /**
   * 启用
   */
  ENABLE("ENABLE", "启用"),
  /**
   * 禁用
   */
  FORBIDDEN("FORBIDDEN", "禁用"),
  /**
   * 配置资源
   */
  ALLOCATE_RESOURCES("ALLOCATE_RESOURCES", "配置资源"),
  /**
   * 解锁
   */
  UN_LOCK("UN_LOCK", "解锁"),
  /**
   * 清退
   */
  CHECK_AND_RETURN("CHECK_AND_RETURN", "清退"),
  /**
   * 导出
   */
  EXPORT("EXPORT", "导出"),
  /**
   * 发布
   */
  PUBLISH("PUBLISH", "发布"),
  /**
   * 下线
   */
  OFF_LINE("OFF_LINE", "下线"),
  /**
   * 已读
   */
  READ("READ", "已读"),
  /**
   * 分配
   */
  ALLOCATION("ALLOCATION", "分配"),
  /**
   * 通过
   */
  PASS("PASS", "通过"),
  /**
   * 退回
   */
  BACK("BACK", "退回"),
  /**
   * 重置密码
   */
  RESET_PASSWORD("RESET_PASSWORD", "重置密码"),

  ;

  private String code;
  private String info;

  private AuditLogOperationTypeEnum(String code, String info) {
    this.code = code;
    this.info = info;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}
