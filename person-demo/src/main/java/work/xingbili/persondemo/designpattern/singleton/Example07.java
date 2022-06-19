/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.singleton;

/**
 * 静态内部类的方式
 *
 * @author xinghuolin
 * @date 2022/6/18 18:43
 */
public class Example07 {

    private Example07() {
    }

    private static class ExHolder{
        private final static Example07 INSTANCE = new Example07();
    }
    public static Example07 getInstance(){
        return ExHolder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(Example07.getInstance().hashCode())).start();
        }
    }

}
