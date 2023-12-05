/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.controller;

import work.xingbili.persondemo.model.OrderDiscount;
import work.xingbili.persondemo.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import work.xingbili.persondemo.service.OrderDiscountService;

/**
 * @author xinghuolin
 * @date 2023/3/13 16:44
 */
@RestController
public class OrderDiscountController {

  @Autowired
  private OrderDiscountService orderDiscountService;

  @PostMapping("/get-discount")
  public ResponseEntity<OrderDiscount> getDiscount(@RequestBody OrderRequest orderRequest) {
    OrderDiscount discount = orderDiscountService.getDiscount(orderRequest);
    return new ResponseEntity<>(discount, HttpStatus.OK);
  }
}