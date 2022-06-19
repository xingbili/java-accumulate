/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy.example.taxplus03;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import work.xingbili.persondemo.designpattern.strategy.example.tax.TaxType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xinghuolin
 * @date 2022/6/19 18:02
 */
public class AnnotationTaxStrategyFactory {

    /**
     * // 存储税策略
     */
    static Map<TaxType, ITaxStrategy> taxStrategyMap = new HashMap<>();

    static {
        registerTaxStrategy();
    }


    /**
     * // 通过map获取税策略，当增加新的税策略时无需修改代码，对修改封闭，对扩展开放，遵循开闭原则
     *
     * @param
     * @return
     * @date 2022/6/19
     */
    public static ITaxStrategy getTaxStrategy(TaxType taxType) throws Exception {
        // 当增加新的税类型时，需要修改代码，同时增加圈复杂度
        if (taxStrategyMap.containsKey(taxType)) {
            return taxStrategyMap.get(taxType);
        } else {
            throw new Exception("The tax type is not supported.");
        }
    }

    /**
     * 自动注册税策略
     *
     * @param
     * @return
     * @date 2022/6/19
     */
    private static void registerTaxStrategy() {
        try {
            // 通过反射找到所有的税策略子类进行注册
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage(ITaxStrategy.class.getPackage().getName()))
                    .setScanners(new SubTypesScanner()));
            Set<Class<? extends ITaxStrategy>> taxStrategyClassSet = reflections.getSubTypesOf(ITaxStrategy.class);

            if (taxStrategyClassSet != null) {
                for (Class<?> clazz : taxStrategyClassSet) {
                    // 找到税类型注解，自动完成税策略注册
                    if (clazz.isAnnotationPresent(TaxTypeAnnotation.class)) {
                        TaxTypeAnnotation taxTypeAnnotation = clazz.getAnnotation(TaxTypeAnnotation.class);
                        TaxType taxType = taxTypeAnnotation.taxType();
                        taxStrategyMap.put(taxType, (ITaxStrategy) clazz.newInstance());
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            // 自行定义异常处理
            e.printStackTrace();
        }
    }
}

