/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xinghuolin
 * @date 2023/3/13 16:40
 */
@Getter
@Setter
public class OrderRequest {

  /**
   * 客户号
   */
  private String customerNumber;

  /**
   * 年龄
   */
  private Integer age;
  /**
   * 订单金额
   */
  private Integer amount;
  /**
   * 客户类型
   */
  private CustomerType customerType;

}