package work.xingbili.persondemo.designpattern.singleton;

/**
 * 利用枚举实现单例模式  枚举不支持反序列或，线程同步
 * @author xinghuolin
 * @date 2022/6/18 18:58
 */
public enum Example08 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Example08.INSTANCE.hashCode());
            }).start();
        }
    }
}
