package work.xingbili.javabase;

/**
 * @des: TODO
 * @author xinghuolin
 * @date 2021/9/6 16:29
 */
public interface InterfaceStudy2 {
    default void methodA(){
        System.out.println("InterfaceStudy2 methodA");
    }
    static void methodB(){
        System.out.println("InterfaceStudy2  methodB");
    }
}
