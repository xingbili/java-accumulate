package work.xingbili.javabase;

/**
 * @des: TODO
 * @author xinghuolin
 * @date 2021/9/6 16:28
 */
public class ClassA implements InterfaceStudy,InterfaceStudy2{

    @Override
    public void methodA() {
        // InterfaceStudy.super.methodA();
        System.out.println("ClassA methodA");
    }
}
