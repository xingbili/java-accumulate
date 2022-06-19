/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy.example;

import work.xingbili.persondemo.designpattern.strategy.example.tax.TaxType;
import work.xingbili.persondemo.designpattern.strategy.example.taxplus02.AutoRegisterTaxStrategyFactory;
import work.xingbili.persondemo.designpattern.strategy.example.taxplus02.ITaxStrategy;

/**
 * 测试类
 *
 * @author xinghuolin
 * @date 2022/6/19 17:56
 */
public class DecisionDemo {
    public static void main(String[] args) throws Exception {
        ITaxStrategy taxStrategy = AutoRegisterTaxStrategyFactory.getTaxStrategy(TaxType.INTER);
        System.out.println(taxStrategy.calc(100));
    }
}
