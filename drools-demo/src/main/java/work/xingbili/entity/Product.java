/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.entity;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author xinghuolin
 * @date 2023/12/6 14:46
 */
@Data
public class Product {


  private BigDecimal price;

  private String name;

  private String type;

}