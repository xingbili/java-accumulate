/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.templatemethod;

/**
 * 模板方法的精髓：抽象类去决定模板方法的执行规则，实现了去定义具体的实现
 * @author xinghuolin
 * @date 2022/6/24 12:35
 */
public class AppTest {
    public static void main(String[] args) {
        TemplateCls templateCls = new ConCls();
        templateCls.templateMethod();
    }
}

abstract class TemplateCls {
    abstract void method();

    void templateMethod() {
        System.out.println("start");
        method();
        System.out.println("end");
    }
}

class ConCls extends  TemplateCls{

    @Override
    void method() {
        System.out.println("test");
    }
}
