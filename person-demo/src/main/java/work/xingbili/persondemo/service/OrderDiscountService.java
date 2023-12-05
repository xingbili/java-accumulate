/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.xingbili.persondemo.model.OrderDiscount;
import work.xingbili.persondemo.model.OrderRequest;

/**
 * @author xinghuolin
 * @date 2023/3/13 16:43
 */
@Service
public class OrderDiscountService {

  @Autowired
  private KieContainer kieContainer;

  public OrderDiscount getDiscount(OrderRequest orderRequest) {
    OrderDiscount orderDiscount = new OrderDiscount();
    // 开启会话
    KieSession kieSession = kieContainer.newKieSession();
    // 设置折扣对象
    kieSession.setGlobal("orderDiscount", orderDiscount);
    // 设置订单对象
    kieSession.insert(orderRequest);
    // 触发规则
    kieSession.fireAllRules();
    // 中止会话
    kieSession.dispose();
    return orderDiscount;
  }

}