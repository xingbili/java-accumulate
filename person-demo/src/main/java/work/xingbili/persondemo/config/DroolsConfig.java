/*
 *  版权信息: © 聚均科技
 */

package work.xingbili.persondemo.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xinghuolin
 * @date 2023/3/13 16:37
 */
@Configuration
public class DroolsConfig {
  // 制定规则文件的路径
  private static final String RULES_CUSTOMER_RULES_DRL = "rules/customer-discount.drl";
  private static final KieServices kieServices = KieServices.Factory.get();

  @Bean
  public KieContainer kieContainer() {
    System.out.println("start config");
    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_CUSTOMER_RULES_DRL));
    KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
    kb.buildAll();
    KieModule kieModule = kb.getKieModule();
    KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
    return kieContainer;
  }
}