/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy.example.taxplus01;

import work.xingbili.persondemo.designpattern.strategy.example.tax.ITaxStrategy;
import work.xingbili.persondemo.designpattern.strategy.example.tax.InterTaxStrategy;
import work.xingbili.persondemo.designpattern.strategy.example.tax.OuterTaxStrategy;
import work.xingbili.persondemo.designpattern.strategy.example.tax.TaxType;

import java.util.HashMap;
import java.util.Map;

/**
 * 优化调if 语句
 *
 * @author xinghuolin
 * @date 2022/6/19 15:17
 */
public class MapTaxStrategyFactory {
    /**
     * 存储税策略
     **/
    static Map<TaxType, ITaxStrategy> taxStrategyMap = new HashMap<>();


    // 注册默认税策略
    static {
        registerTaxStrategy(TaxType.INTER, new InterTaxStrategy());
        registerTaxStrategy(TaxType.OUTER, new OuterTaxStrategy());
    }


    // 提供税注册策略接口，外部只需要调用此接口接口新增税策略，而无需修改策略工厂内部代码
    public static void registerTaxStrategy(TaxType taxType, ITaxStrategy taxStrategy) {
        taxStrategyMap.put(taxType, taxStrategy);
    }

    // 通过map获取税策略，当增加新的税策略时无需修改代码，对修改封闭，对扩展开放，遵循开闭原则
    public static ITaxStrategy getTaxStrategy(TaxType taxType) throws Exception {
        // 当增加新的税类型时，需要修改代码，同时增加圈复杂度
        if (taxStrategyMap.containsKey(taxType)) {
            return taxStrategyMap.get(taxType);
        } else {
            throw new Exception("The tax type is not supported.");
        }
    }


}
