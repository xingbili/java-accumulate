/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.common.constant;

/**
 * 操作日志模板code
 *
 * @author Aning
 * @date 2021/05/24 16:40
 **/
public class AuditLogTemplateConstant {
  /*
  操作平台（企业01 运营02 电子03 统一04
  AUDIT+功能名+操作平台+操作功能
   */

  /**
   *  个人信息_门户企业系统_【操作人】【操作类型】了个人信息
   */
  public final static String AUDIT010101 = "AUDIT010101";
  /**
   *  个人信息_门户运营系统_【操作人】【操作类型】了个人信息
   */
  public final static String AUDIT010201 = "AUDIT010201";
  /**
   * 个人信息_电子债券凭证系统_【操作人】【操作类型】了个人信息
   */
  public final static String AUDIT010301 = "AUDIT010301";
  /**
   *    个人信息_银行统一管理系统_【操作人】【操作类型】了个人信息
   */
  public final static String AUDIT010401 = "AUDIT010401";
  /**
   * 安全设置_门户企业系统_【操作人】【操作类型】了手机号码
   */
  public final static String AUDIT020101 = "AUDIT020101";
  /**
   *     安全设置_门户企业系统_【操作人】【操作类型】了登录密码
   */
  public final static String AUDIT020102 = "AUDIT020102";
  /**
   *     安全设置_门户运营系统_【操作人】【操作类型】了手机号码
   */
  public final static String AUDIT020201 = "AUDIT020201";
  /**
   *   安全设置_门户运营系统_【操作人】【操作类型】了登录密码
   */
  public final static String AUDIT020202 = "AUDIT020202";
  /**
   *      安全设置_电子债券凭证系统_【操作人】【操作类型】了手机号码
   */
  public final static String AUDIT020301 = "AUDIT020301";
  /**
   *   安全设置_电子债券凭证系统_【操作人】【操作类型】了登录密码
   */
  public final static String AUDIT020302 = "AUDIT020302";
  /**
   *     安全设置_银行统一管理系统_【操作人】【操作类型】了手机号码
   */
  public final static String AUDIT020401 = "AUDIT020401";
  /**
   * 安全设置_银行统一管理系统_【操作人】【操作类型】了登录密码
   */
  public final static String AUDIT020402 = "AUDIT020402";
  /**
   *   角色管理_门户企业系统_【操作人】【操作类型】了角色【角色名称】
   */
  public final static String AUDIT030101 = "AUDIT030101";
  /**
   * 角色管理_门户运营系统_【操作人】【操作类型】了角色【角色名称】
   */
  public final static String AUDIT030201 = "AUDIT030201";
  /**
   *   角色管理_银行统一管理系统_【操作人】【操作类型】了角色【角色名称】
   */
  public final static String AUDIT030401 = "AUDIT030401";
  /**
   *   菜单管理_门户运营系统_【操作人】【操作类型】了菜单【菜单名称】
   */
  public final static String AUDIT040201 = "AUDIT040201";
  /**
   *    菜单管理_门户运营系统_【操作人】为菜单【菜单名称】【操作类型】
   */
  public final static String AUDIT040202 = "AUDIT040202";
  /**
   * 菜单管理_银行统一管理系统_【操作人】【操作类型】了菜单【菜单名称】
   */
  public final static String AUDIT040401 = "AUDIT040401";
  /**
   * 菜单管理_银行统一管理系统_【操作人】为菜单【菜单名称】【操作类型】
   */
  public final static String AUDIT040402 = "AUDIT040402";
  /**
   *  用户管理_门户企业系统_【操作人】【操作类型】了用户【用户姓名】
   */
  public final static String AUDIT050101 = "AUDIT050101";
  /**
   *  用户管理_门户运营系统_【操作人】【操作类型】了用户【用户姓名】
   */
  public final static String AUDIT050201 = "AUDIT050201";
  /**
   *     在线用户_门户企业系统_【操作人】【操作类型】了用户【用户姓名（顿号分隔）】
   */
  public final static String AUDIT060101 = "AUDIT060101";
  /**
   *     在线用户_门户运营系统_【操作人】【操作类型】了用户【用户姓名】
   */
  public final static String AUDIT060201 = "AUDIT060201";
  /**
   * 登录日志_门户企业系统_【操作人】【操作类型】了登录日志
   */
  public final static String AUDIT070101 = "AUDIT070101";
  /**
   *  登录日志_门户运营系统_【操作人】【操作类型】了登录日志
   */
  public final static String AUDIT070201 = "AUDIT070201";

