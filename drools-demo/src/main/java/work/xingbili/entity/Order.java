/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author xinghuolin
 * @date 2023/12/6 10:16
 */
@Data
public class Order {

  /**
   * 订单优惠前价格
   */
  private BigDecimal originalPrice;
  /**
   * 订单优惠后价格
   */
  private BigDecimal realPrice;

}