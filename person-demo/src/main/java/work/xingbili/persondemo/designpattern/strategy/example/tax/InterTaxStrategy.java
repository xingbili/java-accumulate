/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy.example.tax;

/**
 *
 *
 * @author xinghuolin
 * @date 2022/6/19 15:13
 */
public class InterTaxStrategy implements ITaxStrategy{
    @Override
    public double calc(long amount) {
        // 获取税率
        final double taxRate = 0.2;
        return amount * taxRate;
    }
}
