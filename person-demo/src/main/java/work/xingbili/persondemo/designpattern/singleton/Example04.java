/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.singleton;

/**
 * 加锁的懒汉模式
 *
 * @author xinghuolin
 * @date 2022/6/18 18:33
 */
public class Example04 {

    private static Example04 INSTANCE;

    private Example04() {
    }

    public static synchronized Example04 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
            INSTANCE = new Example04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(Example04.getInstance().hashCode())).start();
        }
    }

}
