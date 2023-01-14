/*
 *  版权信息: © 聚均科技
 */

package datamasking;

import java.io.Serializable;

/**
 * @author xinghuolin
 * @date 2022/8/27 10:15
 */
public class User implements Serializable {
  /**
   * 主键ID
   */
  private Long id;

  /**
   * 姓名
   */
  @DataMasking(maskFunc = DataMaskingFunc.ALL_MASK)
  private String name;

  /**
   * 年龄
   */
  private Integer age;

  /**
   * 邮箱
   */
  @DataMasking(maskFunc = DataMaskingFunc.ALL_MASK)
  private String email;

}