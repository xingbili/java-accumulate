/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.abstractfactory;

/**
 * 抽象工厂设计模式
 *
 * @author xinghuolin
 * @date 2022/6/25 9:46
 */
public class AppTest {
}

interface IPhone{
    void createCpu();
    void createMemory();
    void createGpu();
}

class MIPhone implements IPhone{

    @Override
    public void createCpu() {

    }

    @Override
    public void createMemory() {

    }

    @Override
    public void createGpu() {

    }
}


