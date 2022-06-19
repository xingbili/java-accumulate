/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.singleton;

/**
 *  单例模式第一种写法:饿汉模式
 *  关键：1、私有构造函数
 *       2、生成一个静态不可变更的
 *
 * @author xinghuolin
 * @date 2022/6/18 17:50
 */
public class Example01 {
    private  static final Example01 INSTANCE = new Example01();
    private Example01(){

    }
    public static  Example01 getInstance(){return INSTANCE;}
    public void test(){
        System.out.println("test");
    }

    public static void main(String[] args) {
        Example01 ex01 = Example01.getInstance();
        Example01 ex02 = Example01.getInstance();
        System.out.println(ex01.equals(ex02));
    }
}
