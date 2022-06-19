/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.singleton;

/**
 * 单例设计模式实现方式2：静态语句块初始化
 *
 *
 * @author xinghuolin
 * @date 2022/6/18 18:01
 */
public class Example02 {
    private static final Example02 INSTANCE;
    static {
        INSTANCE = new Example02();
    }
    private Example02(){};

    public static Example02 getInstance(){
        return INSTANCE;
    }
}
