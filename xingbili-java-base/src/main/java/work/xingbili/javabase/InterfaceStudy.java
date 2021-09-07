/**
 * xingbili
 */

package work.xingbili.javabase;

/**
 * @author xinghuolin
 * @des: TODO
 * @date 2021/9/6 16:10
 */
public interface InterfaceStudy {
    // 抽象方法

    /**
     *@des:
     *@author: xinghuolin
     *@date: 16:23
     */
    default  void methodA(){
        System.out.println("default methodA");
    }
    /**
     *@return:
     *@author: xinghuolin
     *@date: 16:23
     */
    static void methodB(){
        System.out.println("static methodB");
    }

    // 私有方法  Java 9 实现

}
