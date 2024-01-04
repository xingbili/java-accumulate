/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.persondemo.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description:
 * @author: xing
 * @date: 2024/1/2 13:57
 */
@Data
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}
