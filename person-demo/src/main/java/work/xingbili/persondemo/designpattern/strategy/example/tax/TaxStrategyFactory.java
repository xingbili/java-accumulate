/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy.example.tax;

/**
 * @author xinghuolin
 * @date 2022/6/19 15:17
 */
public class TaxStrategyFactory {
    public static ITaxStrategy getTaxStrategy(TaxType taxType) throws Exception {
        // 当增加新的税类型时，需要修改代码，同时会增加圈复杂度
        if (taxType == TaxType.INTER) {
            return new InterTaxStrategy();
        } else if (taxType == TaxType.OUTER) {
            return new OuterTaxStrategy();
        } else {
            throw new Exception("The tax type is not supported.");
        }
    }


}
