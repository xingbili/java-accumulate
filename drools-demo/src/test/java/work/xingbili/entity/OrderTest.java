package work.xingbili.entity;

import java.io.InputStream;
import java.math.BigDecimal;
import org.drools.core.io.impl.UrlResource;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import work.xingbili.DroolsApplication;

@SpringBootTest(classes = DroolsApplication.class)
class OrderTest {

  @Autowired
  private KieContainer kieContainer;
  @Test
  public void test() {
    // 从Kie容器对象中获取会话对象（默认session对象
    KieSession kieSession = kieContainer.newKieSession();

    Order order = new Order();
    order.setOriginalPrice(BigDecimal.valueOf(180));
    // 将order对象插入工作内存
    kieSession.insert(order);
    // 匹配对象
    // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
    kieSession.fireAllRules();
    System.out.println("优惠前价格：" + order.getOriginalPrice() + ",优惠后价格：" + order.getRealPrice());

    kieSession = kieContainer.newKieSession();
    order.setOriginalPrice(BigDecimal.valueOf(300));
    // 将order对象插入工作内存
    kieSession.insert(order);
    // 匹配对象
    // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
    kieSession.fireAllRules();
    System.out.println("优惠前价格：" + order.getOriginalPrice() + ",优惠后价格：" + order.getRealPrice());

    kieSession = kieContainer.newKieSession();
    order.setOriginalPrice(BigDecimal.valueOf(600));
    // 将order对象插入工作内存
    kieSession.insert(order);
    // 匹配对象
    // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
    kieSession.fireAllRules();
    System.out.println("优惠前价格：" + order.getOriginalPrice() + ",优惠后价格：" + order.getRealPrice());

    kieSession = kieContainer.newKieSession();
    order.setOriginalPrice(BigDecimal.valueOf(1200));
    // 将order对象插入工作内存
    kieSession.insert(order);
    // 匹配对象
    // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
    kieSession.fireAllRules();
    System.out.println("优惠前价格：" + order.getOriginalPrice() + ",优惠后价格：" + order.getRealPrice());

    kieSession = kieContainer.newKieSession();
    order.setOriginalPrice(BigDecimal.valueOf(3000));
    // 将order对象插入工作内存
    kieSession.insert(order);
    // 匹配对象
    // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
    kieSession.fireAllRules();
    System.out.println("优惠前价格：" + order.getOriginalPrice() + ",优惠后价格：" + order.getRealPrice());

    kieSession = kieContainer.newKieSession();
    order.setOriginalPrice(BigDecimal.valueOf(8000));
    // 将order对象插入工作内存
    kieSession.insert(order);
    // 匹配对象
    // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
    kieSession.fireAllRules();
    System.out.println("优惠前价格：" + order.getOriginalPrice() + ",优惠后价格：" + order.getRealPrice());

    kieSession = kieContainer.newKieSession();
    order.setOriginalPrice(BigDecimal.valueOf(12000));
    // 将order对象插入工作内存
    kieSession.insert(order);
    // 匹配对象
    // 激活规则，由drools框架自动进行规则匹配。若匹配成功，则执行
    kieSession.fireAllRules();
    System.out.println("优惠前价格：" + order.getOriginalPrice() + ",优惠后价格：" + order.getRealPrice());

    // 关闭会话
    kieSession.dispose();

  }


  /**
   * 通过workbench 生成的jar 加载去解析规则
   * @throws Exception
   */
  @Test
  public void test1() throws Exception {
    //通过此URL可以访问到maven仓库中的jar包
    // URL地址构成：http://ip地址:Tomcat端⼝号/WorkBench⼯程名/maven2/坐标/版 本号/xxx.jar
    String url = "http://localhost:8080/kie-web/maven2/work/xingbili/orderDiscount/1.0.0/orderDiscount-1.0.0.jar";
    KieServices kieServices = KieServices.Factory.get();
    // 通过Resource资源对象加载jar包
    UrlResource resource = (UrlResource) kieServices.getResources().newUrlResource(url);
    // 通过Workbench提供的服务来访问maven仓库中的jar包资源，需要先进⾏Workbench 的认证
    resource.setUsername("kie-web");
    resource.setPassword("kie-web123");
    resource.setBasicAuthentication("enabled");
    //将资源转换为输⼊流，通过此输⼊流可以读取jar包数据
    InputStream inputStream = resource.getInputStream();
    //创建仓库对象，仓库对象中保存Drools的规则信息
    KieRepository repository = kieServices.getRepository();
    //通过输⼊流读取maven仓库中的jar包数据，包装成KieModule模块添加到仓库中
    KieModule kieModule = repository.addKieModule(kieServices.getResources().newInputStreamResource(inputStream));
    //基于KieModule模块创建容器对象，从容器中可以获取session会话
//    KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
//    KieSession session = kieContainer.newKieSession();
    KieSession session = kieContainer.newKieSession();
    Product product = new Product();
    product.setPrice(new BigDecimal("200"));
    session.insert(product);
    session.fireAllRules();
    session.dispose();

  }

}