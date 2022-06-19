/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.singleton;

/**
 * 锁粒度缩小范围
 * @author xinghuolin
 * @date 2022/6/18 18:43
 */
public class Example05 {
    private static Example05 INSTANCE;

    private Example05() {
    }

    public static Example05 getInstance() {
        if (INSTANCE == null) {
            //这里有可能有漏洞
            synchronized (Example05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
                INSTANCE = new Example05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(Example05.getInstance().hashCode())).start();
        }
    }
}
