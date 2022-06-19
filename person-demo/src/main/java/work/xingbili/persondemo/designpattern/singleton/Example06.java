/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.singleton;

/**
 * 双重枷锁
 *
 * @author xinghuolin
 * @date 2022/6/18 18:43
 */
public class Example06 {
    private static Example06 INSTANCE;

    private Example06() {
    }

    public static Example06 getInstance() {
        if (INSTANCE == null) {
            synchronized (Example06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    INSTANCE = new Example06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(Example06.getInstance().hashCode())).start();
        }
    }
}
