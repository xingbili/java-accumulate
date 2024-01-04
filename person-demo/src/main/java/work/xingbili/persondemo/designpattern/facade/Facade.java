/*
 * 版权信息: © 聚均科技
 */
package work.xingbili.persondemo.designpattern.facade;

/**
 * @description:
 * @author: xing
 * @date: 2024/1/3 10:30
 */
public class Facade {
    private Component1 component1;
    private Component2 component2;

    public Facade() {
        component1 = new Component1();
        component2 = new Component2();
    }

    public void operation() {
        component1.operation1();
        component2.operation2();
    }
}
