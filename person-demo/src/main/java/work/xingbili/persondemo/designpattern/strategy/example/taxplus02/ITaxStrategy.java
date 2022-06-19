/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.strategy.example.taxplus02;

/**
 * 背景 ：现在要实现一个算税策略，税计算类型有价内税和价外税，将来可能会增加新的税类型，初始设计类结构如下
 * 税策略接口
 *
 * @author xinghuolin
 * @date 2022/6/19 15:11
 */
public interface ITaxStrategy {
    /**
     * 计算税收方法
     *
     * @param amount
     * @return double
     * @date 2022/6/19
     */
    double calc(long amount);

    /**
     * 新增注册策略方法
     *
     * @date 2022/6/19
     */
    void register();
}