  /**
   *  登录日志_银行统一管理系统_【操作人】【操作类型】了登录日志
   */
  public final static String AUDIT070401 = "AUDIT070401";
  /**
   * 公告管理_门户运营系统_【操作人】【操作类型】了公告信息
   */
  public final static String AUDIT080201 = "AUDIT080201";
  /**
   *   公告管理_银行统一管理系统_【操作人】【操作类型】了公告信息
   */
  public final static String AUDIT080401 = "AUDIT080401";
  /**
   *   新闻管理_门户运营系统_【操作人】【操作类型】了新闻
   */
  public final static String AUDIT090201 = "AUDIT090201";
  /**
   *  大事件管理_门户运营系统_【操作人】【操作类型】了大事件
   */
  public final static String AUDIT100201 = "AUDIT100201";
  /**
   * 常见问题管理_门户运营系统_【操作人】【操作类型】了常见问题
   */
  public final static String AUDIT110201 = "AUDIT110201";
  /**
   *  机构消息_门户运营系统_【操作人】【操作类型】了机构消息
   */
  public final static String AUDIT120201 = "AUDIT120201";
  /**
   * 机构消息_电子债券凭证系统_【操作人】【操作类型】了消息
   */
  public final static String AUDIT120301 = "AUDIT120301";
  /**
   * 机构消息_银行统一管理系统_【操作人】【操作类型】了消息
   */
  public final static String AUDIT120401 = "AUDIT120401";
  /**
   *     身份认证审批_门户运营系统_【操作人】将【客户名称】【操作类型】至【所属网点】
   */
  public final static String AUDIT130201 = "AUDIT130201";
  /**
   * 身份认证审批_门户运营系统_【操作人】【操作类型】了【客户名称】的身份认证审批申请
   */
  public final static String AUDIT130202 = "AUDIT130202";

  /**
   * 身份认证审批_企业门户系统_【操作人】【操作类型】了【客户名称】的身份认证审批申请
   */
  public final static String AUDIT130101 = "AUDIT130101";

  /**
   * 企业消息_门户企业系统_【操作人】【操作类型】了企业消息
   */
  public final static String AUDIT140201 = "AUDIT140201";
  /**
   * 机构管理_银行统一管理系统_【操作人】【操作类型】了机构【机构名称】
   */
  public final static String AUDIT150401 = "AUDIT150401";
  /**
   *     操作员管理_银行统一管理系统_【操作人】【操作类型】了操作员【操作员姓名】
   */
  public final static String AUDIT160401 = "AUDIT160401";
  /**
   * 在线操作员_银行统一管理系统_【操作人】【操作类型】了操作员【操作员姓名（顿号分隔）】
   */
  public final static String AUDIT170401 = "AUDIT170401";
  /**
   *  操作日志_银行统一管理系统_【操作人】【操作类型】了操作日志
   */
  public final static String AUDIT180401 = "AUDIT180401";
  /**
   * 部门管理_银行统一管理系统_【操作人】【操作类型】了部门【部门名称】
   */
  public final static String AUDIT190401 = "AUDIT190401";
  /**
   *  岗位管理_银行统一管理系统_【操作人】【操作类型】了岗位【岗位名称】
   */
  public final static String AUDIT200401 = "AUDIT200401";
  /**
   * 数据字典管理_银行统一管理系统_【操作人】【操作类型】了数据字典【字典名称】
   */
  public final static String AUDIT210401 = "AUDIT210401";
  /**
   *系统参数管理_银行统一管理系统_【操作人】【操作类型】了系统参数：【参数编码】的参数值
   */
  public final static String AUDIT220401 = "AUDIT220401";

}
