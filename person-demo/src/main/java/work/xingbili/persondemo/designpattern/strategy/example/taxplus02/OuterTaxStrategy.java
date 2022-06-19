/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy.example.taxplus02;


import work.xingbili.persondemo.designpattern.strategy.example.tax.TaxType;

/**
 * @author xinghuolin
 * @date 2022/6/19 15:15
 */
public class OuterTaxStrategy implements ITaxStrategy {
    @Override
    public double calc(long amount) {
        // 获取税率
        final double taxRate = 0.2;
        return amount / (1 + taxRate) * taxRate;
    }

    @Override
    public void register() {
        AutoRegisterTaxStrategyFactory.registerTaxStrategy(TaxType.INTER, this);
    }
}
