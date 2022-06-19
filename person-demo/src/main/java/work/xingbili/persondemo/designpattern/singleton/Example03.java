/**
 * 版权信息: © 聚均科技
 */

package work.xingbili.persondemo.designpattern.singleton;

/**
 * 懒汉式
 *
 * @author xinghuolin
 * @date 2022/6/18 18:29
 */
public class Example03 {
    private static Example03 INSTANCE;

    private Example03() {
    }

    ;

    public static Example03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
            INSTANCE = new Example03();
        }
        return INSTANCE;
    }

    /**
     * 测试
     * 
     * @date 2022/6/19
     * @param 
     * @return 
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() ->
                    System.out.println(Example03.getInstance().hashCode())
            ).start();
        }
    }
}
