/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.model;

/**
 * @author xinghuolin
 * @date 2023/3/13 16:41
 */
public enum CustomerType {
  LOYAL, NEW, DISSATISFIED;

  public String getValue() {
    return this.toString();
  }
}